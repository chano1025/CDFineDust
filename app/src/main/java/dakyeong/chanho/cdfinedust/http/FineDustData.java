package dakyeong.chanho.cdfinedust.http;

public class FineDustData {

    String login;
    String html_url;

    int contributions;

    @Override
    public String toString(){
        return login + "(" + contributions + ")";
    }
}
