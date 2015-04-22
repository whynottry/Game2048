package com.example.game2048;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridLayout;

public class GameView extends GridLayout {

	public GameView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initGame();
	}

	public GameView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initGame();
	}

	public GameView(Context context) {
		super(context);
		initGame();
	}
	
	private void initGame(){
		
		setColumnCount(4);
		//ff不透明
		setBackgroundColor(0xffbbada0);
		
		OnTouchListener onTouchListener = new OnTouchListener(){

			@Override
			public boolean onTouch(View arg0, MotionEvent e) {
				// TODO Auto-generated method stub
				float downX = 0,downY = 0,offestX,offestY;
				switch(e.getAction()){
				case  MotionEvent.ACTION_DOWN:
					downX = e.getX();
					downY = e.getY();
					break;
				case MotionEvent.ACTION_UP:
					offestX = e.getX() - downX;
					offestY = e.getY() - downY;
					if(Math.abs(offestX) > Math.abs(offestY)){
						//左右滑动
						if(offestX < -5){
							//向左滑动
							swipeLeft();
						}else if(offestX > 5){
							//向右滑动
							swipeRight();
						}
					}else{
						//上下滑动
						if(offestY < -5){
							//向上滑动，原点应该在手机屏幕的左上角
							swipeUp();
						}else if(offestY > 5){
							//向下滑动
							swipeDown();
						}
						
					}
					break;
				default:
				}
				return true;
			}
		};
	}
	
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		// TODO Auto-generated method stub
		super.onSizeChanged(w, h, oldw, oldh);
		
		int cardWidth = (Math.min(w,h) - 10)/4;
		
		addCard(cardWidth,cardWidth);
	}
	
	private Card[][] cards = new Card[4][4];
	private void addCard(int w, int h){
		Card c;
		for(int y = 0; y < 4; y++){
			for(int x = 0; x < 4; x++){
				c = new Card(getContext());
				c.setNum(2);
				addView(c,w,h);
				cards[x][y] = c;
			}
		}
	}

	private List<Point> emptyPoints = new ArrayList<Point>();
	private void addRandomNum(){
		emptyPoints.clear();
		for(int y = 0; y < 4; y++){
			for(int x = 0; x < 4; x++){
				
			}
		}
		
	}
	private void swipeLeft(){
		
	}
	
	private void swipeRight(){
			
	}
	
	private void swipeUp(){
		
	}
	
	private void swipeDown(){
		
	}
}
