package crux.backend;

import crux.ast.SymbolTable.Symbol;
import crux.ir.*;
import crux.ir.insts.*;
import crux.printing.IRValueFormatter;

import java.lang.reflect.Parameter;
import java.util.*;

/**
 * Convert the CFG into Assembly Instructions
 */
public final class CodeGen extends InstVisitor {
  private final Program p;
  private final CodePrinter out;
  private final String[] regs = { "%rdi", "%rdx",
    "%rsi", "%rcx", "%r8", "%r9"};
  private HashMap<Variable, Integer> varIndexMap;
  private int varIndex;
  private final HashSet<Instruction> discovered = new HashSet<>();
  private HashMap<Instruction, String> currLabelMap;

  public CodeGen(Program p) {
    this.p = p;
    // Do not change the file name that is outputted or it will
    // break the grader!

    out = new CodePrinter("a.s");
  }

  /**
   * It should allocate space for globals call genCode for each Function
   */
  public void genCode() {
    //TODO
    for(Iterator<GlobalDecl> glob_it = p.getGlobals(); glob_it.hasNext();){
      GlobalDecl g = glob_it.next();
      System.out.println(".comm " + g.getSymbol().getName() + ", "
              + ((IntegerConstant) (g.getNumElement())).getValue() * 8 + ",8");
      out.printCode(".comm " + g.getSymbol().getName() + ", "
              + ((IntegerConstant) (g.getNumElement())).getValue() * 8 + ",8");

    }
    int[] count = new int[1];
    for(Iterator<Function> func_it = p.getFunctions(); func_it.hasNext();){
      Function f = func_it.next();
      genCode(f, count);
    }
    
    out.close();
  }

  private void genCode(Function f, int[] count) {
    currLabelMap = f.assignLabels(count);
    out.printCode(".globl " + f.getName());
    out.printLabel(f.getName() + ":");
    int argIndex = 0;
    for(LocalVar variable : f.getArguments()){
      varIndex++;
      argIndex++;
      varIndexMap.put(variable,-8 * varIndex);

      if (varIndex <= 6) {
        out.bufferCode("movq " + regs[varIndex - 1] + ", " + (-8 * argIndex) + "(%rbp)");
      }else {
        out.bufferCode("movq " + (16 + 8 * (argIndex-7)) + "(%rbp), %r10");
        out.bufferCode("movq %r10 " + (-8 * argIndex) + "(%rbp)");
      }
      //transform to CFG
      //use dfs
      if(f.getStart() != null){
        discovered.clear();
        dfs(f.getStart());
      }
      if (varIndex % 2 == 0){
        out.printCode("enter $(8 * " + (varIndex) + "), $0");
      }else{
        out.printCode("enter $(8 * " + (varIndex + 1 ) + "), $0");
      }

    }

  }

  private void dfs(Instruction instruction) {
    if (!discovered.contains(instruction)) {
      discovered.add(instruction);
      if (currLabelMap.containsKey(instruction)) {
        out.bufferCode(currLabelMap.get(instruction) + ":");
      }
      instruction.accept(this);
      if (instruction.numNext() == 0) {
        //no more insturction after so we leave and return
        out.bufferCode("leave");
        out.bufferCode("ret");
      } else {
        //we keep iterating the upcoming instructons and calling the function recursively using DFS till
        // it reaches 0
        for (int i = 0; i < instruction.numNext(); i++) {
          dfs(instruction.getNext(i));
        }
      }
      //otherwise jump
    } else {
      out.bufferCode("jmp" + currLabelMap.get(instruction));
    }
  }


  public void visit(AddressAt i) {
    if(i.getOffset() == null) {
      out.bufferCode("movq " + i.getBase().getName() + "@GOTPCREL(%rip), %r11");
      out.bufferCode("movq %r11, " + varIndexMap.get(i.getDst()) + "(%rbp)");
    }
    if(i.getOffset() != null) {
        out.bufferCode("movq " + i.getBase().getName() + "@GOTPCREL(%rip), %r11");
        out.bufferCode("movq " + varIndexMap.get(i.getOffset()) + "(%rbp), %r10");
        out.bufferCode("imulq $8, %r10");
        out.bufferCode("addq %r10, %r11");
        out.bufferCode("movq %r11, " + varIndexMap.get(i.getDst()) + "(%rbp)");
      }
  }

  public void visit(BinaryOperator i) {


    out.printCode("movq " + (-8 * varIndexMap.get(i.getLeftOperand())) + "(%rdi), %r8");
    switch (i.getOperator()) {
      case Add:
        out.printCode("addq " + (-8 * varIndexMap.get(i.getRightOperand())) + "%rdi, %r8");
        break;
      case Sub:
        out.printCode("subq" + (-8 * varIndexMap.get(i.getRightOperand())) + "%rdi, %r8");
        break;
      case Mul:
        out.printCode("mulq" + (-8 * varIndexMap.get(i.getRightOperand())) + "%rdi, %r8");
        break;
      case Div:
        out.printCode("divq" + (-8 * varIndexMap.get(i.getRightOperand())) + "%rdi, %r8");
        break;
    }
    out.printCode("movq %r10, " + (-8 * varIndexMap.get(i.getDst())) + "%rdi");
  }

  public void visit(CompareInst i) {}

  public void visit(CopyInst i) {

    if(i.getSrcValue() instanceof  IntegerConstant){
    }
  }

  public void visit(JumpInst i) {
    out.bufferCode("movq " + (-8 * varIndexMap.get(i.getPredicate())) + "(%rbp), %r10");
    out.bufferCode("cmp $1, %r10");
    out.bufferCode("je " + currLabelMap.get(i.getNext(1)));
  }

  public void visit(LoadInst i) {

  }

  public void visit(NopInst i) {

  }

  public void visit(StoreInst i) {}

  public void visit(ReturnInst i) {
    out.bufferCode("movq " + varIndexMap.get(i.getReturnValue()) + "(%rbp), %rax");
    out.bufferCode("leave");
    out.bufferCode("ret");
}


  public void visit(CallInst i) {
    int numOfArgs = 0 ;
    for(LocalVar param : i.getParams()){
      numOfArgs++;
      if(numOfArgs > 6) {
        out.bufferCode("movq " + (varIndexMap.get(param)) + "(%rbp), %r10");
        out.bufferCode("movq %r10, " + (16 + 8 * (numOfArgs - 7)) + "(%rbp)");
      }else {
        out.bufferCode("movq " + (varIndexMap.get(param)) + "(%rbp), " + regs[numOfArgs - 1]);
      }
      out.bufferCode("call " + i.getCallee().getName());
      out.bufferCode("movq %rax, " + (-8 * varIndexMap.get(i.getDst()) ) + "(%rbp)");

      }
    }

  public void visit(UnaryNotInst i) {
    out.bufferCode("movq " + varIndexMap.get(i.getInner()) + "(%rbp), %r10");
    out.bufferCode("subq %r10, " + varIndexMap.get(i.getDst()) + "(%rbp)");

  }
}
