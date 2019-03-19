package pt.mobiweb.mvp2.sign_in

import android.content.Intent
import android.os.Bundle
import android.view.View.VISIBLE
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_sign_in.*
import pt.mobiweb.mvp2.R
import pt.mobiweb.mvp2.home.HomeActivity
import pt.mobiweb.mvp2.utils.AppPrefs
import pt.mobiweb.mvp2.utils.BaseActivity
import pt.mobiweb.mvp2.utils.GlobalVariables

class SignInActivity : BaseActivity(), SignInContract.View {

    //Variables
    private lateinit var signInPresenter: SignInPresenter

    //Overrides
    override fun onSuccess(message: String) {
        //Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }

    override fun onError(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }

    override fun navigateToSuccessfulSignIn() {
        val intent = Intent(applicationContext, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        //Init
        signInPresenter = SignInPresenter(this)

        //Event
        signIn_btn_signIn.setOnClickListener {
            signInPresenter.onSignIn(signIn_et_email.text.toString(), signIn_et_password.text.toString())
        }

        AppPrefs.mValue = "this is a test"
    }

    override fun onResume() {
        super.onResume()
        if (!GlobalVariables.connectionStatus)
            include_connection_snackbar.visibility = VISIBLE
    }
}
