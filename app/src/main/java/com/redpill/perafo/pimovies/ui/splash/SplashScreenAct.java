package com.redpill.perafo.pimovies.ui.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import com.redpill.perafo.pimovies.Config;
import com.redpill.perafo.pimovies.R;
import com.redpill.perafo.pimovies.ui.main.MainAct;

public class SplashScreenAct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestFullScreen();
        setContentView(R.layout.splashscreen_act);

        Handler handler = new Handler();
        handler.postDelayed(this::goToMain, Config.SPLASH_TIME);
    }

    private void goToMain(){
        Intent intent = new Intent(this, MainAct.class);
        this.startActivity(intent);
    }

    private void requestFullScreen(){
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
}
