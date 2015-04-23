package com.example.game2048;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;
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
		setOnTouchListener(new OnTouchListener(){
			float downX = 0,downY = 0,offestX,offestY;
			@Override
			public boolean onTouch(View arg0, MotionEvent e) {
				// TODO Auto-generated method stub
				
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
		});
	}
	
	//在AndroidMainfest固定之后，只执行一次
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		// TODO Auto-generated method stub
		super.onSizeChanged(w, h, oldw, oldh);
		
		int cardWidth = (Math.min(w,h) - 10)/4;
		
		addCard(cardWidth,cardWidth);
		startGame();
	}
	
	private void startGame(){
		for(int y = 0; y < 4; y++){
			for(int x = 0; x < 4; x++){
				cards[x][y].setNum(0);
			}
		}
		MainActivity.getMainActivity().clearScore();
		
		addRandomNum();
		addRandomNum();
		
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
				if(cards[x][y].getNum() <= 0){
					//说明空点，空点才可以添加数字
					emptyPoints.add(new Point(x,y));
				}
			}
		}
		Point p = emptyPoints.remove((int)(Math.random()*emptyPoints.size()));
		cards[p.x][p.y].setNum(Math.random()>0.1?2:4);
	}
	
	private void swipeLeft(){
		boolean merge = false;
		for(int y = 0; y < 4; y++){
			for(int x = 0; x < 4; x++){
				for(int x1 = x+1; x1 < 4; x1++){
					if(cards[x1][y].getNum() > 0){
						if(cards[x][y].getNum() <= 0){
							cards[x][y].setNum(cards[x1][y].getNum());
							cards[x1][y].setNum(0);
							x--;
							merge = true;
							break;
						}else if(cards[x][y].equal(cards[x1][y])){
							//合并
							cards[x][y].setNum(2*cards[x1][y].getNum());
							cards[x1][y].setNum(0);
							
							MainActivity.getMainActivity().addScore(cards[x][y].getNum());
							merge = true;
							break;
						}
					}
				}
			}
		}
		if(merge){
			addRandomNum();
			checkComplete();
		}
	}
	
	private void swipeRight(){
		boolean merge = false;
		for(int y = 0; y < 4; y++){
			for(int x = 0; x < 4; x++){
				for(int x1 = 0; x1 < x; x1++){
					if(cards[x1][y].getNum() > 0){
						if(cards[x][y].getNum() <= 0){
							cards[x][y].setNum(cards[x1][y].getNum());
							cards[x1][y].setNum(0);
							x--;
							merge = true;
							break;
						}else if(cards[x][y].equal(cards[x1][y])){
							//合并
							cards[x][y].setNum(2*cards[x1][y].getNum());
							cards[x1][y].setNum(0);
							MainActivity.getMainActivity().addScore(cards[x][y].getNum());
							merge = true;
							break;
						}
					}
				}
			}
		}	
		if(merge){
			addRandomNum();
			checkComplete();
		}
	}
	
	private void swipeUp(){
		boolean merge = false;
		for(int x = 0; x < 4; x++){
			for(int y = 0; y < 4; y++){
				for(int y1 = y+1; y1 < 4; y1++){
					if(cards[x][y1].getNum() > 0){
						if(cards[x][y].getNum() <= 0){
							cards[x][y].setNum(cards[x][y1].getNum());
							cards[x][y1].setNum(0);
							y--;
							merge = true;
							break;
						}else if(cards[x][y].equal(cards[x][y1])){
							//合并
							cards[x][y].setNum(2*cards[x][y1].getNum());
							cards[x][y1].setNum(0);
							MainActivity.getMainActivity().addScore(cards[x][y].getNum());
							merge = true;
							break;
						}
					}
				}
			}
		}
		if(merge){
			addRandomNum();
			checkComplete();
		}
	}
	
	private void swipeDown(){
		boolean merge = false;
		for(int x = 0; x < 4; x++){
			for(int y = 0; y < 4; y++){
				for(int y1 = 0; y1 < y; y1++){
					if(cards[x][y1].getNum() > 0){
						if(cards[x][y].getNum() <= 0){
							cards[x][y].setNum(cards[x][y1].getNum());
							cards[x][y1].setNum(0);
							y--;
							merge = true;
							break;
						}else if(cards[x][y].equal(cards[x][y1])){
							//合并
							cards[x][y].setNum(2*cards[x][y1].getNum());
							cards[x][y1].setNum(0);
							MainActivity.getMainActivity().addScore(cards[x][y].getNum());
							merge = true;
							break;
						}
					}
				}
			}
		}
		if(merge){
			addRandomNum();
			checkComplete();
		}
	}
	
	private void checkComplete(){
boolean complete = true;
		
		ALL:
		for (int y = 0; y < 4; y++) {
			for (int x = 0; x < 4; x++) {
				if (cards[x][y].getNum()==0||
						(x>0&&cards[x][y].equals(cards[x-1][y]))||
						(x<3&&cards[x][y].equals(cards[x+1][y]))||
						(y>0&&cards[x][y].equals(cards[x][y-1]))||
						(y<3&&cards[x][y].equals(cards[x][y+1]))) {
					
					complete = false;
					break ALL;
				}
			}
		}
		
		if (complete) {
			new AlertDialog.Builder(getContext()).setTitle("hello").setMessage("Game over").setPositiveButton("Again", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					startGame();
				}
			}).show();
		}
	}
}
