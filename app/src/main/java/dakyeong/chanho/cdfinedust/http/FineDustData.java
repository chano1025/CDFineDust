package dakyeong.chanho.cdfinedust.http;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Headers;

public class FineDustData {

    public List<list> list = new ArrayList<>();
    public List<list> getList() { return list; }

    public class list{
        @SerializedName("coValue") float coValue;

        public float getCoValue() {
            return coValue;
        }
    }
}
