package minion.kim.wannab;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class Splash extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        Handler hd = new Handler();
        hd.postDelayed(new Runnable() {

            @Override
            public void run() {

            }
        }, 2000);

        Intent intent = null;
        intent = new Intent(Splash.this, LoginActivity.class);

        if (intent != null) {
            Splash.this.startActivity(intent);
        }

        finish();
    }
}
