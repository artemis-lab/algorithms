package net.artemislab.algorithms.utils;

/** Utility class providing common operations for algorithm implementations. */
public class Utils {

  private Utils() {}

  /**
   * Swaps two elements in the given array.
   *
   * @param <T> the type of the array's elements.
   * @param array the array whose elements to swap.
   * @param i the index of the first element to swap.
   * @param j the index of the second element to swap.
   */
  public static <T> void swap(T[] array, int i, int j) {
    T temp = array[i];
    array[i] = array[j];
    array[j] = temp;
  }
}
