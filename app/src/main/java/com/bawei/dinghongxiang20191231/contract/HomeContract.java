package com.bawei.dinghongxiang20191231.contract;

import com.bawei.dinghongxiang20191231.entity.HomeEntity;
import com.bawei.dinghongxiang20191231.mvp.IBaseModel;
import com.bawei.dinghongxiang20191231.mvp.IBaseView;

public interface HomeContract {

    interface IModel extends IBaseModel {
        void getData(String url,IModelCallBack iModelCallBack);
        interface IModelCallBack{
            void success(HomeEntity homeEntity);
            void failure(Throwable throwable);
        }
    }


    interface IView extends IBaseView {
        void success(HomeEntity homeEntity);
        void failure(Throwable throwable);
    }

    interface IPresenter{
        void getData(String url);
    }
}
