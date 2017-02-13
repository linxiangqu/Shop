package com.linxiangqu.shop.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.linxiangqu.shop.Config;
import com.linxiangqu.shop.R;
import com.linxiangqu.shop.adapter.ReplyRecyclerAdapter;
import com.linxiangqu.shop.bean.platform.GetMallBbsReplyList;
import com.linxiangqu.shop.common.IDefineView;
import com.linxiangqu.shop.common.base.BaseActivity;
import com.linxiangqu.shop.okhttp.OKHttpManager;
import com.linxiangqu.shop.okhttp.SpotsCallBack;
import com.linxiangqu.shop.utils.PicassoTransformationUtils;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

public class JiaoLiuQuActivity extends BaseActivity implements IDefineView, View.OnClickListener {

    private ImageButton mToolbar_back;
    private TextView mToolbar_tv;
    private int bbsId;
    private ImgRecyclerAdapter adapter;
    private RecyclerView img_recycler, pl_recycler;
    private LinearLayout linear;
    private ImageView img;
    private TextView name, time, title, content, repliednumber;
    private Button btn_pl;
    private ReplyRecyclerAdapter adapterOne;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jiaoliuqu);
        EventBus.getDefault().register(this);
        initData();
        initToolbar();
        initView();
        bindData();
        initListener();
        setStatusBar();
    }

    @Override
    public void initData() {
        bbsId = getIntent().getIntExtra("bbsId", -1);
    }

    @Override
    public void initView() {
        img_recycler = (RecyclerView) findViewById(R.id.activity_jiaoliuqu_img_recycler);
        pl_recycler = (RecyclerView) findViewById(R.id.activity_jiaoliuqu_pl_recycler);
        linear = (LinearLayout) findViewById(R.id.activity_jiaoliuqu_linear);
        img = (ImageView) findViewById(R.id.activity_jiaoliuqu_img);
        name = (TextView) findViewById(R.id.activity_jiaoliuqu_name);
        time = (TextView) findViewById(R.id.activity_jiaoliuqu_time);
        title = (TextView) findViewById(R.id.activity_jiaoliuqu_title);
        content = (TextView) findViewById(R.id.activity_jiaoliuqu_content);
        repliednumber = (TextView) findViewById(R.id.activity_jiaoliuqu_repliednumber);
        btn_pl = (Button) findViewById(R.id.activity_jiaoliuqu_replied);
    }

    @Override
    public void bindData() {
        OKHttpManager.postGetMallBbsReplyList(Config.GET_MALL_BBS_REPLY_LIST, bbsId, new SpotsCallBack<GetMallBbsReplyList>(this) {
            @Override
            public void onSuccess(Call call, Response response, GetMallBbsReplyList getMallBbsReplyList) {
                if (getMallBbsReplyList.getStateCode() == 0) {
                    if (getMallBbsReplyList.getMallBbsReplyDTOList().size() != 0) {
                        linear.setVisibility(View.VISIBLE);
                    }
                    if (getMallBbsReplyList.getUserBbsName() == null) {
                        name.setText("");
                    } else
                        name.setText(getMallBbsReplyList.getUserBbsName());
                    time.setText(getMallBbsReplyList.getCreateTime());
                    title.setText(getMallBbsReplyList.getTitle());
                    content.setText(getMallBbsReplyList.getContent());
                    repliednumber.setText(getMallBbsReplyList.getMallBbsReplyDTOList().size() + "");
                    Picasso.with(JiaoLiuQuActivity.this).load(Config.IP + getMallBbsReplyList.getUserBbsImg()).transform(new PicassoTransformationUtils()).error(R.mipmap.icon_others).fit().into(img);
                    adapter = new ImgRecyclerAdapter(JiaoLiuQuActivity.this, getMallBbsReplyList.getBbsImg());
                    img_recycler.setLayoutManager(new LinearLayoutManager(JiaoLiuQuActivity.this));
                    img_recycler.setAdapter(adapter);
                    adapterOne = new ReplyRecyclerAdapter(JiaoLiuQuActivity.this, getMallBbsReplyList.getMallBbsReplyDTOList(), bbsId);
                    pl_recycler.setLayoutManager(new LinearLayoutManager(JiaoLiuQuActivity.this));
                    pl_recycler.setAdapter(adapterOne);
                } else
                    showToasts(getMallBbsReplyList.getMsg());
                call.cancel();
            }

            @Override
            public void onError(Call call, Response response) {
                showErrorToasts(call, response);
            }
        });
    }

    @Override
    public void initListener() {
        btn_pl.setOnClickListener(this);
    }

    @Override
    public void initToolbar() {
        mToolbar_back = (ImageButton) findViewById(R.id.toolbar_back);
        mToolbar_tv = (TextView) findViewById(R.id.toolbar_tv);
        mToolbar_tv.setText("帖子正文");
        mToolbar_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.toolbar_back:
//                EventBus.getDefault().post("replyed");
                finish();
                break;
            case R.id.activity_jiaoliuqu_replied:
                Intent intent = new Intent(this, ReplyActivity.class);
                intent.putExtra("bbsId", bbsId);
                startActivity(intent);
            default:
                break;
        }
    }

    @Subscribe
    public void onEventMainThread(String msg) {
        if (msg.equals("reply")) {
            bindData();
        }
    }

//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (keyCode == KeyEvent.KEYCODE_BACK) {
//            EventBus.getDefault().post("replyed");
//            finish();
//        }
//        return false;
//    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    class ImgRecyclerAdapter extends RecyclerView.Adapter<ImgRecyclerAdapter.MyViewHolder> {

        private Context mContext;
        private List<GetMallBbsReplyList.BbsImgBean> mList;

        public ImgRecyclerAdapter(Context mContext, List<GetMallBbsReplyList.BbsImgBean> mList) {
            this.mContext = mContext;
            this.mList = mList;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.activity_jiaoliuqu_img_item, parent, false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, int position) {
            Picasso.with(mContext).load(Config.IP + mList.get(position).getImgPath()).fit().centerInside().error(R.mipmap.example_about_us_sj).into(holder.img);
        }

        @Override
        public int getItemCount() {
            return mList == null ? 0 : mList.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {
            private ImageView img;

            public MyViewHolder(View itemView) {
                super(itemView);
                img = (ImageView) itemView.findViewById(R.id.activity_jiaoliuqu_img_item);
            }
        }
    }
}
