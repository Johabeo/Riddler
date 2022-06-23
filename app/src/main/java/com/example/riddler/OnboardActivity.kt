package com.example.riddler

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.okta.oidc.AuthorizationStatus
import com.okta.oidc.ResultCallback
import com.okta.oidc.clients.sessions.SessionClient
import com.okta.oidc.clients.web.WebAuthClient
import com.okta.oidc.util.AuthorizationException
import java.util.logging.Logger


class OnboardActivity : AppCompatActivity() {
    private val logger: Logger = Logger.getLogger("OnboardActivity")
    private var client: WebAuthClient? = null
    private var sessionClient: SessionClient? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboard)

        findViewById<View>(R.id.browser_sign_in_btn).setOnClickListener { v: View? ->
            client!!.signIn(
                this,
                null
            )
        }
        findViewById<View>(R.id.logout_btn).setOnClickListener { v: View? ->
            client!!.signOutOfOkta(
                this
            )
        }
        client = AuthClient.getWebAuthClient(this)


        sessionClient = client!!.sessionClient

        if(sessionClient != null){
            println("AUTH STATUS: " + sessionClient!!.isAuthenticated)
        }

        client!!.registerCallback(object : ResultCallback<AuthorizationStatus, AuthorizationException> {
            override fun onSuccess(status: AuthorizationStatus) {
                if (status == AuthorizationStatus.AUTHORIZED) {
                    // client is authorized.
                    logger.info("Auth success")
                    runOnUiThread {
                        findViewById<View>(R.id.browser_sign_in_btn).visibility = View.GONE
                        findViewById<View>(R.id.logout_btn).visibility = View.VISIBLE
                    }

                } else if (status == AuthorizationStatus.SIGNED_OUT) {
                    // this only clears the browser session.
                    logger.info("Sign out success")
                    runOnUiThread {
                        findViewById<View>(R.id.browser_sign_in_btn).visibility = View.VISIBLE
                        findViewById<View>(R.id.logout_btn).visibility = View.GONE
                    }
                }
            }

            override fun onCancel() {
                // authorization canceled
                logger.info("Auth cancelled")
            }

            override fun onError(msg: String?, exception: AuthorizationException?) {
                logger.severe(String.format("Error: %s : %s", exception?.error, exception?.errorDescription))
            }
        }, this)
    }
}