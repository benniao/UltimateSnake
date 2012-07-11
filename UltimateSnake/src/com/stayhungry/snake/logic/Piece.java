/**
 * 
 */
package com.stayhungry.snake.logic;

import java.lang.reflect.Field;

import com.stayhungry.libuyi.logic.R;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * @author
 * 
 */
public class Piece implements Cloneable
{ // used in type

	// 地图上每个物体的标识
	// EMPTY 又表示可以产生随机食物的地方。
	// 产生随机食物的地方包括原来放了食物的地方
	protected static final int EMPTY = 0;
	// 蛇头部
	protected static final int HEAD = 1;
	// 蛇身体
	protected static final int BODY = 2;
	// 蛇尾巴
	protected static final int TAIL = 3;
	// 其他物体，包括食物，可以移动的障碍物，围墙，草地，还有特殊食物
	protected static final int other = 4;
	// type记录背景中某点保存物体的类型。取值为1,2,3,0.分别表示上面的意思。
	protected int type;
	/*
	 * @priority 各个元素的优先级（通行） 1、冲撞更高或相同优先级调用onHurt() onHurt() 使元素掉头(后期或者加载头晕动画)
	 * 如果是蛇还要执行扣分检查生命值等操作 2、冲撞更低优先级调用onEat() onEat()调用对方元素的onDestroy()
	 * 并获取对方元素的score(后期可以考虑获取再获取speed) 障碍物score 为负数
	 */
	protected int priority;

	// 保存方块对象的所对应的图片
	private Bitmap image;

	// 该对象在Piece[][]数组中第一维的索引值
	protected int indexX;
	// 该对象在Piece[][]数组中第二维的索引值
	protected int indexY;

	// state属性用于动画切换.一个动画需要三幅图.
	protected int state;
	// 随着direction朝向不同,图片也要切换
	protected int direction;
	// 转身时候的方向 只有0和1两个值,上下或者是左右
	protected int turnDirection;
	// 得到资源，为了得到图片
	protected Resources res;
	// 该piece加载图片的图片名
	protected String imageName;
	public Logic logic;

	// 只设置该Piece对象在数组中的索引值
	public Piece(int indexX, int indexY)
	{
		this.indexX = indexX;// 设置在二维数组中的坐标
		this.indexY = indexY;
		type = 0; // 默认设置为空类型(该位置无东西)
		state = 0;// 动画的第一幅图状态(假如有动画)
		direction = 0;// 方向
		turnDirection = 0; // 转身的方向 ：逆时针
		priority = 0;// 通行的优先级
	}

	public Piece(int indexX, int indexY, int t)
	{
		this.indexX = indexX;// 设置在二维数组中的坐标
		this.indexY = indexY;
		type = t; // 构造时该图片的类型
		state = 0;
		direction = 2;
		turnDirection = 0;
		priority = 0;
	}

	public Piece(int indexX, int indexY, int t, String pic_name, Logic logic)
	{
		this.indexX = indexX;
		this.indexY = indexY;
		type = t;
		state = 0;
		direction = 2;
		turnDirection = 0;
		priority = 0;
		this.logic = logic;
		res = logic.getM_res();
		setImage(pic_name);// 构造时加载图片
	}

	public void setIndexY(int indexY)
	{
		this.indexY = indexY;
	}

	// 主动冲撞者才能调用 优先级低的时候.很受伤
	public void onHurt()
	{
	}

	// 主动冲撞者调用 优先级高的时候
	public void onEat()
	{
	}

	// 假如有动画的时候 需要用onTimer改变state
	public void onTimer()
	{
	}

	/*
	 * onDestroy()将自身状态标志量设为dead, 以便Logic中刷新状态时将其移除出table容器
	 */
	public void onDestroy()
	{
	}

	/*
	 * 通过文件名加载图片Bitmap
	 */
	public void setImage(String pic_name)
	{
		Field[] drawFields = R.drawable.class.getFields();
		for (Field field : drawFields)
		{
			if (field.getName().equals(pic_name))
			{
				try
				{
					int id = field.getInt(R.drawable.class);
					Bitmap curBitmap = BitmapFactory.decodeResource(res, id);
					setImage(curBitmap);
					break;
				}
				catch (IllegalArgumentException e)
				{
					e.printStackTrace();
				}
				catch (IllegalAccessException e)
				{
					e.printStackTrace();
				}
			}
		}
	}

	public Bitmap getImage()
	{
		return image;
	}

	public void setImage(Bitmap image)
	{
		this.image = image;
	}

	/**
	 * @return the direction
	 */
	public int getDirection()
	{
		return direction;
	}

	/**
	 * @param direction
	 *            the direction to set
	 */
	public void setDirection(int direction)
	{
		this.direction = direction;
	}

	/**
	 * @return the turnDirection
	 */
	public int getTurnDirection()
	{
		return turnDirection;
	}

	/**
	 * @param turnDirection
	 *            the turnDirection to set
	 */
	public void setTurnDirection(int turnDirection)
	{
		this.turnDirection = turnDirection;
	}

	/**
	 * @return the priority
	 */
	public int getPriority()
	{
		return priority;
	}

	/**
	 * @param priority
	 *            the priority to set
	 */
	public void setPriority(int priority)
	{
		this.priority = priority;
	}

	/**
	 * @return the imageName
	 */
	public String getImageName()
	{
		return imageName;
	}

	/**
	 * @param imageName
	 *            the imageName to set
	 */
	public void setImageName(String imageName)
	{
		this.imageName = imageName;
	}

	/**
	 * @return the type
	 */
	public int getType()
	{
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(int type)
	{
		this.type = type;
	}

	/**
	 * @return the state
	 */
	public int getState()
	{
		return state;
	}

	/**
	 * @param state
	 *            the state to set
	 */
	public void setState(int state)
	{
		this.state = state;
	}

	public int getIndexX()
	{
		return indexX;
	}

	public void setIndexX(int indexX)
	{
		this.indexX = indexX;
	}

	public int getIndexY()
	{
		return indexY;
	}

}
