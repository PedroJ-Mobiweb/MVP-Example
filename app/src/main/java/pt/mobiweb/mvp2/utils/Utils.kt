package pt.mobiweb.mvp2.utils

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager

object Utils {

    fun replaceFragment(manager: FragmentManager, fragment: Fragment, frameId: Int) {
        val transaction = manager.beginTransaction()
        transaction.replace(frameId, fragment)
        transaction.commit()
    }
}