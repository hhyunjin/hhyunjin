package com.example.a203.myapplication;

import java.util.ArrayList;
import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Calendar extends Activity implements OnClickListener,//달력이 출려되는 액티비티
		OnItemClickListener {
	ArrayList<String> mItems;//그리드뷰(달력)에 이용 될 배열
	TextView textYear;//년도를 입력할 에딧창
	TextView textMon;//달을 입력할 에딧창
	GridView gird;//그리드 뷰(달력)
	Adapter adapter;//어댑터, 그리드뷰와 어레이를 연결함(달력에 날짜적어줌)
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.calendar);

		textYear = (TextView) this.findViewById(R.id.edit1);//텍스트뷰 객체생성
		textMon = (TextView) this.findViewById(R.id.edit2);//텍스트뷰 객체생성

		mItems = new ArrayList<String>();//배열 객체생성
		adapter = new Adapter(this,//어댑터 객체생성
				R.layout.list_calendar, mItems);

		gird = (GridView) this.findViewById(R.id.grid1);//그리드뷰 객체생성
		gird.setAdapter(adapter);//그리드뷰에 어댑터 등록
		gird.setOnItemClickListener(this);//그리드뷰 아이템(날짜)에 리스너등록
		Date date = new Date();// 오늘에 날짜를 세팅 해줌
		int year = date.getYear() + 1900;
		int mon = date.getMonth() + 1;
		textYear.setText(year + "");
		textMon.setText(mon + "");
		fillDate(year, mon);//각 년도와 달에 맞게 적어줄 날짜를 배열에 등록하고, 어댑터에 그리드뷰와 연결된 배열이 변경됬음을 알린다.

		Button btnmove = (Button) this.findViewById(R.id.bt1);//버튼 객체생성(이동 버튼)
		btnmove.setOnClickListener(this);

	}

	@Override
	public void onClick(View arg0) {
// TODO Auto-generated method stub
		if (arg0.getId() == R.id.bt1) {
			int year = Integer.parseInt(textYear.getText().toString());
			int mon = Integer.parseInt(textMon.getText().toString());
			fillDate(year, mon);
		}

	}

	private void fillDate(int year, int mon) {
		mItems.clear();

		mItems.add("일");
		mItems.add("월");
		mItems.add("화");
		mItems.add("수");
		mItems.add("목");
		mItems.add("금");
		mItems.add("토");

		Date current = new Date(year - 1900, mon - 1, 1);
		int day = current.getDay(); // 요일도 int로 저장.

		for (int i = 0; i < day; i++) {//요일(일~토)과 날짜(1~31) 사이에 적혀질 공백
			mItems.add("");
		}

		current.setDate(32);// 32일까지 입력하면 1일로 바꿔준다.
		int last = 32 - current.getDate();

		for (int i = 1; i <= last; i++) {//날짜
			mItems.add(i + "");

		}
		adapter.notifyDataSetChanged();//배열이 변경됬음을 알리는 부분
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {//날짜를 클릭하면 취할 액션
// TODO Auto-generated method stub
		if (!mItems.get(arg2).matches("[0-9]") && !mItems.get(arg2).matches("[0-9]+[0-9]")) {//날짜가 선택되지 않았으면 아무일도 일어나지 않음
			;
		} else {
			Intent intent = new Intent(this, ExToday.class);//해당 일을 눌렸을때
			intent.putExtra("Param1", textYear.getText().toString() + "/"
					+ textMon.getText().toString() + "/" + mItems.get(arg2));
			startActivity(intent);
		}
	}
}