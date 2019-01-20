package com.example.a203.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuMain extends AppCompatActivity {//초기화면

    Button search;
    Button calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_main);

        search = (Button)findViewById(R.id.button_search);
        calendar = (Button)findViewById(R.id.button_calendar);
    }

    public void onClickGoSearch(View v){//날짜 검색버튼
        Intent intent = new Intent(this, Search.class);
        startActivity(intent);

    }

    public void onClickGoCalendar(View v){//일정 입력 버튼

        Intent intent = new Intent(this, Calendar.class);
        startActivity(intent);

    }
}
