package com.bawei.dinghongxiang20191231.presenter;

import com.bawei.dinghongxiang20191231.contract.HomeContract;
import com.bawei.dinghongxiang20191231.entity.HomeEntity;
import com.bawei.dinghongxiang20191231.model.HomeModel;
import com.bawei.dinghongxiang20191231.mvp.BasePresenter;

public class HomePresenter extends BasePresenter<HomeModel, HomeContract.IView> implements HomeContract.IPresenter {
    @Override
    public void getData(String url) {
        model.getData(url, new HomeContract.IModel.IModelCallBack() {
            @Override
            public void success(HomeEntity homeEntity) {
                getView().success(homeEntity);
            }

            @Override
            public void failure(Throwable throwable) {
                getView().failure(throwable);
            }
        });
    }

    @Override
    protected HomeModel initModel() {
        return new HomeModel();
    }
}
