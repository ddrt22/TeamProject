package com.example.wonbaeteamtest;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<ShelterData> mData=new ArrayList<ShelterData>();
    private ListView mList;
    private MyAdapter mAdapter;
    ArrayList<ShelterData> arraylist;

   Toolbar toolbar;
    private long backKeyPressedTime = 0; //뒤로가기 버튼 눌렀던 시간 저장
    private Toast toast;//첫번째 뒤로가기 버튼을 누를때 표시하는 변수

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*툴바생성*/
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        super.setSupportActionBar(toolbar);


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {//메뉴 만들기
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){//메뉴에서 버튼이 눌리면
        switch (item.getItemId()){
            case R.id.btnAdd://추가 버튼이 눌르면
                Intent intent2 = new Intent(this,ShelterEdit.class);//편집 엑티비티로 이동
                startActivityForResult(intent2,1);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        int position;
        if (requestCode==1 && resultCode==RESULT_OK){//추가를 누르고 편집엑티비티에서 저장을 누를경우 값들을 객체에 저장하고 리스트뷰 추가
            mData.add(new ShelterData(R.drawable.testpic,data.getStringExtra("name"),
                    data.getStringExtra("provider"),data.getStringExtra("address")));
            arraylist.add(new ShelterData(R.drawable.testpic,data.getStringExtra("name"),
                    data.getStringExtra("provider"),data.getStringExtra("address")));
        }
        else if(requestCode==0&&resultCode==2){//대피소 보기 엑티비티에서 삭제버튼을 눌렀을 경우 해당 객체를 삭제
            position=data.getIntExtra("position",-1);
            mData.remove(position);
        }
        else if(requestCode==0 && resultCode==3){//대피소 편집 엑티비티에서 저장을 누를 경우 객체 정보를 갱신
            position=data.getIntExtra("position",-1);
            mData.get(position).name=data.getStringExtra("name");
            mData.get(position).address=data.getStringExtra("address");
            mData.get(position).provider=data.getStringExtra("provider");
        }
        mAdapter.notifyDataSetChanged();
        super.onActivityResult(requestCode, resultCode, data);
    }


    public void onBackPressed() {
        //super.onBackPressed();
        //기존의 뒤로가기 버튼 기능 막기
        if(System.currentTimeMillis()>backKeyPressedTime+2000){
            backKeyPressedTime=System.currentTimeMillis();
            toast= Toast.makeText(this,"뒤로 버튼 한번더 누르시면 종료됩니다",Toast.LENGTH_SHORT);
            toast.show();
            return;
        }// 뒤로가기버튼을 한번누르면 현재시간값에 현재버튼누른시간 저장
        if(System.currentTimeMillis()<=backKeyPressedTime+2000){
            finish();
            toast.cancel();
        }//위에서 저장한 현재시간값에 2초안에 버튼을 한번 더 누르면 앱을 종료함.
    }
}