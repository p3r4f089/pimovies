package com.redpill.perafo.pimovies.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.view.Window
import android.view.WindowManager

import com.redpill.perafo.pimovies.Config
import com.redpill.perafo.pimovies.R
import com.redpill.perafo.pimovies.ui.main.MainAct

class SplashScreenAct : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestFullScreen()
        setContentView(R.layout.splashscreen_act)

        val handler = Handler()
        handler.postDelayed({ this.goToMain() }, Config.SPLASH_TIME.toLong())
    }

    private fun goToMain() {
        val intent = Intent(this, MainAct::class.java)
        this.startActivity(intent)
    }

    private fun requestFullScreen() {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN)
    }
}
