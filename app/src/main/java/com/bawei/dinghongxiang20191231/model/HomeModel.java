package com.bawei.dinghongxiang20191231.model;

import com.bawei.dinghongxiang20191231.contract.HomeContract;
import com.bawei.dinghongxiang20191231.entity.HomeEntity;
import com.bawei.dinghongxiang20191231.utils.OkHttpUtils;
import com.google.gson.Gson;

public class HomeModel implements HomeContract.IModel {
    @Override
    public void getData(String url, IModelCallBack iModelCallBack) {
        OkHttpUtils.getInstance().doGet(url, new OkHttpUtils.OkHttpCallBack() {
            @Override
            public void success(String json) {
                final HomeEntity homeEntity = new Gson().fromJson(json, HomeEntity.class);
                iModelCallBack.success(homeEntity);
            }

            @Override
            public void failure(Throwable throwable) {
                iModelCallBack.failure(throwable);
            }
        });
    }
}
