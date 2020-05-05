package multi.android.gotcha.DB;

import android.os.AsyncTask;
import android.util.Log;
import android.content.Context;
import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.Map;
import multi.android.gotcha.R;

public class Task extends AsyncTask<Map<String, String>, Integer, String> {
    private String result = "";
    private String method;

    public static String ip = "14.47.113.175"; // 자신의 IP주소를 쓰시면 됩니다.

    @Override
    protected String doInBackground(Map<String, String>... maps) { // 내가 전송하고 싶은 파라미터
        method = maps[0].get("method");
        String result = "";

        switch (method) {
            case "searchIdInKakao":
                result = SearchIdInKakao(maps[0]);
                setResult(result);
                break;
            case "signUpComplete":
                result = SignUpComplete(maps[0]);
                break;
            case "signDown":
                result = signDown(maps[0]);
                break;
            case "saleInsert":
                result = saleInsert(maps[0]);
                break;
            case "modelSearch":
                result = modelSearch(maps[0]);
                setResult(result);
                break;
            case "fileUpload":
                result = fileUpload(maps[0]);
                break;
            case "myList":
                result = myList(maps[0]);
                Log.d("check", "myList실행됨");
                setResult(result);
                break;
            case "myListDelete":
                result = myListDelete(maps[0]);
                break;
            case "brandSearch":
                result = brandSearch(maps[0]);
                setResult(result);
                break;
            case "fuelSearch":
                result = fuelSearch(maps[0]);
                setResult(result);
                break;
            case "kmSearch":
                result = kmSearch(maps[0]);
                setResult(result);
                break;
            case "CommunityList":
                result = CommunityList(maps[0]); // body로 return된 것이 String 형식으로 이곳에 저장됨.
                Log.d("msg", "처음 게시판 조회 = " + result);
                setResult(result);
                break;
            case "CommunityListByTitle":
                result = CommunityListByTitle(maps[0]);
                Log.d("msg", "게시판 제목으로 조회" + result);
                setResult(result);
                break;
            case "CommunityListByNickname":
                result = CommunityListByNickname(maps[0]);
                Log.d("msg", "게시판 작성자로 조회" + result);
                setResult(result);
                break;
            case "CommunityListByContent":
                result = CommunityListByContent(maps[0]);
                Log.d("msg", "게시판 작성자로 조회" + result);
                setResult(result);
                break;
            case "CommunityNext":
                result = CommunityNext(maps[0]);
                Log.d("msg", "게시판 무한스크롤" + result);
                setResult(result);
                break;
            case "CommunityWrite":
                result = CommunityWrite(maps[0]);
                Log.d("msg", "게시판 글쓰기");
                break;
            case "CommunityDelete":
                result = CommunityDelete(maps[0]);
                break;
            case "HitUpdate":
                result = HitUpdate(maps[0]);
                Log.d("msg", "게시판 업데이트");
                break;
            case "ReplyList":
                result = ReplyList(maps[0]);
                Log.d("msg", "댓글 전체조회" + result);
                setResult(result);
                break;
            case "ReplyWrite":
                result = ReplyWrite(maps[0]);
                Log.d("msg", "댓글 등록 성공" + result);
                break;
            case "fileCommUpload":
                result = fileCommUpload(maps[0]);
                break;
        }
        return result;
    }

    @Override
    protected void onPostExecute(String s) {
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String SearchIdInKakao(Map<String, String> maps) {
        HttpClient.Builder http = new HttpClient.Builder("GET", "http://" + ip + ":8088/DBServer/searchIdInKakao");
        http.addAllParameters(maps);
        HttpClient post = http.create();
        post.request();
        int statusCode = post.getHttpStatusCode();
        String body = post.getBody();
        return body;
    }

    public String SignUpComplete(Map<String, String> maps) {
        HttpClient.Builder http = new HttpClient.Builder("GET", "http://" + ip + ":8088/DBServer/signUp");
        http.addAllParameters(maps);
        HttpClient post = http.create();
        post.request();
        int statusCode = post.getHttpStatusCode();
        String body = post.getBody();
        return body;
    }

    public String signDown(Map<String, String> maps) {
        HttpClient.Builder http = new HttpClient.Builder("GET", "http://" + ip + ":8088/DBServer/signDown");
        http.addAllParameters(maps);
        HttpClient post = http.create();
        post.request();
        int statusCode = post.getHttpStatusCode();
        String body = post.getBody();
        return body;
    }

    public String saleInsert(Map<String, String> maps) {
        HttpClient.Builder http = new HttpClient.Builder("GET", "http://" + ip + ":8088/DBServer/saleInsert");
        http.addAllParameters(maps);
        HttpClient post = http.create();
        post.request();
        int statusCode = post.getHttpStatusCode();
        String body = post.getBody();
        Log.d("check", "판매등록 테스트 성공");
        return body;
    }

    public String fileUpload(Map<String, String> maps) {
        Log.d("images2",maps.get("filename1"));
        Log.d("images2",maps.get("filename2"));
        Log.d("images2",maps.get("filename3"));
        Log.d("images2",maps.get("filename4"));
        try {
            File file1 = new File("/sdcard/Pictures/" + maps.get("filename1"));
            FileInputStream inputStream1 = new FileInputStream(file1);
            byte[] buffer1 = new byte[(int) file1.length()];
            for (int i = 0; i < buffer1.length; i++) {
                inputStream1.read(buffer1);
            }
            String b1 = fileUpload.byteArrayToBinaryString(buffer1);
            maps.put("file1", b1);
            inputStream1.close();

            File file2 = new File("/sdcard/Pictures/" + maps.get("filename2"));
            FileInputStream inputStream2 = new FileInputStream(file2);
            byte[] buffer2 = new byte[(int) file2.length()];
            for (int i = 0; i < buffer2.length; i++) {
                inputStream2.read(buffer2);
            }
            String b2 = fileUpload.byteArrayToBinaryString(buffer2);
            maps.put("file2", b2);
            inputStream2.close();

            File file3 = new File("/sdcard/Pictures/" + maps.get("filename3"));
            FileInputStream inputStream3 = new FileInputStream(file3);
            byte[] buffer3 = new byte[(int) file3.length()];
            for (int i = 0; i < buffer3.length; i++) {
                inputStream3.read(buffer3);
            }
            String b3 = fileUpload.byteArrayToBinaryString(buffer3);
            maps.put("file3", b3);
            inputStream3.close();

            File file4 = new File("/sdcard/Pictures/" + maps.get("filename4"));
            FileInputStream inputStream4 = new FileInputStream(file4);
            byte[] buffer4 = new byte[(int) file4.length()];
            for (int i = 0; i < buffer4.length; i++) {
                inputStream4.read(buffer4);
            }
            String b4 = fileUpload.byteArrayToBinaryString(buffer4);
            maps.put("file4", b4);
            inputStream4.close();

        } catch (Exception e) {
        }
        //HTTP 요청 준비
        HttpClient.Builder http = new HttpClient.Builder("GET", "http://" + ip + ":8088/DBServer/fileUpload");
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
    public String fileCommUpload(Map<String, String> maps) {
        Log.d("fileupload",maps.get("length"));
        for(int k=0; k<Integer.parseInt(maps.get("length"));k++){
        try {
            Log.d("fileupload",maps.keySet().toString());
            File file1 = new File("/sdcard/Pictures/" + maps.get("filename"+k));
            FileInputStream inputStream1 = new FileInputStream(file1);
            byte[] buffer1 = new byte[(int) file1.length()];
            for (int i = 0; i < buffer1.length; i++) {
                inputStream1.read(buffer1);
            }
            String b1 = fileUpload.byteArrayToBinaryString(buffer1);
            maps.put("file"+k, b1);
            inputStream1.close();
        } catch (Exception e) {
        }
        }
        //HTTP 요청 준비
        HttpClient.Builder http = new HttpClient.Builder("GET", "http://" + ip + ":8088/DBServer/fileCommUpload");
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
    public String myList(Map<String, String> maps) {
        HttpClient.Builder http = new HttpClient.Builder("GET", "http://" + ip + ":8088/DBServer/myList");
        http.addAllParameters(maps);
        HttpClient post = http.create();
        post.request();
        int statusCode = post.getHttpStatusCode();
        String body = post.getBody();
        return body;
    }

    public String myListDelete(Map<String, String> maps) {
        HttpClient.Builder http = new HttpClient.Builder("GET", "http://" + ip + ":8088/DBServer/myListDelete");
        http.addAllParameters(maps);
        HttpClient post = http.create();
        post.request();
        int statusCode = post.getHttpStatusCode();
        String body = post.getBody();
        return body;
    }

    public String modelSearch(Map<String, String> maps) {
        HttpClient.Builder http = new HttpClient.Builder("GET", "http://" + ip + ":8088/DBServer/modelSearch");
        http.addAllParameters(maps);
        HttpClient post = http.create();
        post.request();
        int statusCode = post.getHttpStatusCode();
        String body = post.getBody(); //Spring의 Controller에서 반환한 값. JSON 형식
        return body;
    }

    public String brandSearch(Map<String, String> maps) {
        HttpClient.Builder http = new HttpClient.Builder("GET", "http://" + ip + ":8088/DBServer/brandSearch");
        http.addAllParameters(maps);
        HttpClient post = http.create();
        post.request();
        int statusCode = post.getHttpStatusCode();
        String body = post.getBody();
        return body;
    }

    public String fuelSearch(Map<String, String> maps) {
        HttpClient.Builder http = new HttpClient.Builder("GET", "http://" + ip + ":8088/DBServer/fuelSearch");
        http.addAllParameters(maps);
        HttpClient post = http.create();
        post.request();
        int statusCode = post.getHttpStatusCode();
        String body = post.getBody();
        return body;
    }

    public String kmSearch(Map<String, String> maps) {
        HttpClient.Builder http = new HttpClient.Builder("GET", "http://" + ip + ":8088/DBServer/kmSearch");
        http.addAllParameters(maps);
        HttpClient post = http.create();
        post.request();
        int statusCode = post.getHttpStatusCode();
        String body = post.getBody();
        return body;
    }

    /*커뮤니티 시작*/
    //게시판 전체 조회
    public String CommunityList(Map<String, String> maps) {
        HttpClient.Builder http = new HttpClient.Builder("GET", "http://" + ip + ":8088/DBServer/CommunityList");
        http.addAllParameters(maps);
        HttpClient post = http.create();
        post.request();
        int statusCode = post.getHttpStatusCode();
        String body = post.getBody(); //Spring의 Controller에서 반환한 값. JSON 형식
        return body;
    }

    //게시판 제목으로 조회
    public String CommunityListByTitle(Map<String, String> maps) {
        HttpClient.Builder http = new HttpClient.Builder("GET", "http://" + ip + ":8088/DBServer/CommunityListByTitle");
        http.addAllParameters(maps);
        HttpClient post = http.create();
        post.request();
        int statusCode = post.getHttpStatusCode();
        String body = post.getBody(); //Spring의 Controller에서 반환한 값. JSON 형식
        return body;
    }

    //게시판 작성자로 조회
    public String CommunityListByNickname(Map<String, String> maps) {
        HttpClient.Builder http = new HttpClient.Builder("GET", "http://" + ip + ":8088/DBServer/CommunityListByNickname");
        http.addAllParameters(maps);
        HttpClient post = http.create();
        post.request();
        int statusCode = post.getHttpStatusCode();
        String body = post.getBody(); //Spring의 Controller에서 반환한 값. JSON 형식
        return body;

    }

    //게시판 내용으로 조회
    public String CommunityListByContent(Map<String, String> maps) {
        HttpClient.Builder http = new HttpClient.Builder("GET", "http://" + ip + ":8088/DBServer/CommunityListByContent");
        http.addAllParameters(maps);
        HttpClient post = http.create();
        post.request();
        int statusCode = post.getHttpStatusCode();
        String body = post.getBody(); //Spring의 Controller에서 반환한 값. JSON 형식
        return body;
    }

    //게시판 무한스크롤 조회
    public String CommunityNext(Map<String, String> maps) {
        HttpClient.Builder http = new HttpClient.Builder("GET", "http://" + ip + ":8088/DBServer/CommunityNext");
        http.addAllParameters(maps);
        HttpClient post = http.create();
        post.request();
        int statusCode = post.getHttpStatusCode();
        String body = post.getBody(); //Spring의 Controller에서 반환한 값. JSON 형식
        return body;
    }

    //커뮤니티 글 작성
    public String CommunityWrite(Map<String, String> maps) {
        HttpClient.Builder http = new HttpClient.Builder("GET", "http://" + ip + ":8088/DBServer/CommunityWrite");
        http.addAllParameters(maps);
        HttpClient post = http.create();
        post.request();
        int statusCode = post.getHttpStatusCode();
        String body = post.getBody(); //Spring의 Controller에서 반환한 값. JSON 형식
        return body;
    }

    //커뮤니티 글 삭제
    public String CommunityDelete(Map<String, String> maps) {
        HttpClient.Builder http = new HttpClient.Builder("GET", "http://" + ip + ":8088/DBServer/CommunityDelete");
        http.addAllParameters(maps);
        HttpClient post = http.create();
        post.request();
        int statusCode = post.getHttpStatusCode();
        String body = post.getBody(); //Spring의 Controller에서 반환한 값. JSON 형식
        return body;
    }

    //조회수 증가
    public String HitUpdate(Map<String, String> maps) {
        HttpClient.Builder http = new HttpClient.Builder("GET", "http://" + ip + ":8088/DBServer/HitUpdate");
        http.addAllParameters(maps);
        HttpClient post = http.create();
        post.request();
        int statusCode = post.getHttpStatusCode();
        String body = post.getBody(); //Spring의 Controller에서 반환한 값. JSON 형식
        return body;
    }

    /*커뮤니티 끝*/
    /*댓글 시작*/
    //댓글 조회
    public String ReplyList(Map<String, String> maps) {
        HttpClient.Builder http = new HttpClient.Builder("GET", "http://" + ip + ":8088/DBServer/ReplyList");
        http.addAllParameters(maps);
        HttpClient post = http.create();
        post.request();
        int statusCode = post.getHttpStatusCode();
        String body = post.getBody(); //Spring의 Controller에서 반환한 값. JSON 형식
        Log.d("msg", "댓글 조회 성공?");
        return body;
    }

    //댓글작성
    public String ReplyWrite(Map<String, String> maps) {
        HttpClient.Builder http = new HttpClient.Builder("GET", "http://" + ip + ":8088/DBServer/ReplyWrite");
        http.addAllParameters(maps);
        HttpClient post = http.create();
        post.request();
        int statusCode = post.getHttpStatusCode();
        String body = post.getBody();
        return body;
    }
}