package crux.ast.types;

/**
 * The field args is a TypeList with a type for each param. The type ret is the type of the function
 * return. The function return could be int, bool, or void. This class should implement the call
 * method.
 */
public final class FuncType extends Type implements java.io.Serializable {
  static final long serialVersionUID = 12022L;

  private TypeList args;
  private Type ret;

  public FuncType(TypeList args, Type returnType) {
    this.args = args;
    this.ret = returnType;
  }

  public Type getRet() {
    return ret;
  }

  public TypeList getArgs() {
    return args;
  }

  @Override
  public String toString() {
    return "func(" + args + "):" + ret;
  }

  @Override
  public boolean equivalent(Type that) {
    if (that == null) {
      return false;
    }
    if (!(that instanceof FuncType)) {
      return false;
    } else {
      FuncType function = (FuncType) that;
      return this.ret.equivalent(function.getRet()) && this.args.equivalent(function.getArgs());
    }
  }

  @Override
  public Type call(Type args){
    TypeList argsType = (TypeList) args;
    if (this.args == null && argsType.isEmpty()){
      return this.ret;
    }
    if(!(args instanceof TypeList) || !(this.args.equivalent(args))){
      return super.call(args);
    }
    return this.ret;
  }
}
