package multi.android.gotcha.DB;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;

import java.util.List;
import java.util.Map;

public class Task extends AsyncTask<Map<String, String>, Integer, String> {
    private String result="";
    private String method;
    public static String ip = "70.12.224.117"; // 자신의 IP주소를 쓰시면 됩니다.

    @Override
    protected String doInBackground(Map<String, String>... maps) { // 내가 전송하고 싶은 파라미터
        method=maps[0].get("method");
        String result = "";
        Gson gson = new Gson();
        switch (method){
            case "searchIdInKakao":
                result = SearchIdInKakao(maps[0]);
                MemberVO data = gson.fromJson(result, MemberVO.class);
                setResult(data.getStatus());
                break;
            case "signUpComplete":
                result = SignUpComplete(maps[0]);
                break;
            case "signDown":
                result = signDown(maps[0]);
                break;
        }
        return result;
    }

    @Override
    protected void onPostExecute(String s) { //서블릿으로부터 값을 받을 함수
        /*Gson gson = new Gson();
        if(method.equals("SearchIdInKakao")) {
            MemberVO data = gson.fromJson(s, MemberVO.class);
            setResult(data.getStatus());
        }*/
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String SearchIdInKakao(Map<String, String> maps) {

        //HTTP 요청 준비
        HttpClient.Builder http = new HttpClient.Builder("GET", "http://" + ip + ":8088/DBServer/searchIdInKakao");
        //Parameter 전송
        http.addAllParameters(maps);
        //HTTP 요청 전송
        HttpClient post = http.create();
        post.request();
        //응답 상태 코드
        int statusCode = post.getHttpStatusCode();
        //응답 본문
        String body = post.getBody(); //Spring의 Controller에서 반환한 값. JSON 형식

        return body;
    }
    public String SignUpComplete(Map<String, String> maps){
        //HTTP 요청 준비
        HttpClient.Builder http = new HttpClient.Builder("GET", "http://" + ip + ":8088/DBServer/signUp");
        //Parameter 전송
        http.addAllParameters(maps);
        //HTTP 요청 전송
        HttpClient post = http.create();
        post.request();
        //응답 상태 코드
        int statusCode = post.getHttpStatusCode();
        //응답 본문
        String body = post.getBody(); //Spring의 Controller에서 반환한 값. JSON 형식
        return body;
    }
    public String signDown(Map<String, String> maps){
        //HTTP 요청 준비
        HttpClient.Builder http = new HttpClient.Builder("GET", "http://" + ip + ":8088/DBServer/signDown");
        //Parameter 전송
        http.addAllParameters(maps);
        //HTTP 요청 전송
        HttpClient post = http.create();
        post.request();
        //응답 상태 코드
        int statusCode = post.getHttpStatusCode();
        //응답 본문
        String body = post.getBody(); //Spring의 Controller에서 반환한 값. JSON 형식
        Log.d("testtest","탈퇴"+"TAST성공");
        return body;
    }
}