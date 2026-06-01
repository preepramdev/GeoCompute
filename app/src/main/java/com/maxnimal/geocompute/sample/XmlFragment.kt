package com.maxnimal.geocompute.sample

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.maxnimal.geocompute.core.GeoRadiusEngine
import com.maxnimal.geocompute.model.GeoPoint
import com.maxnimal.geocompute.sample.databinding.FragmentXmlLayoutBinding
import com.maxnimal.geocompute.utils.toMiles
import com.maxnimal.geocompute.utils.toReadableDistance

class XmlFragment : Fragment() {

    private var _binding: FragmentXmlLayoutBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentXmlLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Pre-fill values
        binding.etCenterLat.setText("13.7651")
        binding.etCenterLon.setText("100.5383")
        binding.etTargetLat.setText("13.7653")
        binding.etTargetLon.setText("100.5385")
        binding.etRadius.setText("50.0")

        binding.btnCalculate.setOnClickListener {
            val cLat = binding.etCenterLat.text.toString().toDoubleOrNull() ?: 0.0
            val cLon = binding.etCenterLon.text.toString().toDoubleOrNull() ?: 0.0
            val tLat = binding.etTargetLat.text.toString().toDoubleOrNull() ?: 0.0
            val tLon = binding.etTargetLon.text.toString().toDoubleOrNull() ?: 0.0
            val r = binding.etRadius.text.toString().toDoubleOrNull() ?: 0.0

            val center = GeoPoint(cLat, cLon)
            val target = GeoPoint(tLat, tLon)

            val distance = GeoRadiusEngine.calculateDistance(center, target)
            val isInside = GeoRadiusEngine.isWithinRadius(center, target, r)

            updateUI(distance, isInside)
        }
    }

    private fun updateUI(distance: Double, isInside: Boolean) {
        if (isInside) {
            binding.resultCard.setCardBackgroundColor(Color.parseColor("#E8F5E9"))
            binding.tvStatus.text = "Status: INSIDE RADIUS"
            binding.tvStatus.setTextColor(Color.parseColor("#2E7D32"))
        } else {
            binding.resultCard.setCardBackgroundColor(Color.parseColor("#FFEBEE"))
            binding.tvStatus.text = "Status: OUTSIDE RADIUS"
            binding.tvStatus.setTextColor(Color.parseColor("#C62828"))
        }

        binding.tvDistanceM.text = "Distance: ${distance.toReadableDistance()}"
        binding.tvDistanceMiles.text = "Distance: ${String.format("%.4f", distance.toMiles())} miles"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
