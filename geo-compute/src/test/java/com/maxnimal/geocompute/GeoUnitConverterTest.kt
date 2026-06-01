package com.maxnimal.geocompute

import com.maxnimal.geocompute.utils.toKilometers
import com.maxnimal.geocompute.utils.toMiles
import com.maxnimal.geocompute.utils.toReadableDistance
import org.junit.Assert.assertEquals
import org.junit.Test

class GeoUnitConverterTest {

    @Test
    fun `test conversion to kilometers`() {
        val meters = 1500.0
        assertEquals(1.5, meters.toKilometers(), 0.001)
    }

    @Test
    fun `test conversion to miles`() {
        val meters = 1609.344
        assertEquals(1.0, meters.toMiles(), 0.001)
    }

    @Test
    fun `test toReadableDistance below 1km`() {
        val meters = 450.0
        assertEquals("450.00 m", meters.toReadableDistance())
    }

    @Test
    fun `test toReadableDistance above 1km`() {
        val meters = 1250.0
        assertEquals("1.25 km", meters.toReadableDistance())
    }

    @Test
    fun `test toReadableDistance with custom digits`() {
        val meters = 1234.567
        assertEquals("1.235 km", meters.toReadableDistance(3))
    }
}
