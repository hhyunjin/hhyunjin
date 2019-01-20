package com.example.a203.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;

public class ExToday extends Activity implements OnItemClickListener,//어떠한 날짜를 누르거나 검색할 경우 그려질 액티비티
		OnClickListener {
	MyDBHelper mDBHelper;
	String today;
	Cursor cursor;
	SimpleCursorAdapter adapter;
	ListView list;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.extoday);

		Intent intent = getIntent();
		today = intent.getStringExtra("Param1");//인텐트로 날짜에 대한 데이터를 넘겨받는다.

		TextView text = (TextView) findViewById(R.id.texttoday);//뷰에대한 객체 생성
		text.setText(today);

		mDBHelper = new MyDBHelper(this, "Today.db", null, 1);
		SQLiteDatabase db = mDBHelper.getWritableDatabase();

		cursor = db.rawQuery(//쿼리를 이용해 날짜에 대한 데이터로 커서 이동
				"SELECT * FROM today WHERE date = '" + today + "'", null);

		adapter = new SimpleCursorAdapter(this,//list_extoday에 있는 리스트뷰에 등록할 어댑터 생성, 해당 어댑터는 db의 데이터를 list_extoday의 텍스트 뷰들(제목, 시간, 내용)에 담아서 리스트뷰에 넣어준다.
				R.layout.list_extoday, cursor, new String[] {
						"title", "time", "memo" }, new int[] { R.id.list_extoday_title,
						R.id.list_extoday_time, R.id.list_extoday_memo });

		ListView list = (ListView) findViewById(R.id.list1);
		list.setAdapter(adapter);//리스트뷰에 어댑터 등록
		list.setOnItemClickListener(this);

		mDBHelper.close();

		Button btn = (Button) findViewById(R.id.btnadd);
		btn.setOnClickListener(this);

	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,//각 아이템을 클릭할 경우(메모리스트 중 하나를 클릭할 경우)
			long id) {
		// TODO Auto-generated method stub
		Intent intent = new Intent(this, Detail.class);//인텐트 생성
		cursor.moveToPosition(position);//클릭된 아이템의 위치에 맞게 db커서를 이동시킴
		intent.putExtra("ParamID", cursor.getInt(0));//인텐트로 커서가 가리키는 db의 값중 필요한 정보 넘겨줌
		startActivityForResult(intent, 0);
	}

	@Override
	public void onClick(View v) {//일정 추가를 누를 경우
		// TODO Auto-generated method stub
		Intent intent = new Intent(this, Detail.class);
		intent.putExtra("ParamDate", today);//해당 날짜만 넘겨준다.
		startActivityForResult(intent, 1);

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {//startActivityForResult에서 취할 액션
		// TODO Auto-generated method stub
		// super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
		case 0:
		case 1:
			if (resultCode == RESULT_OK) {
				// adapter.notifyDataSetChanged();
				SQLiteDatabase db = mDBHelper.getWritableDatabase();
				cursor = db.rawQuery("SELECT * FROM today WHERE date = '"
						+ today + "'", null);
				adapter.changeCursor(cursor);
				mDBHelper.close();
			}
			break;
		}
	}
}
