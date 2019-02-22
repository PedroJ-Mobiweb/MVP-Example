package pt.mobiweb.mvp2.utils

import android.os.AsyncTask
import java.io.IOException
import java.net.InetSocketAddress
import java.net.Socket

//Used to check if there is a connection to the internet
class InternetCheck(private val onInternetChecked: (Boolean) -> Unit): AsyncTask<Void, Void, Boolean>() {

    init {
        execute()
    }

    override fun doInBackground(vararg voids: Void): Boolean {
        return try {
            val sock = Socket()
            sock.connect(InetSocketAddress("8.8.8.8", 53), 1500)
            sock.close()
            true
        } catch (e: IOException) {
            false
        }

    }

    override fun onPostExecute(internet: Boolean) {
        onInternetChecked(internet)
    }
}