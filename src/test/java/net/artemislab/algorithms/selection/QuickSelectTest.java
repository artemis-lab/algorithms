package net.artemislab.algorithms.selection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import org.junit.jupiter.api.Test;

/** The {@code QuickSelectTest} class implements tests for {@code QuickSelect}. */
public class QuickSelectTest {

  @Test
  public void testQuickSelect_NullArray_ThrowsError() {
    NullPointerException exception =
        assertThrows(NullPointerException.class, () -> QuickSelect.quickSelect(null, 0));

    assertEquals("The array must not be null.", exception.getMessage());
  }

  @Test
  public void testQuickSelect_EmptyArray_ThrowsError() {
    Integer[] array = new Integer[] {};
    IllegalArgumentException exception =
        assertThrows(IllegalArgumentException.class, () -> QuickSelect.quickSelect(array, 0));

    assertEquals("The array must not be empty.", exception.getMessage());
  }

  @Test
  public void testQuickSelect_K_LessZero_ThrowsError() {
    Integer[] array = new Integer[] {1, 2};
    IllegalArgumentException exception =
        assertThrows(IllegalArgumentException.class, () -> QuickSelect.quickSelect(array, -1));

    assertEquals("k must be between 0 and 1, but was -1.", exception.getMessage());
  }

  @Test
  public void testQuickSelect_K_GreaterArrayLength_ThrowsError() {
    Integer[] array = new Integer[] {1, 2};
    IllegalArgumentException exception =
        assertThrows(IllegalArgumentException.class, () -> QuickSelect.quickSelect(array, 2));

    assertEquals("k must be between 0 and 1, but was 2.", exception.getMessage());
  }

  @Test
  public void testQuickSelect_OneElement() {
    Integer[] array = new Integer[] {1};
    Integer element = QuickSelect.quickSelect(array, 0);

    assertEquals(1, element);
  }

  @Test
  public void testQuickSelect_K_EqualsZero() {
    Integer[] array = new Integer[] {1, 2, 3};
    Integer element = QuickSelect.quickSelect(array, 0);

    assertEquals(1, element);
  }

  @Test
  public void testQuickSelect_K_EqualsLastIndex() {
    Integer[] array = new Integer[] {1, 3, 5};
    Integer element = QuickSelect.quickSelect(array, 2);

    assertEquals(5, element);
  }

  @Test
  public void testQuickSelect_OriginalArrayIsNotModified() {
    Integer[] array = new Integer[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    Integer[] snapshot = array.clone();
    Integer element = QuickSelect.quickSelect(array, 4);

    assertTrue(Arrays.equals(array, snapshot));
    assertEquals(5, element);
  }

  @Test
  public void testQuickSelect_SortedArray_DistinctElements() {
    Integer[] array = new Integer[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    Integer element = QuickSelect.quickSelect(array, 4);

    assertEquals(5, element);
  }

  @Test
  public void testQuickSelect_SortedArray_DuplicateElements() {
    Integer[] array = new Integer[] {1, 1, 2, 2, 2, 3, 3, 4, 4, 5};
    Integer element = QuickSelect.quickSelect(array, 4);

    assertEquals(2, element);
  }

  @Test
  public void testQuickSelect_UnsortedArray_DistinctElements() {
    Integer[] array = new Integer[] {2, 3, 1, 5, 6, 4, 10, 9, 8, 7};
    Integer element = QuickSelect.quickSelect(array, 4);

    assertEquals(5, element);
  }

  @Test
  public void testQuickSelect_UnsortedArray_DuplicateElements() {
    Integer[] array = new Integer[] {2, 1, 2, 1, 2, 4, 3, 5, 4, 3};
    Integer element = QuickSelect.quickSelect(array, 4);

    assertEquals(2, element);
  }

  @Test
  public void testQuickSelect_AllSameElements() {
    Integer[] array = new Integer[] {2, 2, 2, 2, 2, 2, 2, 2, 2, 2};
    Integer element = QuickSelect.quickSelect(array, 4);

    assertEquals(2, element);
  }
}
