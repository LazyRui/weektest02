package com.bawei.wuruitao.contract;

import com.bawei.wuruitao.base.mvp.IBaseModel;
import com.bawei.wuruitao.base.mvp.IBaseView;

/**
 * ProjectName: WuRuitao20200106
 * PackageName: com.bawei.wuruitao.contract
 * ClassName:   CategoryContract
 * Description: Java类的作用
 * Author: Lazy
 * CreateDate: 2020/1/6_8:58
 */
public interface CategoryContract {
    interface IModel extends IBaseModel{
        void getCategoryData(DataCallBack dataCallBack);
        void getByCategoryData(String name,DataCallBack dataCallBack);
        interface DataCallBack{
            void success(Object o);
            void failure(Throwable throwable);
        }
    }

    interface IView extends IBaseView{
        void success(Object o);
        void failure(Throwable throwable);
    }

    interface IPresenter{
        void getCategoryData();
        void getByCategoryData(String name);
    }
}
