package softing.ubah4ukdev.mymapapplication.ui

/**
 *   Project: MyMapApplication
 *
 *   Package: softing.ubah4ukdev.mymapapplication.ui
 *
 *   Created by Ivan Sheynmaer
 *
 *   Description:
 *
 *
 *   2022.02.20
 *
 *   v1.0
 */
class Publisher {
    private val observers: MutableList<UpdateObserver>

    fun subscribe(observer: UpdateObserver) {
        observers.add(observer)
    }

    fun unsubscribe(observer: UpdateObserver) {
        observers.remove(observer)
    }

    fun startUpdate() {
        for (observer in observers) {
            observer.updateMarkers()
        }
    }

    init {
        observers = ArrayList<UpdateObserver>()
    }
}