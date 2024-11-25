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

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ComparableWrappersSuite {

  @Test
  public void testGenericArrayKey() {
   byte[] b1 = new byte[] { 0x01, 0x02, 0x03 };
   byte[] b2 = new byte[] { 0x01, 0x02 };
   int[] i1 = new int[] { 1, 2, 3 };
   int[] i2 = new int[] { 1, 2 };
   String[] s1 = new String[] { "1", "2", "3" };
   String[] s2 = new String[] { "1", "2" };

   assertEquals(ComparableWrappers.forArray(b1), ComparableWrappers.forArray(b1));
   assertNotEquals(ComparableWrappers.forArray(b1), ComparableWrappers.forArray(b2));
   assertNotEquals(ComparableWrappers.forArray(b1), ComparableWrappers.forArray(i1));
   assertNotEquals(ComparableWrappers.forArray(b1), ComparableWrappers.forArray(s1));

   assertEquals(ComparableWrappers.forArray(i1), ComparableWrappers.forArray(i1));
   assertNotEquals(ComparableWrappers.forArray(i1), ComparableWrappers.forArray(i2));
   assertNotEquals(ComparableWrappers.forArray(i1), ComparableWrappers.forArray(b1));
   assertNotEquals(ComparableWrappers.forArray(i1), ComparableWrappers.forArray(s1));

   assertEquals(ComparableWrappers.forArray(s1), ComparableWrappers.forArray(s1));
   assertNotEquals(ComparableWrappers.forArray(s1), ComparableWrappers.forArray(s2));
   assertNotEquals(ComparableWrappers.forArray(s1), ComparableWrappers.forArray(b1));
   assertNotEquals(ComparableWrappers.forArray(s1), ComparableWrappers.forArray(i1));

   assertEquals(0, ComparableWrappers.forArray(b1).compareTo(ComparableWrappers.forArray(b1)));
   assertTrue(ComparableWrappers.forArray(b1).compareTo(ComparableWrappers.forArray(b2)) > 0);

   assertEquals(0, ComparableWrappers.forArray(i1).compareTo(ComparableWrappers.forArray(i1)));
   assertTrue(ComparableWrappers.forArray(i1).compareTo(ComparableWrappers.forArray(i2)) > 0);

   assertEquals(0, ComparableWrappers.forArray(s1).compareTo(ComparableWrappers.forArray(s1)));
   assertTrue(ComparableWrappers.forArray(s1).compareTo(ComparableWrappers.forArray(s2)) > 0);
  }

}
