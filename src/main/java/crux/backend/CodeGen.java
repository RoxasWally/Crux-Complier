package crux.backend;

import crux.ast.SymbolTable.Symbol;
import crux.ir.*;
import crux.ir.insts.*;
import crux.printing.IRValueFormatter;

import java.util.*;

/**
 * Convert the CFG into Assembly Instructions
 */
public final class CodeGen extends InstVisitor {
  private final Program p;
  private final CodePrinter out;
  private String[] regs = { "%rdi", "%rdx",
    "%rsi", "%rcx", "%r8", "%r9"};
  private HashMap<Variable, Integer> varIndexMap;
  private int varIndex;
  private HashSet<Instruction> discovered = new HashSet<>();
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
    int count [] = new int[1];
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
        out.bufferCode("push" + variable.getName());
      }
      //transform to CFG
      //use dfs
      if(f.getStart() != null){
        discovered.clear();
        dfs(f.getStart())
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



      }

    }
  }

  public void visit(AddressAt i) {}

  public void visit(BinaryOperator i) {}

  public void visit(CompareInst i) {}

  public void visit(CopyInst i) {}

  public void visit(JumpInst i) {}

  public void visit(LoadInst i) {}

  public void visit(NopInst i) {}

  public void visit(StoreInst i) {}

  public void visit(ReturnInst i) {}

  public void visit(CallInst i) {}

  public void visit(UnaryNotInst i) {}
}
