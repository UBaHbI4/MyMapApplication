package softing.ubah4ukdev.mymapapplication.ui.edit

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import softing.ubah4ukdev.mymapapplication.databinding.FragmentEditBinding
import softing.ubah4ukdev.mymapapplication.domain.AppState
import softing.ubah4ukdev.mymapapplication.domain.models.MarkerDomain
import softing.ubah4ukdev.mymapapplication.domain.models.MarkerResult
import softing.ubah4ukdev.mymapapplication.domain.models.OperationResult
import softing.ubah4ukdev.mymapapplication.ui.Publisher
import softing.ubah4ukdev.mymapapplication.ui.Updater
import softing.ubah4ukdev.mymapapplication.ui.markers.MarkersFragment

/**
 *   Project: MyMapApplication
 *
 *   Package: softing.ubah4ukdev.mymapapplication.ui.edit
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
class EditFragment : BottomSheetDialogFragment() {

    private val viewBinding: FragmentEditBinding by viewBinding(CreateMethod.INFLATE)
    private val viewModel: EditViewModel by viewModel()
    private var publisher: Publisher? = null
    private val markerId: Int? by lazy { arguments?.getInt(MarkersFragment.KEY_MARKER_ID) }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        publisher = (requireContext() as Updater).getUpdater()
    }

    override fun onDetach() {
        super.onDetach()
        publisher = null
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.parseColor(TRANSPARENT_COLOR)))
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        markerId?.let {
            viewModel.getMarker(it)
        }

        viewModel.getOperationLiveData()
            .observe(viewLifecycleOwner) { res -> renderData(result = res) }

        initButtonClickListeners()
    }

    private fun initButtonClickListeners() {
        viewBinding.btnSave.setOnClickListener {
            markerId?.let {
                updateMarker(it)
            }
        }

        viewBinding.btnCancel.setOnClickListener {
            this.dismiss()
        }
    }

    private fun updateMarker(it: Int) {
        viewModel.updateMarker(
            MarkerDomain(
                markerId = it,
                latitude = viewBinding.lanValue.text.toString().toDouble(),
                longitude = viewBinding.lonValue.text.toString().toDouble(),
                visible = true,
                title = viewBinding.titleValue.text.toString(),
                description = viewBinding.descriptionValue.text.toString()
            )
        )
    }

    private fun renderData(result: AppState) {
        when (result) {
            is AppState.Error -> {}
            is AppState.Loading -> {}
            is AppState.Success -> renderSuccess(result)
        }
    }

    private fun renderSuccess(result: AppState.Success) {
        when (val marker = result.data) {
            is MarkerResult -> {
                renderData(marker)
            }
            is OperationResult -> {
                publisher?.startUpdate()
                this.dismiss()
            }
        }
    }

    private fun renderData(marker: MarkerResult) {
        with(viewBinding) {
            titleValue.setText(marker.result.title)
            descriptionValue.setText(marker.result.description)
            lonValue.setText(marker.result.longitude.toString())
            lanValue.setText(marker.result.latitude.toString())
        }
    }

    companion object {
        const val TAG = "EditFragmentBottomSheet"
        const val TRANSPARENT_COLOR = "#00000000"
        fun newInstance() = EditFragment()
    }
}