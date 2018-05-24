package dakyeong.chanho.cdfinedust.http;

public class FineDustData {

    String resultCode;
    String resultMsg;


    @Override
    public String toString(){
        return resultCode + "(" + resultMsg + ")";
    }
}
