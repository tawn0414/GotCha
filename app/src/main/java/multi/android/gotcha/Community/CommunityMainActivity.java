package multi.android.gotcha.Community;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import multi.android.gotcha.DB.CommunityVO;
import multi.android.gotcha.DB.Task;
import multi.android.gotcha.Home;
import multi.android.gotcha.MainActivity;
import multi.android.gotcha.R;
import multi.android.gotcha.member.login.MemberInfo;
import multi.android.gotcha.sale.mysale_list;

public class CommunityMainActivity extends AppCompatActivity{
    RecyclerView freeboard_listRecycle;
    FreeboardAdapter adapter;
    ImageButton freeboard_listSearchbtn;
    ImageButton freeboard_listbtn;
    Button forwardbtn;
    Button backwardbtn;
    EditText freeboard_listallSearch;
    Spinner freeboard_listallSpinner;
    ProgressBar progressBar;
    List<CommunityVO> list;
    MainActivity mainActivity = new MainActivity();
    int k = 19;
    int count = 0;
    int lastPage = 0;
    int middlePage = 0;
    Intent intent =null;
    /*네비게이션 바*/
    Home home = new Home();
    DrawerLayout mainLayout;
    MemberInfo memberInfo = new MemberInfo();
    ActionBarDrawerToggle toggle;
    String name;
    String profile;
    String email;
    String ageRange;
    String gender;
    String birthday;
    String kakaoNo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.community_listall);
        freeboard_listRecycle = findViewById(R.id.freeboard_listRecycle);
        freeboard_listbtn = findViewById(R.id.freeboard_listbtn);
        freeboard_listSearchbtn = findViewById(R.id.freeboard_listSearchbtn);
        freeboard_listallSearch = findViewById(R.id.freeboard_listallSearch);
        freeboard_listallSpinner = findViewById(R.id.freeboard_listallSpinner);
        forwardbtn = findViewById(R.id.forwardbtn);
        backwardbtn = findViewById(R.id.backwardbtn);
        final NavigationView navigationView = findViewById(R.id.main_drawer_view);
        intent = getIntent();
        name = intent.getStringExtra("name");
        profile = intent.getStringExtra("strProfile");
        email = intent.getStringExtra("strEmail");
        ageRange = intent.getStringExtra("strAgeRange");
        gender = intent.getStringExtra("strGender");
        birthday = intent.getStringExtra("strBirthday");
        kakaoNo = intent.getStringExtra("strKakaoNo");
        mainLayout = findViewById(R.id.main_container);
        toggle = new ActionBarDrawerToggle(this, mainLayout, R.string.open_str, R.string.close_str);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toggle.syncState();



        progressBar = findViewById(R.id.progresssss);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        RecyclerDecoration spacDecoration = new RecyclerDecoration(50);
        freeboard_listRecycle.addItemDecoration(spacDecoration);
        Adapterset();

        /*아이템에 이벤트 연결해서 상세보기 화면 띄우기 시작*/
        adapter.setOnItemClickListener(new FreeboardAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                //여기다가 상세보기
                /*조회수 증가 시작*/
                Map<String, String> map = new HashMap<String, String>();
                map.put("method", "HitUpdate");
                map.put("board_NUM",list.get(position).getBoard_NUM());
                Task networkTask = new Task();
                networkTask.execute(map);
                /*조회수 증가 끝*/

                /*게시판 상세보기 시작*/
                if (list.get(position).getMem_NICKNAME().equals(name)){//NickName은 로그인 한 사람의 닉네임.
                    //나의 게시판 상세보기
                    Intent intent = new Intent(CommunityMainActivity.this,Freeboard_MyDetailActivity.class);
                    intent.putExtra("board_num",list.get(position).getBoard_NUM());
                    intent.putExtra("board_content",list.get(position).getBoard_CONTENT());
                    intent.putExtra("board_title",list.get(position).getBoard_TITLE());
                    intent.putExtra("image",list.get(position).getImage());//이미지 가져오기
                    intent.putExtra("Nickname",name);
                    startActivity(intent);
                } else{
                    //게시판 상세보기
                    Intent intent = new Intent(CommunityMainActivity.this,Freeboard_DetailActivity.class);
                    intent.putExtra("board_num",list.get(position).getBoard_NUM());
                    intent.putExtra("board_content",list.get(position).getBoard_CONTENT());
                    intent.putExtra("board_title",list.get(position).getBoard_TITLE());
                    intent.putExtra("image",list.get(position).getImage());//이미지 가져오기
                    intent.putExtra("Nickname",name);
                    startActivity(intent);
                }
                /*게시판 상세보기 끝*/
            }
        });
        /*아이템에 이벤트 연결해서 상세보기 화면 띄우기 끝*/

        /*네비게ㅅ이션 뷰 시작*/
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                        int id = menuItem.getItemId();
                        if (id == R.id.item1) {
                            FragmentTransaction transaction;
                            transaction = getSupportFragmentManager().beginTransaction();
                            transaction.replace(R.id.fragment, home);
                            getSupportFragmentManager().popBackStackImmediate(null, getSupportFragmentManager().POP_BACK_STACK_INCLUSIVE);
                            transaction.commit();
                            mainLayout.closeDrawer(navigationView);
                        } else if (id == R.id.item2) {
                            getSupportFragmentManager().popBackStackImmediate(null, getSupportFragmentManager().POP_BACK_STACK_INCLUSIVE);
                            FragmentTransaction transaction;
                            transaction = getSupportFragmentManager().beginTransaction();
                            transaction.replace(R.id.fragment, memberInfo);
                            Bundle bundle = new Bundle();
                            bundle.putString("strName", name);
                            bundle.putString("strProfile", profile);
                            bundle.putString("strEmail", email);
                            bundle.putString("strAgeRange", ageRange);
                            bundle.putString("strGender", gender);
                            bundle.putString("strBirthday", birthday);
                            bundle.putString("strKakaoNo", kakaoNo);
                            memberInfo.setArguments(bundle);
                            transaction.addToBackStack("memberInfo");
                            transaction.commit();
                            mainLayout.closeDrawer(navigationView);
                        }  else if (id == R.id.item3) {
                            Intent intent = new Intent(CommunityMainActivity.this, mysale_list.class);
                            intent.putExtra("name", kakaoNo);
                            startActivity(intent);
                        }

                        return false;
                    }
                });
        /*네비게이션 뷰 끝*/
    }

    public void onClick(View v) {
        if (v.getId() == R.id.freeboard_listbtn) {
            //글쓰기 버튼을 눌렀을 때 게시글 번호와 닉네임을 넘겨줘야 글쓸 때 등록 가능
            Intent intent = new Intent(CommunityMainActivity.this, Freeboard_WriteActivity.class);
            intent.putExtra("name",name);//여기에 세션 유지한 아이디 넣기
            startActivityForResult(intent,RESULT_OK);
        }if (v.getId() == R.id.freeboard_listSearchbtn){
            if (freeboard_listallSearch.getText().toString().equals("")){
                k=19;
                count = 0;
                lastPage = 0;
                middlePage = 0;
                Adapterset();
                adapter.setOnItemClickListener(new FreeboardAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View v, int position) {
                        Intent intent = new Intent(CommunityMainActivity.this,Freeboard_DetailActivity.class);
                        startActivity(intent);
                    }
                });
            }else {
                if (freeboard_listallSpinner.getSelectedItem().toString().equals("제목")) {
                    CommunityByTitle();
                    adapterItemClick();

                }else if (freeboard_listallSpinner.getSelectedItem().toString().equals("닉네임")){
                    Log.d("msg","닉네임을 눌렀어");
                    CommunityByNickname();
                    adapterItemClick();
                }else if (freeboard_listallSpinner.getSelectedItem().toString().equals("내용")){
                    Log.d("msg","내용을 눌렀어");
                    CommunityByContent();
                    adapterItemClick();

                }
            }
        }if (v.getId() == R.id.forwardbtn){
            forward();
        }if (v.getId() == R.id.backwardbtn){
            backward();
        }
    }

    public void Adapterset(){
            /*DB연동 시작*/
            Map<String, String> map = new HashMap<String, String>();
            map.put("method", "CommunityList");
            Task networkTask = new Task();
            networkTask.execute(map);
            while (networkTask.getResult().equals("")) {
                SystemClock.sleep(10);
            }
            Gson gson = new Gson();
            CommunityVO[] array = gson.fromJson(networkTask.getResult(), CommunityVO[].class);
            list = Arrays.asList(array);
            Log.d("msg","처음 게시판 목록들 =="+list);
            /*DB연동 끝*/

            adapter = new FreeboardAdapter(this, R.layout.community_row_listview,list);
            freeboard_listRecycle.addItemDecoration(new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL));
            final LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext());
            manager.setOrientation(LinearLayoutManager.VERTICAL);
            freeboard_listRecycle.setLayoutManager(manager);
            freeboard_listRecycle.setAdapter(adapter);
        }

    /*forwardbtn 시작*/
    public void forward(){
        if (count>=2){
            Log.d("msg","forward 첫번째 if문...");
            Toast.makeText(CommunityMainActivity.this,"마지막 페이지 입니다.",Toast.LENGTH_SHORT).show();
            middlePage = 0;
        } else if(lastPage == 1){
            Log.d("msg","forward 두번째 if문...");
            k=k+10;
            InForward();
            middlePage = 1;
        }else {
            Log.d("msg","forward 세번째 if문...");
            InForward();
            k=k+10;
            middlePage = 1;
        }
    }
    /*forwardbtn 끝*/
    /*forward 메소드에 들어갈 조회구문 시작*/
    public void InForward(){
        final Gson gson = new Gson();
        final Task networkTask = new Task();
        Log.d("msg","컨트롤러에 전달 될 k ==="+k);
        Map<String, String> map = new HashMap<String, String>();
        map.put("method", "CommunityNext");
        map.put("num1", Integer.toString(k - 9));
        map.put("num2", Integer.toString(k));
        networkTask.execute(map);

        while (networkTask.getResult().equals("")) {
            SystemClock.sleep(10);
        }
        progressBar.setVisibility(View.VISIBLE);
        new Handler().postDelayed(new Runnable() {
            public void run() {
                CommunityVO[] array = gson.fromJson(networkTask.getResult(), CommunityVO[].class);
                list = Arrays.asList(array);
                Log.d("msg", "sfadssf" + list);
                adapter = new FreeboardAdapter(CommunityMainActivity.this, R.layout.community_row_listview, list);
                freeboard_listRecycle.addItemDecoration(new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL));
                final LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext());
                manager.setOrientation(LinearLayoutManager.VERTICAL);
                freeboard_listRecycle.setLayoutManager(manager);
                freeboard_listRecycle.setAdapter(adapter);
                freeboard_listallSearch.setText("");
                adapterItemClick();
                progressBar.setVisibility(View.GONE);
                count ++;
                Log.d("msg","count ==="+count);
            }
        }, 500);
    }
    /*forward 메소드에 들어갈 조회구문 끝*/


    /*backwardbtn 시작*/
    public void backward(){
            if (count <= 1) {
                Log.d("msg","backward 첫번째 if 문//.. ===");
                progressBar.setVisibility(View.VISIBLE);
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        Adapterset();
                        adapterItemClick();
                        Toast.makeText(CommunityMainActivity.this, "첫 페이지 입니다.", Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                    }
                }, 500);
                k = 19;
                count = 0;
                lastPage = 0;
                middlePage = 0;
                Log.d("msg", "상수 뭔지 확인22222//.. === " + k);
            }else if(middlePage==1){
                Log.d("msg","backward 두번째 if 문//.. ===");
                k=k-20;
                lastPage = 1;
                //middlePage = 1;
                InBackward();

            }else if (middlePage==0){
                //마지막페이지 next버튼 눌렀을 땐 여기가 실행되야함
                Log.d("msg","backward 세번째 if 문//.. ===");
                k=k-20;
                lastPage = 0;
                InBackward();
            }
        }
    /*backwardbtn 끝*/
    /*backward 메소드에 들어갈 조회구문 시작*/
    public void InBackward(){
        final Gson gson = new Gson();
        final Task networkTask = new Task();
        //k = z + j;
        Log.d("msg","컨트롤러에 전달 될 k ==="+k);
        Map<String, String> map = new HashMap<String, String>();
        map.put("method", "CommunityNext");
        map.put("num1", Integer.toString(k - 9));
        map.put("num2", Integer.toString(k));
        networkTask.execute(map);

        while (networkTask.getResult().equals("")) {
            SystemClock.sleep(10);
        }
        progressBar.setVisibility(View.VISIBLE);
        new Handler().postDelayed(new Runnable() {
            public void run() {
                CommunityVO[] array = gson.fromJson(networkTask.getResult(), CommunityVO[].class);
                list = Arrays.asList(array);
                Log.d("msg", "sfadssf" + list);
                adapter = new FreeboardAdapter(CommunityMainActivity.this, R.layout.community_row_listview, list);
                freeboard_listRecycle.addItemDecoration(new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL));
                final LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext());
                manager.setOrientation(LinearLayoutManager.VERTICAL);
                freeboard_listRecycle.setLayoutManager(manager);
                freeboard_listRecycle.setAdapter(adapter);
                freeboard_listallSearch.setText("");
                adapterItemClick();
                progressBar.setVisibility(View.GONE);
                count --;
                Log.d("msg","count ==="+count);
            }
        }, 500);
    }
    /*backward 메소드에 들어갈 조회구문 끝*/


    /*제목으로 조회하기 시작*/
    public void CommunityByTitle(){
        /*DB연동 시작*/
        Map<String, String> map = new HashMap<String, String>();
        map.put("method", "CommunityListByTitle");
        map.put("board_Title",freeboard_listallSearch.getText().toString());
        Task networkTaskTitle = new Task();
        networkTaskTitle.execute(map);
        while (networkTaskTitle.getResult().equals("")) {
            SystemClock.sleep(10);
        }
        Gson gson = new Gson();
        CommunityVO[] array = gson.fromJson(networkTaskTitle.getResult(), CommunityVO[].class);
        list = Arrays.asList(array);
        Log.d("msg","제목으로 조회된 결과"+list);
        /*DB연동 끝*/

        adapter = new FreeboardAdapter(this, R.layout.community_row_listview,list);
        freeboard_listRecycle.addItemDecoration(new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL));
        final LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        freeboard_listRecycle.setLayoutManager(manager);
        freeboard_listRecycle.setAdapter(adapter);
        freeboard_listallSearch.setText("");
    }
    /*제목으로 조회하기 끝*/

    /*작성자로 조회하기 시작*/
    public void CommunityByNickname(){
        /*DB연동 시작*/
        Map<String, String> map = new HashMap<String, String>();
        map.put("method", "CommunityListByNickname");
        map.put("mem_Nickname",freeboard_listallSearch.getText().toString());
        Task networkTaskTitle = new Task();
        networkTaskTitle.execute(map);
        while (networkTaskTitle.getResult().equals("")) {
            SystemClock.sleep(10);
        }
        Gson gson = new Gson();
        CommunityVO[] array = gson.fromJson(networkTaskTitle.getResult(), CommunityVO[].class);
        list = Arrays.asList(array);
        Log.d("msg","작성자로 조회된 결과"+list);
        /*DB연동 끝*/

        adapter = new FreeboardAdapter(this, R.layout.community_row_listview,list);
        freeboard_listRecycle.addItemDecoration(new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL));
        final LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        freeboard_listRecycle.setLayoutManager(manager);
        freeboard_listRecycle.setAdapter(adapter);
        freeboard_listallSearch.setText("");
    }
    /*작성자로 조회하기 끝*/

    /*내용으로 조회하기 시작*/
    public void CommunityByContent(){
        /*DB연동 시작*/
        Map<String, String> map = new HashMap<String, String>();
        map.put("method", "CommunityListByContent");
        map.put("board_Content",freeboard_listallSearch.getText().toString());
        Task networkTaskTitle = new Task();
        networkTaskTitle.execute(map);
        while (networkTaskTitle.getResult().equals("")) {
            SystemClock.sleep(10);
        }
        Gson gson = new Gson();
        CommunityVO[] array = gson.fromJson(networkTaskTitle.getResult(), CommunityVO[].class);
        list = Arrays.asList(array);
        Log.d("msg","내용으로 조회된 결과"+list);
        /*DB연동 끝*/

        adapter = new FreeboardAdapter(this, R.layout.community_row_listview,list);
        freeboard_listRecycle.addItemDecoration(new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL));
        final LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        freeboard_listRecycle.setLayoutManager(manager);
        freeboard_listRecycle.setAdapter(adapter);
        freeboard_listallSearch.setText("");
    }
    /*내용으로 조회하기 끝*/

    /*아이템에 이벤트 연결해서 상세보기 화면 띄우기 시작*/
    public void adapterItemClick(){
        adapter.setOnItemClickListener(new FreeboardAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                //여기다가 상세보기
                /*조회수 증가 시작*/
                Map<String, String> map = new HashMap<String, String>();
                map.put("method", "HitUpdate");
                map.put("board_NUM",list.get(position).getBoard_NUM());
                Task networkTask = new Task();
                networkTask.execute(map);
                /*조회수 증가 끝*/

                /*게시판 상세보기 시작*/
                if (list.get(position).getMem_NICKNAME().equals(name)){
                    //나의 게시판 상세보기
                    Intent intent = new Intent(CommunityMainActivity.this,Freeboard_MyDetailActivity.class);
                    intent.putExtra("board_num",list.get(position).getBoard_NUM());
                    intent.putExtra("board_content",list.get(position).getBoard_CONTENT());
                    intent.putExtra("board_title",list.get(position).getBoard_TITLE());
                    intent.putExtra("image",list.get(position).getImage());//이미지 가져오기
                    startActivity(intent);
                } else{
                    //게시판 상세보기
                    Intent intent = new Intent(CommunityMainActivity.this,Freeboard_DetailActivity.class);
                    intent.putExtra("board_num",list.get(position).getBoard_NUM());
                    intent.putExtra("board_content",list.get(position).getBoard_CONTENT());
                    intent.putExtra("board_title",list.get(position).getBoard_TITLE());
                    intent.putExtra("image",list.get(position).getImage());//이미지 가져오기
                    startActivity(intent);
                }
                /*게시판 상세보기 끝*/
            }
        });
    }
    /*아이템에 이벤트 연결해서 상세보기 화면 띄우기 끝*/


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        ImageView iv = findViewById(R.id.navigation_header_image);
        TextView tv = findViewById(R.id.navigation_header_id);
        tv.setText(name);
        if (profile != null)
            Glide.with(this).load(profile).into(iv);

        if (toggle.onOptionsItemSelected(item)) {

        }
        return super.onOptionsItemSelected(item);
    }


   @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("msg","리스타트 호출??");
        Adapterset();
        adapterItemClick();

    }
}
