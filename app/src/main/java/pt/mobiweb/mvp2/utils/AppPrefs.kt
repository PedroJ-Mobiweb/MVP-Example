package pt.mobiweb.mvp2.utils

import android.content.Context
import android.content.SharedPreferences

object AppPrefs {
    private const val NAME = ""
    private const val MODE = Context.MODE_PRIVATE
    private lateinit var preferences: SharedPreferences

    private val IS_FIRST_RUN_PREFS = Pair("is_first_run", false)
    private val MY_VALUE = Pair("mValue", "default")

    fun init(context: Context) {
        preferences = context.getSharedPreferences(NAME, MODE)
    }

    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = edit()
        operation(editor)
        editor.apply()
    }

    // SP content
    var firstRun: Boolean
        get() = preferences.getBoolean(IS_FIRST_RUN_PREFS.first, IS_FIRST_RUN_PREFS.second)
        set(value) = preferences.edit { it.putBoolean(IS_FIRST_RUN_PREFS.first, value) }

    var mValue: String?
        get() = preferences.getString(MY_VALUE.first, MY_VALUE.second)
        set(value) = preferences.edit { it.putString(MY_VALUE.first, value) }
}