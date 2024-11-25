/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.spark.util.kvstore;

import java.util.Arrays;

import com.google.common.base.Preconditions;

/**
 * A factory for array wrappers so that arrays can be used as keys in a map, sorted or not.
 *
 * The comparator implementation makes two assumptions:
 * - All elements are instances of Comparable
 * - When comparing two arrays, they both contain elements of the same type in corresponding
 *   indices.
 *
 * Otherwise, ClassCastExceptions may occur. The equality method can compare any two arrays.
 *
 * This class is not efficient and is mostly meant to compare really small arrays, like those
 * generally used as indices and keys in a KVStore.
 */
class ComparableWrappers {

  @SuppressWarnings("unchecked")
  private static ComparableUnmodifiableAttribute<?> forElement(Object inputArgumentObject) {

    ComparableUnmodifiableAttribute<?> comparableUnmodifiableAttribute;

    boolean isPrimitive = inputArgumentObject.getClass().getComponentType().isPrimitive();

    if (isPrimitive) {
      if (inputArgumentObject instanceof byte byteAttribute) {
        comparableUnmodifiableAttribute = new ComparableUnmodifiableAttribute<Byte>(byteAttribute);
      } else if (inputArgumentObject instanceof short shortAttribute) {
        comparableUnmodifiableAttribute = new ComparableUnmodifiableAttribute<Short>(shortAttribute);
      } else if (inputArgumentObject instanceof int intAttribute) {
        comparableUnmodifiableAttribute = new ComparableUnmodifiableAttribute<Integer>(intAttribute);
      } else if (inputArgumentObject instanceof long longAttribute) {
        comparableUnmodifiableAttribute = new ComparableUnmodifiableAttribute<Long>(longAttribute);
      } else if (inputArgumentObject instanceof float floatAttribute) {
        comparableUnmodifiableAttribute = new ComparableUnmodifiableAttribute<Float>(floatAttribute);
      } else if (inputArgumentObject instanceof double doubleAttribute) {
        comparableUnmodifiableAttribute = new ComparableUnmodifiableAttribute<Double>(doubleAttribute);
      } else if (inputArgumentObject instanceof boolean booleanAttribute) {
        comparableUnmodifiableAttribute = new ComparableUnmodifiableAttribute<Boolean>(booleanAttribute);
      } else if (inputArgumentObject instanceof char charAttribute) {
        comparableUnmodifiableAttribute = new ComparableUnmodifiableAttribute<Character>(charAttribute);
      } else if (inputArgumentObject instanceof void) {
        comparableUnmodifiableAttribute = new ComparableUnmodifiableAttribute<Void>(null);
      }
      return comparableUnmodifiableAttribute;
    }

    Preconditions.checkArgument(!isPrimitive);
    comparableUnmodifiableAttribute = new ComparableUnmodifiableAttribute<Object>(inputArgumentObject);
    return comparableUnmodifiableAttribute;
  }

  @SuppressWarnings("unchecked")
  private static ComparableUnmodifiableArray<?> forArray(Object inputArgumentObject) {

    ComparableUnmodifiableArray<?> comparableUnmodifiableArray;
    if (inputArgumentObject instanceof byte[] byteArray) {
      comparableUnmodifiableArray = new ComparableUnmodifiableArray<Byte>(byteArray);
    } else if (inputArgumentObject instanceof short[] shortArray) {
      comparableUnmodifiableArray = new ComparableUnmodifiableArray<Short>(shortArray);
    } else if (inputArgumentObject instanceof int[] intArray) {
      comparableUnmodifiableArray = new ComparableUnmodifiableArray<Integer>(intArray);
    } else if (inputArgumentObject instanceof long[] longArray) {
      comparableUnmodifiableArray = new ComparableUnmodifiableArray<Long>(longArray);
    } else if (inputArgumentObject instanceof float[] floatArray) {
      comparableUnmodifiableArray = new ComparableUnmodifiableArray<Float>(floatArray);
    } else if (inputArgumentObject instanceof double[] doubleArray) {
      comparableUnmodifiableArray = new ComparableUnmodifiableArray<Double>(doubleArray);
    } else if (inputArgumentObject instanceof boolean[] booleanArray) {
      comparableUnmodifiableArray = new ComparableUnmodifiableArray<Boolean>(booleanArray);
    } else if (inputArgumentObject instanceof char[] charArray) {
      comparableUnmodifiableArray = new ComparableUnmodifiableArray<Character>(charArray);
    //} else if (inputArgumentObject instanceof void[]) {
    //  comparableUnmodifiableArray = new ComparableUnmodifiableArray<Void>(new void[]);
    } else {
      boolean isPrimitive = inputArgumentObject.getClass().getComponentType().isPrimitive();
      Preconditions.checkArgument(!isPrimitive);
      comparableUnmodifiableArray = new ComparableUnmodifiableArray<Object>((Object[]) inputArgumentObject);
    }

    return comparableUnmodifiableArray;
  }

  @SuppressWarnings("unchecked")
  public static Comparable<?> forInput(Object inputArgumentObject) {

    boolean isArray = inputArgumentObject.getClass().isArray();

    return isArray
        ? forArray(inputArgumentObject)
        : forElement(inputArgumentObject);
  }

}
