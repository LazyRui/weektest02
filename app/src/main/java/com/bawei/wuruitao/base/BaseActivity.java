package com.bawei.wuruitao.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bawei.wuruitao.base.mvp.BasePresenter;
import com.bawei.wuruitao.base.mvp.IBaseView;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * ProjectName: WuRuitao20200106
 * PackageName: com.bawei.wuruitao.base
 * ClassName:   BaseActivity
 * Description: Java类的作用
 * Author: Lazy
 * CreateDate: 2020/1/6_8:56
 */
public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements IBaseView {

    protected P presenter;
    private Unbinder bind;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutId());
        bind = ButterKnife.bind(this);
        presenter = initPresenter();
        if (presenter != null) {
            presenter.attach(this);
        }

        initView();
        initData();
    }

    protected abstract P initPresenter();

    protected abstract void initData();

    protected abstract void initView();

    protected abstract int layoutId();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.detach();
        }
        if (bind != null) {
            bind.unbind();
        }
    }
}
