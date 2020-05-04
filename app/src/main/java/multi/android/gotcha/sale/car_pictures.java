package multi.android.gotcha.sale;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;


import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import multi.android.gotcha.DB.Task;
import multi.android.gotcha.DB.fileUpload;
import multi.android.gotcha.R;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class car_pictures extends Fragment implements View.OnClickListener {
    boolean permission_state;
    String carNumber,from,pic1="",pic2="",pic3="",pic4="";
    Bitmap bitPic1,bitPic2,bitPic3,bitPic4;
    TextView carFromNumber;
    ImageView sailImg1,sailImg2,sailImg3,sailImg4;
    int place;
    private final int CAPTURE_IMAGE = 100;
    private final int GET_GALLERY_IMAGE = 200;

    public car_pictures() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_car_pictures, container, false);
        carFromNumber = v.findViewById(R.id.carFromNumber4);
        sailImg1 = v.findViewById(R.id.sailImg1);
        sailImg1.setOnClickListener(this);
        sailImg2 = v.findViewById(R.id.sailImg2);
        sailImg2.setOnClickListener(this);
        sailImg3 = v.findViewById(R.id.sailImg3);
        sailImg3.setOnClickListener(this);
        sailImg4 = v.findViewById(R.id.sailImg4);
        sailImg4.setOnClickListener(this);


        Bundle bundle = this.getArguments();
        if (bundle!=null){
            bundle = getArguments();
            carNumber = bundle.getString("carNum");
            from = bundle.getString("carFrom");
            permission_state = bundle.getBoolean("permission");
        }

        carFromNumber.setText(from+" / "+carNumber);




        return v;
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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.sailImg1:
                alertDial();
                place = 1;
                break;
            case R.id.sailImg2:
                alertDial();
                place = 2;
                break;
            case R.id.sailImg3:
                alertDial();
                place = 3;
                break;
            case R.id.sailImg4:
                alertDial();
                place = 4;
        }
    }

    public void alertDial(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext(),R.style.Theme_AppCompat_Light_Dialog);
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
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GET_GALLERY_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri selectedImageUri = data.getData();
            switch (place){
                case 1:
                    String[] projection = { MediaStore.Images.ImageColumns.DISPLAY_NAME };
                    Cursor cursor = getActivity().managedQuery(selectedImageUri, projection, null, null, null);
                    int column_index = cursor
                            .getColumnIndexOrThrow(MediaStore.Images.ImageColumns.DISPLAY_NAME);
                    cursor.moveToFirst();
                    sailImg1.setImageURI(selectedImageUri);
                    sailImg1.setBackgroundResource(R.drawable.border_rectangle);
                    pic1 = cursor.getString(column_index);
                    Log.d("images",pic1);
                    try {
                        bitPic1 = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(),selectedImageUri);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case 2:
                    projection = new String[]{ MediaStore.Images.ImageColumns.DISPLAY_NAME };
                    cursor = getActivity().managedQuery(selectedImageUri, projection, null, null, null);
                    column_index = cursor
                            .getColumnIndexOrThrow(MediaStore.Images.ImageColumns.DISPLAY_NAME);
                    cursor.moveToFirst();
                    sailImg2.setImageURI(selectedImageUri);
                    sailImg2.setBackgroundResource(R.drawable.border_rectangle);
                    pic2 = cursor.getString(column_index);
                    Log.d("images",pic2);
                    try {
                        bitPic2 = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(),selectedImageUri);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case 3:
                    projection = new String[]{ MediaStore.Images.ImageColumns.DISPLAY_NAME };
                    cursor = getActivity().managedQuery(selectedImageUri, projection, null, null, null);
                    column_index = cursor
                            .getColumnIndexOrThrow(MediaStore.Images.ImageColumns.DISPLAY_NAME);
                    cursor.moveToFirst();
                    sailImg3.setImageURI(selectedImageUri);
                    sailImg3.setBackgroundResource(R.drawable.border_rectangle);
                    pic3 = cursor.getString(column_index);
                    Log.d("images",pic3);
                    try {
                        bitPic3 = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(),selectedImageUri);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case 4:
                    projection = new String[]{ MediaStore.Images.ImageColumns.DISPLAY_NAME };
                    cursor = getActivity().managedQuery(selectedImageUri, projection, null, null, null);
                    column_index = cursor
                            .getColumnIndexOrThrow(MediaStore.Images.ImageColumns.DISPLAY_NAME);
                    cursor.moveToFirst();
                    sailImg4.setImageURI(selectedImageUri);
                    sailImg4.setBackgroundResource(R.drawable.border_rectangle);
                    pic4 = cursor.getString(column_index);
                    Log.d("images",pic4);
                    try {
                        bitPic4 = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(),selectedImageUri);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
            }
        } else if (requestCode == CAPTURE_IMAGE && resultCode == Activity.RESULT_OK && data.hasExtra("data")) {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            if (bitmap !=null){
                switch (place){
                    case 1:
                        sailImg1.setImageBitmap(bitmap);
                        pic1 = System.currentTimeMillis()+"";
                        MediaStore.Images.Media.insertImage(getContext().getContentResolver(),bitmap,pic1,"사진저장완료");
                        break;
                    case 2:
                        sailImg2.setImageBitmap(bitmap);
                        pic2 = System.currentTimeMillis()+"";
                        MediaStore.Images.Media.insertImage(getContext().getContentResolver(),bitmap,pic2,"사진저장완료");
                        break;
                    case 3:
                        sailImg3.setImageBitmap(bitmap);
                        pic3 = System.currentTimeMillis()+"";
                        MediaStore.Images.Media.insertImage(getContext().getContentResolver(),bitmap,pic3,"사진저장완료");
                        break;
                    case 4:
                        sailImg4.setImageBitmap(bitmap);
                        pic4 = System.currentTimeMillis()+"";
                        MediaStore.Images.Media.insertImage(getContext().getContentResolver(),bitmap,pic4,"사진저장완료");
                        break;
                }
            }
        }
    }

}
