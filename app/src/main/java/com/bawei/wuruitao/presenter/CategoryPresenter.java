package com.bawei.wuruitao.presenter;

import com.bawei.wuruitao.base.mvp.BasePresenter;
import com.bawei.wuruitao.contract.CategoryContract;
import com.bawei.wuruitao.model.CategoryModel;

/**
 * ProjectName: WuRuitao20200106
 * PackageName: com.bawei.wuruitao.presenter
 * ClassName:   CategoryPresenter
 * Description: Java类的作用
 * Author: Lazy
 * CreateDate: 2020/1/6_9:05
 */
public class CategoryPresenter extends BasePresenter<CategoryModel, CategoryContract.IView> implements CategoryContract.IPresenter {
    @Override
    protected CategoryModel initModel() {
        return new CategoryModel();
    }

    @Override
    public void getCategoryData() {
        model.getCategoryData(new CategoryContract.IModel.DataCallBack() {
            @Override
            public void success(Object o) {
                getView().success(o);
            }

            @Override
            public void failure(Throwable throwable) {
                getView().failure(throwable);
            }
        });
    }

    @Override
    public void getByCategoryData(String name) {
        model.getByCategoryData(name,new CategoryContract.IModel.DataCallBack() {
            @Override
            public void success(Object o) {
                getView().success(o);
            }

            @Override
            public void failure(Throwable throwable) {
                getView().failure(throwable);
            }
        });
    }
}
