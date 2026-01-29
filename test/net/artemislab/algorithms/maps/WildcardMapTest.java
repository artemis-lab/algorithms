package net.artemislab.algorithms.maps;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.function.ThrowingRunnable;

/**
 * The {@code WildcardMapTest} class implements tests for {@code WildcardMap}.
 */
public class WildcardMapTest {

  private WildcardMap wildcardMap;

  @Before
  public void initialize() {
    wildcardMap = new WildcardMap();
  }

  @Test
  public void testPutOneEntry_NullKey1_ThrowsError() {
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, new ThrowingRunnable() {
      @Override
      public void run() throws Throwable {
        wildcardMap.put(null, "Civic", "Blue", "123");
      }
    });

    assertEquals(IllegalArgumentException.class, exception.getClass());
    assertEquals("Key1 must not be null, empty or blank.", exception.getMessage());
  }

  @Test
  public void testPutOneEntry_EmptyKey1_ThrowsError() {
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, new ThrowingRunnable() {
      @Override
      public void run() throws Throwable {
        wildcardMap.put("", "Civic", "Blue", "123");
      }
    });

    assertEquals(IllegalArgumentException.class, exception.getClass());
    assertEquals("Key1 must not be null, empty or blank.", exception.getMessage());
  }

  @Test
  public void testPutOneEntry_BlankKey1_ThrowsError() {
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, new ThrowingRunnable() {
      @Override
      public void run() throws Throwable {
        wildcardMap.put("   ", "Civic", "Blue", "123");
      }
    });

    assertEquals(IllegalArgumentException.class, exception.getClass());
    assertEquals("Key1 must not be null, empty or blank.", exception.getMessage());
  }

  @Test
  public void testPutOneEntry_NullKey2_ThrowsError() {
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, new ThrowingRunnable() {
      @Override
      public void run() throws Throwable {
        wildcardMap.put("Honda", null, "Blue", "123");
      }
    });

    assertEquals(IllegalArgumentException.class, exception.getClass());
    assertEquals("Key2 must not be null, empty or blank.", exception.getMessage());
  }

  @Test
  public void testPutOneEntry_EmptyKey2_ThrowsError() {
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, new ThrowingRunnable() {
      @Override
      public void run() throws Throwable {
        wildcardMap.put("Honda", "", "Blue", "123");
      }
    });

    assertEquals(IllegalArgumentException.class, exception.getClass());
    assertEquals("Key2 must not be null, empty or blank.", exception.getMessage());
  }

  @Test
  public void testPutOneEntry_BlankKey2_ThrowsError() {
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, new ThrowingRunnable() {
      @Override
      public void run() throws Throwable {
        wildcardMap.put("Honda", "   ", "Blue", "123");
      }
    });

    assertEquals(IllegalArgumentException.class, exception.getClass());
    assertEquals("Key2 must not be null, empty or blank.", exception.getMessage());
  }

  @Test
  public void testPutOneEntry_NullKey3_ThrowsError() {
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, new ThrowingRunnable() {
      @Override
      public void run() throws Throwable {
        wildcardMap.put("Honda", "Civic", null, "123");
      }
    });

    assertEquals(IllegalArgumentException.class, exception.getClass());
    assertEquals("Key3 must not be null, empty or blank.", exception.getMessage());
  }

  @Test
  public void testPutOneEntry_EmptyKey3_ThrowsError() {
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, new ThrowingRunnable() {
      @Override
      public void run() throws Throwable {
        wildcardMap.put("Honda", "Civic", "", "123");
      }
    });

    assertEquals(IllegalArgumentException.class, exception.getClass());
    assertEquals("Key3 must not be null, empty or blank.", exception.getMessage());
  }

  @Test
  public void testPutOneEntry_BlankKey3_ThrowsError() {
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, new ThrowingRunnable() {
      @Override
      public void run() throws Throwable {
        wildcardMap.put("Honda", "Civic", "   ", "123");
      }
    });

    assertEquals(IllegalArgumentException.class, exception.getClass());
    assertEquals("Key3 must not be null, empty or blank.", exception.getMessage());
  }

  @Test
  public void testPutOneEntry_NullValue_ThrowsError() {
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, new ThrowingRunnable() {
      @Override
      public void run() throws Throwable {
        wildcardMap.put("Honda", "Civic", "Blue", null);
      }
    });

    assertEquals(IllegalArgumentException.class, exception.getClass());
    assertEquals("Value must not be null, empty or blank.", exception.getMessage());
  }

  @Test
  public void testPutOneEntry_EmptyValue_ThrowsError() {
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, new ThrowingRunnable() {
      @Override
      public void run() throws Throwable {
        wildcardMap.put("Honda", "Civic", "Blue", "");
      }
    });

    assertEquals(IllegalArgumentException.class, exception.getClass());
    assertEquals("Value must not be null, empty or blank.", exception.getMessage());
  }

  @Test
  public void testPutOneEntry_BlankValue_ThrowsError() {
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, new ThrowingRunnable() {
      @Override
      public void run() throws Throwable {
        wildcardMap.put("Honda", "Civic", "Blue", "   ");
      }
    });

    assertEquals(IllegalArgumentException.class, exception.getClass());
    assertEquals("Value must not be null, empty or blank.", exception.getMessage());
  }

  @Test
  public void testPutZeroEntries_GetByAllKeys() {
    List<String> values = wildcardMap.get("Honda", "Civic", "Blue");

    assertEquals(Arrays.asList(), values);
  }

  @Test
  public void testPutOneEntry_GetByAllKeys() {
    wildcardMap.put("Honda", "Civic", "Blue", "123");
    List<String> values = wildcardMap.get("Honda", "Civic", "Blue");

    assertEquals(Arrays.asList("123"), values);
  }

  @Test
  public void testPutTwoEntries_SameKeys_GetByAllKeys() {
    wildcardMap.put("Honda", "Civic", "Blue", "123");
    wildcardMap.put("Honda  ", "Civic  ", " Blue ", " 456   ");
    List<String> values = wildcardMap.get("Honda", "Civic", "Blue");

    assertEquals(Arrays.asList("123", "456"), values);
  }

  @Test
  public void testPutManyEntries_DifferentKeys_GetByAllKeys() {
    wildcardMap.put("Honda", "Civic", "Blue", "123");
    wildcardMap.put("Honda", "Civic", "Blue", "456");
    wildcardMap.put("Honda", "Acord", "Black", "789");
    wildcardMap.put("Honda", "Acord", "Black Metallic", "098");
    wildcardMap.put("Toyota", "Corolla", "Red", "468");
    wildcardMap.put("Toyota", "Corolla", "White", "654");
    wildcardMap.put("Toyota", "Camry", "Silver", "246");
    wildcardMap.put("Nissan", "Juke", "White", "135");
    wildcardMap.put("Nissan", "Juke", "Red Metallic", "579");

    assertEquals(Arrays.asList("123", "456"), wildcardMap.get("Honda", "Civic", "Blue"));
    assertEquals(Arrays.asList("789"), wildcardMap.get("Honda", "Acord", "Black"));
    assertEquals(Arrays.asList("098"), wildcardMap.get("Honda", "Acord", "Black Metallic"));
    assertEquals(Arrays.asList("468"), wildcardMap.get("Toyota", "Corolla", "Red"));
    assertEquals(Arrays.asList("654"), wildcardMap.get("Toyota", "Corolla", "White"));
    assertEquals(Arrays.asList("246"), wildcardMap.get("Toyota", "Camry", "Silver"));
    assertEquals(Arrays.asList("135"), wildcardMap.get("Nissan", "Juke", "White"));
    assertEquals(Arrays.asList("579"), wildcardMap.get("Nissan", "Juke", "Red Metallic"));
  }

  @Test
  public void testPutManyEntries_DifferentKeys_GetByWildcard() {
    wildcardMap.put("Honda", "Civic", "Blue", "123");
    wildcardMap.put("Honda", "Civic", "Blue", "456");
    wildcardMap.put("Honda", "Acord", "Black", "789");
    wildcardMap.put("Honda", "Acord", "Black Metallic", "098");
    wildcardMap.put("Toyota", "Corolla", "Red", "468");
    wildcardMap.put("Toyota", "Corolla", "White", "654");
    wildcardMap.put("Toyota", "Camry", "Silver", "246");
    wildcardMap.put("Nissan", "Juke", "White", "135");
    wildcardMap.put("Nissan", "Juke", "Red Metallic", "579");

    assertEquals(Arrays.asList("123", "456"), wildcardMap.get("Honda", "Civic", null));
    assertEquals(Arrays.asList("123", "456", "789", "098"), wildcardMap.get("Honda", null, ""));
    assertEquals(Arrays.asList("123", "456"), wildcardMap.get("", "Civic", null));
    assertEquals(Arrays.asList("123", "456"), wildcardMap.get(null, " ", "Blue"));
    assertEquals(Arrays.asList("468", "654", "246"), wildcardMap.get("Toyota", null, null));
    assertEquals(Arrays.asList("468", "654"), wildcardMap.get("Toyota", "Corolla", null));
    assertEquals(Arrays.asList("468"), wildcardMap.get(" ", " ", "Red"));
    assertEquals(Arrays.asList("135", "579"), wildcardMap.get(null, "Juke", null));
    assertEquals(Arrays.asList("654", "135"), wildcardMap.get(null, null, "White"));
    assertEquals(Arrays.asList("579"), wildcardMap.get(null, null, "Red Metallic"));
    assertEquals(Arrays.asList("123", "456", "789", "098", "468", "654", "246", "135", "579"),
        wildcardMap.get(null, null, null));
    assertEquals(Arrays.asList("123", "456", "789", "098", "468", "654", "246", "135", "579"),
        wildcardMap.get("", "  ", null));
  }

  @Test
  public void testKeysContainingUnderscores_NoCollision() {
    // These keys would collide with underscore separator: "a_b" + "c" + "d" vs "a"
    // + "b_c" + "d"
    wildcardMap.put("a_b", "c", "d", "value1");
    wildcardMap.put("a", "b_c", "d", "value2");
    wildcardMap.put("a", "b", "c_d", "value3");

    // Each should be stored and retrieved separately
    assertEquals(Arrays.asList("value1"), wildcardMap.get("a_b", "c", "d"));
    assertEquals(Arrays.asList("value2"), wildcardMap.get("a", "b_c", "d"));
    assertEquals(Arrays.asList("value3"), wildcardMap.get("a", "b", "c_d"));

    // Wildcard queries should also work correctly
    assertEquals(Arrays.asList("value1"), wildcardMap.get("a_b", null, null));
    assertEquals(Arrays.asList("value2", "value3"), wildcardMap.get("a", null, null));
    assertEquals(Arrays.asList("value3"), wildcardMap.get(null, null, "c_d"));
    assertEquals(Arrays.asList("value1", "value2", "value3"), wildcardMap.get(null, null, null));
  }

  @Test
  public void testGetTrimsWhitespaceInKeys() {
    wildcardMap.put("Honda", "Civic", "Blue", "123");

    // get() should trim keys before lookup
    assertEquals(Arrays.asList("123"), wildcardMap.get("Honda  ", "  Civic", "Blue  "));
    assertEquals(Arrays.asList("123"), wildcardMap.get("  Honda  ", "  Civic  ", "  Blue  "));
  }

  @Test
  public void testGetReturnsImmutableList() {
    wildcardMap.put("Honda", "Civic", "Blue", "123");
    List<String> result = wildcardMap.get("Honda", "Civic", "Blue");

    // List.copyOf() returns immutable list
    assertThrows(UnsupportedOperationException.class, new ThrowingRunnable() {
      @Override
      public void run() throws Throwable {
        result.add("new");
      }
    });
  }

  @Test
  public void testDuplicateValueInsertion() {
    // Same value added twice should appear twice
    wildcardMap.put("a", "b", "c", "value");
    wildcardMap.put("a", "b", "c", "value");

    assertEquals(Arrays.asList("value", "value"), wildcardMap.get("a", "b", "c"));
  }

  @Test
  public void testKeysWithSpecialCharacters() {
    wildcardMap.put("café", "naïve", "résumé", "french");
    wildcardMap.put("über", "größe", "straße", "german");
    wildcardMap.put("key@#$", "key%^&", "key*()", "symbols");

    assertEquals(Arrays.asList("french"), wildcardMap.get("café", "naïve", "résumé"));
    assertEquals(Arrays.asList("german"), wildcardMap.get("über", "größe", "straße"));
    assertEquals(Arrays.asList("symbols"), wildcardMap.get("key@#$", "key%^&", "key*()"));

    // Wildcard queries should also work
    assertEquals(Arrays.asList("french", "german", "symbols"), wildcardMap.get(null, null, null));
  }

  @Test
  public void testIsEmpty_NewMap_ReturnsTrue() {
    assertEquals(true, wildcardMap.isEmpty());
  }

  @Test
  public void testIsEmpty_AfterPut_ReturnsFalse() {
    wildcardMap.put("a", "b", "c", "value");

    assertEquals(false, wildcardMap.isEmpty());
  }

  @Test
  public void testClear_RemovesAllEntries() {
    wildcardMap.put("Honda", "Civic", "Blue", "123");
    wildcardMap.put("Toyota", "Camry", "Red", "456");

    assertEquals(false, wildcardMap.isEmpty());

    wildcardMap.clear();

    assertEquals(true, wildcardMap.isEmpty());
    assertEquals(Arrays.asList(), wildcardMap.get("Honda", "Civic", "Blue"));
    assertEquals(Arrays.asList(), wildcardMap.get("Toyota", "Camry", "Red"));
    assertEquals(Arrays.asList(), wildcardMap.get(null, null, null));
  }

  @Test
  public void testClear_CanAddEntriesAfterClear() {
    wildcardMap.put("a", "b", "c", "old");
    wildcardMap.clear();
    wildcardMap.put("x", "y", "z", "new");

    assertEquals(Arrays.asList(), wildcardMap.get("a", "b", "c"));
    assertEquals(Arrays.asList("new"), wildcardMap.get("x", "y", "z"));
  }
}
