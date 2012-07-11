/**
 * 
 */
package com.stayhungry.snake.logic; 
import java.lang.reflect.Field;

import com.stayhungry.libuyi.logic.R;

import android.graphics.Bitmap; 
import android.graphics.BitmapFactory;
/**
 * @author xiaoyue26
 * 移动动画元素
 */
public class MovePiece extends Piece {
	//存放12幅图片 以形成动画
	/**
	 * @uml.property  name="moveBitmaps"
	 */
	private Bitmap[][] moveBitmaps;
	//移动速度
	/**
	 * @uml.property  name="speed"
	 */
	private int speed;
	/**
	 * @param indexX
	 * @param indexY
	 */
	public MovePiece(int indexX, int indexY) {
		super(indexX, indexY);
		moveBitmaps=new Bitmap[4][3];
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param indexX
	 * @param indexY
	 * @param t
	 */
	public MovePiece(int indexX, int indexY, int t) {
		super(indexX, indexY, t); 
		moveBitmaps=new Bitmap[4][3];
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param indexX
	 * @param indexY
	 * @param t
	 * @param pic_name
	 * 根据文件名前缀pic_name加载12幅图
	 */
	public MovePiece(int indexX, int indexY, int t,String pic_name,Logic logic) {
		super(indexX, indexY, t);
		moveBitmaps=new Bitmap[4][3];
		this.logic=logic;
		res=logic.getM_res();
		setMoveBitmaps(pic_name);
		// TODO Auto-generated constructor stub
	} 
	private void setMoveBitmaps(String pic_name) {
		// TODO Auto-generated method stub 
		Field[] drawFields=R.drawable.class.getFields();
		int id=0; 
		for(Field field:drawFields)
	   	    {
	   		 if (field.getName().startsWith(pic_name)) 
	   		   {  
	   			 try {  id=field.getInt(R.drawable.class);break; } 
	   			 catch (IllegalArgumentException e)  
	   				{e.printStackTrace(); } 
	   			 catch (IllegalAccessException e)  
	   				{e.printStackTrace(); } 
				}
	   	      } 
		for (int i = 0; i < 4; i++) { 
			for (int j = 0; j < 3; j++) { 
				Bitmap curBitmap=BitmapFactory.decodeResource(res, id+j+i*3);
				  moveBitmaps[i][j]=curBitmap;  
	    }
	  }
	} 
	/**
	 * @return  the moveBitmaps
	 * @uml.property  name="moveBitmaps"
	 */
	public Bitmap[][] getMoveBitmaps() {
		return moveBitmaps;
	}

	/**
	 * @param moveBitmaps  the moveBitmaps to set
	 * @uml.property  name="moveBitmaps"
	 */
	public void setMoveBitmaps(Bitmap[][] moveBitmaps) {
		this.moveBitmaps = moveBitmaps;
	}

	/**
	 * @return  the speed
	 * @uml.property  name="speed"
	 */
	public int getSpeed() {
		return speed;
	}

	/**
	 * @param speed  the speed to set
	 * @uml.property  name="speed"
	 */
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	@Override
	public void onTimer()
	{   //通过改变state 改变Image里存放的图片 形成动态效果
		//同时考虑朝向
		state=(state+1)%3; 
		setImage(moveBitmaps[direction][state]); 
		move();
	}

	private void move() {
		// TODO Auto-generated method stub 
	}

	/* (non-Javadoc)
	 * @see snake.july.namespace.Piece#getImage()
	 */
	@Override
	public Bitmap getImage() {
		// TODO Auto-generated method stub
		return moveBitmaps[direction][state];
	}
	
}
