package com.maxnimal.geocompute.core

import com.maxnimal.geocompute.model.GeoPoint
import kotlin.math.*

/**
 * Implementation of [GeoRadiusClient] using the Haversine formula.
 */
object GeoRadiusEngine : GeoRadiusClient {

    private const val EARTH_RADIUS_METERS = 6371000.0

    override fun calculateDistance(start: GeoPoint, end: GeoPoint): Double {
        val lat1 = Math.toRadians(start.latitude)
        val lon1 = Math.toRadians(start.longitude)
        val lat2 = Math.toRadians(end.latitude)
        val lon2 = Math.toRadians(end.longitude)

        val dLat = lat2 - lat1
        val dLon = lon2 - lon1

        val a = sin(dLat / 2).pow(2) +
                cos(lat1) * cos(lat2) *
                sin(dLon / 2).pow(2)
        
        val c = 2 * atan2(sqrt(a), sqrt(1 - a))

        return EARTH_RADIUS_METERS * c
    }

    override fun isWithinRadius(center: GeoPoint, target: GeoPoint, radiusInMeters: Double): Boolean {
        return calculateDistance(center, target) <= radiusInMeters
    }

    override fun filterWithinRadius(
        center: GeoPoint,
        targets: List<GeoPoint>,
        radiusInMeters: Double
    ): List<GeoPoint> {
        return targets.filter { isWithinRadius(center, it, radiusInMeters) }
    }
}
