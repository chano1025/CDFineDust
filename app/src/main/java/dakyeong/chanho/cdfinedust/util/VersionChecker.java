package dakyeong.chanho.cdfinedust.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by sec on 2018-04-21.
 */

public class VersionChecker extends AsyncTask<Void, Void, String>{
    private static final String TAG = "VersionChecker";
    private Context context;

    private String versionName;
    private int versionCode;
    private String marketVersion;

    public VersionChecker(Context context){
        this.context = context;
    }

    public String getVersionName(){
        try{
            PackageInfo info = context.getPackageManager().getPackageInfo(context.getPackageName(), PackageManager.GET_META_DATA);
            versionName = info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionName;
    }

    public int getVersionCode(){
        try{
            PackageInfo info = context.getPackageManager().getPackageInfo(context.getPackageName(), PackageManager.GET_META_DATA);
            versionCode = info.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionCode;
    }

    @Override
    protected String doInBackground(Void... voids) {

        try {
            Document doc = Jsoup.connect("https://play.google.com/store/apps/details?id=dakyeong.chanho.cdfinedust").get();
            Elements version = doc.select(".content");
            for(Element v:version){
                if(v.attr("itemprop").equals("softwareVersion")){
                    marketVersion = v.text();
                }
            }
            return marketVersion;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String marketVersion) {

        PackageInfo info = null;
        try {
            info = context.getPackageManager().getPackageInfo(context.getPackageName(),0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        String version = info.versionName;
        this.marketVersion = marketVersion;

        if(!version.equals(marketVersion)){
            AlertDialog.Builder dialog = new AlertDialog.Builder(context);
            dialog.setMessage("업데이트 후 사용해주세요.")
                    .setCancelable(false)
                    .setPositiveButton("업데이트 바로가기", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {
                            Intent marketLanunch = new Intent(Intent.ACTION_VIEW);
                            marketLanunch.setData(Uri.parse("https://play.google.com/store/apps/details?id=dakyeong.chanho.cdfinedust"));
                            context.startActivity(marketLanunch);
                            //TODO finish 추가할것
                        }
                    });
            AlertDialog alert = dialog.create();
            alert.setTitle("안내");
            alert.show();
        }
        super.onPostExecute(marketVersion);
    }
}
