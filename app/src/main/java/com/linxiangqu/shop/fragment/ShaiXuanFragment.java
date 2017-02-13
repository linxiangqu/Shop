package com.linxiangqu.shop.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.linxiangqu.shop.Config;
import com.linxiangqu.shop.R;
import com.linxiangqu.shop.bean.good.GetGoodsList;
import com.linxiangqu.shop.bean.eventbus.ShaiXuanEvent;
import com.linxiangqu.shop.common.IDefineView;
import com.linxiangqu.shop.common.base.BaseFragment;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by linxiangqu on 2016/8/4.
 */
public class ShaiXuanFragment extends BaseFragment implements IDefineView, View.OnClickListener {

    private View mView;
    private String title;
    private Context context;
    private EditText left, right;
    private RecyclerView xl, fl;
    private Button ok;
    private TextView fl_tv;
    private List<GetGoodsList.MallGoodsSecondClassDTOBean> mallGoodsSecondClassDTOs;
    private List<String> xiaoLiang;
    private XiaoLiangAdapter xlAdapter;
    private FenLeiAdapter flAdapter;
    private String xiaoliang;
    private int firstMoney = 0, endMoney = Integer.MAX_VALUE, firstXL = 0, endXL = Integer.MAX_VALUE, fenlei = -1;

    public static ShaiXuanFragment newInstance(Context context, String title, List<GetGoodsList.MallGoodsSecondClassDTOBean> mallGoodsSecondClassDTOs) {
        ShaiXuanFragment fragment = new ShaiXuanFragment();
        fragment.context = context;
        fragment.mallGoodsSecondClassDTOs = mallGoodsSecondClassDTOs;
        Bundle args = new Bundle();
        args.putString(Config.KEY_TITLE, title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            title = getArguments().getString(Config.KEY_TITLE);
        }
        if (mallGoodsSecondClassDTOs == null)
            mallGoodsSecondClassDTOs = new ArrayList<>();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mView == null) {
            mView = inflater.from(mActivity).inflate(R.layout.fragment_fenlei_shaixuan, container, false);
            bindData();
            initView();
            initData();
            initListener();
        }
        return mView;
    }

    @Override
    public void bindData() {
        if (xiaoLiang == null) {
            xiaoLiang = new ArrayList<>();
            xiaoLiang.add("500以下");
            xiaoLiang.add("500-1000");
            xiaoLiang.add("1000-5000");
            xiaoLiang.add("5000-10000");
            xiaoLiang.add("10000以上");
        }
    }

    @Override
    public void initView() {
        left = (EditText) mView.findViewById(R.id.fragment_fenlei_shaixuan_je_left);
        right = (EditText) mView.findViewById(R.id.fragment_fenlei_shaixuan_je_right);
        xl = (RecyclerView) mView.findViewById(R.id.fragment_fenlei_shaixuan_xiaoliang);
        fl = (RecyclerView) mView.findViewById(R.id.fragment_fenlei_shaixuan_fenlei);
        fl_tv = (TextView) mView.findViewById(R.id.fragment_fenlei_shaixuan_fenlei_tv);
        ok = (Button) mView.findViewById(R.id.fragment_fenlei_shaixuan_ok);
    }

    @Override
    public void initData() {
        if (mallGoodsSecondClassDTOs.size() != 0) {
            fl_tv.setVisibility(View.VISIBLE);
        }
        xlAdapter = new XiaoLiangAdapter(mActivity, xiaoLiang);
        flAdapter = new FenLeiAdapter(context, mallGoodsSecondClassDTOs);
        fl.setLayoutManager(new GridLayoutManager(mActivity, 2));
        xl.setLayoutManager(new GridLayoutManager(mActivity, 2));
        xl.setAdapter(xlAdapter);
        fl.setAdapter(flAdapter);
    }

    @Override
    public void initListener() {
        ok.setOnClickListener(this);
        xlAdapter.setmOnShaiLiangItemClickListener(new XiaoLiangAdapter.OnShaiLiangItemClickListener() {
            @Override
            public void OnSLClick(View view, int position, String string) {
                xiaoliang = string;
            }
        });
        flAdapter.setmOnFenLeiItemClickListener(new FenLeiAdapter.OnFenLeiItemClickListener() {
            @Override
            public void OnFLClick(View view, int position, GetGoodsList.MallGoodsSecondClassDTOBean mallGoodsSecondClassDTO) {
                fenlei = mallGoodsSecondClassDTO.getGcId();
            }
        });
    }

    @Override
    public void initToolbar() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fragment_fenlei_shaixuan_ok:
                if (!left.getText().toString().trim().isEmpty())
                    firstMoney = Integer.valueOf(left.getText().toString().trim());
                if (!right.getText().toString().trim().isEmpty())
                    endMoney = Integer.valueOf(right.getText().toString().trim());
                if (xiaoliang == null) {
                } else if (xiaoliang.equals("500以下"))
                    endXL = 499;
                else if (xiaoliang.equals("10000以上"))
                    firstXL = 10001;
                else {
                    firstXL = Integer.valueOf(xiaoliang.split("-")[0]);
                    endXL = Integer.valueOf(xiaoliang.split("-")[1]);
                }
                if (firstMoney > endMoney) {
                    firstMoney = Integer.valueOf(right.getText().toString().trim());
                    endMoney = Integer.valueOf(left.getText().toString().trim());
                }
                EventBus.getDefault().post(new ShaiXuanEvent(firstMoney, endMoney, firstXL, endXL, fenlei));
                break;
            default:
                break;
        }
    }

    static class XiaoLiangAdapter extends RecyclerView.Adapter<XiaoLiangAdapter.MyViewHolder> {

        private Context mContext;
        private List<String> mList;
        private OnShaiLiangItemClickListener mOnShaiLiangItemClickListener;
        private List<Boolean> isCheck = new ArrayList<>();
        private int positions = -1;

        public XiaoLiangAdapter(Context mContext, List<String> mList) {
            this.mContext = mContext;
            this.mList = mList;
            for (int i = 0; i < mList.size(); i++) {
                isCheck.add(i, false);
            }
        }

        public void setmOnShaiLiangItemClickListener(OnShaiLiangItemClickListener mOnShaiLiangItemClickListener) {
            this.mOnShaiLiangItemClickListener = mOnShaiLiangItemClickListener;
        }

        public interface OnShaiLiangItemClickListener {
            void OnSLClick(View view, int position, String string);
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View mView = LayoutInflater.from(mContext).inflate(R.layout.fragment_myshopcar_bianji_item, parent, false);
            return new MyViewHolder(mView);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            holder.sx_tv.setText(mList.get(position));
            if (isCheck.get(position)) {
                holder.sx_tv.setBackgroundResource(R.mipmap.param_child_selected);
                positions = position;
            } else {
                holder.sx_tv.setBackgroundResource(R.mipmap.paramchild_bg);
            }
        }

        @Override
        public int getItemCount() {
            return mList.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {
            private TextView sx_tv;

            public MyViewHolder(View itemView) {
                super(itemView);
                sx_tv = (TextView) itemView.findViewById(R.id.fragment_myshopcar_bianji_item_tv);
                sx_tv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        for (int i = 0; i < mList.size(); i++) {
                            isCheck.set(i, false);
                        }
                        if (positions == getLayoutPosition()) {
                            notifyItemChanged(positions);
                            positions = -1;
                        } else {
                            if (positions == -1) {
                                isCheck.set(getLayoutPosition(), true);
                                notifyItemChanged(getLayoutPosition());
                            } else {
                                isCheck.set(getLayoutPosition(), true);
                                notifyItemChanged(positions);
                                notifyItemChanged(getLayoutPosition());
                            }
                        }
                        if (mOnShaiLiangItemClickListener != null)
                            mOnShaiLiangItemClickListener.OnSLClick(sx_tv, getLayoutPosition(), mList.get(getLayoutPosition()));
                    }
                });
            }
        }
    }

    static class FenLeiAdapter extends RecyclerView.Adapter<FenLeiAdapter.MyViewHolder> {
        private Context mContext;
        private List<GetGoodsList.MallGoodsSecondClassDTOBean> mList;
        private OnFenLeiItemClickListener mOnFenLeiItemClickListener;
        private List<Boolean> isCheck = new ArrayList<>();
        private int positions = -1;

        public FenLeiAdapter(Context mContext, List<GetGoodsList.MallGoodsSecondClassDTOBean> mList) {
            this.mContext = mContext;
            this.mList = mList;
            for (int i = 0; i < mList.size(); i++) {
                isCheck.add(i, false);
            }
        }

        public void setmOnFenLeiItemClickListener(OnFenLeiItemClickListener mOnFenLeiItemClickListener) {
            this.mOnFenLeiItemClickListener = mOnFenLeiItemClickListener;
        }

        public interface OnFenLeiItemClickListener {
            void OnFLClick(View view, int position, GetGoodsList.MallGoodsSecondClassDTOBean mallGoodsSecondClassDTO);
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View mView = LayoutInflater.from(mContext).inflate(R.layout.fragment_myshopcar_bianji_item, parent, false);
            return new MyViewHolder(mView);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            holder.fl_tv.setText(mList.get(position).getGcName());
            if (isCheck.get(position)) {
                holder.fl_tv.setBackgroundResource(R.mipmap.param_child_selected);
                positions = position;
            } else
                holder.fl_tv.setBackgroundResource(R.mipmap.paramchild_bg);
        }

        @Override
        public int getItemCount() {
            return mList == null ? 0 : mList.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {
            private TextView fl_tv;

            public MyViewHolder(View itemView) {
                super(itemView);
                fl_tv = (TextView) itemView.findViewById(R.id.fragment_myshopcar_bianji_item_tv);
                fl_tv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        for (int i = 0; i < mList.size(); i++) {
                            isCheck.set(i, false);
                        }
                        if (positions == getLayoutPosition()) {
                            notifyItemChanged(positions);
                            positions = -1;
                        } else {
                            if (positions == -1) {
                                isCheck.set(getLayoutPosition(), true);
                                notifyItemChanged(getLayoutPosition());
                            } else {
                                isCheck.set(getLayoutPosition(), true);
                                notifyItemChanged(positions);
                                notifyItemChanged(getLayoutPosition());
                            }
                        }
                        if (mOnFenLeiItemClickListener != null)
                            mOnFenLeiItemClickListener.OnFLClick(fl_tv, getLayoutPosition(), mList.get(getLayoutPosition()));
                    }
                });
            }
        }
    }
}
