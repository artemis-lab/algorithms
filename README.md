[![Code Quality & Build](https://github.com/artemis-lab/algorithms/actions/workflows/ci.yml/badge.svg)](https://github.com/artemis-lab/algorithms/actions/workflows/ci.yml)
[![codecov](https://codecov.io/gh/artemis-lab/algorithms/graph/badge.svg?token=RD3FIZ4IWU)](https://codecov.io/gh/artemis-lab/algorithms)
![Java Version](https://img.shields.io/badge/Java-25-orange?logo=openjdk&logoColor=white)
![Maven Version](https://img.shields.io/badge/Maven-3.9.12-C71A36?logo=apachemaven&logoColor=white)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

<!-- omit in toc -->
# Algorithms & Data Structures

A collection of algorithms & data structures implemented in Java.

<!-- omit in toc -->
## Table of Contents

- [Algorithms](#algorithms)
  - [Shuffle](#shuffle)
- [Data Structures](#data-structures)
  - [WildcardMap](#wildcardmap)
- [Prerequisites](#prerequisites)
- [Build](#build)
  - [NVD API Key](#nvd-api-key)
- [Code Quality](#code-quality)
  - [IDE Setup](#ide-setup)
- [License](#license)

## Algorithms

### Shuffle

Fisher-Yates shuffle implementations providing three variants:

- **`shuffle`** - Inside-out variant that returns a shuffled copy without modifying the original array
- **`shuffleForward`** - In-place forward variant, iterating first to last
- **`shuffleReverse`** - In-place Durstenfeld/Knuth variant, iterating last to first

**Example:**

```java
Integer[] array = {1, 2, 3, 4, 5};

// Shuffled copy (original unchanged)
Integer[] shuffled = Shuffle.shuffle(array);

// In-place shuffle
Shuffle.shuffleForward(array);
Shuffle.shuffleReverse(array);
```

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
- Maven 3.9.12+

## Build

```bash
./mvnw compile         # compile sources
./mvnw test            # run tests
./mvnw verify          # full build with all quality checks
./mvnw verify -Ppitest # full build with mutation testing
```

### NVD API Key

OWASP Dependency-Check requires an [NVD API key](https://nvd.nist.gov/developers/request-an-api-key) for fast vulnerability database updates. You can pass it via command line:

```bash
./mvnw verify -Dnvd.api.key=YOUR_KEY
```

Or configure it permanently in `~/.m2/settings.xml`:

```xml
<settings>
  <profiles>
    <profile>
      <id>security-profile</id>
      <activation>
        <activeByDefault>true</activeByDefault>
      </activation>
      <properties>
        <nvd.api.key>YOUR_KEY</nvd.api.key>
      </properties>
    </profile>
  </profiles>
</settings>
```

## Code Quality

All checks run automatically via GitHub Actions on every push and pull request. Mutation testing runs only on pull requests.

| Tool                                                                          | Purpose                                                   | Phase    |
| ----------------------------------------------------------------------------- | --------------------------------------------------------- | -------- |
| [Checkstyle](https://checkstyle.org/)                                         | Google Java Style enforcement                             | validate |
| [Maven Enforcer](https://maven.apache.org/enforcer/maven-enforcer-plugin/)    | Build constraints (Java/Maven versions, dependency rules) | validate |
| [Sortpom](https://github.com/Ekryd/sortpom)                                   | POM formatting and ordering                               | validate |
| [Error Prone](https://errorprone.info/)                                        | Compile-time bug detection                                | compile  |
| [JaCoCo](https://www.jacoco.org/)                                             | Code coverage (80% line, 75% branch minimum)              | verify   |
| [Maven Dependency](https://maven.apache.org/plugins/maven-dependency-plugin/) | Unused/undeclared dependency analysis                     | verify   |
| [Maven Javadoc](https://maven.apache.org/plugins/maven-javadoc-plugin/)       | Javadoc validation and completeness                       | verify   |
| [Modernizer](https://github.com/gaul/modernizer-maven-plugin)                 | Legacy API detection                                      | verify   |
| [OWASP Dependency-Check](https://owasp.org/www-project-dependency-check/)     | CVE vulnerability scanning (CVSS >= 7 fails build)        | verify   |
| [PMD](https://pmd.github.io/)                                                 | Static code analysis (unused code, dead code, complexity) | verify   |
| [Pitest](https://pitest.org/)                                                  | Mutation testing (80% threshold, profile: `-Ppitest`)     | verify   |
| [SpotBugs](https://spotbugs.github.io/)                                       | Static bug detection                                      | verify   |

[Dependabot](https://docs.github.com/en/code-security/dependabot) is enabled for automated Maven and GitHub Actions dependency updates.

Coverage reports are uploaded to [Codecov](https://codecov.io/).

### IDE Setup

The project uses [google-java-format](https://github.com/google/google-java-format) for formatting. VSCode will recommend the extension automatically via `.vscode/extensions.json`.

## License

MIT
