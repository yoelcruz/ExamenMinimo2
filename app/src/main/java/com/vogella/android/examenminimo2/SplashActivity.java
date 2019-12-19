package com.vogella.android.examenminimo2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

public class SplashActivity extends Activity {
    /* Duration of wait */
    private final int SPLASH_DISPLAY_LENGTH = 3000;

    /* Called when the activity is first created. */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.splash1);

        final String PREF_FILE_NAME = "datos";
        final SharedPreferences preferences = getSharedPreferences(PREF_FILE_NAME, MODE_PRIVATE);

         //New Handler to start the Menu-Activity
         // and close this Splash-Screen after some seconds.
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                 //Create an Intent that will start the Menu-Activity.
                SharedPreferences preferencias = getSharedPreferences("datos", Context.MODE_PRIVATE);
                //Log.d("***********************MIN2", preferences.getString("nombreUsuario", null));

                boolean autenticado = preferences.getString("nombreUsuario", null)!=null;
                if (autenticado){
                    Intent mainIntent = new Intent(SplashActivity.this, MainActivity.class);
                    SplashActivity.this.startActivity(mainIntent);
                    SplashActivity.this.finish();
                }
                else{
                    Intent mainIntent = new Intent(SplashActivity.this, Login.class);
                    SplashActivity.this.startActivity(mainIntent);
                    SplashActivity.this.finish();
                }
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}
