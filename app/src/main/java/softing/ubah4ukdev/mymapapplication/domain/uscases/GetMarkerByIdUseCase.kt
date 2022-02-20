package softing.ubah4ukdev.mymapapplication.domain.uscases

import softing.ubah4ukdev.mymapapplication.domain.AppState
import softing.ubah4ukdev.mymapapplication.domain.repository.CacheRepository

/**
 *   Project: MyMapApplication
 *
 *   Package: softing.ubah4ukdev.mymapapplication.domain.uscases
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
class GetMarkerByIdUseCase(private val repository: CacheRepository) {
    suspend fun execute(markerId: Int): AppState =
        repository.getMarkerById(markerId = markerId)
}