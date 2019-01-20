package com.example.a203.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBHelper extends SQLiteOpenHelper {//db를 연동시키기 위한 클래스

	public MyDBHelper(Context context, String name, CursorFactory factory,//생성자
			int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub

		db.execSQL("CREATE TABLE today(_id INTEGER PRIMARY KEY AUTOINCREMENT, "//테이블 생성
				+ "title TEXT, " + "date TEXT , " + "time TEXT, "
				+ "memo TEXT );");

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {//업그레이드
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS today;");
		onCreate(db);
	}

}
