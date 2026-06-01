# GeoCompute 🌍

A lightweight, production-ready Android library for high-precision geofencing and distance calculations using the Haversine formula. Built with modern Android standards (Kotlin 2.x, SDK 35).

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
[![Kotlin](https://img.shields.io/badge/kotlin-2.0.20-blue.svg)](https://kotlinlang.org)
[![Platform](https://img.shields.io/badge/platform-Android-green.svg)](https://developer.android.com)

## 🚀 Features

- **Precision Distance**: High-accuracy great-circle distance calculations using the Haversine formula.
- **Geofencing**: Simple API to check if a point is within a specific radius.
- **Unit Conversion**: Built-in extensions for meters, kilometers, and miles.
- **Human Readable**: Formatted distance strings (e.g., "1.25 km", "450 m").
- **Clean Architecture**: Decoupled core logic with zero external dependencies (no Google Play Services required).
- **Multi-UI Showcase**: Sample app demonstrating implementations in both **Jetpack Compose** and **XML View System**.

## 📦 Installation

### 1. Add the JitPack repository to your `settings.gradle.kts`:

```kotlin
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
}
```

### 2. Add the dependency to your module's `build.gradle.kts`:

```kotlin
dependencies {
    implementation("com.github.preepramdev:GeoCompute:1.0.0")
}
```

## 🛠 Usage

### Core Logic

```kotlin
import com.maxnimal.geocompute.core.GeoRadiusEngine
import com.maxnimal.geocompute.model.GeoPoint

val center = GeoPoint(13.7651, 100.5383) // Victory Monument
val target = GeoPoint(13.7653, 100.5385)

// 1. Calculate Distance
val distanceInMeters = GeoRadiusEngine.calculateDistance(center, target)

// 2. Check Radius
val isInside = GeoRadiusEngine.isWithinRadius(center, target, 50.0)

// 3. Filter List
val points = listOf(...)
val nearbyPoints = GeoRadiusEngine.filterWithinRadius(center, points, 500.0)
```

### Unit Extensions

```kotlin
import com.maxnimal.geocompute.utils.toMiles
import com.maxnimal.geocompute.utils.toReadableDistance

val distance = 1250.0
println(distance.toReadableDistance()) // Output: "1.25 km"
println(distance.toMiles())            // Output: 0.7767...
```

## 📱 Showcase App

The included `:app` module provides a comprehensive demonstration:
- **Compose (Modern)**: Declarative UI using Material 3.
- **XML (Classic)**: Traditional View System using View Binding and TabLayout/ViewPager2.

## 🛠 Tech Stack

- **Build System**: Gradle Kotlin DSL (.gradle.kts)
- **Language**: Kotlin 2.x
- **UI**: Jetpack Compose & XML (Hybrid Showcase)
- **Minimum SDK**: 21
- **Target SDK**: 35

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---
Built with ❤️ by [preepramdev](https://github.com/preepramdev)
