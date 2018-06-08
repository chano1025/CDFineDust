package dakyeong.chanho.cdfinedust.service;

import android.app.Service;
import android.content.Intent;
import android.nfc.Tag;
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
import retrofit2.Callback;
import retrofit2.Response;

public class FineDustService extends Service{
    private final String TAG = getClass().getSimpleName();
    private String sidoName = "대구";
    private String pageNo = "1";
    private String numOfRows = "10";
    private String ver = "1.3";
    private String returnType = "json";
    private FineDustData fineDustData;

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
        final Call<FineDustData> call = fineDustHttp.getRepos(sidoName,pageNo,numOfRows,ver,returnType);

        call.enqueue(new Callback<FineDustData>() {
            @Override
            public void onResponse(Call<FineDustData> call, Response<FineDustData> response) {
                Log.d("Retrofit", response.toString());
                if (response.body() != null) {
                    Log.e(TAG, response.body().toString());
                }
                fineDustData = response.body();
                float co = fineDustData.getList().get(0).getCoValue();

            }

            @Override
            public void onFailure(Call<FineDustData> call, Throwable t) {
                Log.e("Err", t.getMessage());
            }
        });




       // new Task().execute(call);

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

//    private class Task extends AsyncTask<Call,Void,String>{
//
//        @Override
//        protected String doInBackground(Call... params) {
//            try {
//                Call<ArrayList<FineDustData>> call = params[0];
//                Response<ArrayList<FineDustData>> response = call.clone().execute();
//                Log.e(TAG, response.body().toString());
//            } catch (Exception e){
//                e.printStackTrace();
//            }
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(String s) {
//            super.onPostExecute(s);
//        }
//    }
}
