package pt.mobiweb.mvp2.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View.VISIBLE
import kotlinx.android.synthetic.main.activity_main.*
import pt.mobiweb.mvp2.R
import pt.mobiweb.mvp2.sign_in.SignInActivity
import pt.mobiweb.mvp2.utils.AppPrefs
import pt.mobiweb.mvp2.utils.BaseActivity
import pt.mobiweb.mvp2.utils.GlobalVariables

class MainActivity : BaseActivity(), MainContract.View {

    //Variables
    private lateinit var mainPresenter: MainPresenter

    //Overrides
    override fun navigateToSignInScreen() {
        val intent = Intent(applicationContext, SignInActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Init
        mainPresenter = MainPresenter(this)

        AppPrefs.init(this)

        if (!AppPrefs.firstRun) {
            AppPrefs.firstRun = true
        }

        Log.d("firstRun", AppPrefs.firstRun.toString())
        Log.d("mValue", AppPrefs.mValue)

        //Event
        main_btn_sign_in.setOnClickListener { mainPresenter.signInButtonClick() }
    }

    override fun onResume() {
        super.onResume()
        if (!GlobalVariables.connectionStatus)
            include_connection_snackbar.visibility = VISIBLE
    }
}
