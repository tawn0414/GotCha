package multi.android.gotcha.Community;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import multi.android.gotcha.DB.ImageVO;
import multi.android.gotcha.DB.Task;
import multi.android.gotcha.R;

public class Freeboard_WriteActivity extends AppCompatActivity {
    Button freeboard_ImageUpload;
    Button freeboard_writeSaveBtn;
    Button freeboard_writeCancelBtn;
    EditText freeboard_writeTitle;
    EditText freeboard_writeContent;
    LinearLayout freeboard_writelinear;
    ImageView WriteImage;
    boolean permission_state;
    List<ImageVO> imageList;
    List<Uri> list;
    List<String> filename = new ArrayList<>();
    private final int CAPTURE_IMAGE = 100;
    private final int GET_GALLERY_IMAGE = 200;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.community_write);
        /*findViewById 시작*/
        freeboard_ImageUpload = findViewById(R.id.freeboard_ImageUpload);
        freeboard_writeSaveBtn = findViewById(R.id.freeboard_writeSaveBtn);
        freeboard_writeCancelBtn = findViewById(R.id.freeboard_writeCancelBtn);
        freeboard_writeTitle = findViewById(R.id.freeboard_writeTitle);
        freeboard_writeContent = findViewById(R.id.freeboard_writeContent);
        freeboard_writelinear = findViewById(R.id.freeboard_writelinear);
        WriteImage = findViewById(R.id.WriteImage);
        /*findViewById 끝*/
        Date currentTime = Calendar.getInstance().getTime();
        final String date_text = new SimpleDateFormat("yyyy년 MM월 dd일 hh:mm", Locale.getDefault()).format(currentTime);

        //1. Permission을 먼저 체크
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED) {
                permission_state = true;
                printToast("권한이 설정되었습니다.");
            }
        } else {
            permission_state = false;
            printToast("권한을 설정해야 합니다.");
            //2. 권한이 없는 경우 권한을 설정하라는 메시지를 띄운다.
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA},
                    1000);//requestCode는 아무거나 준다.
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    1001);
        }

        Intent intent = getIntent();
        final String name = intent.getStringExtra("name");
        freeboard_writeSaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*DB연동 시작*/
                Map<String, String> map = new HashMap<String, String>();
                map.put("method", "CommunityWrite");
                map.put("mem_NICKNAME",name);//세션 유지한 아이디나 닉데임 등 써주기
                map.put("board_TITLE",freeboard_writeTitle.getText().toString());
                map.put("board_CONTENT",freeboard_writeContent.getText().toString());
                if(list!=null) {
                    map.put("image_List", list.get(0).toString());
                }


                Map<String, String> map2 = new HashMap<String, String>();
                map2.put("method", "fileCommUpload");
                map2.put("length", filename.size()+"");
                for(int i=0; i<filename.size();i++){
                    map2.put("filename"+i,filename.get(i));
                    map.put("filename"+i,filename.get(i));
                    Log.d("filename","filename"+i+"    "+filename.get(i));
                }

                Task networkTask = new Task();
                networkTask.execute(map);
                Task networkTask2 = new Task();
                networkTask2.execute(map2);

                /*DB연동 끝*/
                finish();
            }
        });
        freeboard_writeCancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        freeboard_ImageUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(Freeboard_WriteActivity.this,R.style.Theme_AppCompat_Light_Dialog);
                alertDialog.setTitle("사진 업로드").setIcon(R.drawable.camera).setCancelable(
                        true).setPositiveButton("사진촬영",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Log.v("알림", "다이얼로그 > 사진촬영 선택");
                                // 사진 촬영 클릭
                                takePhoto();
                            }
                        }).setNeutralButton("앨범선택",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogInterface, int id) {
                                Log.v("알림", "다이얼로그 > 앨범선택 선택");
                                //앨범에서 선택
                                album();
                            }
                        });
                AlertDialog alert = alertDialog.create();
                alert.show();
            }
            public void album(){
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setDataAndType(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(intent, GET_GALLERY_IMAGE);
            }

            public void takePhoto(){
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent,CAPTURE_IMAGE);
            }

        });
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        int count = 1;

        if (requestCode == GET_GALLERY_IMAGE && resultCode == RESULT_OK && data != null) {
            //갤러리에서 이미지를 가져올 때 실행되는 부분
            list = new ArrayList<Uri>();
            if (data.getClipData() !=null){
                //갤러리에서 이미지를 여러개 선택했을 때
                count = data.getClipData().getItemCount();//내가 선택한 사진의 개수
                LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext());
                manager.setOrientation(RecyclerView.HORIZONTAL);
                for (int i = 0; i < count; i++){
                    Uri selectedImageUri = data.getClipData().getItemAt(i).getUri();
                    String[] projection = { MediaStore.Images.ImageColumns.DISPLAY_NAME };
                    Cursor cursor = this.managedQuery(selectedImageUri, projection, null, null, null);
                    int column_index = cursor
                            .getColumnIndexOrThrow(MediaStore.Images.ImageColumns.DISPLAY_NAME);
                    cursor.moveToFirst();
                    list.add(selectedImageUri);
                    filename.add(cursor.getString(column_index));
                    //imageList = new ImageVO(list.get(i));
                    LinearLayout.LayoutParams imagelayout = new LinearLayout.LayoutParams(500, 500);
                    imagelayout.gravity = Gravity.CENTER_HORIZONTAL;
                    imagelayout.setMargins(10,10,10,10);
                    ImageView imageView = new ImageView(this);//이미지뷰 생성
                    imageView.setLayoutParams(imagelayout);//이미지뷰에 레이아웃 조건 주기
                    freeboard_writelinear.addView(imageView);//레이아웃에 이미지뷰 추가
                    imageView.setImageURI(list.get(i));
                }
                Log.d("msg","받아온 이미지 Uri"+list.get(0));
            }else if (data.getData() != null){
                Log.d("msg","다중 이미지 하나만 선택했을 때");
                //갤러리에서 이미지를 하나만 선택했을 때
                Uri selectedImageUri = data.getData();
                String[] projection = { MediaStore.Images.ImageColumns.DISPLAY_NAME };
                Cursor cursor = this.managedQuery(selectedImageUri, projection, null, null, null);
                int column_index = cursor
                        .getColumnIndexOrThrow(MediaStore.Images.ImageColumns.DISPLAY_NAME);
                cursor.moveToFirst();
                list.add(selectedImageUri);
                filename.add(cursor.getString(column_index));
                WriteImage.setImageURI(selectedImageUri);
            }
        } else if (requestCode == CAPTURE_IMAGE && resultCode == Activity.RESULT_OK && data.hasExtra("data")) {
            //찍은 사진 가져올 때 실행되는 부분
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            if (bitmap !=null){
                WriteImage.setImageBitmap(bitmap);//찍은 사진을 이미지뷰에 저장
                String pic1 = System.currentTimeMillis()+".jpg";
                MediaStore.Images.Media.insertImage(this.getContentResolver(),bitmap,pic1,"사진저장완료");
                filename.add(pic1);
            }
        }



    }

    /*private Bitmap resize(Context context,Uri uri,int resize){
        Bitmap resizeBitmap=null;

        BitmapFactory.Options options = new BitmapFactory.Options();
        try {
            BitmapFactory.decodeStream(context.getContentResolver().openInputStream(uri), null, options); // 1번

            int width = options.outWidth;
            int height = options.outHeight;
            int samplesize = 1;

            while (true) {//2번
                if (width / 2 < resize || height / 2 < resize)
                    break;
                width /= 2;
                height /= 2;
                samplesize *= 2;
            }

            options.inSampleSize = samplesize;
            Bitmap bitmap = BitmapFactory.decodeStream(context.getContentResolver().openInputStream(uri), null, options); //3번
            resizeBitmap=bitmap;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return resizeBitmap;
    }*/


    /*퍼미션 메소드*/
    public void printToast(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode ==1000 && grantResults.length>0){
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                permission_state = true;
                printToast("권한 설정 마무리 완료");
            }else{
                printToast("권한 설정을 하지 않았으므로 기능을 사용할 수 없습니다.");
            }
        }
    }


}
