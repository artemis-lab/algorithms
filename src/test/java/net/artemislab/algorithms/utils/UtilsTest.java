package net.artemislab.algorithms.utils;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

/** The {@code UtilsTest} class implements tests for {@code Utils}. */
public class UtilsTest {

  @Test
  public void testSwap_SameIndexes() {
    Integer[] array = new Integer[] {1, 2};
    Utils.swap(array, 0, 0);

    assertArrayEquals(new Integer[] {1, 2}, array);
  }

  @Test
  public void testSwap() {
    Integer[] array = new Integer[] {1, 2};
    Utils.swap(array, 0, 1);

    assertArrayEquals(new Integer[] {2, 1}, array);
  }
}
