package softing.ubah4ukdev.mymapapplication.ui.map

import com.yandex.mapkit.geometry.Point
import kotlinx.coroutines.launch
import softing.ubah4ukdev.mymapapplication.domain.AppState
import softing.ubah4ukdev.mymapapplication.domain.models.MarkerDomain
import softing.ubah4ukdev.mymapapplication.domain.uscases.AddMarkerUseCase
import softing.ubah4ukdev.mymapapplication.domain.uscases.GetMarkersUseCase
import softing.ubah4ukdev.mymapapplication.ui.map.base.BaseMapViewModel

class MapViewModel(
    private val addMarkerUseCase: AddMarkerUseCase,
    private val getMarkersUseCase: GetMarkersUseCase
) : BaseMapViewModel() {

    override fun handleError(throwable: Throwable) {
        getOperationLiveData().postValue(AppState.Error(throwable))
    }

    override fun saveMarker(position: Point) =
        viewModelScopeCoroutine.launch {
            getOperationLiveData().postValue(AppState.Loading)
            addMarker(position)
        }

    private suspend fun addMarker(position: Point) {
        getOperationLiveData()
            .postValue(
                addMarkerUseCase.execute(
                    MarkerDomain(
                        latitude = position.latitude,
                        longitude = position.longitude
                    )
                )
            )
    }

    override fun getMarkers() =
        viewModelScopeCoroutine.launch {
            getOperationLiveData().postValue(AppState.Loading)
            getOperationLiveData()
                .postValue(getMarkersUseCase.execute())
        }
}