package com.jason.mvplib.view.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.jason.mvplib.IMvpBase;
import com.jason.mvplib.presenter.IPresenter;
import com.jason.mvplib.view.iview.IView;

/**
 * Created by jason on 16/12/2.
 */
public abstract class MVPFragment<V extends IView,P extends IPresenter<V>> extends Fragment
        implements IView, IMvpBase<V> {

    protected P presenter;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(presenter == null) {
            presenter= createPresenter();
        }

        presenter.attachView(getMvpView());
    }
//调用 setRetainInstance(true) 方法可保留fragment。
// 已保留的fragment 不会随activity 一起被销毁。相反，它会被一直保留并在需要时原封不动的传递给新的activity。
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.detachView(getRetainInstance());
    }

    protected abstract P createPresenter();

    @Nullable
    @Override
    public V getMvpView() {
        return (V)this;
    }
}
