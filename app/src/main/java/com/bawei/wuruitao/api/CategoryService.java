package com.bawei.wuruitao.api;

import com.bawei.wuruitao.model.entity.CategoryEntity;
import com.bawei.wuruitao.model.entity.ShopByCateGoryEntity;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * ProjectName: WuRuitao20200106
 * PackageName: com.bawei.wuruitao.api
 * ClassName:   CategoryService
 * Description: Java类的作用
 * Author: Lazy
 * CreateDate: 2020/1/6_9:00
 */
public interface CategoryService {

    @GET(Api.CATEGORY_URL)
    Observable<CategoryEntity> getData();

    @GET(Api.SHOPBYCATEGORY_URL)
    Observable<ShopByCateGoryEntity> getByData(@Query("category") String name);

}
