/**
 * 
 */
package com.stayhungry.snake.logic;

import java.util.Vector;

/**
 * @author xiaoyue26
 * 
 */
public class Snake
{
	// 表示蛇有几节
	private int length;
	// speed尚未实现
	private int speed;
	// 蛇可以动态增长
	public Vector<Piece> snake;
	/*
	 * directionX 表示x方向速度 
	 * directionY 表示y方向速度 
	 * 只有一个不是0 不可以用这个控制速度（导致不均匀前进）
	 * 改变速度应该通过改变计数 改变onTimer调用频率 （sleep间隔与动画帧数一致,不变）
	 */
	private int directionX;
	private int directionY;
    //记录一个周期内，control()时候被调用？？？？？
	private Boolean once_flag;

	public Snake()
	{
		setLength(4);
		snake = new Vector<Piece>(4);
		directionX = 1;
		directionY = 0;
		once_flag = true;// 初始状态标志为true表示control调用起效
        
		//head在piece中2行0列处。1表示类型。头部
		Piece head = new Piece(3, 0, 1);
		snake.add(head);
		
		Piece objPiece1 = new Piece(2, 0, 2);
		snake.add(objPiece1);
		
		Piece objPiece2 = new Piece(1, 0, 2);
		snake.add(objPiece2);

		Piece tail = new Piece(0, 0, 3);
		snake.add(tail);

	}
    //构造一个蛇头，一个蛇尾和le-2哥蛇蛇。le>=3;
	public Snake(int le)
	{
		setLength(le);
		snake = new Vector<Piece>(le);
		directionX = 1;
		directionY = 0;
		once_flag = true;// 初始状态标志为true表示control调用起效

		Piece head = new Piece(le - 1, 0, 1);
		snake.add(head);
		int i;
		for (i = 1; i < le - 1; i++)
		{
			Piece objPiece = new Piece(le - 1 - i, 0, 2);
			snake.add(objPiece);
		}
		Piece tail = new Piece(le - 1 - i, 0, 3);
		snake.add(tail);
	}

	public void onTimer()
	{
		move();
		once_flag = true;
	}

	/**
	 * 按首字母排列 direction: 方向 0 down 1 left 2 right 3 up
	 */
	/*
	 * move函数目的：
	 *  1. 前移位置(蛇头、蛇身、蛇尾)
	 *  2. 设定朝向(蛇头、蛇身、蛇尾) 
	 *  3. 设定转身点(蛇身)
	 */
	private void move()
	{
		// 必须从尾巴往前处理;最后处理头部
		for (int i = snake.size() - 1; i > 0; --i)
		{
			Piece pre = snake.get(i - 1);// 正在处理(i-1)的前一个蛇身的位置
			Piece cur = snake.get(i); // 正在处理的位置
			int px = pre.getIndexX(); // 获取前一块的坐标
			int py = pre.getIndexY();
			int cx = cur.getIndexX();// 获取当前块的坐标
			int cy = cur.getIndexY();
			if (i == snake.size() - 1)
			{
				// 假如是尾巴的话，直接前移其位置，返回
				// 如果是转向的尾巴在for循环外处理
				cur.setIndexX(px);
				cur.setIndexY(py);
				continue;
			}
			// 判断cur的移动方向 设置direction 朝向
			switch (px - cx)
			{
			case -1:// 如果x坐标需要减少,说明当前块需要向左
				cur.setDirection(1);
				break;
			case 0:// 如果一样的话 说明要上下移动
				if (py > cy) cur.setDirection(0);// 向下
				else
					cur.setDirection(3); // 向上
				break;
			case 1:// x需要增加,说明当前块需要向右
				cur.setDirection(2);
				break;
			default:// 出错
				break;
			}
			cur.setIndexX(px);// 前移当前块
			cur.setIndexY(py);
		} // 至此已经把除了蛇头以外的位置全部前移(1.完成了除蛇头部分)
			// 把除了蛇头蛇尾以外的朝向全部设定 (2.完成了除蛇头蛇尾部分)
			// 设置头部的位置
		Piece head = snake.get(0);
		int x = head.getIndexX();
		int y = head.getIndexY();
		head.setIndexX(x + directionX); // 两者肯定有一个是0
		head.setIndexY(y + directionY); // (1.完成)
		// 重新处理尾部的方向
		Piece tail = snake.get(snake.size() - 1);
		Piece pre_tail = snake.get(snake.size() - 2);
		tail.setDirection(pre_tail.getDirection()); // (2.完成)
		// 设置转折切换
		setTurn();

	}

	public void setTurn()
	{
		// 设置转身切换
		for (int i = 1; i < snake.size() - 1; i++)
		{
			Piece pre = snake.get(i - 1);
			Piece cur = snake.get(i);
			// 假如当前块和前一块方向相同则没有转身 直接continue
			if (cur.getDirection() == pre.getDirection())
			{
				cur.setTurnDirection(0);// 设置为0表示不转向
				continue;
			}
			int index = 0;
			cur.setTurnDirection(index);// 设置为0表示不转向
			switch (cur.getDirection())
			{
			case 0:// 向下 down
				if (pre.getDirection() == 1)
				{// 前一块向左
					index = 1;
				}
				else
				{// 前一块向右
					index = 2;
				}
				break;
			case 1:// 向左 left
				if (pre.getDirection() == 0)
				{// 前一块向下
					index = 1;
				}
				else
				{// 前一块向上
					index = 2;
				}
				break;
			case 2:// 向右 right
				if (pre.getDirection() == 0)
				{// 前一块向下
					index = 1;
				}
				else
				{// 前一块向上
					index = 2;
				}
				break;
			case 3:// 向上 up
				if (pre.getDirection() == 1)
				{ // 前一块向左
					index = 1;
				}
				else
				{// 前一块向右
					index = 2;
				}
				break;
			default:
				break;
			}
			cur.setTurnDirection(index);
		}
	}

	/*
	 * 吃东西生长的函数 长出来那一块使尾巴伸长
	 */

	public Boolean grow()
	{
		// 获取尾巴和其前一块 (最后一块和倒数第二块)
		// 获取它们的坐标
		Piece obj, preObj;
		obj = snake.get(getLength() - 1);
		preObj = snake.get(getLength() - 2);
		int x = obj.getIndexX();
		int y = obj.getIndexY();
		int pre_x = preObj.getIndexX();
		int pre_y = preObj.getIndexY();

		// 1 表示蛇
		if (pre_x == x) // 长在尾巴的下面或者上面
		obj = new Piece(x, 2 * y - pre_y, 3);
		// 向上时2 * y - pre_y=y+1
		// 向下时2 * y - pre_y=y-1
		else
			// 长在尾巴的左边或者右边
			obj = new Piece(2 * x - pre_x, y, 3);
		// 向左时2 * x - pre_x=x+1
		// 向右时2 * x - pre_x=x-1
		// 把原来的尾巴变身体
		snake.get(snake.size() - 1).setType(2);
		// 上述判断保证吃下去那块长在尾巴后面
		// 未保证尾巴不越出边界
		snake.addElement(obj);
		setLength(getLength() + 1);
		return true;
	}

	public int getLength()
	{
		return length;
	}

	public void setLength(int length)
	{
		this.length = length;
	}

	/*
	 * 方向控制
	 */
	public void control(int sx, int sy)
	{
		/*
		 * 假如方向不被允许 直接返回
         * 假如一个周期内已经调用一次也直接返回
		 */
		if (sx == directionX || directionY == sy || false == once_flag) return;
		directionX = sx;
		directionY = sy;
		once_flag = false;
		/*
		 * 设置头部direction 头部所朝的方向
		 */
		Piece head = snake.get(0);
		if (sy == 1) head.setDirection(0);
		else if (sx == -1) head.setDirection(1);
		else if (sx == 1) head.setDirection(2);
		else
			head.setDirection(3);
	}

	/**
	 * @return the speed
	 */
	public int getSpeed()
	{
		return speed;
	}

	/**
	 * @param speed
	 *            the speed to set
	 */
	public void setSpeed(int speed)
	{
		this.speed = speed;
	}

	/**
	 * @param directionX
	 *            the directionX to set
	 */
	public void setDirectionX(int directionX)
	{
		this.directionX = directionX;
	}

	/**
	 * @param directionY
	 *            the directionY to set
	 */
	public void setDirectionY(int directionY)
	{
		this.directionY = directionY;
	}
	/**
	 * @return the directionX
	 */
	public int getDirectionX()
	{
		return directionX;
	}

	/**
	 * @return the directionY
	 */
	public int getDirectionY()
	{
		return directionY;
	}

	//

}
