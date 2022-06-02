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
  private  String[] regs = {"%rdi","%rsi","%rdx",
          "%rcx","%r8","%r9"};
  private  HashMap<Variable, Integer> varIndexMap = new HashMap<>();
  private int varIndex;
  private HashSet<Instruction> discovered = new HashSet<>();
  private HashMap<Instruction, String> currLabelMap;

  public CodeGen(Program p) {
    this.p = p;
    // Do not change the file name that is outputted, or it will
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
      var Size = (IntegerConstant) g.getNumElement();
      out.printCode(".comm " + g.getSymbol().getName() + ", " + Size.getValue() * 8 + ", " + 8);
    }
    int[] count = new int[1];
    for(Iterator<Function> func_it = p.getFunctions(); func_it.hasNext();){
      Function f = func_it.next();
      genCode(f, count);
      out.outputBuffer();

    }
    out.close();
  }

  private void genCode(Function f, int[] count) {
    currLabelMap = f.assignLabels(count);
    varIndexMap.clear();
    varIndex = 0;
    out.printCode(".globl " + f.getName());
    out.printLabel(f.getName() + ":");

    int numslots = 0;
    int current = 0;

    for(var variable : f.getArguments()) {
      varIndex++;
      numslots++;
      current++;
      varIndexMap.put(variable, -8 * varIndex);
      //placing on the stack
      if (current <= 6) {
        out.bufferCode("movq " + regs[numslots - 1] + ", " + (-8 * numslots) + "(%rbp)");
      } else {
        out.bufferCode("movq " + (24 * (current - 5)) + "(%rbp), %r10");
        out.bufferCode("movq %r10, " + (-8 * current ) + "(%rbp)");
      }
    }
      //transform to CFG
      //use dfs
      if(f.getStart() != null){
        dfs(f.getStart());
      }
      if (varIndex % 2 == 0) {
        out.printCode("enter $(" + 8 *  varIndex + "), $0");
      } else {
        out.printCode("enter $(" + 8 * (varIndex + 1) + "), $0");

      }

    }

  private void dfs(Instruction instruction) {
    if (!discovered.contains(instruction)) {
      discovered.add(instruction);

      if (currLabelMap.containsKey(instruction)) {
        out.bufferLabel(currLabelMap.get(instruction) + ":");
      }
      instruction.accept(this);
      if (instruction.numNext() == 0) {
        //no more insturction after so we leave and return
        out.bufferCode("leave");
        out.bufferCode("ret");
      } else {
        //we keep iterating the upcoming instructions and calling the function recursively using DFS till
        // it reaches 0
        for (int i = 0; i < instruction.numNext(); i++) {
          dfs(instruction.getNext(i));
        }
      }
      //otherwise jump
    } else {
      out.bufferCode("jmp " + currLabelMap.get(instruction));
    }
  }


  public void visit(AddressAt i) {
    if(!varIndexMap.containsKey(i.getDst())) {
      varIndex++;
      varIndexMap.put(i.getDst(),varIndex  *  -8);
    }

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
    if(!varIndexMap.containsKey(i.getDst())) {
      varIndex++;
      varIndexMap.put(i.getDst(),varIndex   *  -8);
    }

    if (i.getOperator() == BinaryOperator.Op.Div) {
      out.bufferCode("movq " + varIndexMap.get(i.getLeftOperand()) + "(%rbp), %rax");
      out.bufferCode("cqto");
      out.bufferCode("idivq " + varIndexMap.get(i.getRightOperand()) + "(%rbp)");
      out.bufferCode("movq %rax, " + varIndexMap.get(i.getDst()) + "(%rbp)");
    } else {
      out.bufferCode("movq " + varIndexMap.get(i.getLeftOperand()) + "(%rbp), %r10");
      switch(i.getOperator()) {
        case Add:
          out.bufferCode("addq " + varIndexMap.get(i.getRightOperand()) + "(%rbp), %r10");
          break;
        case Sub:
          out.bufferCode("subq " + varIndexMap.get(i.getRightOperand()) + "(%rbp), %r10");
          break;
        case Mul:
          out.bufferCode("imulq " + varIndexMap.get(i.getRightOperand()) + "(%rbp), %r10");
          break;
      }
      out.bufferCode("movq %r10, " + varIndexMap.get(i.getDst()) + "(%rbp)");
    }
  }

  public void visit(CompareInst i) {
    if(!varIndexMap.containsKey(i.getDst())) {
      varIndex++;
      varIndexMap.put(i.getDst(),varIndex  *  -8);
    }

    out.bufferCode("movq $0, %rax");
    out.bufferCode("movq $1, %r10");
    out.bufferCode("movq " + varIndexMap.get(i.getLeftOperand()) + "(%rbp), %r11");
    out.bufferCode("cmp " + varIndexMap.get(i.getRightOperand()) + "(%rbp), %r11");
    switch (i.getPredicate()) {
      case EQ:
        out.bufferCode("cmove %r10, %rax");
        break;
      case NE:
        out.bufferCode("cmovne %r10, %rax");
        break;
      case GT:
        out.bufferCode("cmovg %r10, %rax");
        break;
      case GE:
        out.bufferCode("cmovge %r10, %rax");
        break;
      case LT:
        out.bufferCode("cmovl %r10, %rax");
        break;
      case LE:
        out.bufferCode("cmovle %r10, %rax");
        break;
    }
    out.bufferCode("movq %rax, " + varIndexMap.get(i.getDst()) + "(%rbp)");


  }

  public void visit(CopyInst i) {
    if (!varIndexMap.containsKey(i.getDstVar())) {
      varIndex++;
      varIndexMap.put(i.getDstVar(), varIndex * -8);
    }


    if (i.getSrcValue() instanceof LocalVar) {
      if (varIndexMap.containsKey(i.getSrcValue())) {
        out.bufferCode("movq " + varIndexMap.get(i.getSrcValue()) + "(%rbp), %r11");
      }
      out.bufferCode("movq %r11, " + varIndexMap.get(i.getDstVar()) + "(%rbp)");


    } else if (i.getSrcValue() instanceof crux.ir.IntegerConstant) {
      out.bufferCode("movq $" + ((IntegerConstant) i.getSrcValue()).getValue() + ", " + varIndexMap.get(i.getDstVar()) + "(%rbp)");


    } else if (i.getSrcValue() instanceof crux.ir.BooleanConstant) {
      if (((BooleanConstant) i.getSrcValue()).getValue()) {
        out.bufferCode("movq $1, " + varIndexMap.get(i.getDstVar()) + "(%rbp)");
      }
      if (!((BooleanConstant) i.getSrcValue()).getValue()) {
        out.bufferCode("movq $0, " + varIndexMap.get(i.getDstVar()) + "(%rbp)");
      }
    }
  }
  public void visit(JumpInst i) {
    out.bufferCode("movq " + varIndexMap.get(i.getPredicate()) + "(%rbp), %r10");
    out.bufferCode("cmp $1, %r10");
    //jump if equal
    out.bufferCode("je " + currLabelMap.get(i.getNext(1)));
  }

  public void visit(LoadInst i) {
    if (!varIndexMap.containsKey(i.getDst())) {
      varIndex++;
      varIndexMap.put(i.getDst(), varIndex * - 8);
    }

    out.bufferCode("movq " + varIndexMap.get(i.getSrcAddress()) + "(%rbp), %r10");
    out.bufferCode("movq 0(%r10), %r11");
    out.bufferCode("movq %r11, " + varIndexMap.get(i.getDst()) + "(%rbp)");

  }

  public void visit(NopInst i) {

  }

  public void visit(StoreInst i) {
    if (!varIndexMap.containsKey(i.getDestAddress())) {
      varIndex++;
      varIndexMap.put(i.getDestAddress(),varIndex* - 8);
    }

    out.bufferCode("movq " + varIndexMap.get(i.getSrcValue()) + "(%rbp), %r11");
    out.bufferCode("movq " + varIndexMap.get(i.getDestAddress()) + "(%rbp), %r10");
    out.bufferCode("movq %r11, 0(%r10)");

  }

  public void visit(ReturnInst i) {
    if (i.getReturnValue() != null) {
      out.bufferCode("movq " + varIndexMap.get(i.getReturnValue()) + "(%rbp), %rax");
    }

    out.bufferCode("movq " + varIndexMap.get(i.getReturnValue()) + "(%rbp), %rax");
    out.bufferCode("leave");
    out.bufferCode("ret");
}


  public void visit(CallInst i) {
    if (!varIndexMap.containsKey(i.getDst())) {
      varIndex++;
      varIndexMap.put(i.getDst(),varIndex*-8);
    }
    int arguments = 0;

    for(LocalVar param : i.getParams()) {

      arguments++;
      if (arguments <= 6) {
        out.bufferCode("movq " + varIndexMap.get(param) + "(%rbp), " + regs[arguments - 1]);
      } else {
        out.bufferCode("movq " + varIndexMap.get(param)+ "(%rbp), %r10");
        out.bufferCode("movq %r10, " + (24 * (arguments-7)) + "(%rsp)");
      }
    }
    out.bufferCode("call " + i.getCallee().getName());
    out.bufferCode("movq %rax, " +  varIndexMap.get(i.getDst()) + "(%rbp)");

  }

  public void visit(UnaryNotInst i) {
    if (!varIndexMap.containsKey(i.getDst())) {
      varIndex++;
      varIndexMap.put(i.getDst(), varIndex*-8);
    }

    out.bufferCode("movq " + varIndexMap.get(i.getInner()) + "(%rbp), %r10");
    out.bufferCode("subq %r10, " + varIndexMap.get(i.getDst()) + "(%rbp)");

  }
}
