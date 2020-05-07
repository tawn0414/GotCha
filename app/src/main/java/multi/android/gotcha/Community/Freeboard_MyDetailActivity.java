package multi.android.gotcha.Community;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import multi.android.gotcha.DB.ReplyVO;
import multi.android.gotcha.DB.Task;
import multi.android.gotcha.R;

public class Freeboard_MyDetailActivity extends AppCompatActivity {
    TextView freeboard_mydetailTitle;
    ImageView freeboard_mydetailImage;
    TextView freeboard_mydetailContent;
    EditText freeboard_myCommentWrite;
    Button freeboard_mylistMyDeleteBtn;
    Button freeboard_myCommentSavebtn;
    String board_num = null;
    List<ReplyVO> replylist;
    CommentAdapter adapter;
    RecyclerView freeboard_mydetailComment;
    String Nickname;
    String image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.community_listmydetail);
        /*findViewById 시작*/
        freeboard_mydetailTitle = findViewById(R.id.freeboard_mydetailTitle);
        freeboard_mydetailImage = findViewById(R.id.freeboard_mydetailImage);
        freeboard_mydetailContent = findViewById(R.id.freeboard_mydetailContent);
        freeboard_myCommentWrite = findViewById(R.id.freeboard_myCommentWrite);
        freeboard_mylistMyDeleteBtn = findViewById(R.id.freeboard_mylistMyDeleteBtn);
        freeboard_myCommentSavebtn = findViewById(R.id.freeboard_myCommentSavebtn);
        freeboard_mydetailComment = findViewById(R.id.freeboard_mydetalComment);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        /*findViewById 끝*/

        Intent intent = getIntent();
        board_num = intent.getStringExtra("board_num");
        String board_content = intent.getStringExtra("board_content");
        String board_title = intent.getStringExtra("board_title");
        Nickname = intent.getStringExtra("Nickname");
        image = intent.getStringExtra("image");
        freeboard_mydetailContent.setText(board_content);
        freeboard_mydetailTitle.setText(board_title);

        setReplylist();
        setAdapter();
        String link = "http://" + getString(R.string.ip) + ":8088/DBServer/images/"+ image;
        Glide.with(this).load(link).into(freeboard_mydetailImage);

        freeboard_mylistMyDeleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*DB연동 시작*/
                //게시판 상세보기가 작동됬을 때 게시판 번호를 지울 수 있게
                Map<String, String> map = new HashMap<String, String>();
                map.put("method", "CommunityDelete");
                map.put("board_NUM",board_num);
                //map.put("board_REGDATE",date_text);
                Task networkTask = new Task();
                networkTask.execute(map);
                /*DB연동 끝*/
                finish();

            }
        });
        freeboard_myCommentSavebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //댓글 등록 sql문
                if (freeboard_myCommentWrite.getText().toString().equals("")){
                    Toast.makeText(Freeboard_MyDetailActivity.this,"댓글창이 비었습니다.",Toast.LENGTH_LONG).show();

                }else{
                    String str = freeboard_myCommentWrite.getText().toString();
                    Log.d("msg","등록될 댓글 ===== "+str);
                    Gson gson = new Gson();
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("method", "ReplyWrite");
                    map.put("board_NUM", board_num);
                    map.put("mEM_NICKNAME", Nickname);
                    map.put("reply_CONTENT", str);
                    Task networkTask = new Task();
                    networkTask.execute(map);
                    setReplylist();
                    setAdapter();
                    freeboard_myCommentWrite.setText("");
                    //adapter.notifyDataSetChanged();
                }
            }
        });

    }

    public void setReplylist(){
        /*댓글 데이터 가져오기 시작*/
        Gson gson = new Gson();
        Map<String, String> map = new HashMap<String, String>();
        map.put("method", "ReplyList");
        map.put("board_NUM", board_num);
        Task networkTask = new Task();
        networkTask.execute(map);
        while(networkTask.getResult().equals("")){
            SystemClock.sleep(10);
        }
        ReplyVO[] array = gson.fromJson(networkTask.getResult(), ReplyVO[].class);
        replylist = Arrays.asList(array);
        Log.d("msg","나의 댓글 리스트 ====="+replylist);
    }

    public void setAdapter(){
        adapter = new CommentAdapter(this, R.layout.community_row_comment, replylist);
        freeboard_mydetailComment.addItemDecoration(new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL));
        LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        freeboard_mydetailComment.setLayoutManager(manager);
        freeboard_mydetailComment.setAdapter(adapter);
    }
}
