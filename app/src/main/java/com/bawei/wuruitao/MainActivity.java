package com.bawei.wuruitao;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bawei.wuruitao.base.BaseActivity;
import com.bawei.wuruitao.contract.CategoryContract;
import com.bawei.wuruitao.greeendao.CategoryBeanDao;
import com.bawei.wuruitao.greeendao.DaoMaster;
import com.bawei.wuruitao.greeendao.DaoSession;
import com.bawei.wuruitao.model.entity.CategoryBean;
import com.bawei.wuruitao.model.entity.CategoryEntity;
import com.bawei.wuruitao.model.entity.ShopByCateGoryEntity;
import com.bawei.wuruitao.presenter.CategoryPresenter;
import com.bawei.wuruitao.utils.NetState;
import com.bawei.wuruitao.view.adapter.LeftRVAdapter;
import com.bawei.wuruitao.view.adapter.RightRVAdapter;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity<CategoryPresenter> implements CategoryContract.IView {

    @BindView(R.id.rv_left)
    RecyclerView rvLeft;
    @BindView(R.id.rv_right)
    RecyclerView rvRight;
    private CategoryBeanDao categoryBeanDao;
    private int countItem;

    @Override
    protected CategoryPresenter initPresenter() {
        return new CategoryPresenter();
    }

    @Override
    protected void initData() {
        //创建数据库
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(this, "product.db");

        DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDb());

        DaoSession daoSession = daoMaster.newSession();

        categoryBeanDao = daoSession.getCategoryBeanDao();


        //判断是否有网
        boolean b = NetState.getInstance().hashNet(this);
        if (b) {
            presenter.getCategoryData();
        } else {
            //无网时从数据库中读取
            List<CategoryBean> categoryBeans = categoryBeanDao.loadAll();
            String json = categoryBeans.get(0).getJson();
            CategoryEntity categoryEntity = new Gson().fromJson(json, CategoryEntity.class);
            List<String> category = categoryEntity.getCategory();
            LeftRVAdapter leftRVAdapter = new LeftRVAdapter(this, category);
            rvLeft.setAdapter(leftRVAdapter);

            leftRVAdapter.setAddOnItemClickListener(new LeftRVAdapter.AddOnItemClickListener() {
                @Override
                public void onItemClickListener(String shopName, int position) {
                    EventBus.getDefault().post(shopName);
                    countItem = position;
                    //Log.e("xxx", position + "");
                }
            });

            String json1 = categoryBeans.get(countItem).getJson();
            ShopByCateGoryEntity shopByCateGoryEntity = new Gson().fromJson(json1, ShopByCateGoryEntity.class);
            List<ShopByCateGoryEntity.DataBean> data = shopByCateGoryEntity.getData();
            RightRVAdapter rightRVAdapter = new RightRVAdapter(this, data);
            rvRight.setAdapter(rightRVAdapter);


        }

    }

    @Override
    protected void initView() {
        rvLeft.setLayoutManager(new LinearLayoutManager(this));
        rvRight.setLayoutManager(new GridLayoutManager(this, 2));
        //i.在Activity创建的时候注册EventBus，在Activity销毁的时候注销注册
        EventBus.getDefault().register(this);
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void success(Object o) {
        if (o != null) {
            if (o instanceof CategoryEntity) {
                CategoryEntity categoryEntity = (CategoryEntity) o;
                if (categoryEntity != null) {
                    String json = new Gson().toJson(categoryEntity);
                    CategoryBean categoryBean = new CategoryBean();
                    categoryBean.setJson(json);
                    categoryBeanDao.insert(categoryBean);
                }

                List<String> category = ((CategoryEntity) o).getCategory();

                // Toast.makeText(this, category.size()+"", Toast.LENGTH_SHORT).show();
                LeftRVAdapter leftRVAdapter = new LeftRVAdapter(this, category);
                rvLeft.setAdapter(leftRVAdapter);

                leftRVAdapter.setAddOnItemClickListener(new LeftRVAdapter.AddOnItemClickListener() {
                    @Override
                    public void onItemClickListener(String shopName, int position) {
                        EventBus.getDefault().post(shopName);
                    }
                });
                presenter.getByCategoryData("生活");
            } else if (o instanceof ShopByCateGoryEntity) {
                ShopByCateGoryEntity shopByCateGoryEntity = (ShopByCateGoryEntity) o;
                if (shopByCateGoryEntity != null) {
                    String json = new Gson().toJson(shopByCateGoryEntity);
                    CategoryBean categoryBean = new CategoryBean();
                    categoryBean.setJson(json);
                    categoryBeanDao.insert(categoryBean);
                }

                List<ShopByCateGoryEntity.DataBean> data = ((ShopByCateGoryEntity) o).getData();
                //Toast.makeText(this, data.size()+"", Toast.LENGTH_SHORT).show();

                RightRVAdapter rightRVAdapter = new RightRVAdapter(this, data);
                rvRight.setAdapter(rightRVAdapter);
            }
        }
    }

    @Override
    public void failure(Throwable throwable) {

    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getEB(String shopName) {
        presenter.getByCategoryData(shopName);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在Activity销毁的时候注销注册
        EventBus.getDefault().unregister(this);
    }
}
