package minion.kim.wannab;


import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.EditText;

public class Splash extends Activity{
    Button login_button;
    EditText uid, pwd;
    HttpPost httppost;
    StringBuffer buf;
    HttpResponse resp;
    HttpClient httpclient;

    @Override
    protected void onCreate(Bundle savedInstanceState)c
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        /*
        Handler hd = new Handler();
        hd.postDelayed(new Runnable(){

            @Override
        public void run(){
                finish();
            }
        }, 3000);*/




    }
}
