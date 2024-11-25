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

   assertEquals(ComparableWrappers.forInput(b1), ComparableWrappers.forInput(b1));
   assertNotEquals(ComparableWrappers.forInput(b1), ComparableWrappers.forInput(b2));
   assertNotEquals(ComparableWrappers.forInput(b1), ComparableWrappers.forInput(i1));
   assertNotEquals(ComparableWrappers.forInput(b1), ComparableWrappers.forInput(s1));

   assertEquals(ComparableWrappers.forInput(i1), ComparableWrappers.forInput(i1));
   assertNotEquals(ComparableWrappers.forInput(i1), ComparableWrappers.forInput(i2));
   assertNotEquals(ComparableWrappers.forInput(i1), ComparableWrappers.forInput(b1));
   assertNotEquals(ComparableWrappers.forInput(i1), ComparableWrappers.forInput(s1));

   assertEquals(ComparableWrappers.forInput(s1), ComparableWrappers.forInput(s1));
   assertNotEquals(ComparableWrappers.forInput(s1), ComparableWrappers.forInput(s2));
   assertNotEquals(ComparableWrappers.forInput(s1), ComparableWrappers.forInput(b1));
   assertNotEquals(ComparableWrappers.forInput(s1), ComparableWrappers.forInput(i1));

   assertEquals(0, ComparableWrappers.forInput(b1).compareTo(ComparableWrappers.forInput(b1)));
   assertTrue(ComparableWrappers.forInput(b1).compareTo(ComparableWrappers.forInput(b2)) > 0);

   assertEquals(0, ComparableWrappers.forInput(i1).compareTo(ComparableWrappers.forInput(i1)));
   assertTrue(ComparableWrappers.forInput(i1).compareTo(ComparableWrappers.forInput(i2)) > 0);

   assertEquals(0, ComparableWrappers.forInput(s1).compareTo(ComparableWrappers.forInput(s1)));
   assertTrue(ComparableWrappers.forInput(s1).compareTo(ComparableWrappers.forInput(s2)) > 0);
  }

}
