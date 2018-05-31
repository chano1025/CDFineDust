package dakyeong.chanho.cdfinedust.http;

import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface FineDustHttp {

    @GET("openapi/services/rest/ArpltnInforInqireSvc/getCtprvnRltmMesureDnsty?ServiceKey=gQYl4JUciJkbDq0Ssww2iuv95HA24DIEMNdrVGzNYPmZa14BMrD6qc6E0pM44KFtqIRU5iFD5S%2FQ828EZdiXWg%3D%3D")
    Call<FineDustData> getRepos(
            @Query("sidoName") String sidoName,
            @Query("pageNo") String pageNo,
            @Query("numOfRows") String numOfRows,
            @Query("ver") String ver,
            @Query("_returnType") String returnType);

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://openapi.airkorea.or.kr/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}

//GET으로 인자값을 넘길 때 @Path("station") 타입 변수명으로 넘기면 {station}로 넘어감
//GET으로 &파라미터=값 형식으로 넘길때는 @Query를 사용

//    @GET("openapi/services/rest/ArpltnInforInqireSvc/getMsrstnAcctoRltmMesureDnsty")
//    Call<ArrayList<JsonObject>> getRepos(
//            @Query("stationName") String stationName,
//            @Query("dataTerm") String month,
//            @Query("pageNo") String pageNo,
//            @Query("numOfRows") String numOfRows,
//            @Query("ServiceKey") String serviceKey,
//            @Query("ver") String ver);
// --->  "?stationName=종로구&dataTerm=month&pageNo=1&numOfRows=10&ServiceKey=gQYl4JUciJkbDq0Ssww2iuv95HA24DIEMNdrVGzNYPmZa14BMrD6qc6E0pM44KFtqIRU5iFD5S%2FQ828EZdiXWg%3D%3D&ver=1.3"

//"@GET("users/{user}/repos")
//    Call<ArrayList<JsonObject>> getListRepos(@Path("user") String id);
// --->  users/chano1025/repos
