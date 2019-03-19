package pt.mobiweb.mvp2.utils

import android.animation.Animator
import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import kotlinx.android.synthetic.main.include_internet_connection_snackbar.view.*
import pt.mobiweb.mvp2.R

@SuppressLint("Registered")
open class BaseActivity : AppCompatActivity(), ConnectivityReceiver.ConnectivityReceiverListener {

    private lateinit var bReceiver: BroadcastReceiver

    override fun onStart() {
        super.onStart()

        bReceiver = ConnectivityReceiver()
        registerReceiver(
            bReceiver,
            IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        )
        ConnectivityReceiver.connectivityReceiverListener = this
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(bReceiver)
    }

    override fun onNetworkConnectionChanged(isConnected: Boolean) {
        handleConnectivitySnackbarView(isConnected)
    }

    private fun handleConnectivitySnackbarView(isConnected: Boolean) {
        val includeSnackbar = window.decorView.rootView.findViewById<View>(R.id.include_connection_snackbar)

        if (isConnected && GlobalVariables.didOnLostTrigger) {
            GlobalVariables.connectionStatus = true
            GlobalVariables.didOnLostTrigger = false
            RxBus.publish(RxEvent.EventBackOnline(true))
            includeSnackbar.include_connection_snackbar_text.text = resources.getText(R.string.include_online)
            includeSnackbar.setBackgroundColor(
                ContextCompat.getColor(
                    window.decorView.rootView.context,
                    R.color.include_snackbar_bg_online
                )
            )

            includeSnackbar.animate()
                .setDuration(800)
                .alpha(1f)
                .setListener(object : Animator.AnimatorListener {
                override fun onAnimationRepeat(animation: Animator?) { return }

                override fun onAnimationEnd(animation: Animator?) { includeSnackbar.visibility = GONE }

                override fun onAnimationCancel(animation: Animator?) { return }

                override fun onAnimationStart(animation: Animator?) { return }
            })

        } else if (!isConnected) {
            GlobalVariables.connectionStatus = false
            GlobalVariables.didOnLostTrigger = true
            includeSnackbar.include_connection_snackbar_text.text = resources.getText(R.string.include_offline)
            includeSnackbar.setBackgroundColor(
                ContextCompat.getColor(
                    window.decorView.rootView.context,
                    R.color.include_snackbar_bg_offline
                )
            )
            includeSnackbar.visibility = VISIBLE
        }
    }
}