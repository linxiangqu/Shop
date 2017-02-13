package com.linxiangqu.shop.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.linxiangqu.shop.Config;
import com.linxiangqu.shop.R;
import com.linxiangqu.shop.adapter.TopicGoodsRecyclerAdapter;
import com.linxiangqu.shop.bean.good.QueryTopicGoods;
import com.linxiangqu.shop.common.IDefineView;
import com.linxiangqu.shop.common.base.BaseActivity;
import com.linxiangqu.shop.okhttp.OKHttpManager;
import com.linxiangqu.shop.okhttp.SpotsCallBack;
import com.squareup.picasso.Picasso;

import okhttp3.Call;
import okhttp3.Response;

public class TopicGoodsActivity extends BaseActivity implements IDefineView, View.OnClickListener {
    private ImageButton mToolbar_back;
    private TextView mToolbar_tv;
    private ImageView img;
    private TextView title, subTitle;
    private RecyclerView mRecyclerView;
    private TopicGoodsRecyclerAdapter mTopicGoodsRecyclerAdapter;
    private int topicId;
    private MaterialRefreshLayout refresh;
    private int page = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topicgoods);
        initToolbar();
        initData();
        initView();
        loadMore();
        initListener();
        initRefreshLayout();
        setStatusBar();
    }

    @Override
    public void initView() {
        img = (ImageView) findViewById(R.id.activity_topicgoods_img);
        title = (TextView) findViewById(R.id.activity_topicgoods_title);
        subTitle = (TextView) findViewById(R.id.activity_topicgoods_subtitle);
        mRecyclerView = (RecyclerView) findViewById(R.id.activity_topicgoods_recyclerview);
        refresh = (MaterialRefreshLayout) findViewById(R.id.activity_topicgoods_refresh);
    }

    @Override
    public void initData() {
        topicId = getIntent().getIntExtra("topicId", 1);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void bindData() {

    }

    public void loadMore() {
        OKHttpManager.postQueryTopicGoods(Config.QUERY_TOPIC_GOODS, topicId, page, 20, new SpotsCallBack<QueryTopicGoods>(this) {
            @Override
            public void onSuccess(Call call, Response response, QueryTopicGoods queryTopicGoods) {
                if (queryTopicGoods.getStateCode() == 0) {
                    title.setText(queryTopicGoods.getTitle());
                    subTitle.setText(queryTopicGoods.getSubTitle());
                    Picasso.with(TopicGoodsActivity.this).load(Config.IP + queryTopicGoods.getImage()).fit().into(img);
                    mTopicGoodsRecyclerAdapter = new TopicGoodsRecyclerAdapter(TopicGoodsActivity.this, queryTopicGoods.getMallTopicGoodsDTOs());
                    mRecyclerView.setLayoutManager(new GridLayoutManager(TopicGoodsActivity.this, 2));
                    mRecyclerView.setAdapter(mTopicGoodsRecyclerAdapter);
                    mTopicGoodsRecyclerAdapter.setOnItemClickListener(new TopicGoodsRecyclerAdapter.OnItemClickListener() {
                        @Override
                        public void onItemChild(View view, int position, QueryTopicGoods.MallTopicGoodsDTOsBean mallTopicGoodsDTOs) {
                            Intent intent = new Intent(TopicGoodsActivity.this, GoodsActivity.class);
                            intent.putExtra("goodsId", mallTopicGoodsDTOs.getGoodsId());
                            startActivity(intent);
                        }
                    });
                } else
                    showToasts(queryTopicGoods.getMsg());
                call.cancel();
                if (page == 1)
                    refresh.finishRefresh();
                else
                    refresh.finishRefreshLoadMore();
            }

            @Override
            public void onError(Call call, Response response) {
                showErrorToasts(call, response);
                if (page == 1)
                    refresh.finishRefresh();
                else
                    refresh.finishRefreshLoadMore();
            }
        });
    }

    private void initRefreshLayout() {
        refresh.setWaveColor(R.color.colorPrimary);
        refresh.setIsOverLay(false);
        refresh.setWaveShow(true);
        refresh.setLoadMore(true);
        refresh.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                page = 1;
                loadMore();
            }

            @Override
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                super.onRefreshLoadMore(materialRefreshLayout);
                page++;
                loadMore();
            }
        });
    }

    @Override
    public void initToolbar() {
        mToolbar_back = (ImageButton) findViewById(R.id.toolbar_back);
        mToolbar_tv = (TextView) findViewById(R.id.toolbar_tv);
        mToolbar_tv.setText(R.string.jrtj);
        mToolbar_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.toolbar_back:
                finish();
                break;
            default:
                break;
        }
    }
}
