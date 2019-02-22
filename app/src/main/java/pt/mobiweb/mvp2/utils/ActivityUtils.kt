package pt.mobiweb.mvp2.utils

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager

class ActivityUtils {
    companion object {
        fun addFragmentToActivity(manager: FragmentManager, fragment: Fragment, frameId: Int) {
            val transaction = manager.beginTransaction()
            transaction.add(frameId, fragment)
            transaction.commit()
        }
    }
}