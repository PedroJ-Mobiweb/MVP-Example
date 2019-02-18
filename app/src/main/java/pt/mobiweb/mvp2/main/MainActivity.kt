package pt.mobiweb.mvp2.main

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import pt.mobiweb.mvp2.R
import pt.mobiweb.mvp2.sign_in.SignInActivity

/**
 * Displays the Main Screen
 */

class MainActivity : AppCompatActivity(), MainContract.View {

    //Variables
    private lateinit var mainPresenter: MainPresenter

    //Overrides
    override fun navigateToSignInScreen() {
        val intent = Intent(applicationContext, SignInActivity::class.java)
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Init
        mainPresenter = MainPresenter(this)

        //Event
        main_btn_sign_in.setOnClickListener {
            mainPresenter.signInButtonClick()
        }
    }
}
