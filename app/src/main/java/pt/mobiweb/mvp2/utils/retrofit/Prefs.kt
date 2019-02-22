package pt.mobiweb.mvp2.utils.retrofit

import android.content.Context
import android.content.SharedPreferences

//SharedPreferences class
class Prefs (context: Context) {
    private val PREFS_FILENAME = "pt.mobiweb.mvp2.prefs"
    private val EXAMPLE = "Boolean example"
    private val prefs: SharedPreferences = context.getSharedPreferences(PREFS_FILENAME, Context.MODE_PRIVATE)

    //This is just and example
    var example: Boolean
        get() = prefs.getBoolean(EXAMPLE, false)
        set(value) = prefs.edit().putBoolean(EXAMPLE, value).apply()
}