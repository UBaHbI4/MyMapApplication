package softing.ubah4ukdev.mymapapplication.ui.edit

import kotlinx.coroutines.launch
import softing.ubah4ukdev.mymapapplication.domain.AppState
import softing.ubah4ukdev.mymapapplication.domain.models.MarkerDomain
import softing.ubah4ukdev.mymapapplication.domain.uscases.GetMarkerByIdUseCase
import softing.ubah4ukdev.mymapapplication.domain.uscases.UpdateMarkerUseCase
import softing.ubah4ukdev.mymapapplication.ui.edit.base.BaseEditViewModel

class EditViewModel(
    private val updateMarkerUseCase: UpdateMarkerUseCase,
    private val getMarkerByIdUseCase: GetMarkerByIdUseCase
) : BaseEditViewModel() {

    override fun handleError(throwable: Throwable) {
        getOperationLiveData().postValue(AppState.Error(throwable))
    }

    override fun updateMarker(marker: MarkerDomain) =
        viewModelScopeCoroutine.launch {
            getOperationLiveData().postValue(AppState.Loading)
            getOperationLiveData()
                .postValue(
                    updateMarkerUseCase.execute(marker)
                )
        }

    override fun getMarker(markerId: Int) =
        viewModelScopeCoroutine.launch {
            getOperationLiveData().postValue(AppState.Loading)
            getOperationLiveData()
                .postValue(getMarkerByIdUseCase.execute(markerId))
        }
}