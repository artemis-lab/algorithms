package net.artemislab.algorithms.shuffle;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import org.junit.jupiter.api.Test;

/** The {@code ShuffleTest} class implements tests for {@code Shuffle}. */
public class ShuffleTest {

  @Test
  public void testShuffle_NullArray_ThrowsError() {
    NullPointerException exception =
        assertThrows(NullPointerException.class, () -> Shuffle.shuffle(null));

    assertEquals("The array must not be null.", exception.getMessage());
  }

  @Test
  public void testShuffle_EmptyArray() {
    Integer[] array = new Integer[] {};
    Integer[] result = Shuffle.shuffle(array);

    assertEquals(array.length, result.length);
    assertArrayEquals(new Integer[] {}, array);
    assertArrayEquals(new Integer[] {}, result);
  }

  @Test
  public void testShuffle_OneElement() {
    Integer[] array = new Integer[] {1};
    Integer[] result = Shuffle.shuffle(array);

    assertEquals(array.length, result.length);
    assertArrayEquals(new Integer[] {1}, array);
    assertArrayEquals(new Integer[] {1}, result);
  }

  @Test
  public void testShuffle_TwoElements() {
    Integer[] array = new Integer[] {1, 2};
    Integer[] snapshot = array.clone();
    Integer[] result = Shuffle.shuffle(array);

    assertEquals(array.length, result.length);
    assertTrue(Arrays.equals(array, snapshot));

    Arrays.sort(result);
    assertTrue(Arrays.equals(array, result));
  }

  @Test
  public void testShuffle_DuplicateElements() {
    Integer[] array = new Integer[] {1, 1, 2, 2, 3, 3};
    Integer[] snapshot = array.clone();
    Integer[] result = Shuffle.shuffle(array);

    assertEquals(array.length, result.length);
    assertTrue(Arrays.equals(array, snapshot));

    Arrays.sort(result);
    assertTrue(Arrays.equals(array, result));
  }

  @Test
  public void testShuffle() {
    Integer[] array = new Integer[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    Integer[] snapshot = array.clone();
    Integer[] result = Shuffle.shuffle(array);

    assertEquals(array.length, result.length);
    assertTrue(Arrays.equals(array, snapshot));

    Arrays.sort(result);
    assertTrue(Arrays.equals(array, result));
  }

  @Test
  public void testShuffleForward_NullArray_ThrowsError() {
    NullPointerException exception =
        assertThrows(NullPointerException.class, () -> Shuffle.shuffleForward(null));

    assertEquals("The array must not be null.", exception.getMessage());
  }

  @Test
  public void testShuffleForward_EmptyArray() {
    Integer[] array = new Integer[] {};
    Shuffle.shuffleForward(array);

    assertArrayEquals(new Integer[] {}, array);
  }

  @Test
  public void testShuffleForward_OneElement() {
    Integer[] array = new Integer[] {1};
    Shuffle.shuffleForward(array);

    assertArrayEquals(new Integer[] {1}, array);
  }

  @Test
  public void testShuffleForward_TwoElements() {
    Integer[] array = new Integer[] {1, 2};
    Shuffle.shuffleForward(array);

    assertEquals(2, array.length);
    Arrays.sort(array);
    assertArrayEquals(new Integer[] {1, 2}, array);
  }

  @Test
  public void testShuffleForward_DuplicateElements() {
    Integer[] array = new Integer[] {1, 1, 2, 2, 3, 3};
    Shuffle.shuffleForward(array);

    assertEquals(6, array.length);
    Arrays.sort(array);
    assertArrayEquals(new Integer[] {1, 1, 2, 2, 3, 3}, array);
  }

  @Test
  public void testShuffleForward() {
    Integer[] array = new Integer[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    Shuffle.shuffleForward(array);

    assertEquals(10, array.length);
    Arrays.sort(array);
    assertArrayEquals(new Integer[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, array);
  }

  @Test
  public void testShuffleReverse_NullArray_ThrowsError() {
    NullPointerException exception =
        assertThrows(NullPointerException.class, () -> Shuffle.shuffleReverse(null));

    assertEquals("The array must not be null.", exception.getMessage());
  }

  @Test
  public void testShuffleReverse_EmptyArray() {
    Integer[] array = new Integer[] {};
    Shuffle.shuffleReverse(array);

    assertArrayEquals(new Integer[] {}, array);
  }

  @Test
  public void testShuffleReverse_OneElement() {
    Integer[] array = new Integer[] {1};
    Shuffle.shuffleReverse(array);

    assertArrayEquals(new Integer[] {1}, array);
  }

  @Test
  public void testShuffleReverse_TwoElements() {
    Integer[] array = new Integer[] {1, 2};
    Shuffle.shuffleReverse(array);

    assertEquals(2, array.length);
    Arrays.sort(array);
    assertArrayEquals(new Integer[] {1, 2}, array);
  }

  @Test
  public void testShuffleReverse_DuplicateElements() {
    Integer[] array = new Integer[] {1, 1, 2, 2, 3, 3};
    Shuffle.shuffleReverse(array);

    assertEquals(6, array.length);
    Arrays.sort(array);
    assertArrayEquals(new Integer[] {1, 1, 2, 2, 3, 3}, array);
  }

  @Test
  public void testShuffleReverse() {
    Integer[] array = new Integer[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    Shuffle.shuffleReverse(array);

    assertEquals(10, array.length);
    Arrays.sort(array);
    assertArrayEquals(new Integer[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, array);
  }
}
