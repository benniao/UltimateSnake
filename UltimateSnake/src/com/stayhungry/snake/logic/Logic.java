/**
 * 
 */
package com.stayhungry.snake.logic;  
import java.io.InputStream;

import org.apache.http.util.EncodingUtils;

import android.R.integer;
import android.content.res.Resources; 
/**
 * @author xiaoyue26
 *
 */
public class Logic {
	//保存游戏中的蛇对象
	private Snake mSnake;
	//元素交互时查询下面这个表table	(或者从后往前画,体现立体感)
    public  Piece[][] table;//储存表里所有元素
    private int w,h;//行数列数 
	private Resources m_res;//资源库
	String map;//地图字符串数据
	
     
    public Logic(int wide,int hight,Resources res)
   	{ 
   	 this.m_res=res;
   	String fileName = "map1.txt"; //文件名字
   	// assets\文件夹下存放地图文件 
   	try{ 
   	   InputStream in = res.getAssets().open(fileName);   	  
   	   int length = in.available();         
   	   byte [] buffer = new byte[length];        
    	in.read(buffer);            
    	map = EncodingUtils.getString(buffer, "UTF-8");   
    	 //把地图布局所有数据存放到map字符串中
    	}		catch(Exception e){ 
   				e.printStackTrace();         
   		}
   		init(wide,hight);	 
   	}
    public void init(int wide,int hight)
	{   
    	w=wide;
    	h=hight;
    	table=new Piece[w][h];	 
    	mSnake=new Snake(3); 
    	loadMap();    	
	}
    public void loadMap() {    
    	if(!map.startsWith("sn"))return;
    	switch (map.charAt(2)) {
		case 'd':
			mSnake.setDirectionX(0);
			mSnake.setDirectionY(1);
			break;
		case 'l':
			mSnake.setDirectionX(-1);
			mSnake.setDirectionY(0);
			break;
		case 'r':
			mSnake.setDirectionX(1);
			mSnake.setDirectionY(0);
			break;
		case 'u':
			mSnake.setDirectionX(0);
			mSnake.setDirectionY(-1);
			break; 
		default: return;
		} 
    	/*
    	 * 依次读取table中每个元素的信息 创建对象存入table
    	 * 并为不同元素设置优先级
    	 */
    	 
    	Boolean comment_flag=false;
    	char cur=' ';
    	int index=2;
    	for (int i = 0; i < w; i++) {
			for (int j = 0; j < h; j++) {
				cur=map.charAt(++index);
				if (cur=='}') { comment_flag=false; --j;continue;}//结束注释
				if (comment_flag){ --j;continue;}//跳过注释
				switch (cur) {
				case '{':
					--j;
					comment_flag=true;
					continue;				 
				case 'o':
					table[i][j]=new AnimationPiece(i, j, 4, "popo" ,this); 
					break;
				case 'x':
					table[i][j]=new AnimationPiece(i, j, 4, "shears",this);
					break;
				case 'i':
					table[i][j]=new AnimationPiece(i, j, 4, "ice",this);
					break;
				case '*':
					table[i][j]=new AnimationPiece(i, j, 4, "star",this);
					break;
				case '%':
					table[i][j]=new AnimationPiece(i, j, 4, "spring",this);
					break;
				case 'a':
					table[i][j]=new Piece(i, j, 4, "apple",this);
					break;
				case 'p':
					table[i][j]=new Piece(i, j, 4, "pear",this);
					break;
				case 'g':
					table[i][j]=new Piece(i, j, 4, "grape",this);
					break;
				case 'e':
					table[i][j]=new Piece(i, j, 4, "egg",this);
					break;
				case '@'://蛇头
					loadSnake(i, j);
					break;
				case '#':
					table[i][j]=new Piece(i, j, 4, "stone",this);
					break;
				case 'v':
					table[i][j]=new Piece(i, j, 4, "stab",this);
					break;
				case 'q':
					table[i][j]=new MovePiece(i, j, 4, "m_egg",this);
					break;
				case 'Q':
					table[i][j]=new MovePiece(i, j, 4, "m_egg",this);
					break;
				case 'h':
					table[i][j]=new MovePiece(i, j, 4, "m_stone",this);
					break;
				case 'H':
					table[i][j]=new MovePiece(i, j, 4, "m_stone",this);
					break;
				case 'w':
					table[i][j]=new MovePiece(i, j, 4, "m_stab",this);
					break;
				case 'W':
					table[i][j]=new MovePiece(i, j, 4, "m_stab",this);
					break;
				case '.'://食物随机产生点
					table[i][j]=new Piece(i,j,0);
					break; 
				case ' ': 
				    continue; 
				default:--j;
					continue; 
				
				}  
		 	 }
		} 
    	
    	String dString=" ";
    	 for (int k = 0; k < table.length; k++) {
			for (int k2 = 0; k2 < table[0].length; k2++) {
				dString+=table[k][k2];
			}
		}
    	 int a=23;
     	a+=22;
	}
    /*
     * 定位蛇的位置：
     * 1.将蛇的每个部位中保存坐标改变
     * 2.把每个部位放入table中相应位置
     */
    private void loadSnake(int x, int y) {
		// TODO Auto-generated method stub
    	int towardX=mSnake.getDirectionX();
    	int towardY=mSnake.getDirectionY();
		for (int i = 0; i < mSnake.getLength(); i++) {
			 Piece cur=mSnake.snake.get(i);
			 cur.setIndexX(x-towardX*i);
			 cur.setIndexY(y-towardY*i);
			 table[x-towardX*i][y-towardY*i]=cur;
		} 
	} 
	public void control(int sx,int sy)
	{ 
        mSnake.control(sx, sy);
	}
	//对table内每个元素执行onTimer() 现阶段只有蛇
    public void onTimer() { 
    	//将蛇尾移除
    	Piece  tail=mSnake.snake.get(mSnake.getLength()-1);
  		int tx=tail.getIndexX();
  		int ty=tail.getIndexY();
  		table[tx][ty]=null; 
  		//检查蛇头若前移是否越界
    	 Piece head=mSnake.snake.get(0);
    	 int x=head.getIndexX()+mSnake.getDirectionX();
    	 int y=head.getIndexY()+mSnake.getDirectionY();
    	 if(!inRange(x, y))    	    	 
    		onDead(-1);   //-1 表示死于超出边界 	
    	 else
    	 { 
    		 mSnake.onTimer();
    		 table[x][y]=head;    		 
       		 int i;
    	    	 for (  i = 1; i < mSnake.getLength(); i++) {    	    		 
    	    		 Piece  cur=mSnake.snake.get(i);
    	    		int cx=cur.getIndexX();
    	    		int cy=cur.getIndexY();
    	    		table[cx][cy]=cur;
    			}   
         }	  
	} 
	private void onDead(int way) {
		// TODO Auto-generated method stub 
	}
	public Snake getmSnake() {
		return mSnake;
	}
	public void setmSnake(Snake mSnake) {
		this.mSnake = mSnake;
	}
	final boolean inRange(int x,int y)
	{
	 if(x>=w||y>=h||x<0||y<0)return false;
	return true;
	} 
	public void grow() {
		// TODO Auto-generated method stub 
		mSnake.grow();
		//蛇已经变长一节,重新加载最后两节在onTimer中已经有操作 所以此处不加载了    	 
	}
	/**
	 * @return the m_res
	 */
	public Resources getM_res() {
		return m_res;
	}
	/**
	 * @param m_res the m_res to set
	 */
	public void setM_res(Resources m_res) {
		this.m_res = m_res;
	}	
	/*
	 * 将传入元素中存放的坐标改为x,y
	 * 并将其放入table[x][y]位置
	 */
	private final void setPiece(Piece element,int x,int y)
	{
	   element.setIndexX(x);
	   element.setIndexY(y);
	   table[x][y]=element;
	}
}