package softing.ubah4ukdev.mymapapplication.ui.markers

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import by.kirich1409.viewbindingdelegate.viewBinding
import softing.ubah4ukdev.mymapapplication.R
import softing.ubah4ukdev.mymapapplication.databinding.FragmentMarkersBinding

class MarkersFragment : Fragment(R.layout.fragment_markers) {

    private val viewBinding: FragmentMarkersBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val markersViewModel =
            ViewModelProvider(this)[MarkersViewModel::class.java]

        markersViewModel.text.observe(viewLifecycleOwner) {
            viewBinding.textMarkers.text = it
        }
    }
}