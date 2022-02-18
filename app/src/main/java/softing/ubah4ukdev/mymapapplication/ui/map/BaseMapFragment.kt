package softing.ubah4ukdev.mymapapplication.ui.map

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.yandex.mapkit.Animation
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition
import softing.ubah4ukdev.mymapapplication.BuildConfig
import softing.ubah4ukdev.mymapapplication.R
import softing.ubah4ukdev.mymapapplication.databinding.FragmentMapBinding

/**
 *   Project: MyMapApplication
 *
 *   Package: softing.ubah4ukdev.mymapapplication.ui.map
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
abstract class BaseMapFragment : Fragment(R.layout.fragment_map) {
    private val viewBinding: FragmentMapBinding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        MapKitFactory.setApiKey(BuildConfig.YANDEX_KEY)
        MapKitFactory.initialize(requireContext())
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.mapView.map.isRotateGesturesEnabled = false
        viewBinding.mapView.map.move(
            CameraPosition(Point(DEF_LATITUDE, DEF_LONGITUDE), DEF_ZOOM, ZERO_FLOAT, ZERO_FLOAT),
            Animation(Animation.Type.SMOOTH, ZERO_FLOAT),
            null
        )
    }

    override fun onStart() {
        super.onStart()
        viewBinding.mapView.onStart()
        MapKitFactory.getInstance().onStart()
    }

    override fun onStop() {
        super.onStop()
        viewBinding.mapView.onStop()
        MapKitFactory.getInstance().onStop()
    }

    companion object {
        const val DEF_LATITUDE = 56.24844
        const val DEF_LONGITUDE = 90.5030683
        const val DEF_ZOOM = 16.0f
        const val ZERO_FLOAT = 0f
    }
}