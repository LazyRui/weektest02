package com.bawei.wuruitao.base.mvp;

import java.lang.ref.WeakReference;

/**
 * ProjectName: WuRuitao20200106
 * PackageName: com.bawei.wuruitao.base.mvp
 * ClassName:   BasePresenter
 * Description: Java类的作用
 * Author: Lazy
 * CreateDate: 2020/1/6_8:53
 */
public abstract class BasePresenter<M extends IBaseModel, V extends IBaseView> {
    //vi.在基类中封装初始化P层的方法，
    protected M model;
    private WeakReference<V> weakReference;

    public BasePresenter() {
        model = initModel();
    }

    public void attach(V v) {
        weakReference = new WeakReference<>(v);
    }


    protected abstract M initModel();

    //并在基类中解决MVP内存泄漏
    public void detach() {
        if (weakReference != null) {
            weakReference.clear();
            weakReference = null;
        }
    }

    public V getView(){
        return weakReference.get();
    }
}
