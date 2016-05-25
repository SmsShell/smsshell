package com.smsshell;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;


public class WebViewActivity extends Activity {
	
	private String strUrl;
	private WebView webView;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        
    	super.onCreate(savedInstanceState);

        setContentView(R.layout.webview);
        webView = (WebView) findViewById(R.id.WebView);
        WebSettings webSettings = webView.getSettings();

        webSettings.setJavaScriptEnabled(true);
        webSettings.setPluginsEnabled(true);

        // 设置允许访问文件数据
        webSettings.setAllowFileAccess(true);  
        // 设置支持html5本地存储
        webSettings.setDomStorageEnabled(true);
        // 设置是否保存密码
        webSettings.setSavePassword(false);  
        // 设置支持各种不同的设备
        // webSettings.setUserAgentString("Mozilla/5.0 (iPad; U; CPU OS 3_2 like Mac OS X;en-us) AppleWebKit/531.21.10 (KHTML, like Gecko) Version/4.0.4 Mobile/7B334bSafari/531.21.10"); 
        // 设置放大
        webSettings.setSupportZoom(false);
        // 设置支持缩放
        webSettings.setBuiltInZoomControls(false);
        // 设置视图缩放
        webSettings.setUseWideViewPort(false);
        // 自动适应屏幕宽度
        webSettings.setLoadWithOverviewMode(true);
        // 显示滚动条
        webView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        webView.setHorizontalScrollBarEnabled(false);
        webView.setVerticalScrollBarEnabled(false);
        
       
        // 指定显示控件(class)
         webView.setWebViewClient(new myWebViewClient());

         // 加载url，但是不会显示，注意哦！！
         strUrl = getIntent().getStringExtra("strUrl");
         webView.loadUrl(strUrl);

    }
    

    //为了让回退键管用
    @Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
    	if(keyCode==event.KEYCODE_BACK&&webView.canGoBack()){
    		webView.goBack();
    		return true;
    	}
		return super.onKeyDown(keyCode, event);
	}

    
	/**
     * webView视图客户端
     * @author Administrator
     *
     */
    class myWebViewClient extends WebViewClient{

		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
//			Log.i("url",url);
			webView.loadUrl(url);
			return true;
		}
    	
    }
}