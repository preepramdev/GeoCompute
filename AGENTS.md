# GeoCompute — AGENTS.md

## Project structure

- `:geo-compute/` — Android library (the published artifact). Entrypoint: `GeoRadiusEngine` (object implementing `GeoRadiusClient` interface).
- `:app/` — Sample app showing both Compose (`ComposeFragment`) and XML (`XmlFragment`) UIs side by side via TabLayout + ViewPager2.

## Build & test

```sh
./gradlew :geo-compose:test          # unit tests only (JUnit 4, no instrumentation)
./gradlew :geo-compute:assembleRelease  # build the AAR for publishing
./gradlew build                      # full build (both modules)
```

No codegen, migrations, or special setup steps.

## Key conventions

- Kotlin code style: `official` (set in `gradle.properties`).
- All distances in meters (`Double`). Earth radius used: 6,371,000 m (Haversine formula).
- Core API is the `GeoRadiusClient` interface; `GeoRadiusEngine` is the default singleton implementation.
- Version catalog in `gradle/libs.versions.toml` — update versions there, not inline.
- Java 17 source/target compatibility.

## Publishing

JitPack-based via `maven-publish` plugin (configured in `geo-compute/build.gradle.kts`). The publication coordinates:

```
groupId = com.github.preepramdev
artifactId = geo-compute
version  = 1.0.0
```

Run `./gradlew :geo-compute:publishReleasePublicationToMavenLocal` to test locally.

## Dependencies (library)

- `androidx.core:core-ktx` (runtime)
- `junit:junit:4.13.2` (test only)
- Zero dependency on Google Play Services.

## App (sample) note

Uses Compose BOM (`2024.09.03`) + Material 3 + ViewPager2. Not published — purely for demonstration.
