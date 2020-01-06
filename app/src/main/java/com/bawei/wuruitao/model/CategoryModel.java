package com.bawei.wuruitao.model;

import com.bawei.wuruitao.api.CategoryService;
import com.bawei.wuruitao.contract.CategoryContract;
import com.bawei.wuruitao.model.entity.CategoryEntity;
import com.bawei.wuruitao.model.entity.ShopByCateGoryEntity;
import com.bawei.wuruitao.utils.RetrofitUtil;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * ProjectName: WuRuitao20200106
 * PackageName: com.bawei.wuruitao
 * ClassName:   CategoryModel
 * Description: Java类的作用
 * Author: Lazy
 * CreateDate: 2020/1/6_9:01
 */
public class CategoryModel implements CategoryContract.IModel {
    @Override
    public void getCategoryData(DataCallBack dataCallBack) {
        RetrofitUtil.getInstance().createService(CategoryService.class)
                .getData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CategoryEntity>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CategoryEntity categoryEntity) {
                        if (dataCallBack != null) {
                            dataCallBack.success(categoryEntity);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (dataCallBack != null) {
                            dataCallBack.failure(e);
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void getByCategoryData(String name, DataCallBack dataCallBack) {
        RetrofitUtil.getInstance().createService(CategoryService.class)
                .getByData(name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ShopByCateGoryEntity>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ShopByCateGoryEntity shopByCateGoryEntity) {
                        if (dataCallBack != null) {
                            dataCallBack.success(shopByCateGoryEntity);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (dataCallBack != null) {
                            dataCallBack.failure(e);
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


}
