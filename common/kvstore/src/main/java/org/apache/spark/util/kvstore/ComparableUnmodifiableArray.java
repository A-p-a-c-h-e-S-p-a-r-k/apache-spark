public class ComparableUnmodifiableArray<T> implements Comparable<ComparableUnmodifiableArray<T>> {

  private final T[] array;

  ComparableUnmodifiableArray(T[] array) {
    this.array = array;
  }

  public Class<T> getType() {
    return T.class;
  }

  public T[] getArray() {
    return array;
  }

  @Override
  public boolean equals(Object other) {
    if (!(other instanceof ComparableUnmodifiableArray<T> comparableUnmodifiableArray)) {
      return false;
    }
    return Arrays.equals(array, comparableUnmodifiableArray.getArray());
  }

  @Override
  public int hashCode() {
    int code = 0;
    for (T o : array) {
      code = (code * 31) + o.hashCode();
    }
    return code;
  }

  @Override
  @SuppressWarnings("unchecked")
  public int compareTo(ComparableUnmodifiableArray<T> other) {
    int len = Math.min(array.length, other.getArray().length);
    for (int i = 0; i < len; i++) {
      int diff = ((Comparable<T>) array[i]).compareTo(other.array[i]);
      if (diff != 0) {
        return diff;
      }
    }

    return array.length - other.array.length;
  }

}