package com.jayjay.splasher;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.view.animation.Animation;
import android.os.Handler;
import android.content.Intent;
import android.view.animation.AnimationUtils;


public class Splash extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 1000;
    private ImageView image;
    private Animation fromLeft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // move to main activity after specific amount of time
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run()
            {
                Intent intent = new Intent(Splash.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_TIME_OUT);

         //Animation
        image = (ImageView) findViewById(R.id.splash);
        fromLeft = AnimationUtils.loadAnimation(this, R.anim.fromleft);
       image.setAnimation(fromLeft);
    }

}


