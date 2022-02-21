package softing.ubah4ukdev.mymapapplication.domain

import softing.ubah4ukdev.mymapapplication.domain.models.DataResult

/**
 *   Project: MyMapApplication
 *
 *   Package: softing.ubah4ukdev.mymapapplication.domain
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
sealed class AppState {
    data class Success(val data: DataResult) : AppState()
    data class Error(val error: Throwable) : AppState()
    object Loading : AppState()
}