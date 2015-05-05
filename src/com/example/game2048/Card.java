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
		//label.setBackgroundResource(R.drawable.p_8);
		
		setNum(0);
		setCardBg();
		
		//-1,-1说明宽高填充满整个容器
		LayoutParams lp = new LayoutParams(-1,-1);
		lp.setMargins(10, 10, 0, 0);
		addView(label,lp);
	}
	
	void setCardBg(){
		switch(num){
		case 0:
			//半透明白色
			label.setBackgroundColor(0x33ffffff);
			break;
		case 2:
			label.setBackgroundResource(R.drawable.p_2);
			break;
		case 4:
			label.setBackgroundResource(R.drawable.p_4);
			break;
		case 8:
			label.setBackgroundResource(R.drawable.p_8);
			break;
		case 16:
			label.setBackgroundResource(R.drawable.p_16);
			break;
		case 32:
			label.setBackgroundResource(R.drawable.p_32);
			break;
		case 64:
			label.setBackgroundResource(R.drawable.p_64);
			break;
		case 128:
			label.setBackgroundResource(R.drawable.p_128);
			break;
		case 256:
			label.setBackgroundResource(R.drawable.p_256);
			break;
		case 512:
			label.setBackgroundResource(R.drawable.p_512);
			break;
		case 1024:
			label.setBackgroundResource(R.drawable.p_1024);
			break;
		case 2048:
			label.setBackgroundResource(R.drawable.p_2048);
			break;
		case 4096:
			label.setBackgroundResource(R.drawable.p_4096);
			break;
		default:
			label.setBackgroundResource(R.drawable.never);	
		}
	}
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
//		if(num <= 0){
//			label.setText("");
//		}else{
//			label.setText(num+"");
//		}
	}
	private int num;
	private TextView label;
	
	public boolean equal(Card o){
		return getNum()==o.getNum();
	}

}
