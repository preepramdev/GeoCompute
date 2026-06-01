package com.maxnimal.geocompute.core

import com.maxnimal.geocompute.model.GeoPoint

/**
 * Interface for the GeoRadius client.
 */
interface GeoRadiusClient {
    /**
     * Calculates the distance between two points in meters.
     */
    fun calculateDistance(start: GeoPoint, end: GeoPoint): Double

    /**
     * Checks if a target point is within a given radius from a center point.
     */
    fun isWithinRadius(center: GeoPoint, target: GeoPoint, radiusInMeters: Double): Boolean

    /**
     * Filters a list of target points that are within a given radius from a center point.
     */
    fun filterWithinRadius(center: GeoPoint, targets: List<GeoPoint>, radiusInMeters: Double): List<GeoPoint>
}
