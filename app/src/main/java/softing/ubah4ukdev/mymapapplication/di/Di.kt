package softing.ubah4ukdev.mymapapplication.di

import androidx.room.Room
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import softing.ubah4ukdev.mymapapplication.data.repository.CacheRepositoryImpl
import softing.ubah4ukdev.mymapapplication.data.repository.datasource.CacheDataSource
import softing.ubah4ukdev.mymapapplication.data.repository.datasource.CacheDataSourceImpl
import softing.ubah4ukdev.mymapapplication.data.storage.Storage
import softing.ubah4ukdev.mymapapplication.domain.repository.CacheRepository
import softing.ubah4ukdev.mymapapplication.domain.uscases.AddMarkerUseCase
import softing.ubah4ukdev.mymapapplication.domain.uscases.GetMarkersUseCase
import softing.ubah4ukdev.mymapapplication.domain.uscases.RemoveMarkerUseCase
import softing.ubah4ukdev.mymapapplication.domain.uscases.UpdateMarkerUseCase
import softing.ubah4ukdev.mymapapplication.ui.map.MapViewModel
import softing.ubah4ukdev.mymapapplication.ui.markers.MarkersViewModel

/**
 *   Project: MyMapApplication
 *
 *   Package: softing.ubah4ukdev.mymapapplication.di
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
object Di {

    private const val PERSISTED = "Persisted"
    private const val IN_MEMORY = "InMemory"
    private const val DB_NAME = "MapDataBase"

    fun viewModelModule() = module {
        viewModel() {
            MapViewModel(
                addMarkerUseCase = get(),
                getMarkersUseCase = get()
            )
        }

        viewModel() {
            MarkersViewModel()
        }
    }

    fun useCasesModule() = module {
        factory<AddMarkerUseCase> {
            AddMarkerUseCase(repository = get())
        }

        factory<GetMarkersUseCase> {
            GetMarkersUseCase(repository = get())
        }

        factory<RemoveMarkerUseCase> {
            RemoveMarkerUseCase(repository = get())
        }

        factory<UpdateMarkerUseCase> {
            UpdateMarkerUseCase(repository = get())
        }
    }

    fun repositoryModule() = module {
        single<CacheRepository>() {
            CacheRepositoryImpl(dataSource = get())
        }

        single<CacheDataSource> {
            CacheDataSourceImpl(storage = get(qualifier = named(PERSISTED)))
        }
    }

    fun storageModule() = module {
        single<Storage>(qualifier = named(PERSISTED)) {
            Room.databaseBuilder(androidContext(), Storage::class.java, DB_NAME)
                .fallbackToDestructiveMigration()
                .build()
        }

        single<Storage>(qualifier = named(IN_MEMORY)) {
            Room.inMemoryDatabaseBuilder(androidContext(), Storage::class.java)
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}