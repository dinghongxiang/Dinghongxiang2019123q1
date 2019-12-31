package com.bawei.dinghongxiang20191231.mvp;

import java.lang.ref.WeakReference;

public abstract class BasePresenter<M extends IBaseModel,V extends IBaseView> {

    public M model;
    public WeakReference<V> weakReference;


    public BasePresenter(){
        model=initModel();
    }


    public void attch(V v){
        weakReference=new WeakReference<>(v);
    }

    protected abstract M initModel();

    public void death(){
        if (weakReference!=null){
            weakReference.clear();
            weakReference=null;
        }
    }

    public V getView(){
        return weakReference.get();
    }
}
