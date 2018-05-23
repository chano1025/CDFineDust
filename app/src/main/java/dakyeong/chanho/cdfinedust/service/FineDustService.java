package dakyeong.chanho.cdfinedust.service;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import dakyeong.chanho.cdfinedust.http.FineDustData;
import dakyeong.chanho.cdfinedust.http.FineDustHttp;
import retrofit2.Call;
import retrofit2.Response;

public class FineDustService extends Service{
    private final String TAG = getClass().getSimpleName();
    private boolean flag = true;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG,"onBind");
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG,"onCreate");
        FineDustHttp fineDustHttp = FineDustHttp.retrofit.create(FineDustHttp.class);
        final Call<ArrayList<JsonObject>> call = fineDustHttp.getRepos("chano1025");
        new Task().execute(call);

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG,"onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.d(TAG,"onDestroy");
        super.onDestroy();
    }

    private class Task extends AsyncTask<Call,Void,String>{

        @Override
        protected String doInBackground(Call... params) {
            try {
                while (flag) {
                    Call<ArrayList<JsonObject>> call = params[0];
                    Response<ArrayList<JsonObject>> response = call.clone().execute();
                    Log.d(TAG, response.body().toString());
                    Thread.sleep(1000);
                }
            } catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }
    }
}
