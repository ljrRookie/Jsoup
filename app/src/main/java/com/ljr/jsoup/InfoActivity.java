package com.ljr.jsoup;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class InfoActivity extends AppCompatActivity {

    @BindView(R.id.title_left)
    ImageView mTitleLeft;
    @BindView(R.id.title)
    TextView mTitle;
    @BindView(R.id.size)
    ImageView mSize;
    @BindView(R.id.title_setting)
    ImageView mTitleSetting;
    @BindView(R.id.wb_show_activity)
    WebView mWbShowActivity;
    @BindView(R.id.pb_show_activity)
    ProgressBar mPbShowActivity;
    @BindView(R.id.activity_show)
    LinearLayout mActivityShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        ButterKnife.bind(this);
        initView();
        String link = getIntent().getStringExtra("link");
        initWebView(link);
        initWebSettings();

        initWebViewClient();

        initWebChromeClient();
    }
    private void initWebChromeClient() {
        mWbShowActivity.setWebChromeClient(new WebChromeClient(){
            private Bitmap mDefaultVideoPoster;//默认得视频展示图

            @Override
            public void onReceivedTitle(WebView view, final String title) {
                super.onReceivedTitle(view, title);
                if (mTitle != null){
                    mTitle.post(new Runnable() {
                        @Override
                        public void run() {
                            mTitle.setText(TextUtils.isEmpty(title) ? "加载中..." : title);

                        }
                    });
                }
            }

            @Override
            public Bitmap getDefaultVideoPoster() {
                if (mDefaultVideoPoster == null){
                    mDefaultVideoPoster = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
                    return mDefaultVideoPoster;
                }
                return super.getDefaultVideoPoster();
            }
        });
    }
    private void initWebViewClient() {
        mWbShowActivity.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                mWbShowActivity.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                mPbShowActivity.setVisibility(View.GONE);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(request.toString());
                return true;
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);

            }
        });
    }
    private void initWebSettings() {
        WebSettings settings = mWbShowActivity.getSettings();
        //支持获取手势焦点
        mWbShowActivity.requestFocusFromTouch();
        //支持JS
        settings.setJavaScriptEnabled(true);
        //支持插件
        settings.setPluginState(WebSettings.PluginState.ON);
        //设置适应屏幕
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        //支持缩放
        settings.setSupportZoom(true);
        //隐藏原生得缩放控件
        settings.setDisplayZoomControls(false);
        //支持内容重新布局
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.supportMultipleWindows();
        settings.setSupportMultipleWindows(true);
        //设置缓存模式
        settings.setDomStorageEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);
        settings.setAppCacheEnabled(true);
        settings.setAppCachePath(mWbShowActivity.getContext().getCacheDir().getAbsolutePath());

        //设置可访问文件
        settings.setAllowFileAccess(true);
        //当webview调用requestFocus时为webview设置节点
        settings.setNeedInitialFocus(true);
        //支持自动加载图片
        if (Build.VERSION.SDK_INT >= 19){
            settings.setLoadsImagesAutomatically(true);
        } else {
            settings.setLoadsImagesAutomatically(false);
        }
        settings.setNeedInitialFocus(true);
        //设置编码格式
        settings.setDefaultTextEncodingName("UTF-8");
    }
    private void initWebView(String link) {
        mWbShowActivity.loadUrl(link);

    }

    private void initView() {
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.hide();
        }
        mTitleLeft.setImageResource(R.drawable.ic_back);
    }

    @OnClick(R.id.title_left)
    public void onClick() {
        finish();
    }
}
