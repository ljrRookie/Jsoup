package com.ljr.jsoup.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ljr.jsoup.R;
import com.ljr.jsoup.bean.InfoBean;

/**
 * Created by LinJiaRong on 2017/8/18.
 * TODO：
 */

public class JsoupAdapter extends BaseQuickAdapter<InfoBean,BaseViewHolder> {

private Context mContext;
    public JsoupAdapter(Context context) {
        super(R.layout.item_home);
        this.mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, InfoBean item) {
helper.setText(R.id.tv_title,item.getTitle())
        .setText(R.id.tv_content,item.getContent())
        .setText(R.id.tv_read,item.getReadNum())
        .setText(R.id.tv_talk,item.getTalkNum())
        .setText(R.id.tv_bookmark,item.getBookMarkNum())
        .setText(R.id.tv_auther_name,item.getAuthorName())
        .setText(R.id.tv_data,item.getTime())
        .addOnClickListener(R.id.iv_title_img)
        .addOnClickListener(R.id.tv_auther_name);

        Glide.with(mContext).load(item.getAuthorImg()).into((ImageView) helper.getView(R.id.iv_author_img));
        Glide.with(mContext).load(item.getTitleImg()).into((ImageView) helper.getView(R.id.iv_title_img));
    }

}
