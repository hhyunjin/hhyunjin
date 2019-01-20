package com.example.a203.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class Detail extends Activity implements OnClickListener {//메모를 입력하는 액티비티
	MyDBHelper mDBHelper;
	int mId;
	String today;
	EditText editDate, editTitle, editTime, editMemo;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.detail);

		editDate = (EditText) findViewById(R.id.editdate);//각 뷰들을 가져온다.
		editTitle = (EditText) findViewById(R.id.edittitle);
		editTime = (EditText) findViewById(R.id.edittime);
		editMemo = (EditText) findViewById(R.id.editmemo);

		Intent intent = getIntent();
		mId = intent.getIntExtra("ParamID", -1);//인텐트로 넘겨받은 ID에 대한 데이터
		today = intent.getStringExtra("ParamDate");//인텐트로 넘겨받은 날짜에 대한 데이터

		mDBHelper = new MyDBHelper(this, "Today.db", null, 1);

		if (mId == -1) {//새로 생성하는 메모일 경우
			editDate.setText(today);
		} else {//기존에 존재하던 메모를 수정하는 경우
			SQLiteDatabase db = mDBHelper.getWritableDatabase();
			Cursor cursor = db.rawQuery("SELECT * FROM today WHERE _id='" + mId
					+ "'", null);

			if (cursor.moveToNext()) {
				editTitle.setText(cursor.getString(1));
				editDate.setText(cursor.getString(2));
				editTime.setText(cursor.getString(3));
				editMemo.setText(cursor.getString(4));
			}
			mDBHelper.close();
		}

		Button btn1 = (Button) findViewById(R.id.btnsave);
		btn1.setOnClickListener(this);
		Button btn2 = (Button) findViewById(R.id.btndel);
		btn2.setOnClickListener(this);
		Button btn3 = (Button) findViewById(R.id.btncancel);
		btn3.setOnClickListener(this);

		if (mId == -1) {
			btn2.setVisibility(View.INVISIBLE);

		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		SQLiteDatabase db = mDBHelper.getWritableDatabase();

		switch (v.getId()) {
		case R.id.btnsave://저장할 경우
			if (mId != -1) {
				db.execSQL("UPDATE today SET title='"//해당 요소가 존재할 경우에는 업데이트
						+ editTitle.getText().toString() + "',date='"
						+ editDate.getText().toString() + "', time='"
						+ editTime.getText().toString() + "', memo='"
						+ editMemo.getText().toString() + "' WHERE _id='" + mId
						+ "';");
			} else {
				db.execSQL("INSERT INTO today VALUES(null, '"//존재하지 않을 경우 새로 생성함
						+ editTitle.getText().toString() + "', '"
						+ editDate.getText().toString() + "', '"
						+ editTime.getText().toString() + "', '"
						+ editMemo.getText().toString() + "');");
			}
			mDBHelper.close();
			setResult(RESULT_OK);
			break;
		case R.id.btndel:
			if (mId != -1) {//삭제했을경우(db에서삭제시킨다)
				db.execSQL("DELETE FROM today WHERE _id='" + mId + "';");
				mDBHelper.close();
			}
			setResult(RESULT_OK);
			break;
		case R.id.btncancel://취소할경우(저장하지않고 바로 액티비티 종료)
			setResult(RESULT_CANCELED);
			break;
		}
		finish();
	}
}
