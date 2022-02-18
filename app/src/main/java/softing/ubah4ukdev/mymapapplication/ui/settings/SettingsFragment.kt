package softing.ubah4ukdev.mymapapplication.ui.settings

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import by.kirich1409.viewbindingdelegate.viewBinding
import softing.ubah4ukdev.mymapapplication.R
import softing.ubah4ukdev.mymapapplication.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment(R.layout.fragment_settings) {

    private val viewBinding: FragmentSettingsBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val settingsViewModel =
            ViewModelProvider(this)[SettingsViewModel::class.java]

        settingsViewModel.text.observe(viewLifecycleOwner) {
            viewBinding.textSettings.text = it
        }
    }
}