package com.linxiangqu.shop.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.linxiangqu.shop.Config;
import com.linxiangqu.shop.R;
import com.linxiangqu.shop.bean.City;
import com.linxiangqu.shop.bean.addressandcitymanger.AddAddress;
import com.linxiangqu.shop.bean.addressandcitymanger.GetAddressListByCustomer;
import com.linxiangqu.shop.bean.addressandcitymanger.SetDefaultAddress;
import com.linxiangqu.shop.bean.addressandcitymanger.UpdateAddress;
import com.linxiangqu.shop.citypicker.AddressInitTask;
import com.linxiangqu.shop.common.IDefineView;
import com.linxiangqu.shop.common.base.BaseActivity;
import com.linxiangqu.shop.okhttp.OKHttpManager;
import com.linxiangqu.shop.okhttp.SpotsCallBack;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

public class AddAddressActivity extends BaseActivity implements IDefineView, View.OnClickListener {
    private ImageButton mToolbar_back;
    private TextView mToolbar_tv;
    private EditText shr, sjh, yzbm, xxdz;
    private TextView ssq;
    private Button btnOk, btnTrue;
    private int state, isTrue = 0;
    private GetAddressListByCustomer getAddressListByCustomer;
    private City pickerCity;
    private List<String> fisrtData = new ArrayList<>();
    private String provinceName, cityName, districtName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addaddress);
        EventBus.getDefault().register(this);
        initData();
        initToolbar();
        initView();
        bindData();
        initListener();
        setStatusBar();
    }

    @Override
    public void initView() {
        shr = (EditText) findViewById(R.id.activity_addaddress_shr);
        sjh = (EditText) findViewById(R.id.activity_addaddress_sjh);
        yzbm = (EditText) findViewById(R.id.activity_addaddress_yzbm);
        xxdz = (EditText) findViewById(R.id.activity_addaddress_xxdz);
        ssq = (TextView) findViewById(R.id.activity_addaddress_ssq);
        btnOk = (Button) findViewById(R.id.activity_addaddress_btnOk);
        btnTrue = (Button) findViewById(R.id.activity_addaddress_btnTrue);
        if (state == 2) {
            if (getAddressListByCustomer.getIsDefault() != 1)
                btnTrue.setVisibility(View.VISIBLE);
            btnOk.setText("确认修改");
            isTrue = getAddressListByCustomer.getIsDefault();
        }
    }

    @Override
    public void initData() {
        state = getIntent().getIntExtra("state", 1);
        if (state == 2)
            getAddressListByCustomer = (GetAddressListByCustomer) getIntent().getSerializableExtra("getAddressListByCustomer");
    }

    @Override
    public void bindData() {
        if (state == 2) {
            shr.setText(getAddressListByCustomer.getTrueName());
            sjh.setText(getAddressListByCustomer.getMobPhone());
            yzbm.setText(getAddressListByCustomer.getZipCode());
            ssq.setText(getAddressListByCustomer.getProvinceName() + getAddressListByCustomer.getCityName() + getAddressListByCustomer.getDistrictName());
            xxdz.setText(getAddressListByCustomer.getAddress());
        }
    }

    @Override
    public void initListener() {
        btnTrue.setOnClickListener(this);
        btnOk.setOnClickListener(this);
        ssq.setOnClickListener(this);
    }

    @Override
    public void initToolbar() {
        mToolbar_back = (ImageButton) findViewById(R.id.toolbar_back);
        mToolbar_tv = (TextView) findViewById(R.id.toolbar_tv);
        if (state == 1)
            mToolbar_tv.setText("添加地址");
        else if (state == 2)
            mToolbar_tv.setText("修改地址");
        mToolbar_back.setOnClickListener(this);
    }

    private void initCityPickerView() {
        new AddressInitTask(this).execute("北京", "北京", "东城");
    }

    public void setDefaultAddress() {
        OKHttpManager.postSetDefaultAddress(Config.SET_DEFAULT_ADDRESS, Config.getCacheUserId(this), getAddressListByCustomer.getAddressId(), new SpotsCallBack<SetDefaultAddress>(this) {
            @Override
            public void onSuccess(Call call, Response response, SetDefaultAddress setDefaultAddress) {
                if (setDefaultAddress.getStateCode() == 2207) {
                    btnTrue.setVisibility(View.GONE);
                    isTrue = 1;
                    EventBus.getDefault().post("true");
                }
                showToasts(setDefaultAddress.getMsg());
                call.cancel();
            }

            @Override
            public void onError(Call call, Response response) {
                showErrorToasts(call, response);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.toolbar_back:
                finish();
                break;
            case R.id.activity_addaddress_btnOk:
                addAddress();
                break;
            case R.id.activity_addaddress_btnTrue:
                setDefaultAddress();
                break;
            case R.id.activity_addaddress_ssq:
                initCityPickerView();
                break;
            default:
                break;
        }
    }

    private void addAddress() {
        String trueName = shr.getText().toString().trim();
        String mobPhone = sjh.getText().toString().trim();
        String zipCode = yzbm.getText().toString().trim();
        String address = xxdz.getText().toString().trim();
        String city = ssq.getText().toString().trim();
        if (state == 1) {
            if (pickerCity != null) {
                provinceName = pickerCity.getProvince();
                cityName = pickerCity.getCity();
                districtName = pickerCity.getCounty();
            } else {
                provinceName = null;
                cityName = null;
                districtName = null;
            }
            if (trueName.isEmpty() || mobPhone.isEmpty() || zipCode.isEmpty() || city.isEmpty() || address.isEmpty())
                showToasts("请填写详细信息");
            else {
                if (!Config.isMobileNO(mobPhone))
                    showToasts("请输入正确的手机号码");
                else if (!Config.isZipNO(zipCode))
                    showToasts("请输入正确的邮政编码");
                else
                    OKHttpManager.postAddAddress(Config.ADD_ADDRESS, Config.getCacheUserId(this), trueName, mobPhone, zipCode, provinceName, cityName, districtName, address, new SpotsCallBack<AddAddress>(this) {
                        @Override
                        public void onSuccess(Call call, Response response, AddAddress addAddress) {
                            if (addAddress.getStateCode() == 2203) {
                                shr.setText("");
                                sjh.setText("");
                                yzbm.setText("");
                                ssq.setText("");
                                xxdz.setText("");
                                EventBus.getDefault().post("address");
                            }
                            showToasts(addAddress.getMsg());
                            call.cancel();
                        }

                        @Override
                        public void onError(Call call, Response response) {
                            showErrorToasts(call, response);
                        }
                    });
            }
        }
        if (state == 2) {
            if (pickerCity != null) {
                provinceName = pickerCity.getProvince();
                cityName = pickerCity.getCity();
                districtName = pickerCity.getCounty();
            } else {
                provinceName = getAddressListByCustomer.getProvinceName();
                cityName = getAddressListByCustomer.getCityName();
                districtName = getAddressListByCustomer.getDistrictName();
            }
            if (trueName.isEmpty() || mobPhone.isEmpty() || zipCode.isEmpty() || city.isEmpty() || address.isEmpty())
                showToasts("请填写详细信息");
            else {
                if (!Config.isMobileNO(mobPhone))
                    showToasts("请输入正确的手机号码");
                else if (!Config.isZipNO(zipCode))
                    showToasts("请输入正确的邮政编码");
                else
                    OKHttpManager.postUpdateAddress(Config.UPDATE_ADDRESS, getAddressListByCustomer.getAddressId(), trueName, mobPhone, zipCode, provinceName, cityName, districtName, address, isTrue, new SpotsCallBack<UpdateAddress>(this) {
                        @Override
                        public void onSuccess(Call call, Response response, UpdateAddress updateAddress) {
                            if (updateAddress.getStateCode() == 2205) {
                                shr.setText("");
                                sjh.setText("");
                                yzbm.setText("");
                                ssq.setText("");
                                xxdz.setText("");
                                EventBus.getDefault().post("address");
                            }
                            showToasts(updateAddress.getMsg());
                            call.cancel();
                        }

                        @Override
                        public void onError(Call call, Response response) {
                            showErrorToasts(call, response);
                        }
                    });
            }
        }
    }

    @Subscribe
    public void onEventMainThread(City city) {
        if (city == null) {
            return;
        } else if (city.getCounty() == null) {
            ssq.setText(city.getProvince() + city.getCity());
        } else
            ssq.setText(city.getProvince() + city.getCity() + city.getCounty());
        pickerCity = city;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
