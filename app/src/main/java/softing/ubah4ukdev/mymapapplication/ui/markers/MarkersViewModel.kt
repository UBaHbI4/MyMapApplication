package softing.ubah4ukdev.mymapapplication.ui.markers

import kotlinx.coroutines.launch
import softing.ubah4ukdev.mymapapplication.domain.AppState
import softing.ubah4ukdev.mymapapplication.domain.uscases.GetMarkersUseCase
import softing.ubah4ukdev.mymapapplication.domain.uscases.RemoveMarkerUseCase
import softing.ubah4ukdev.mymapapplication.ui.markers.base.BaseMarkersViewModel

class MarkersViewModel(
    private val getMarkersUseCase: GetMarkersUseCase,
    private val removeMarkerUseCase: RemoveMarkerUseCase
) : BaseMarkersViewModel() {

    override fun getMarkers() =
        viewModelScopeCoroutine.launch {
            getOperationLiveData().postValue(AppState.Loading)
            getOperationLiveData()
                .postValue(getMarkersUseCase.execute())
        }

    override fun removeMarker(markerId: Int) =
        viewModelScopeCoroutine.launch {
            getOperationLiveData().postValue(AppState.Loading)

            getOperationLiveData()
                .postValue(removeMarkerUseCase.execute(markerId))

            getOperationLiveData()
                .postValue(getMarkersUseCase.execute())
        }

    override fun handleError(throwable: Throwable) {
        getOperationLiveData().postValue(AppState.Error(throwable))
    }
}