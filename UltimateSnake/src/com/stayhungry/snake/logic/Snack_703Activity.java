package com.stayhungry.snake.logic;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;  
import android.view.KeyEvent;  
  
public class Snack_703Activity extends Activity  {
    /**
	 * Called when the activity is first created.
	 * @uml.property  name="mySurfaceView"
	 * @uml.associationEnd  
	 */
	
	public Gameview mySurfaceView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        mySurfaceView=new Gameview(this); 
        //---------------------------
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int screenWidth=metrics.widthPixels;            //��Ļ���
        int screenHeight=metrics.heightPixels;   //��Ļ�߶�
        mySurfaceView.w=screenWidth/40;
        mySurfaceView.h=screenHeight/40;  
      //---------------------------
        setContentView(mySurfaceView);    
        /*Snack_703Activity.this.runOnUiThread(new Runnable() {
			//���̲߳���ʾ��
			public void run() {
				// TODO Auto-generated method stub
				
			}
		});*/
      //  setContentView(R.layout.main);
    }   
    @Override  
    public boolean onKeyDown(int keyCode, KeyEvent event) {  
        // TODO Auto-generated method stub  
        if(keyCode==KeyEvent.KEYCODE_BACK){  
            finish();  
        }  
        //return super.onKeyDown(keyCode, event);
        return true;
    }
    
 
}
	 
 