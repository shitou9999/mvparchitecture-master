package com.jason.mvplib.view.activity;

import android.os.Bundle;

import com.jason.baseapp.BaseActivity;
import com.jason.mvplib.IMvpBase;
import com.jason.mvplib.presenter.IPresenter;
import com.jason.mvplib.view.iview.IView;

/**
 * 主要思想是 IView 会关联一个 IPresenter, 并且管理 IPresenter 的生命周期。
 * 大家从上面的代码片段可以看到， 通常 presenter 是绑定在该生命周期上。
 * 所有的初始化或者清理工作都放在 presenter.onAttach() 和 presenter.detach() 上进行。
 * 想大家注意到 IPresenter 是一个接口。 我们还提供一个 BasePresenter,  它只持有 View 的弱引用， 从而避免内存泄漏。
 * 所有，当 presenter 想要调用 view 的方法是，
 * 我们需要判断 isViewPresenter() 并使用 getView()来获取引用，以坚持view是否连接当了 presenter。
 */
public abstract class MVPActivity<V extends IView, P extends IPresenter<V>>
        extends BaseActivity implements IView, IMvpBase<V>{

    protected P presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = createPresenter();
        presenter.attachView(getMvpView());//所有的初始化
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView(false);//清理工作
    }

    public abstract P createPresenter();

    @Override
    public V getMvpView() {
        return (V)this;
    }
}

/*
当然我们经常可能遇到屏幕旋转的问题， 这样一般处理数据持久化问题，
一般的做法是在 onSaveInstanceState()处理， 简单做法记录数据。
当然我们可以通过状态来修复view的状态。*/