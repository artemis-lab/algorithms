# Algorithms & Data Structures

A collection of algorithms & data structures implemented in Java.

## Data Structures

### WildcardMap

A thread-safe map that supports wildcard queries on composite keys. Values are indexed by three string keys, and retrieval supports wildcards (null/empty/blank) for any key component.

**Features:**

- O(1) wildcard lookups
- Thread-safe using ConcurrentHashMap
- Automatic key trimming

**Example:**

```java
WildcardMap map = new WildcardMap();
map.put("Honda", "Civic", "Blue", "VIN123");
map.put("Honda", "Accord", "Red", "VIN456");

map.get("Honda", "Civic", "Blue");  // ["VIN123"]
map.get("Honda", null, null);       // ["VIN123", "VIN456"] - wildcard query
```

## Prerequisites

- JDK 25+
- Maven 3.9+

## Build

```bash
mvn compile
mvn test
```

## License

MIT
