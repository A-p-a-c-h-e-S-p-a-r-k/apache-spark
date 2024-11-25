public class ComparableUnmodifiableAttribute<T> implements Comparable<ComparableUnmodifiableAttribute<T>> {

  private final T attribute;

  ComparableUnmodifiableAttribute(T attribute) {
    this.attribute = attribute;
  }

  public Class<T> getType() {
    return attribute.getClass();
  }

  public T getAttribute() {
    return attribute;
  }

  @Override
  public boolean equals(Object other) {
    if (!(other instanceof ComparableUnmodifiableAttribute<T> comparableUnmodifiableAttribute)) {
      return false;
    }
    return Objects.equals(attribute, comparableUnmodifiableAttribute.getAttribute());
  }

  @Override
  public int hashCode() {
    return (code * 31) + attribute.hashCode();
  }

  @Override
  @SuppressWarnings("unchecked")
  public int compareTo(ComparableUnmodifiableAttribute<T> other) {
    return ((Comparable<T>) attribute).compareTo(other.getAttribute());
  }

}