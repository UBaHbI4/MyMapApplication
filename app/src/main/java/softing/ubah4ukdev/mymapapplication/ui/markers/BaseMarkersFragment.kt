package softing.ubah4ukdev.mymapapplication.ui.markers

import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import softing.ubah4ukdev.mymapapplication.R
import softing.ubah4ukdev.mymapapplication.databinding.FragmentMarkersBinding

/**
 *   Project: MyMapApplication
 *
 *   Package: softing.ubah4ukdev.mymapapplication.ui.markers
 *
 *   Created by Ivan Sheynmaer
 *
 *   Description:
 *
 *
 *   2022.02.19
 *
 *   v1.0
 */
abstract class BaseMarkersFragment : Fragment(R.layout.fragment_markers) {
    protected val viewModel: MarkersViewModel by viewModel()
    protected val viewBinding: FragmentMarkersBinding by viewBinding()
}