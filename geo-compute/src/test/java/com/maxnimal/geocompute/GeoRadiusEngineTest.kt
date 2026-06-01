package com.maxnimal.geocompute

import com.maxnimal.geocompute.core.GeoRadiusEngine
import com.maxnimal.geocompute.model.GeoPoint
import org.junit.Assert.*
import org.junit.Test

class GeoRadiusEngineTest {

    @Test
    fun `test distance between Bangkok and Chiang Mai`() {
        val bangkok = GeoPoint(13.7563, 100.5018)
        val chiangMai = GeoPoint(18.7883, 98.9853)
        
        val distance = GeoRadiusEngine.calculateDistance(bangkok, chiangMai)
        
        // Approx 583 km
        assertEquals(583000.0, distance, 1000.0) 
    }

    @Test
    fun `test distance close range`() {
        val pointA = GeoPoint(13.7563, 100.5018)
        val pointB = GeoPoint(13.7563, 100.5022)
        
        val distance = GeoRadiusEngine.calculateDistance(pointA, pointB)
        
        // Approx 43 meters
        assertEquals(43.0, distance, 2.0)
    }

    @Test
    fun `test isWithinRadius`() {
        val center = GeoPoint(13.7563, 100.5018)
        val inside = GeoPoint(13.7563, 100.5020) // Very close
        val outside = GeoPoint(18.7883, 98.9853) // Chiang Mai
        
        assertTrue(GeoRadiusEngine.isWithinRadius(center, inside, 100.0))
        assertFalse(GeoRadiusEngine.isWithinRadius(center, outside, 100000.0)) // 100km
    }

    @Test
    fun `test filterWithinRadius`() {
        val center = GeoPoint(13.7563, 100.5018)
        val points = listOf(
            GeoPoint(13.7563, 100.5019), // Inside
            GeoPoint(13.7563, 100.5020), // Inside
            GeoPoint(18.7883, 98.9853)   // Outside
        )
        
        val filtered = GeoRadiusEngine.filterWithinRadius(center, points, 500.0)
        
        assertEquals(2, filtered.size)
        assertTrue(filtered.contains(points[0]))
        assertTrue(filtered.contains(points[1]))
        assertFalse(filtered.contains(points[2]))
    }
}
