package com.example.a203.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Search extends AppCompatActivity {//날짜 검색을 누르면 그려질 액티비티

    EditText year;
    EditText month;
    EditText day;
    Button go;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);

        year = (EditText)findViewById(R.id.search_input_year);
        month = (EditText)findViewById(R.id.search_input_month);
        day = (EditText)findViewById(R.id.search_input_day);

        go = (Button)findViewById(R.id.search_button_go);

    }

    public void onClickGo(View v){//날짜 검색에서 이동 버튼을 누르면 취할 액션
        if (year.getText().toString().isEmpty() || month.getText().toString().isEmpty() || day.getText().toString().isEmpty()) {//3칸 모두 입력이 되어야 한다.
            ;
        } else {
            Intent intent = new Intent(this, ExToday.class);//해당 일을 눌렸을때
            intent.putExtra("Param1", year.getText().toString() + "/"
                    + month.getText().toString() + "/" + day.getText().toString());
            startActivity(intent);
        }
    }

}
