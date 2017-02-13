package com.linxiangqu.shop.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.linxiangqu.shop.Config;
import com.linxiangqu.shop.R;
import com.linxiangqu.shop.common.IDefineView;
import com.linxiangqu.shop.common.base.BaseFragment;

/**
 * Created by linxiangqu on 2016/8/12.
 */
public class JiFenFragment extends BaseFragment implements View.OnClickListener, IDefineView, RadioGroup.OnCheckedChangeListener {
    private Context mContext;
    private String jifen;
    private View mView;
    private TextView jifen_tv;
    private RadioGroup radioGroup;
    private RadioButton one, two, three;
    private EditText money;
    private Button ok;

    public static JiFenFragment newInstance(Context context, String jifen) {
        JiFenFragment jiFenFragment = new JiFenFragment();
        jiFenFragment.mContext = context;
        jiFenFragment.jifen = jifen;
        Bundle bundle = new Bundle();
        bundle.putString(Config.KEY_TITLE, jifen);
        jiFenFragment.setArguments(bundle);
        return jiFenFragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null)
            jifen = getArguments().getString(Config.KEY_TITLE);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mView == null) {
            mView = inflater.from(mActivity).inflate(R.layout.fragment_myassets_jifen, container, false);
            initView();
            initData();
            bindData();
            initListener();
        }
        return mView;
    }

    @Override
    public void initView() {
        jifen_tv = (TextView) mView.findViewById(R.id.fragment_myassets_jifen_tv);
        radioGroup = (RadioGroup) mView.findViewById(R.id.fragment_myassets_jifen_radiogroup);
        one = (RadioButton) mView.findViewById(R.id.fragment_myassets_jifen_radiobutton_one);
        two = (RadioButton) mView.findViewById(R.id.fragment_myassets_jifen_radiobutton_two);
        three = (RadioButton) mView.findViewById(R.id.fragment_myassets_jifen_radiobutton_three);
        money = (EditText) mView.findViewById(R.id.fragment_myassets_jifen_edt);
        ok = (Button) mView.findViewById(R.id.fragment_myassets_jifen_ok);
    }

    @Override
    public void initData() {
        jifen_tv.setText(jifen);
    }

    @Override
    public void initListener() {
        ok.setOnClickListener(this);
        radioGroup.setOnCheckedChangeListener(this);
    }

    @Override
    public void bindData() {

    }

    @Override
    public void initToolbar() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fragment_myassets_jifen_ok:
                Toast.makeText(mActivity, "fragment_myassets_jifen_ok", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        switch (radioGroup.getId()) {
            case R.id.fragment_myassets_jifen_radiogroup:
                if (i == one.getId())
                    Toast.makeText(mActivity, one.getText(), Toast.LENGTH_SHORT).show();
                else if (i == two.getId())
                    Toast.makeText(mActivity, two.getText(), Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(mActivity, three.getText(), Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }
}
