package net.artemislab.algorithms.maps;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * The {@code WildcardMap} class represents a map that supports wildcard queries on composite keys.
 *
 * <p>Values are indexed by three string keys, and retrieval supports wildcards (null/empty/blank)
 * for any key component.
 *
 * <p>This implementation is thread-safe, using {@link ConcurrentHashMap} for storage.
 *
 * <p><b>Memory trade-off:</b> Each entry is stored under 2^n key combinations (where n=3 key
 * components) to enable O(1) wildcard lookups.
 */
public class WildcardMap {
  /**
   * Unit Separator character used as key component delimiter. This avoids collisions with keys
   * containing common characters like underscore.
   */
  private static final String SEPARATOR = "\u001F";

  /** Record Separator character used as placeholder for null/wildcard key components. */
  private static final String NULL_MARKER = "\u001E";

  /**
   * Maps composite keys to lists of values. Each entry is stored under multiple key combinations
   * (2^n) to enable O(1) wildcard lookups.
   */
  private Map<String, List<String>> storage;

  /**
   * The cache used to store generated combinations of keys used by the internal storage, for faster
   * access when the same combination of keys is used multiple times.
   */
  private Map<String, List<String>> keysByArgs;

  /** Creates a new instance of {@code WildcardMap}. */
  public WildcardMap() {
    storage = new ConcurrentHashMap<>();
    keysByArgs = new ConcurrentHashMap<>();
  }

  /**
   * Adds a new entry into the map.
   *
   * <p>Throws an {@code IllegalArgumentException} if any of the provided arguments have either
   * null, empty or blank (containing only white space characters) value.
   *
   * <p>Before adding a new entry all arguments are trimmed with all leading and trailing white
   * space characters removed.
   *
   * @param key1 The first key component. Must not be null, empty or blank.
   * @param key2 The second key component. Must not be null, empty or blank.
   * @param key3 The third key component. Must not be null, empty or blank.
   * @param value The value to store. Must not be null, empty or blank.
   * @throws IllegalArgumentException if any argument is null, empty or blank.
   */
  public void put(String key1, String key2, String key3, String value) {
    String trimmedKey1 = checkForNullOrEmptyOrBlank(key1, "Key1 must not be null, empty or blank.");
    String trimmedKey2 = checkForNullOrEmptyOrBlank(key2, "Key2 must not be null, empty or blank.");
    String trimmedKey3 = checkForNullOrEmptyOrBlank(key3, "Key3 must not be null, empty or blank.");
    String trimmedValue =
        checkForNullOrEmptyOrBlank(value, "Value must not be null, empty or blank.");

    String[] args = argsToArray(trimmedKey1, trimmedKey2, trimmedKey3);
    String argsAsString = Arrays.toString(args);
    List<String> keys = keysByArgs.computeIfAbsent(argsAsString, k -> getKeysSubsets(args));

    for (String key : keys) {
      storage.compute(
          key,
          (k, values) -> {
            if (values == null) {
              values = new ArrayList<>();
            }
            values.add(trimmedValue);
            return values;
          });
    }
  }

  /**
   * Retrieves a list of values matching the given key components.
   *
   * <p>Any or all of the given arguments may have null, empty or blank value, which acts as a
   * wildcard matching all values for that key component.
   *
   * @param key1 The first key component. May be null, empty or blank (wildcard).
   * @param key2 The second key component. May be null, empty or blank (wildcard).
   * @param key3 The third key component. May be null, empty or blank (wildcard).
   * @return the list of values matching the given key components, or an empty list if none found.
   */
  public List<String> get(String key1, String key2, String key3) {
    String[] args = argsToArray(key1, key2, key3);
    StringBuilder key = new StringBuilder();

    for (int i = 0; i < args.length; i++) {
      String arg = args[i];
      if (arg == null || arg.trim().isEmpty()) {
        key.append(NULL_MARKER);
      } else {
        key.append(arg.trim());
      }
      if (i < args.length - 1) {
        key.append(SEPARATOR);
      }
    }

    List<String> values = storage.getOrDefault(key.toString(), new ArrayList<>());
    return List.copyOf(values);
  }

  /**
   * Returns {@code true} if this map contains no entries.
   *
   * @return {@code true} if this map contains no entries.
   */
  public boolean isEmpty() {
    return storage.isEmpty();
  }

  /** Removes all entries from this map. */
  public void clear() {
    storage.clear();
    keysByArgs.clear();
  }

  private static String checkForNullOrEmptyOrBlank(String value, String message) {
    if (value == null) {
      throw new IllegalArgumentException(message);
    }

    String trimmedValue = value.trim();
    if (trimmedValue.isEmpty()) {
      throw new IllegalArgumentException(message);
    }

    return trimmedValue;
  }

  private static String[] argsToArray(String key1, String key2, String key3) {
    return new String[] {key1, key2, key3};
  }

  private static List<String> getKeysSubsets(String[] args) {
    List<String> keysSubsets = new ArrayList<>();
    getKeysSubsetsHelper(args, 0, new ArrayList<>(), keysSubsets);
    keysSubsets.add(NULL_MARKER + SEPARATOR + NULL_MARKER + SEPARATOR + NULL_MARKER);
    return keysSubsets;
  }

  private static void getKeysSubsetsHelper(
      String[] args, int start, List<Integer> keySubset, List<String> keysSubsets) {
    for (int i = start; i < args.length; i++) {
      keySubset.add(i);
      keysSubsets.add(keySubsetToString(args, keySubset));
      getKeysSubsetsHelper(args, i + 1, keySubset, keysSubsets);
      keySubset.remove(keySubset.size() - 1);
    }
  }

  private static String keySubsetToString(String[] args, List<Integer> keySubset) {
    StringBuilder result = new StringBuilder();
    for (int i = 0; i < args.length; i++) {
      if (keySubset.contains(i)) {
        result.append(args[i]);
      } else {
        result.append(NULL_MARKER);
      }
      if (i < args.length - 1) {
        result.append(SEPARATOR);
      }
    }
    return result.toString();
  }
}
