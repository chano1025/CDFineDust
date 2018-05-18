package dakyeong.chanho.cdfinedust.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.WebView;

import java.io.IOException;
import java.util.List;

import dakyeong.chanho.cdfinedust.R;
import dakyeong.chanho.cdfinedust.http.FineDustData;
import dakyeong.chanho.cdfinedust.http.FineDustHttp;
import dakyeong.chanho.cdfinedust.service.FineDustService;
import retrofit2.Call;

public class MainActivity extends AppCompatActivity {
    private final String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startService();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void startService(){
        Intent intent = new Intent(getApplicationContext(), FineDustService.class);
        startService(intent);
    }

    private void stopService(){
        Intent intent = new Intent(getApplicationContext(), FineDustService.class);
        stopService(intent);
    }

}
