package dakyeong.chanho.cdfinedust.http;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface FineDustHttp {

    @GET("repos/{owner}/{repo}/contributors")
    Call<List<FineDustData>> repoFineDustDatas(
            @Path("owner") String owner,
            @Path("repo") String repo
    );

    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
