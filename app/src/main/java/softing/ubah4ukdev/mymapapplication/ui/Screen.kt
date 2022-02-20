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
 *   2022.02.19
 *
 *   v1.0
 */
interface Screen {
    fun loading(isLoading: Boolean)
    fun showError(throwable: Throwable)
}