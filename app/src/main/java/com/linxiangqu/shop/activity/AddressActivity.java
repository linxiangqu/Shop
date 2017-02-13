package com.linxiangqu.shop.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.linxiangqu.shop.Config;
import com.linxiangqu.shop.R;
import com.linxiangqu.shop.adapter.AddressRecyclerAdapter;
import com.linxiangqu.shop.bean.addressandcitymanger.DeleteAddress;
import com.linxiangqu.shop.bean.addressandcitymanger.GetAddressListByCustomer;
import com.linxiangqu.shop.bean.addressandcitymanger.SetDefaultAddress;
import com.linxiangqu.shop.common.IDefineView;
import com.linxiangqu.shop.common.base.BaseActivity;
import com.linxiangqu.shop.okhttp.OKHttpManager;
import com.linxiangqu.shop.okhttp.SpotsCallBack;
import com.yanzhenjie.recyclerview.swipe.Closeable;
import com.yanzhenjie.recyclerview.swipe.OnSwipeMenuItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenu;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

public class AddressActivity extends BaseActivity implements IDefineView, View.OnClickListener {
    private ImageButton mToolbar_back;
    private TextView mToolbar_tv, mToolbar_bj;
    private Button btn_ok;
    private AddressRecyclerAdapter adapter;
    private SwipeMenuRecyclerView recyclerView;
    private boolean BianJi = false;
    private int positions;
    private List<GetAddressListByCustomer> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        EventBus.getDefault().register(this);
        initToolbar();
        initView();
        initData();
        initListener();
        setStatusBar();
    }

    /**
     * @param msg== “true” 修改
     *              “address” 添加
     */
    @Subscribe
    public void onEventMainThread(String msg) {
        if (msg.equals("true")) {
            adapter.setDefaultAddress(positions);
        }
        if (msg.equals("address")) {
            initData();
        }
    }

    @Override
    public void initView() {
        recyclerView = (SwipeMenuRecyclerView) findViewById(R.id.activity_address_recycler);
        btn_ok = (Button) findViewById(R.id.activity_address_btnOk);
    }

    @Override
    public void initData() {
        OKHttpManager.postGetAddressListByCustomer(Config.GET_ADDRESS_LIST_BY_CUSTOMER, Config.getCacheUserId(this), new SpotsCallBack<String>(this) {
            @Override
            public void onSuccess(Call call, Response response, String s) {
                mList = new Gson().fromJson(s, new TypeToken<List<GetAddressListByCustomer>>() {
                }.getType());
                if (mList.size() != 0)
                    mToolbar_bj.setOnClickListener(AddressActivity.this);
                adapter = new AddressRecyclerAdapter(AddressActivity.this, mList);
                recyclerView.setLayoutManager(new LinearLayoutManager(AddressActivity.this));
                recyclerView.setSwipeMenuCreator(swipeMenuCreator);
                recyclerView.setSwipeMenuItemClickListener(menuItemClickListener);
                recyclerView.setAdapter(adapter);
                call.cancel();
                adapter.setmItemOnClickListener(new AddressRecyclerAdapter.ItemOnClickListener() {
                    @Override
                    public void ItemClick(View v, GetAddressListByCustomer getAddressListByCustomer, int position) {
                        switch (v.getId()) {
                            case R.id.activity_address_item_nocheck:
                                if (!BianJi) {
                                    if (getAddressListByCustomer.getIsDefault() == 0)
                                        setDefaultAddress(getAddressListByCustomer.getAddressId(), position);
                                }
                                break;
                            case R.id.activity_address_item_bianji:
                                if (BianJi) {
                                    Intent intent = new Intent(AddressActivity.this, AddAddressActivity.class);
                                    intent.putExtra("state", 2);
                                    intent.putExtra("getAddressListByCustomer", getAddressListByCustomer);
                                    positions = position;
                                    startActivity(intent);
                                }
                                break;
                            default:
                                break;
                        }
                    }
                });
            }

            @Override
            public void onError(Call call, Response response) {
                showErrorToasts(call, response);
            }
        });
    }

    @Override
    public void initListener() {
        btn_ok.setOnClickListener(this);
    }

    @Override
    public void bindData() {

    }

    public void setDefaultAddress(int addressId, final int position) {
        OKHttpManager.postSetDefaultAddress(Config.SET_DEFAULT_ADDRESS, Config.getCacheUserId(this), addressId, new SpotsCallBack<SetDefaultAddress>(this) {
            @Override
            public void onSuccess(Call call, Response response, SetDefaultAddress setDefaultAddress) {
                if (setDefaultAddress.getStateCode() == 2207) {
                    adapter.setDefaultAddress(position);
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
    public void initToolbar() {
        mToolbar_back = (ImageButton) findViewById(R.id.toolbar_back);
        mToolbar_tv = (TextView) findViewById(R.id.toolbar_tv);
        mToolbar_bj = (TextView) findViewById(R.id.toolbar_bj);
        mToolbar_bj.setVisibility(View.VISIBLE);
        mToolbar_tv.setText("地址管理");
        mToolbar_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.toolbar_back:
                finish();
                break;
            case R.id.toolbar_bj:
                if (!BianJi) {
                    adapter.bianJi(true);
                    BianJi = true;
                } else {
                    adapter.bianJi(false);
                    BianJi = false;
                }
                break;
            case R.id.activity_address_btnOk: {
                Intent intent = new Intent(AddressActivity.this, AddAddressActivity.class);
                intent.putExtra("state", 1);
                startActivity(intent);
            }
            break;
            default:
                break;
        }
    }

    /**
     * 菜单点击监听。
     */
    private OnSwipeMenuItemClickListener menuItemClickListener = new OnSwipeMenuItemClickListener() {
        /**
         * Item的菜单被点击的时候调用。
         * @param closeable       closeable. 用来关闭菜单。
         * @param adapterPosition adapterPosition. 这个菜单所在的item在Adapter中position。
         * @param menuPosition    menuPosition. 这个菜单的position。比如你为某个Item创建了2个MenuItem，那么这个position可能是是 0、1，
         * @param direction       如果是左侧菜单，值是：SwipeMenuRecyclerView#LEFT_DIRECTION，如果是右侧菜单，值是：SwipeMenuRecyclerView#RIGHT_DIRECTION.
         */
        @Override
        public void onItemClick(Closeable closeable, final int adapterPosition, int menuPosition, int direction) {
            closeable.smoothCloseMenu();// 关闭被点击的菜单。
            // TODO 如果是删除：推荐调用Adapter.notifyItemRemoved(position)，不推荐Adapter.notifyDataSetChanged();
            OKHttpManager.postDeleteAddress(Config.DELETE_ADDRESS, mList.get(adapterPosition).getAddressId(), new SpotsCallBack<DeleteAddress>(AddressActivity.this) {
                @Override
                public void onSuccess(Call call, Response response, DeleteAddress deleteAddress) {
                    if (deleteAddress.getStateCode() == 2201) {
                        adapter.deleteAddress(adapterPosition);
                    }
                    showToasts(deleteAddress.getMsg());
                    call.cancel();
                }

                @Override
                public void onError(Call call, Response response) {
                    showErrorToasts(call, response);
                }
            });
        }
    };

    /**
     * 菜单创建器。在Item要创建菜单的时候调用。
     */
    private SwipeMenuCreator swipeMenuCreator = new SwipeMenuCreator() {
        @Override
        public void onCreateMenu(SwipeMenu swipeLeftMenu, SwipeMenu swipeRightMenu, int viewType) {
            int width = getResources().getDimensionPixelSize(R.dimen.item_height);

            // MATCH_PARENT 自适应高度，保持和内容一样高；也可以指定菜单具体高度，也可以用WRAP_CONTENT。
            int height = ViewGroup.LayoutParams.MATCH_PARENT;

            // 添加左侧的，如果不添加，则左侧不会出现菜单。
//            {
//                SwipeMenuItem addItem = new SwipeMenuItem(mContext)
//                        .setBackgroundDrawable(R.drawable.selector_green)// 点击的背景。
//                        .setImage(R.mipmap.ic_action_add) // 图标。
//                        .setWidth(width) // 宽度。
//                        .setHeight(height); // 高度。
//                swipeLeftMenu.addMenuItem(addItem); // 添加一个按钮到左侧菜单。
//
//                SwipeMenuItem closeItem = new SwipeMenuItem(mContext)
//                        .setBackgroundDrawable(R.drawable.selector_red)
//                        .setImage(R.mipmap.ic_action_close)
//                        .setWidth(width)
//                        .setHeight(height);
//
//                swipeLeftMenu.addMenuItem(closeItem); // 添加一个按钮到左侧菜单。
//            }

            // 添加右侧的，如果不添加，则右侧不会出现菜单。
            {
                SwipeMenuItem deleteItem = new SwipeMenuItem(AddressActivity.this)
                        .setBackgroundDrawable(R.color.colorPrimary)
                        .setText("删除") // 文字，还可以设置文字颜色，大小等。。
                        .setTextColor(Color.WHITE)
                        .setTextSize(18)
                        .setWidth(width)
                        .setHeight(height);
                swipeRightMenu.addMenuItem(deleteItem);// 添加一个按钮到右侧侧菜单。
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
