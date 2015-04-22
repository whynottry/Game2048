package com.example.game2048;

import android.content.Context;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.TextView;

public class Card extends FrameLayout {
	
	public Card(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		label = new TextView(getContext());
		label.setTextSize(32);
		label.setGravity(Gravity.CENTER);
		//半透明白色
		label.setBackgroundColor(0x33ffffff);
		setNum(0);
		
		//-1,-1说明宽高填充满整个容器
		LayoutParams lp = new LayoutParams(-1,-1);
		lp.setMargins(10, 10, 0, 0);
		addView(label,lp);
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
		if(num <= 0){
			label.setText("");
		}else{
			label.setText(num+"");
		}
	}
	private int num;
	private TextView label;
	
	public boolean equal(Card o){
		return getNum()==o.getNum();
	}

}
