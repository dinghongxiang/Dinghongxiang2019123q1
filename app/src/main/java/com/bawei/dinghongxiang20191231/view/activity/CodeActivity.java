package com.bawei.dinghongxiang20191231.view.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bawei.dinghongxiang20191231.R;
import com.bawei.dinghongxiang20191231.base.BaseActivity;
import com.bawei.dinghongxiang20191231.mvp.BasePresenter;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CodeActivity extends BaseActivity {


    @BindView(R.id.ma)
    ImageView ma;
    @BindView(R.id.weixin)
    Button weixin;
    @BindView(R.id.qq)
    Button qq;

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

        CodeUtils.init(this);

        EventBus.getDefault().register(this);

        String dhx = "丁鸿翔";

        final Bitmap bitmap = CodeUtils.createImage(dhx, 400, 400, null);
        ma.setImageBitmap(bitmap);


        ma.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Toast.makeText(CodeActivity.this, ""+dhx, Toast.LENGTH_SHORT).show();

                return true;
            }
        });
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_code;
    }


    @OnClick({R.id.weixin, R.id.qq})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.weixin:

                EventBus.getDefault().post("微信");

                break;
            case R.id.qq:
                EventBus.getDefault().post("QQ");

                break;
        }
    }


//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void wei(String s){
//
//        Toast.makeText(this, ""+s, Toast.LENGTH_SHORT).show();
//    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void setQq(String s){

        Toast.makeText(this, ""+s, Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault()!=null){
            EventBus.clearCaches();
        }
    }
}
