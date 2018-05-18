package dakyeong.chanho.cdfinedust.service;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.IOException;
import java.util.List;

import dakyeong.chanho.cdfinedust.http.FineDustData;
import dakyeong.chanho.cdfinedust.http.FineDustHttp;
import retrofit2.Call;

public class FineDustService extends Service{
    private final String TAG = getClass().getSimpleName();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG,"onBind");
        return null;
    }

    @Override
    public void onCreate() {
        Log.d(TAG,"onCreate");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG,"onStartCommand");

        new Task().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.d(TAG,"onDestroy");
        super.onDestroy();
    }

    private class Task extends AsyncTask<Void,Void,String>{

        @Override
        protected String doInBackground(Void... params) {
            FineDustHttp fineDustHttp = FineDustHttp.retrofit.create(FineDustHttp.class);
            Call<List<FineDustData>> call = fineDustHttp.repoFineDustDatas("square","retrofit");

            try {
                return  call.execute().body().toString();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.d(TAG,s);
        }
    }
}
