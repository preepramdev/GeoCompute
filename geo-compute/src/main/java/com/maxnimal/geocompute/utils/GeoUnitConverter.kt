package com.maxnimal.geocompute.utils

import java.util.Locale

/**
 * Extension functions for Double representing meters.
 */

fun Double.toKilometers(): Double = this / 1000.0

fun Double.toMiles(): Double = this / 1609.344

/**
 * Formats the distance into a readable string.
 * If < 1000m, returns "X.XX m".
 * If >= 1000m, returns "X.XX km".
 */
fun Double.toReadableDistance(digits: Int = 2): String {
    return if (this < 1000.0) {
        String.format(Locale.US, "%.${digits}f m", this)
    } else {
        String.format(Locale.US, "%.${digits}f km", this.toKilometers())
    }
}
