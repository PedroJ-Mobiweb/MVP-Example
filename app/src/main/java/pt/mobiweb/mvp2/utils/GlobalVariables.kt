package pt.mobiweb.mvp2.utils

import android.app.Application

class GlobalVariables : Application() {
    companion object {
        var connectionStatus = true
        var didOnLostTrigger = false
    }
}
