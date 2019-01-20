package com.example.a203.myapplication;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class Adapter extends BaseAdapter {//어댑터
    Context context;
    int layout;
    LayoutInflater inf;
    ArrayList<String> mItems;

    public Adapter(Context context, int layout, ArrayList<String> mItems) {//생성자
        this.context = context;
        this.layout = layout;
        this.mItems = mItems;
        inf = (LayoutInflater) context.getSystemService
                (Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mItems.size();
    }//배열 사이즈 반환

    @Override
    public Object getItem(int position) {//배열의 요소 반환
        return mItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {//그리드 뷰에 날짜를 적어주는 실질적인 부분
        if (convertView==null)
            convertView = inf.inflate(layout, null);//인플레이터를 이용해 동적으로 뷰를 등록한다(그리드 뷰의 각 칸마다 list_calendar을 넣어줌)

        TextView tv = (TextView) convertView.findViewById(R.id.list_calendar_text);//list_calendar에 있는 리스트뷰에 대한 객체 생성
        tv.setHeight(170);//텍스트뷰 크기조절(텍스트뷰한칸에 날짜1개가 적히므로 날짜칸의 크기를 조절하는 것이라 볼 수 있다.
        tv.setGravity(Gravity.CENTER);//글자 위치 조정(중앙배치)
        tv.setText(mItems.get(position));//배열의 position에 해당하는 요소를 적어준다(Calendar 클래스의 filldate서 요소들을 입력했었다.)
        if((position % 7) == 0) {//일요일일 경우 빨간색으로
            tv.setTextColor(Color.RED);
        }
        else if((position % 7) == 6){//토요일일 경우 파란색으로
            tv.setTextColor(Color.BLUE);
        }



        return convertView;
    }
}
