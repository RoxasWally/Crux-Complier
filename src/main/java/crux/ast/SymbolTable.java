package crux.ast;

import com.sun.jdi.BooleanType;
import crux.ast.Position;
import crux.ast.types.*;


import java.io.PrintStream;
import java.util.*;

/**
 * Symbol table will map each symbol from Crux source code to its declaration or appearance in the
 * source. The symbol table is made up of scopes, Each scope is a map which maps an identifier to
 * its symbol. Scopes are inserted to the table starting from the first scope (Global Scope). The
 * Global scope is the first scope in each Crux program, and it contains all the built-in functions
 * and names. The symbol table is an ArrayList of scopes.
 */
public final class SymbolTable {

  /**
   * Symbol is used to record the name and type of names in the code. Names include function names,
   * global variables, global arrays, and local variables.
   */
  static public final class Symbol implements java.io.Serializable {
    static final long serialVersionUID = 12022L;
    private final String name;
    private final Type type;
    private final String error;

    /**
     *
     * @param name String
     * @param type the Type
     */
    private Symbol(String name, Type type) {
      this.name = name;
      this.type = type;
      this.error = null;
    }

    private Symbol(String name, String error) {
      this.name = name;
      this.type = null;
      this.error = error;
    }

    /**
     *
     * @return String the name
     */
    public String getName() {
      return name;
    }

    /**
     *
     * @return the type
     */
    public Type getType() {
      return type;
    }

    @Override
    public String toString() {
      if (error != null) {
        return String.format("Symbol(%s:%s)", name, error);
      }
      return String.format("Symbol(%s:%s)", name, type);
    }

    public String toString(boolean includeType) {
      if (error != null) {
        return toString();
      }
      return includeType ? toString() : String.format("Symbol(%s)", name);
    }
  }

  private final PrintStream err;
  private final ArrayList<Map<String, Symbol>> symbolScopes = new ArrayList<>();

  private boolean encounteredError = false;

  SymbolTable(PrintStream err) {
    this.err = err;
//    enter();
//    add(null,"readInt", new FuncType(new TypeList(), new IntType()));
//    add(null,"readChar", new FuncType(new TypeList(), new IntType()));
//    add(null,"printBool",new FuncType(new TypeList(List.of(new BoolType())), new VoidType()));
//    add(null,"printInt",new FuncType(new TypeList(List.of(new IntType())), new VoidType()));
//    add(null,"printChar",new FuncType(new TypeList(List.of(new IntType())), new VoidType()));
//    add(null,"println",new FuncType(new TypeList(), new VoidType()));
    //placing them in a list of does not work
    //retry by declaring each scope from global all the way to local using hashmap
    symbolScopes.add(new HashMap<>());
    FuncType readInt = new FuncType(TypeList.of(), new IntType());
    FuncType readChar = new FuncType(TypeList.of(), new IntType());
    FuncType printBool = new FuncType(TypeList.of(new BoolType()), new VoidType());
    FuncType printInt = new FuncType(TypeList.of(new IntType()), new VoidType());
    FuncType printChar = new FuncType(TypeList.of(new IntType()), new VoidType());
    FuncType println =  new FuncType(TypeList.of(), new VoidType());

    symbolScopes.get(0).put("readInt", new Symbol("readInt",readInt));
    symbolScopes.get(0).put("readChar", new Symbol("readChar", readChar));
    symbolScopes.get(0).put("printBool", new Symbol("printBool", printBool));
    symbolScopes.get(0).put("printInt", new Symbol("printInt", printInt));
    symbolScopes.get(0).put("printChar", new Symbol("printChar", printChar));
    symbolScopes.get(0).put("println", new Symbol("println", println));
  }

  boolean hasEncounteredError() {
    return encounteredError;
  }

  /**
   * Called to tell symbol table we entered a new scope.
   */

  void enter() {
    symbolScopes.add(new HashMap<>());
  }

  /**
   * Called to tell symbol table we are exiting a scope.
   */

  void exit() {
    symbolScopes.remove(symbolScopes.size()-1);
  }

  /**
   * Insert a symbol to the table at the most recent scope. if the name already exists in the
   * current scope that's a declaration error.
   */
  Symbol add(Position pos, String name, Type type) {
    int ind = symbolScopes.size() - 1;
    if(symbolScopes.get(ind).containsKey(name)){
      err.printf("DeclarationError%s[%s already exists within the scope.]%n",pos,name);
      encounteredError = true;
      return new Symbol(name, "Declaration error ");
    }else{
      enter();
      symbolScopes.get(ind).put(name, new Symbol(name,type));
      return new Symbol(name,type);
    }
  }

  /**
   * lookup a name in the SymbolTable, if the name not found in the table it should encounter an
   * error and return a symbol with ResolveSymbolError error. if the symbol is found then return it.
   */
  Symbol lookup(Position pos, String name) {
    var symbol = find(name);
    if (symbol == null) {
      err.printf("ResolveSymbolError%s[Could not find %s.]%n", pos, name);
      encounteredError = true;
      return new Symbol(name, "ResolveSymbolError");
    } else {
      return symbol;
    }
  }

  /**
   * Try to find a symbol in the table starting form the most recent scope.
   */
  private Symbol find(String name) {
    int ind = symbolScopes.size() - 1;
    for(; ind > -1; --ind){
      boolean find = symbolScopes.get(ind).containsKey(name);
      if(find){
        return symbolScopes.get(ind).get(name);
      }
    }
    return null;
  }
}
