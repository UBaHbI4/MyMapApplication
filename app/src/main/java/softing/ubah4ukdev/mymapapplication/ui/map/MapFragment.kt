package softing.ubah4ukdev.mymapapplication.ui.map

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import by.kirich1409.viewbindingdelegate.viewBinding
import softing.ubah4ukdev.mymapapplication.R
import softing.ubah4ukdev.mymapapplication.databinding.FragmentMapBinding

class MapFragment : Fragment(R.layout.fragment_map) {

    private val viewBinding: FragmentMapBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val mapViewModel =
            ViewModelProvider(this)[MapViewModel::class.java]

        mapViewModel.text.observe(viewLifecycleOwner) {
            viewBinding.textMap.text = it
        }
    }
}