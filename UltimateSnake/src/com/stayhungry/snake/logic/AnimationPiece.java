/**
 * 
 */
package com.stayhungry.snake.logic;

import java.lang.reflect.Field;

import com.stayhungry.libuyi.logic.R;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * @author xiaoyue26 固定动画元素
 */
public class AnimationPiece extends Piece
{
	//一个动画有三张bitmap即可
	private Bitmap anim_image[];

	/**
	 * @param indexX
	 * @param indexY
	 */
	public AnimationPiece(int indexX, int indexY)
	{
		super(indexX, indexY);
		anim_image = new Bitmap[3];
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param indexX
	 * @param indexY
	 * @param t
	 */
	public AnimationPiece(int indexX, int indexY, int t)
	{
		super(indexX, indexY, t);
		anim_image = new Bitmap[3];
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param indexX
	 * @param indexY
	 * @param t
	 * @param pic_name
	 *            把pic_name对应的三张图片加载到对象中保存
	 */
	public AnimationPiece(int indexX, int indexY, int t, String pic_name,
			Logic logic)
	{
		super(indexX, indexY, t);
		anim_image = new Bitmap[3];
		this.logic = logic;
		res = logic.getM_res();
		setAnim_image(pic_name);
	}

	@Override
	public void onTimer()
	{ // 通过改变state 改变Image里存放的图片 形成动态效果
		setState((state + 1) % 3);
		setImage(anim_image[state]);
	}

	/**
	 * @return the anim_image
	 */
	public Bitmap[] getAnim_image()
	{
		return anim_image;
	}

	/**
	 * @param anim_image
	 *            the anim_image to set
	 */
	public void setAnim_image(Bitmap anim_image[])
	{
		this.anim_image = anim_image;
	}

	/*
	 * 通过三幅图片文件名共同的前缀"pic_name" 加载三幅图片(形成动态效果)
	 */
	public void setAnim_image(String pic_name)
	{
		Field[] drawFields = R.drawable.class.getFields();
		int id = 0;
		for (Field field : drawFields)
		{ /*
		 * 与父类中的equal不同, startsWith获取第一副以pic_name开头的图片id(int类型)
		 */
			if (field.getName().startsWith(pic_name))
			{ // 一旦找到前缀符合的图片,存储id并跳出for循环
				try
				{
					id = field.getInt(R.drawable.class);
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
		for (int i = 0; i < 3; i++)
		{
			Bitmap curBitmap = BitmapFactory.decodeResource(res, id + i);
			anim_image[i] = curBitmap;

		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see snake.july.namespace.Piece#getImage()
	 */
	@Override
	public Bitmap getImage()
	{
		// TODO Auto-generated method stub
		return anim_image[state];
	}

}
