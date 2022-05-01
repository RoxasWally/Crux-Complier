package crux.ast.types;

/**
 * The variable base is the type of the array element. This could be int or bool. The extent
 * variable is number of elements in the array.
 *
 */
public final class ArrayType extends Type implements java.io.Serializable {
  static final long serialVersionUID = 12022L;
  private final Type base;
  private final long extent;

  public ArrayType(long extent, Type base) {
    this.extent = extent;
    this.base = base;
  }

  public Type getBase() {
    return base;
  }

  public long getExtent() {
    return extent;
  }

  @Override
  public String toString() {
    return String.format("array[%d,%s]", extent, base);
  }
  @Override
  public boolean equivalent(Type that){
    if (!that.getClass().equals(ArrayType.class)){
      return false;
    }
    if (!this.base.equals(getBase())){
      return false;
    }
    if(!(this.extent != getExtent())){
      return false;
    }
    return true;
  }
  @Override public Type index(Type that){
    //check if the same
    if (equivalent(that)){
      return getBase();
    }
    return super.index(that);
  }
}
