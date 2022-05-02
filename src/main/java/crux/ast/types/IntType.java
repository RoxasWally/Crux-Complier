package crux.ast.types;

/**
 * Types for Integers values. This should implement the equivalent methods along with add, sub, mul,
 * div, and compare. The method equivalent will check if the param is an instance of IntType.
 */
public final class IntType extends Type implements java.io.Serializable {
  static final long serialVersionUID = 12022L;

  @Override
  public String toString() {
    return "int";
  }

  @Override
  public boolean equivalent(Type that) {
    return that.getClass() == IntType.class;
  }

  @Override
  Type add(Type that) {
    if (!(this.equivalent(that))) {
      return super.add(that);
    } else {
      return this;
    }
  }

  @Override
  Type sub(Type that) {
    if (!(this.equivalent(that))) {
      return super.sub(that);
    } else {
      return this;
    }
  }

  @Override
  Type mul(Type that) {
    if (!(this.equivalent(that))) {
      return super.mul(that);
    } else {
      return this;
    }
  }

  @Override
  Type div(Type that) {
    if (!(this.equivalent(that))) {
      return super.div(that);
    } else {
      return this;
    }
  }
 @Override
  Type compare(Type that){
   if (!(this.equivalent(that))) {
     return super.compare(that);
   } else {
     return new BoolType();
   }
 }

 @Override
  Type assign(Type source){
    if(source.getClass() == IntType.class){
      return new IntType();
    }else{
      return super.assign(source);
    }
 }

}
