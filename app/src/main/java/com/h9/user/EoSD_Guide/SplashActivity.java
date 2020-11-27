/** The name of my app package how it would be shown to an android device**/
package com.h9.user.EoSD_Guide;

/** Imports**/
/** Importing android.content.intent allows me to use it in the code to start new activities from this one**/
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatDelegate;

public class SplashActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /** Set night mode for this screen (overrides switch)**/
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        /** Set the content view to the splashscreen.xml file in the layout folder**/
        setContentView(R.layout.splashscreen);

        /** Set secondsdelayed value to count in seconds**/
        int secondsDelayed = 1;
        new Handler().postDelayed(new Runnable() {
            public void run() {
                /** Start mainactivity after secondsdelayed is over**/
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
            /**Set secondsdelayed in miliseconds (1000 = 1 second)**/

            /**Too long = User thinks app is frozen**/
            /**Too Short = User may not be able to read everything on the splashscreen**/
        }, secondsDelayed * 2750);
    }
}