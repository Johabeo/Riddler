package com.example.riddler

import android.content.Context
import android.graphics.Color
import com.okta.oidc.OIDCConfig
import com.okta.oidc.Okta.WebAuthBuilder
import com.okta.oidc.clients.web.WebAuthClient
import com.okta.oidc.storage.SharedPreferenceStorage
import java.util.concurrent.Executors


class AuthClient(context: Context) {

  companion object {

    private val FIRE_FOX = "org.mozilla.firefox"
    private val CHROME_BROWSER = "com.android.chrome"

    @Volatile
    private var webAuthClient: WebAuthClient? = null
    var INSTANCE: AuthClient? = null

    fun getWebAuthClient(context: Context?): WebAuthClient? {

      val config = OIDCConfig.Builder()
        .withJsonFile(context, R.raw.okta_oidc_config)
        .create()

      webAuthClient = WebAuthBuilder()
        .withConfig(config)
        .withContext(context)
        .withStorage(SharedPreferenceStorage(context))
        .withCallbackExecutor(Executors.newSingleThreadExecutor())
        .withTabColor(Color.BLUE)
        .supportedBrowsers(
          CHROME_BROWSER,
          FIRE_FOX
        )
        .setRequireHardwareBackedKeyStore(false) // required for emulators
        .create()

      if (INSTANCE == null) {
        INSTANCE = AuthClient(context!!)
      }
      return webAuthClient
    }
  }

}