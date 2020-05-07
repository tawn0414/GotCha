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

public class Freeboard_DetailActivity extends AppCompatActivity {
    TextView freeboard_detailTitle;
    ImageView freeboard_detailImage;
    TextView freeboard_detailContent;
    EditText freeboard_detailCommentWrite;
    Button freeboard_detailCommentBtn;
    List<ReplyVO> replylist;
    CommentAdapter adapter;
    RecyclerView freeboard_detailComment;
    /*데이터들*/
    String board_num = null;
    String Nickname;
    String image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.community_listdetail);
        /*findViewById 시작*/
        freeboard_detailTitle = findViewById(R.id.freeboard_detailTitle);
        freeboard_detailImage = findViewById(R.id.freeboard_detailImage);
        freeboard_detailContent = findViewById(R.id.freeboard_detailContent);
        freeboard_detailCommentWrite = findViewById(R.id.freeboard_detailCommentWrite);
        freeboard_detailCommentBtn = findViewById(R.id.freeboard_detailCommentBtn);
        freeboard_detailComment = findViewById(R.id.freeboard_detailComment);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        /*findViewById 끝*/
        //Date currentTime = Calendar.getInstance().getTime();
        //final String date_text = new SimpleDateFormat("yyyy년 MM월 dd일 hh:mm", Locale.getDefault()).format(currentTime);
        Intent intent = getIntent();

        board_num = intent.getStringExtra("board_num");
        String board_content = intent.getStringExtra("board_content");
        String board_title = intent.getStringExtra("board_title");
        Nickname = intent.getStringExtra("Nickname");
        image = intent.getStringExtra("image");
        freeboard_detailContent.setText(board_content);
        freeboard_detailTitle.setText(board_title);

        setReplylist();
        setAdapter();
        String link = "http://" + getString(R.string.ip) + ":8088/DBServer/images/"+ image;
        Glide.with(this).load(link).into(freeboard_detailImage);
        Log.d("fileupload", image);

        freeboard_detailCommentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //댓글 등록 sql문
                if (freeboard_detailCommentWrite.getText().toString().equals("")){
                    Toast.makeText(Freeboard_DetailActivity.this,"댓글창이 비었습니다.",Toast.LENGTH_LONG).show();

                }else{
                    String str = freeboard_detailCommentWrite.getText().toString();
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
                    freeboard_detailCommentWrite.setText("");
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
        /*댓글 데이터 가져오기 끝*/
    }
    public void setAdapter(){
        adapter = new CommentAdapter(this, R.layout.community_row_comment, replylist);
        freeboard_detailComment.addItemDecoration(new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL));
        LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        freeboard_detailComment.setLayoutManager(manager);
        freeboard_detailComment.setAdapter(adapter);
    }
}
