package net.artemislab.algorithms.shuffle;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;
import org.junit.function.ThrowingRunnable;

/**
 * The {@code ShuffleTest} class implements tests for {@code Shuffle}.
 */
public class ShuffleTest {

  @Test
  public void testShuffle_NullArray_ThrowsError() {
    NullPointerException exception = assertThrows(NullPointerException.class, new ThrowingRunnable() {
      @Override
      public void run() throws Throwable {
        Shuffle.shuffle(null);
      }
    });

    assertEquals("The array must not be null.", exception.getMessage());
  }

  @Test
  public void testShuffle_EmptyArray() {
    Integer[] array = new Integer[] {};
    Integer[] result = Shuffle.<Integer>shuffle(array);

    assertEquals(array.length, result.length);
    assertArrayEquals(array, new Integer[] {});
    assertArrayEquals(result, new Integer[] {});
  }

  @Test
  public void testShuffle_OneElement() {
    Integer[] array = new Integer[] { 1 };
    Integer[] result = Shuffle.<Integer>shuffle(array);

    assertEquals(array.length, result.length);
    assertArrayEquals(array, new Integer[] { 1 });
    assertArrayEquals(result, new Integer[] { 1 });
  }

  @Test
  public void testShuffle_TwoElements() {
    Integer[] array = new Integer[] { 1, 2 };
    Integer[] snapshot = array.clone();
    Integer[] result = Shuffle.<Integer>shuffle(array);

    assertEquals(array.length, result.length);
    assertTrue(Arrays.equals(array, snapshot));

    Arrays.sort(result);
    assertTrue(Arrays.equals(array, result));
  }

  @Test
  public void testShuffle_DuplicateElements() {
    Integer[] array = new Integer[] { 1, 1, 2, 2, 3, 3 };
    Integer[] snapshot = array.clone();
    Integer[] result = Shuffle.<Integer>shuffle(array);

    assertEquals(array.length, result.length);
    assertTrue(Arrays.equals(array, snapshot));

    Arrays.sort(result);
    assertTrue(Arrays.equals(array, result));
  }

  @Test
  public void testShuffle() {
    Integer[] array = new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
    Integer[] snapshot = array.clone();
    Integer[] result = Shuffle.<Integer>shuffle(array);

    assertEquals(array.length, result.length);
    assertTrue(Arrays.equals(array, snapshot));

    Arrays.sort(result);
    assertTrue(Arrays.equals(array, result));
  }

  @Test
  public void testShuffleForward_NullArray_ThrowsError() {
    NullPointerException exception = assertThrows(NullPointerException.class, new ThrowingRunnable() {
      @Override
      public void run() throws Throwable {
        Shuffle.shuffleForward(null);
      }
    });

    assertEquals("The array must not be null.", exception.getMessage());
  }

  @Test
  public void testShuffleForward_EmptyArray() {
    Integer[] array = new Integer[] {};
    Shuffle.<Integer>shuffleForward(array);

    assertArrayEquals(array, new Integer[] {});
  }

  @Test
  public void testShuffleForward_OneElement() {
    Integer[] array = new Integer[] { 1 };
    Shuffle.<Integer>shuffleForward(array);

    assertArrayEquals(array, new Integer[] { 1 });
  }

  @Test
  public void testShuffleForward_TwoElements() {
    Integer[] array = new Integer[] { 1, 2 };
    Shuffle.<Integer>shuffleForward(array);

    assertEquals(2, array.length);
    Arrays.sort(array);
    assertArrayEquals(array, new Integer[] { 1, 2 });
  }

  @Test
  public void testShuffleForward_DuplicateElements() {
    Integer[] array = new Integer[] { 1, 1, 2, 2, 3, 3 };
    Shuffle.<Integer>shuffleForward(array);

    assertEquals(6, array.length);
    Arrays.sort(array);
    assertArrayEquals(array, new Integer[] { 1, 1, 2, 2, 3, 3 });
  }

  @Test
  public void testShuffleForward() {
    Integer[] array = new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
    Shuffle.<Integer>shuffleForward(array);

    assertEquals(10, array.length);
    Arrays.sort(array);
    assertArrayEquals(array, new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 });
  }

  @Test
  public void testShuffleReverse_NullArray_ThrowsError() {
    NullPointerException exception = assertThrows(NullPointerException.class, new ThrowingRunnable() {
      @Override
      public void run() throws Throwable {
        Shuffle.shuffleReverse(null);
      }
    });

    assertEquals("The array must not be null.", exception.getMessage());
  }

  @Test
  public void testShuffleReverse_EmptyArray() {
    Integer[] array = new Integer[] {};
    Shuffle.<Integer>shuffleReverse(array);

    assertArrayEquals(array, new Integer[] {});
  }

  @Test
  public void testShuffleReverse_OneElement() {
    Integer[] array = new Integer[] { 1 };
    Shuffle.<Integer>shuffleReverse(array);

    assertArrayEquals(array, new Integer[] { 1 });
  }

  @Test
  public void testShuffleReverse_TwoElements() {
    Integer[] array = new Integer[] { 1, 2 };
    Shuffle.<Integer>shuffleReverse(array);

    assertEquals(2, array.length);
    Arrays.sort(array);
    assertArrayEquals(array, new Integer[] { 1, 2 });
  }

  @Test
  public void testShuffleReverse_DuplicateElements() {
    Integer[] array = new Integer[] { 1, 1, 2, 2, 3, 3 };
    Shuffle.<Integer>shuffleReverse(array);

    assertEquals(6, array.length);
    Arrays.sort(array);
    assertArrayEquals(array, new Integer[] { 1, 1, 2, 2, 3, 3 });
  }

  @Test
  public void testShuffleReverse() {
    Integer[] array = new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
    Shuffle.<Integer>shuffleReverse(array);

    assertEquals(10, array.length);
    Arrays.sort(array);
    assertArrayEquals(array, new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 });
  }
}
