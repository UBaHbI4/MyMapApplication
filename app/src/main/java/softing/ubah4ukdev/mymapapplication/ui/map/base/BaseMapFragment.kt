package softing.ubah4ukdev.mymapapplication.ui.map.base

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.yandex.mapkit.Animation
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.map.MapObjectCollection
import com.yandex.runtime.image.ImageProvider
import org.koin.androidx.viewmodel.ext.android.viewModel
import softing.ubah4ukdev.mymapapplication.BuildConfig
import softing.ubah4ukdev.mymapapplication.R
import softing.ubah4ukdev.mymapapplication.databinding.FragmentMapBinding
import softing.ubah4ukdev.mymapapplication.ui.map.MapViewModel
import softing.ubah4ukdev.mymapapplication.ui.markers.MarkersFragment

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
    protected val viewBinding: FragmentMapBinding by viewBinding()
    protected var mapObjects: MapObjectCollection? = null
    protected val viewModel: MapViewModel by viewModel()
    private var locationManager: LocationManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        MapKitFactory.setApiKey(BuildConfig.YANDEX_KEY)
        MapKitFactory.initialize(requireContext())
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        verifyPermission()

        mapObjects = viewBinding.mapView.map.mapObjects

        viewBinding.getPermissionBtn.setOnClickListener { verifyPermission() }
    }

    @SuppressLint("MissingPermission")
    private val locationPermissionRequest = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        when {
            permissions.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false) -> {
                locationManager = requireActivity()
                    .getSystemService(Context.LOCATION_SERVICE) as LocationManager?

                locationManager
                    ?.requestLocationUpdates(
                        LocationManager.GPS_PROVIDER,
                        ZERO_LONG,
                        ZERO_FLOAT,
                        locationListener
                    )
                locationManager
                    ?.requestLocationUpdates(
                        LocationManager.NETWORK_PROVIDER,
                        ZERO_LONG,
                        ZERO_FLOAT,
                        locationListener
                    )
                useMap(true)
            }
            else -> {
                useMap(false)
            }
        }
    }

    private fun verifyPermission() {
        locationPermissionRequest.launch(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION))
    }

    private fun useMap(use: Boolean) =
        if (use) {
            viewBinding.permissionGroup.isVisible = false
            viewBinding.mapView.isVisible = true
        } else {
            viewBinding.permissionGroup.isVisible = true
            viewBinding.mapView.isVisible = false
        }

    private val locationListener: LocationListener = LocationListener { location ->
        viewBinding.mapView.map.move(
            CameraPosition(
                Point(location.latitude, location.longitude),
                DEF_ZOOM,
                ZERO_FLOAT,
                ZERO_FLOAT
            ),
            Animation(Animation.Type.SMOOTH, ZERO_FLOAT),
            null
        )

        /* Если в bundle есть координаты, значит мы выбрали переход к выбранной точке
        * Получаем координаты, перемещаем карту к точке и удаляем из bundle координаты, чтобы
        * можно было вернуться к стартовой точке на карте
        */
        getLocationToMove()

        mapObjects?.addPlacemark(
            Point(location.latitude, location.longitude),
            ImageProvider.fromResource(
                requireContext(),
                R.drawable.image_location_my
            )
        )

        stopUpdates()
    }

    private fun getLocationToMove() {
        val lon = NavHostFragment.findNavController(this).currentBackStackEntry?.savedStateHandle
            ?.getLiveData<Double>(MarkersFragment.KEY_MARKER_LON)
        val lat = NavHostFragment.findNavController(this).currentBackStackEntry?.savedStateHandle
            ?.getLiveData<Double>(MarkersFragment.KEY_MARKER_LAT)

        lon?.value?.let { lonn ->
            lat?.value?.let { latt ->
                viewBinding.mapView.map.move(
                    CameraPosition(
                        Point(latt, lonn),
                        DEF_ZOOM,
                        ZERO_FLOAT,
                        ZERO_FLOAT
                    ),
                    Animation(Animation.Type.SMOOTH, ZERO_FLOAT),
                    null
                )
                clearLocationInBundle()
            }
        }
    }

    private fun clearLocationInBundle() {
        val navController = NavHostFragment.findNavController(this)
        navController.currentBackStackEntry?.savedStateHandle?.set(
            MarkersFragment.KEY_MARKER_LON,
            null
        )
        navController.currentBackStackEntry?.savedStateHandle?.set(
            MarkersFragment.KEY_MARKER_LAT,
            null
        )
    }

    /* Отпишемся после первого получения. Можно было через locationManager.requestSingleUpdate,
    *  чтобы получить один раз, но метод Deprecated.
    */
    private fun stopUpdates() {
        locationManager?.removeUpdates(locationListener)
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
        const val DEF_ZOOM = 16.0f
        const val ZERO_FLOAT = 0f
        const val ZERO_LONG = 0L
    }
}