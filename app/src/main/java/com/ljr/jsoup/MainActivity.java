package com.ljr.jsoup;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.ljr.jsoup.Utils.LogUtils;
import com.ljr.jsoup.adapter.JsoupAdapter;
import com.ljr.jsoup.bean.InfoBean;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.rv_data)
    RecyclerView mRvData;
    @BindView(R.id.refresh)
    SwipeRefreshLayout mRefresh;
    @BindView(R.id.title_left)
    ImageView mTitleLeft;
    @BindView(R.id.title)
    TextView mTitle;
    @BindView(R.id.title_setting)
    ImageView mTitleSetting;
    private Document document;
    private List<InfoBean> mInfoBeanList;
    private static final String TAG = "MainActivity";
    private JsoupAdapter mJsoupAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mInfoBeanList = new ArrayList<>();
        initView();
        getJsoupData();


    }

    private void initView() {
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.hide();
        }
        mJsoupAdapter = new JsoupAdapter(MainActivity.this);
        mJsoupAdapter.openLoadAnimation();
        mRvData.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        mRvData.setAdapter(mJsoupAdapter);
        mRvData.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch(view.getId()){
                    case R.id.iv_title_img :
                        Intent title = new Intent(MainActivity.this, InfoActivity.class);
                        title.putExtra("link", ((InfoBean)adapter.getItem(position)).getTitleLink());
                        startActivity(title);
                           break;
                    case R.id.tv_auther_name :
                        Intent author = new Intent(MainActivity.this, InfoActivity.class);
                        author.putExtra("link", ((InfoBean)adapter.getItem(position)).getAuthorLink());
                        startActivity(author);
                        break;
                    default:
                           break;
                }
            }
        });

        mRefresh.setColorSchemeColors(Color.RED, Color.YELLOW);
        mRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getJsoupData();
            }
        });
    }

    private void getJsoupData() {
        mRefresh.setRefreshing(true);
        mInfoBeanList.clear();
        new Thread() {
            @Override
            public void run() {
                super.run();

                try {
                    document = Jsoup.connect("http://www.jcodecraeer.com/plus/list.php?tid=18")
                            .timeout(10000)
                            .get();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Elements noteList = document.select("ul.archive-list");
                if (noteList != null) {
                    Elements li = noteList.select("li.archive-item");
                    for (Element element : li) {
                        String[] num = element.select("div.archive-detail").select("ul").select("li.list-msg").select("span.glyphicon-class").text().split(" ");
                        String readNum = null, talkNum = null, bookMarkNum = null;
                        for (int i = 0; i < num.length; i++) {
                            readNum = num[0];
                            talkNum = num[1];
                            bookMarkNum = num[2];
                        }
                        String authorName = element.select("div.archive-detail").select("ul").select("li.list-user").select("span").text();
                        String authorLink = element.select("div.archive-detail").select("ul").select("a").attr("abs:href");
                        String time = element.select("div.archive-detail").select("div.archive-data").select("span.glyphicon-class").text();
                        String authorImg = element.select("div.archive-detail").select("ul").select("img").attr("abs:src");
                        String title = element.select("div.archive-detail").select("a[href]").attr("title");
                        String titleImg = element.select("div.covercon").select("img").attr("abs:src");
                        String titleLink = element.select("div.archive-text").select("a").attr("abs:href");
                        String content = element.select("div.archive-detail").select("p").text();
                        InfoBean infoBean = new InfoBean(authorName, authorLink, time, authorImg, title,
                                titleImg, titleLink, content, readNum, talkNum, bookMarkNum);
                        mInfoBeanList.add(infoBean);
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                        mJsoupAdapter.setNewData(mInfoBeanList);
                            mRefresh.setRefreshing(false);
                        }
                    });
                } else {
                    LogUtils.e("没有数据！！", "没有数据");
                }

            }
        }.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @OnClick({R.id.title_left, R.id.title_setting})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.title_left:
                break;
            case R.id.title_setting:
                break;
        }
    }
}
