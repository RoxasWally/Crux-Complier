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
  private int indexVariable = 0;
  private HashMap<> =

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
    /*Assign labels to jump targets
Declare function and label
Print prologue such that stack is 16 byte aligned
Move arguments from registers and stack to local variable
Generate code for function body
Print epilogue
*/
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
