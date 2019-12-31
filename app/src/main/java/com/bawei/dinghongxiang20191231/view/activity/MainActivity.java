package com.bawei.dinghongxiang20191231.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bawei.dinghongxiang20191231.R;
import com.bawei.dinghongxiang20191231.adapter.HomeAdapter;
import com.bawei.dinghongxiang20191231.api.Api;
import com.bawei.dinghongxiang20191231.base.BaseActivity;
import com.bawei.dinghongxiang20191231.contract.HomeContract;
import com.bawei.dinghongxiang20191231.entity.HomeEntity;
import com.bawei.dinghongxiang20191231.presenter.HomePresenter;
import com.bawei.dinghongxiang20191231.utils.OkHttpUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity<HomePresenter> implements HomeContract.IView {


    @BindView(R.id.dianji)
    TextView dianji;
    @BindView(R.id.rv)
    RecyclerView rv;

    @Override
    protected void initData() {
        presenter.getData(Api.BASE_URL);

        dianji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Intent intent = new Intent(MainActivity.this, CodeActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void initView() {
        rv.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected HomePresenter initPresenter() {
        return new HomePresenter();
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void success(HomeEntity homeEntity) {

        if (OkHttpUtils.getInstance().getNEt(this)){
            final HomeAdapter homeAdapter = new HomeAdapter(this, homeEntity.getRanking());

            rv.setAdapter(homeAdapter);


            homeAdapter.setRevCallBack(new HomeAdapter.RevCallBack() {
                @Override
                public void onclick(String name) {
                    Toast.makeText(MainActivity.this, ""+name, Toast.LENGTH_SHORT).show();
                }
            });
        }else {
            Toast.makeText(this, "没有网了", Toast.LENGTH_SHORT).show();
        }



    }

    @Override
    public void failure(Throwable throwable) {

    }


}
