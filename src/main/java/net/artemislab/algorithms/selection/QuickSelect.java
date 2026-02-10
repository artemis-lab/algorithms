package net.artemislab.algorithms.selection;

import java.util.Objects;
import net.artemislab.algorithms.shuffle.Shuffle;
import net.artemislab.algorithms.utils.Utils;

/** Utility class providing QuickSelect algorithm. */
public class QuickSelect {

  private QuickSelect() {}

  /**
   * Returns the {@code k}-th smallest element in the given array using the QuickSelect algorithm.
   * The original array is not modified.
   *
   * <p><b>Time complexity:</b> O(n) average, O(n^2) worst case. The initial random shuffle makes
   * the worst case extremely unlikely.
   *
   * <p><b>Space complexity:</b> O(n) for the shuffled copy of the input array.
   *
   * @param <T> the type of the array's elements.
   * @param array the array to search.
   * @param k the zero-based index of the desired order statistic (0 for the smallest element,
   *     {@code array.length - 1} for the largest).
   * @return the {@code k}-th smallest element (zero-based).
   * @throws NullPointerException if {@code array} is {@code null}.
   * @throws IllegalArgumentException if {@code array} is empty.
   * @throws IllegalArgumentException if {@code k} is negative or greater than or equal to {@code
   *     array.length}.
   */
  public static <T extends Comparable<T>> T quickSelect(T[] array, int k) {
    Objects.requireNonNull(array, "The array must not be null.");
    if (array.length == 0) {
      throw new IllegalArgumentException("The array must not be empty.");
    }
    if (k < 0 || k >= array.length) {
      throw new IllegalArgumentException(
          "k must be between 0 and " + (array.length - 1) + ", but was " + k + ".");
    }
    T[] shuffled = Shuffle.shuffle(array);
    int lo = 0;
    int hi = shuffled.length - 1;
    while (lo <= hi) {
      int j = partition(shuffled, lo, hi);
      if (j < k) {
        lo = j + 1;
      } else if (j > k) {
        hi = j - 1;
      } else {
        return shuffled[k];
      }
    }
    return shuffled[k];
  }

  /**
   * Partition is due to Nico Lomuto.
   *
   * @param <T> the type of the array's elements.
   * @param array the array to partition.
   * @param lo the lowest index to partition.
   * @param hi the highest index to partition.
   * @return the index of the pivot after partitioning.
   */
  private static <T extends Comparable<T>> int partition(T[] array, int lo, int hi) {
    T pivot = array[hi];
    int i = lo;
    for (int j = lo; j < hi; j++) {
      if (array[j].compareTo(pivot) <= 0) {
        Utils.swap(array, i, j);
        i++;
      }
    }
    Utils.swap(array, i, hi);
    return i;
  }
}
