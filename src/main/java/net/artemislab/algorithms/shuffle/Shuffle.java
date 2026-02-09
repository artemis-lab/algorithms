package net.artemislab.algorithms.shuffle;

import java.lang.reflect.Array;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

/** Utility class providing Fisher-Yates shuffle variants. */
public class Shuffle {

  private Shuffle() {}

  /**
   * Returns a shuffled copy of the given array using the inside-out variant of the Fisher-Yates
   * algorithm.
   *
   * <p>Iterates from the first element to the last, placing each source element at a randomly
   * chosen position in the result array.
   *
   * <p>The original array is not modified.
   *
   * @param <T> the type of the array's elements.
   * @param array the source array to shuffle.
   * @return a new array containing the elements of {@code array} in random order.
   * @throws NullPointerException if {@code array} is {@code null}.
   * @see #shuffleForward(Object[])
   * @see #shuffleReverse(Object[])
   */
  public static <T> T[] shuffle(T[] array) {
    Objects.requireNonNull(array, "The array must not be null.");
    @SuppressWarnings("unchecked")
    T[] result = (T[]) Array.newInstance(array.getClass().getComponentType(), array.length);
    for (int i = 0; i < result.length; i++) {
      int j = ThreadLocalRandom.current().nextInt(0, i + 1);
      if (j != i) {
        result[i] = result[j];
      }
      result[j] = array[i];
    }
    return result;
  }

  /**
   * Shuffles elements of the given array in place using the forward variant of the Fisher-Yates
   * algorithm.
   *
   * <p>Iterates from the first element to the last, swapping each element with a randomly chosen
   * element from those at or before it.
   *
   * @param <T> the type of the array's elements.
   * @param array the array whose elements to shuffle.
   * @throws NullPointerException if {@code array} is {@code null}.
   * @see #shuffle(Object[])
   * @see #shuffleReverse(Object[])
   */
  public static <T> void shuffleForward(T[] array) {
    Objects.requireNonNull(array, "The array must not be null.");
    for (int i = 0; i < array.length; i++) {
      int j = ThreadLocalRandom.current().nextInt(0, i + 1);
      swap(array, i, j);
    }
  }

  /**
   * Shuffles elements of the given array in place using the Durstenfeld variant of the Fisher-Yates
   * algorithm, as popularized by Knuth.
   *
   * <p>Iterates from the last element to the first, swapping each element with a randomly chosen
   * element from those at or before it.
   *
   * @param <T> the type of the array's elements.
   * @param array the array whose elements to shuffle.
   * @throws NullPointerException if {@code array} is {@code null}.
   * @see #shuffle(Object[])
   * @see #shuffleForward(Object[])
   */
  public static <T> void shuffleReverse(T[] array) {
    Objects.requireNonNull(array, "The array must not be null.");
    for (int i = array.length - 1; i >= 0; i--) {
      int j = ThreadLocalRandom.current().nextInt(0, i + 1);
      swap(array, i, j);
    }
  }

  private static <T> void swap(T[] array, int i, int j) {
    T temp = array[i];
    array[i] = array[j];
    array[j] = temp;
  }
}
