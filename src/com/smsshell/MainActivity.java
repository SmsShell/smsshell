package com.smsshell;

import com.smsshell.SMSBroadcastReceiver.MessageListener;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ImageView.ScaleType;
import android.widget.TabHost.OnTabChangeListener;
import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.graphics.Color;

/**
 * Demo描述: 利用BroadcastReceiver实现监听短信
 * 
 * 注意权限: <uses-permission android:name="android.permission.RECEIVE_SMS"/>
 * 
 * 详细资料: http://blog.csdn.net/lfdfhl/article/details/8195400
 * 
 */
public class MainActivity extends TabActivity {

	private String strRef;

	private TabHost tabHost = null;  

	private TextView mTextView;
	private SMSBroadcastReceiver mSMSBroadcastReceiver;
	
	// 定义tab的名称
	private static final String TAG_MESSAGE = "消息";
	private static final String TAG_MEDIA = "媒体";
	private static final String TAG_EXPLORE = "发现";
	private static final String TAG_ME = "我";


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		init();

		tabHost = getTabHost();

    	// 设置TAG_MESSAGE
	    tabHost.addTab(tabHost.newTabSpec(TAG_MESSAGE).setIndicator(composeLayout(TAG_MESSAGE,0,5, R.drawable.tabbar_home,Color.WHITE))
	    		.setContent(new Intent().setClass(this, WebViewActivity.class)));
	    
	    // 设置TAG_MEDIA 
    	tabHost.addTab(tabHost.newTabSpec(TAG_MEDIA).setIndicator(composeLayout(TAG_MEDIA, 1,6, R.drawable.tabbar_message_center,Color.WHITE))
	    		.setContent(new Intent().setClass(this, WebViewActivity.class)));
    	
    	// 设置TAG_EXPLORE
	    tabHost.addTab(tabHost.newTabSpec(TAG_EXPLORE).setIndicator(composeLayout(TAG_EXPLORE, 2,7, R.drawable.tabbar_discover,Color.WHITE))
	    		.setContent(new Intent().setClass(this, WebViewActivity.class)));
	    
    	// 设置TAG_ME
	    tabHost.addTab(tabHost.newTabSpec(TAG_ME).setIndicator(composeLayout(TAG_ME, 3,8, R.drawable.tabbar_profile,Color.WHITE))
	    		.setContent(new Intent().setClass(this, WebViewActivity.class)
	    		.putExtra("strUrl","file:///android_asset/aboutus.html")));

		// 设置第一次打开时默认显示的标签，
		tabHost.setCurrentTabByTag(TAG_MESSAGE);

		// 初始化Tab的颜色，和字体的颜色
		updateTab(tabHost);

		/* Change the menu also on ICS when tab is changed */
		tabHost.setOnTabChangedListener(new OnTabChangeListener() {

			public void onTabChanged(String tabId) {

				tabHost.setCurrentTabByTag(tabId);
				Log.i(this.getClass().getName(), "onTabChanged start " + tabId);
				updateTab(tabHost);

			}
		});


		// 加载strRef，但是不会显示，注意哦！！
		strRef = getIntent().getStringExtra("strRef");
		if (strRef==null) {strRef="";}
		if (strRef.equals("BOOT")) {this.finish();}

	}

	/**
	 * Init setOnReceivedMessageListener
	 */
	private void init() {

		// mTextView=(TextView) findViewById(R.id.textView);
		mSMSBroadcastReceiver = new SMSBroadcastReceiver();
		mSMSBroadcastReceiver
				.setOnReceivedMessageListener(new MessageListener() {
					@Override
					public void OnReceived(String message) {
						// mTextView.setText(message);
					}
				});

	}

	
	
    /** 
     * 更新Tab标签的颜色，和字体的颜色 
     * @param tabHost 
     */  
    private void updateTab(final TabHost tabHost) {  

		Log.i(this.getClass().getName(), "updateTab start");

    	for (int i = 0; i < tabHost.getTabWidget().getChildCount(); i++) {  
        	
        	
            View view = tabHost.getTabWidget().getChildAt(i); 
            
            view.setBackgroundColor(Color.parseColor("#333333"));
            //view.setBackgroundDrawable(getResources().getDrawable(R.drawable.tabbar_background));
            
            if (tabHost.getCurrentTab() == i) {
            	
        		Log.i(this.getClass().getName(), "getCurrentTab start" + i);

            	if (i==0){
                    this.setTitle(this.TAG_MESSAGE);

                    ImageView iv = (ImageView)view.findViewById(0);  
            		iv.setImageResource(R.drawable.tabbar_home_highlighted);
            		
            		TextView tv = (TextView)view.findViewById(5);
            		tv.setTextColor(Color.WHITE);  
            	}
            	
            	if (i==1){
                    this.setTitle(this.TAG_MEDIA);

            		ImageView iv = (ImageView)view.findViewById(1);  
            		iv.setImageResource(R.drawable.tabbar_message_center_highlighted);
            		
            		TextView tv = (TextView)view.findViewById(6);
            		tv.setTextColor(Color.WHITE);  
            	}

            	if (i==2){
                    this.setTitle(this.TAG_EXPLORE);

            		ImageView iv = (ImageView)view.findViewById(2);  
            		iv.setImageResource(R.drawable.tabbar_discover_highlighted);
            		
            		TextView tv = (TextView)view.findViewById(7);
            		tv.setTextColor(Color.WHITE);  
            	}

            	if (i==3){
                    this.setTitle(this.TAG_ME);

            		ImageView iv = (ImageView)view.findViewById(3);  
            		iv.setImageResource(R.drawable.tabbar_profile_highlighted);
            		
            		TextView tv = (TextView)view.findViewById(8);
            		tv.setTextColor(Color.WHITE);  
            	}

           
            } else {
            
            	
            	if (i==0){
            		ImageView iv = (ImageView)view.findViewById(0);  
            		iv.setImageResource(R.drawable.tabbar_home);
            		
            		TextView tv = (TextView)view.findViewById(5);
            		tv.setTextColor(Color.WHITE);  
            	}
            	
            	if (i==1){
            		ImageView iv = (ImageView)view.findViewById(1);  
            		iv.setImageResource(R.drawable.tabbar_message_center);
            		
            		TextView tv = (TextView)view.findViewById(6);
            		tv.setTextColor(Color.WHITE);  
            	}

            	if (i==2){
            		ImageView iv = (ImageView)view.findViewById(2);  
            		iv.setImageResource(R.drawable.tabbar_discover);
            		
            		TextView tv = (TextView)view.findViewById(7);
            		tv.setTextColor(Color.WHITE);  
            	}

            	if (i==3){
            		ImageView iv = (ImageView)view.findViewById(3);  
            		iv.setImageResource(R.drawable.tabbar_profile);
            		
            		TextView tv = (TextView)view.findViewById(8);
            		tv.setTextColor(Color.WHITE);  
            	}            	
            	
            } 
            
        }  
    }  	
	
    
    
    
	/**  
     * 这个设置Tab标签本身的布局，需要TextView和ImageView不能重合  
     * s:是文本显示的内容  
     * i:是ImageView的图片位置  
     * 将它设置到setIndicator(composeLayout("开心", R.drawable.coke))中  
     */  
    public View composeLayout(String s,int ivid, int tvid, int i, int color){  
        
		Log.i(this.getClass().getName(), "composeLayout start" + s);

        LinearLayout layout = new LinearLayout(this);  
        layout.setOrientation(LinearLayout.VERTICAL); 
        layout.setGravity(Gravity.CENTER);
        layout.setPadding(0, 5, 0, 5);

        // ImageView
        ImageView iv = new ImageView(this);  
        iv.setId(ivid);
        iv.setImageResource(i);  
        iv.setScaleType(ScaleType.FIT_CENTER);
        layout.addView(iv, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));  

        // TextView
        TextView tv = new TextView(this);  
        tv.setId(tvid);
        tv.setGravity(Gravity.CENTER);
        tv.getPaint().setFakeBoldText(false);
        tv.setText(s);  
        tv.setTextSize(11);
        tv.setTextColor(color);  
        layout.addView(tv, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));  
        
        return layout;  
    }  	
	
}