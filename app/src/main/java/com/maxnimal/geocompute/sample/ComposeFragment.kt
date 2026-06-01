package com.maxnimal.geocompute.sample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import com.maxnimal.geocompute.core.GeoRadiusEngine
import com.maxnimal.geocompute.model.GeoPoint
import com.maxnimal.geocompute.utils.toMiles
import com.maxnimal.geocompute.utils.toReadableDistance

class ComposeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                MaterialTheme {
                    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                        ComposeScreen()
                    }
                }
            }
        }
    }
}

@Composable
fun ComposeScreen() {
    var centerLat by remember { mutableStateOf("13.7651") }
    var centerLon by remember { mutableStateOf("100.5383") }
    var targetLat by remember { mutableStateOf("13.7653") }
    var targetLon by remember { mutableStateOf("100.5385") }
    var radius by remember { mutableStateOf("50.0") }
    
    var distanceM by remember { mutableStateOf<Double?>(null) }
    var isInside by remember { mutableStateOf<Boolean?>(null) }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        OutlinedTextField(
            value = centerLat,
            onValueChange = { centerLat = it },
            label = { Text("Center Latitude") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = centerLon,
            onValueChange = { centerLon = it },
            label = { Text("Center Longitude") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            modifier = Modifier.fillMaxWidth()
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        OutlinedTextField(
            value = targetLat,
            onValueChange = { targetLat = it },
            label = { Text("Target Latitude") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = targetLon,
            onValueChange = { targetLon = it },
            label = { Text("Target Longitude") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            modifier = Modifier.fillMaxWidth()
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        OutlinedTextField(
            value = radius,
            onValueChange = { radius = it },
            label = { Text("Radius (meters)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            modifier = Modifier.fillMaxWidth()
        )
        
        Spacer(modifier = Modifier.height(24.dp))
        
        Button(
            onClick = {
                val cLat = centerLat.toDoubleOrNull() ?: 0.0
                val cLon = centerLon.toDoubleOrNull() ?: 0.0
                val tLat = targetLat.toDoubleOrNull() ?: 0.0
                val tLon = targetLon.toDoubleOrNull() ?: 0.0
                val r = radius.toDoubleOrNull() ?: 0.0
                
                val center = GeoPoint(cLat, cLon)
                val target = GeoPoint(tLat, tLon)
                
                distanceM = GeoRadiusEngine.calculateDistance(center, target)
                isInside = GeoRadiusEngine.isWithinRadius(center, target, r)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Calculate (Compose)")
        }
        
        Spacer(modifier = Modifier.height(24.dp))
        
        if (distanceM != null && isInside != null) {
            val cardColor = if (isInside!!) Color(0xFFE8F5E9) else Color(0xFFFFEBEE)
            val textColor = if (isInside!!) Color(0xFF2E7D32) else Color(0xFFC62828)
            
            Card(
                colors = CardDefaults.cardColors(containerColor = cardColor),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = if (isInside!!) "Status: INSIDE RADIUS" else "Status: OUTSIDE RADIUS",
                        color = textColor,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = "Distance: ${distanceM!!.toReadableDistance()}")
                    Text(text = "Distance: ${String.format("%.4f", distanceM!!.toMiles())} miles")
                }
            }
        }
    }
}
