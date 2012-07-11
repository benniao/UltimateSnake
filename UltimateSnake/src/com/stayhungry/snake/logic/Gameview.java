
package com.stayhungry.snake.logic;

import com.stayhungry.libuyi.logic.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.GestureDetector.OnGestureListener;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Toast;

/**
 * @author xiaoyue26
 * 
 */
public class Gameview extends SurfaceView implements SurfaceHolder.Callback,
		Runnable, OnTouchListener, OnGestureListener
{

	// 行数列数
	public int w, h;
	// 背景图片
	private Bitmap back = BitmapFactory.decodeResource(
			Gameview.this.getResources(), R.drawable.bigbg2);
	// 蛇图 3*4 蛇头、蛇身、蛇尾*4个方向
	private Bitmap[][] snake;
	// 转身结点图片 4*2
	private Bitmap[][] turn;
	//
	SurfaceHolder surfaceHolder;
	// 监听手势
	private GestureDetector detector;
	// 游戏逻辑层对象
	private Logic logic;

	/**
	 * @param context
	 */
	public Gameview(Context context)
	{
		super(context);
		surfaceHolder = this.getHolder();
		// 为SurfaceHolder添加一个回调函数
		surfaceHolder.addCallback(this);
		this.setFocusable(true);
		// new LearnGestureListener(this)
		detector = new GestureDetector(this);

		setOnTouchListener(this);
		setClickable(true);
		setLongClickable(true);
		detector.setIsLongpressEnabled(true);
		// --------加载图片-------硬编码（待修改,移植性太差）
		//把蛇的各个部位图片保存到snake中
		snake = new Bitmap[3][4];
		//把蛇转身处的图片保存到turn中
		turn = new Bitmap[4][2];
		//必须保证图片按照既定顺序排放。即文件名不能改变
		int first_snake = R.drawable.snake1down;
		for (int i = 0; i < snake.length; i++)
		{
			for (int j = 0; j < snake[i].length; j++)
			{
				snake[i][j] = BitmapFactory.decodeResource(
						Gameview.this.getResources(), first_snake
								+ snake[i].length * i + j);
			}
		}

		int first_turn = R.drawable.t_down_left;
		for (int i = 0; i < turn.length; i++)
		{
			for (int j = 0; j < turn[i].length; j++)
			{
				turn[i][j] = BitmapFactory.decodeResource(
						Gameview.this.getResources(), first_turn
								+ turn[i].length * i + j);
			}
		}
		// --------加载图片-------硬编码
	}

	/**
	 * @param context
	 * @param attrs
	 */
	public Gameview(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param context
	 * @param attrs
	 * @param defStyle
	 */
	public Gameview(Context context, AttributeSet attrs, int defStyle)
	{
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	public void run()
	{
		// TODO Auto-generated method stub
		while (true)
		{ // 死循环 来不停地画
			try
			{
				Thread.sleep(600);
			}
			catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// 同步锁 保证同一时间只有一个线程进入临界区执行代码
			synchronized (surfaceHolder)
			{
				onTimer();
				Draw();
			}
		}

	}

	private void onTimer()
	{
		// TODO Auto-generated method stub
		logic.onTimer();
	}

	// 绘图方法
	private void Draw()
	{
		// TODO Auto-generated method stub
		// 锁定画布 得到Canvas
		Canvas canvas = surfaceHolder.lockCanvas();

		if (surfaceHolder == null || canvas == null)
		{
			return;
		}

		canvas.drawBitmap(back, 0, 0, null); // 画背景
		for (int j = 0; j < logic.table[0].length; j++)
		{
			for (int i = 0; i < logic.table.length; i++)
			{
				Piece curPiece = logic.table[i][j];
				if (curPiece == null || curPiece.getType() == 0) continue;
				Bitmap curPic;
				int type = curPiece.getType() - 1;
				if (type == 3)
				{
					curPic = curPiece.getImage();
					if (curPic == null) continue;
					canvas.drawBitmap(curPic, i * 40, j * 40, null);
				}
				else
				{
					int toward = curPiece.getDirection();
					int c = curPiece.getTurnDirection();
					if (0 == c)
					{
						curPic = snake[type][toward];
					}
					else
					{
						curPic = turn[toward][c - 1];
					}
					canvas.drawBitmap(curPic, i * 40, j * 40, null);
				}
			}
		}

		// 解锁画布
		surfaceHolder.unlockCanvasAndPost(canvas);
		surfaceHolder.lockCanvas(new Rect(0, 0, 0, 0));
		surfaceHolder.unlockCanvasAndPost(canvas);
	}

	// 在surface大小改变时激发
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height)
	{
		// TODO Auto-generated method stub

	}

	public void surfaceCreated(SurfaceHolder holder)
	{
		// 开启视图线程
		new Thread(this).start();
		// logic=new Logic(w, h,Gameview.this.getResources());
		logic = new Logic(12, 15, Gameview.this.getResources());
	}

	public void surfaceDestroyed(SurfaceHolder holder)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public boolean onTouchEvent(MotionEvent event)
	{
		// Toast.makeText( this.getContext() , "onTouchEvent" ,
		// Toast.LENGTH_LONG).show();
		/*
		 * Toast.makeText( this.getContext() , "onTouchEvent" ,
		 * Toast.LENGTH_LONG).show(); if (detector.onTouchEvent(event)) {
		 * event.setAction(MotionEvent.ACTION_CANCEL); return true; } else
		 * return false;
		 */
		// return super.onTouchEvent(event);

		Toast.makeText(this.getContext(), "onTouchEvent", Toast.LENGTH_LONG)
				.show();
		return false;

	}
    //滑动函数。控制蛇的移动方向
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY)
	{
		// TODO Auto-generated method stub
		if (velocityX > 200)
		{
			logic.control(1, 0);
		}
		else if (velocityX < -200) logic.control(-1, 0);

		if (velocityY < -200) logic.control(0, -1);
		else if (velocityY > 200) logic.control(0, 1);

		Toast.makeText(this.getContext(), "onFling", Toast.LENGTH_LONG).show();
		return false;
	}

	public boolean onDown(MotionEvent e)
	{
		// TODO Auto-generated method stub
		// Toast.makeText( this.getContext() , "onDown" ,
		// Toast.LENGTH_LONG).show();
		return false;
	}

	public void onLongPress(MotionEvent e)
	{
		// TODO Auto-generated method stub
		logic.grow();
		Toast.makeText(this.getContext(), "onLongPress", Toast.LENGTH_LONG)
				.show();

	}

	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY)
	{
		// TODO Auto-generated method stub
		// Toast.makeText( this.getContext() , "onScroll" ,
		// Toast.LENGTH_LONG).show();
		return false;
	}

	public void onShowPress(MotionEvent e)
	{
		// TODO Auto-generated method stub
		Toast.makeText(this.getContext(), "onShowPress", Toast.LENGTH_LONG)
				.show();

	}

	public boolean onSingleTapUp(MotionEvent e)
	{
		// TODO Auto-generated method stub
		Toast.makeText(this.getContext(), "onSingleTapUp", Toast.LENGTH_LONG)
				.show();
		return false;
	}

	public boolean onTouch(View v, MotionEvent event)
	{
		// TODO Auto-generated method stub
		// Toast.makeText( this.getContext() , "onTouch" ,
		// Toast.LENGTH_LONG).show();
		if (detector == null) Toast.makeText(this.getContext(), "NULL",
				Toast.LENGTH_LONG).show();
		else
			detector.onTouchEvent(event);
		return true;
	}

}
