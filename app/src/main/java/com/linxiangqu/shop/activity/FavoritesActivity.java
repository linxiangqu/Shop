package com.linxiangqu.shop.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.linxiangqu.shop.Config;
import com.linxiangqu.shop.R;
import com.linxiangqu.shop.adapter.MyFavoritesRecyclerAdapter;
import com.linxiangqu.shop.bean.user.CollectionDel;
import com.linxiangqu.shop.bean.user.CollectionList;
import com.linxiangqu.shop.common.base.BaseActivity;
import com.linxiangqu.shop.okhttp.OKHttpManager;
import com.linxiangqu.shop.okhttp.SpotsCallBack;

import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

public class FavoritesActivity extends BaseActivity implements View.OnClickListener {
    private ImageButton mToolbar_back;
    private TextView mToolbar_tv;
    private RecyclerView recycler;
    private MyFavoritesRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);
        initToolbar();
        initView();
        request();
        setStatusBar();
    }

    private void request() {
        OKHttpManager.postCollectionList(Config.COLLECTION_LIST, Config.getCacheUserId(this), new SpotsCallBack<String>(this) {
            @Override
            public void onSuccess(Call call, Response response, String s) {
                List<CollectionList> mList = new Gson().fromJson(s, new TypeToken<List<CollectionList>>() {
                }.getType());
                adapter = new MyFavoritesRecyclerAdapter(FavoritesActivity.this, mList);
                recycler.setLayoutManager(new LinearLayoutManager(FavoritesActivity.this));
                recycler.setAdapter(adapter);
                call.cancel();
                adapter.setmOnItemClickListener(new MyFavoritesRecyclerAdapter.OnItemClickListener() {
                    @Override
                    public void OnClick(View v, CollectionList collectionList, int position) {
                        cancelGoodsCollection(collectionList.getGoodsId(), position);
                    }
                });
            }
            @Override
            public void onError(Call call, Response response) {
                showErrorToasts(call, response);
            }
        });
    }

    private void cancelGoodsCollection(int goodsId, final int position) {
        OKHttpManager.postCollectionDel(Config.COLLECTION_DEL, Config.getCacheUserId(this), goodsId, new SpotsCallBack<CollectionDel>(this) {
            @Override
            public void onSuccess(Call call, Response response, CollectionDel collectionDel) {
                if (collectionDel.getStateCode() == 2201)
                    adapter.delGoodsCollection(position);
                showToasts(collectionDel.getMsg());
                call.cancel();
            }

            @Override
            public void onError(Call call, Response response) {
                showErrorToasts(call, response);
            }
        });
    }

    private void initToolbar() {
        mToolbar_back = (ImageButton) findViewById(R.id.toolbar_back);
        mToolbar_tv = (TextView) findViewById(R.id.toolbar_tv);
        mToolbar_tv.setText("我的收藏夹");
        mToolbar_back.setOnClickListener(this);
    }

    private void initView() {
        recycler = (RecyclerView) findViewById(R.id.activity_favorites_recycler);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.toolbar_back:
                finish();
                break;
            default:
                break;
        }
    }
}
