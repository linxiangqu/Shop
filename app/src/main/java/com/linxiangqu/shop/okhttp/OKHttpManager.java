package com.linxiangqu.shop.okhttp;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.linxiangqu.shop.Config;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by linxiangqu on 2016/8/18.
 */
public class OKHttpManager {
    private static final String TAG = OKHttpManager.class.getSimpleName();

    private OkHttpClient okHttpClient;
    private volatile static OKHttpManager okHttpManager;
    private Handler handler;
    private Gson gson;

    /**
     * 枚举请求的类型
     */
    private enum HttpMethodType {
        GET, POST
    }

    /**
     * 初始化 okHttpClient，gson，handler
     */
    private OKHttpManager() {
        okHttpClient = new OkHttpClient();
        gson = new Gson();
        handler = new Handler(Looper.getMainLooper());
    }

    /**
     * 采用单例模式获取请求
     *
     * @return
     */
    private static OKHttpManager getInstance() {
        if (okHttpManager == null) {
            synchronized (OKHttpManager.class) {
                if (okHttpManager == null) {
                    okHttpManager = new OKHttpManager();
                }
            }
        }
        return okHttpManager;
    }


//*************************************************对外公布的方法************************************************************************

//全局接口*******************************************************************************************

    /**
     * 主界面信息获取
     *
     * @param url
     * @param userId
     * @param baseCallBack
     */
    public static void getAppIndex(String url, int userId, final BaseCallBack baseCallBack) {
        HashMap<String, String> params = new HashMap<>();
        params.put("userId", userId + "");
        Request request = getInstance().requestBuilder(url, params, HttpMethodType.GET);
        okHttpManager.doRequest(request, baseCallBack, url, params);
    }

//全局接口*******************************************************************************************

//用户接口*******************************************************************************************

    /**
     * 获取用户主页信息
     *
     * @param url
     * @param userId
     * @param baseCallBack
     */
    public static void postGetUserMainMsg(String url, int userId, BaseCallBack baseCallBack) {
        HashMap<String, String> params = new HashMap<>();
        params.put("userId", userId + "");
        Request request = getInstance().requestBuilder(url, params, HttpMethodType.POST);
        okHttpManager.doRequest(request, baseCallBack, url, params);
    }

    /**
     * 用户当日签到信息
     *
     * @param url
     * @param userId
     * @param baseCallBack
     */
    public static void postUserCheckIn(String url, int userId, BaseCallBack baseCallBack) {
        HashMap<String, String> params = new HashMap<>();
        params.put("userId", userId + "");
        Request request = getInstance().requestBuilder(url, params, HttpMethodType.POST);
        okHttpManager.doRequest(request, baseCallBack, url, params);
    }

    /**
     * 用户当月签到日志信息
     *
     * @param url
     * @param userId
     * @param baseCallBack
     */
    public static void postQueryUserCheckInLogList(String url, int userId, BaseCallBack baseCallBack) {
        HashMap<String, String> params = new HashMap<>();
        params.put("userId", userId + "");
        Request request = getInstance().requestBuilder(url, params, HttpMethodType.POST);
        okHttpManager.doRequest(request, baseCallBack, url, params);
    }

    /**
     * 查询用户提成日志信息
     *
     * @param url
     * @param userId
     * @param pageNo
     * @param pageSize
     * @param baseCallBack
     */
    public static void postQueryCustomerCutLog(String url, int userId, int pageNo, int pageSize, BaseCallBack baseCallBack) {
        HashMap<String, String> params = new HashMap<>();
        params.put("userId", userId + "");
        params.put("pageNo", pageNo + "");
        params.put("pageSize", pageSize + "");
        Request request = getInstance().requestBuilder(url, params, HttpMethodType.POST);
        okHttpManager.doRequest(request, baseCallBack, url, params);
    }

    /**
     * 查询用户提成信息
     *
     * @param url
     * @param userId
     * @param parentId
     * @param pageNo
     * @param pageSize
     * @param baseCallBack
     */
    public static void postQueryCustomerCut(String url, int userId, int parentId, int pageNo, int pageSize, BaseCallBack baseCallBack) {
        HashMap<String, String> params = new HashMap<>();
        params.put("userId", userId + "");
        params.put("parentId", parentId + "");
        params.put("pageNo", pageNo + "");
        params.put("pageSize", pageSize + "");
        Request request = getInstance().requestBuilder(url, params, HttpMethodType.POST);
        okHttpManager.doRequest(request, baseCallBack, url, params);
    }

    /**
     * 用户登录
     *
     * @param url
     * @param username
     * @param password
     * @param companyId
     * @param baseCallBack
     */
    public static void postLogin(String url, String username, String password, int companyId, BaseCallBack baseCallBack) {
        HashMap<String, String> params = new HashMap<>();
        params.put("username", username);
        params.put("password", password);
        params.put("companyId", companyId + "");
        Request request = getInstance().requestBuilder(url, params, HttpMethodType.POST);
        okHttpManager.doRequest(request, baseCallBack, url, params);
    }

    /**
     * 获取手机验证码，用户注册
     *
     * @param url
     * @param phone
     * @param companyId
     * @param baseCallBack
     */
    public static void postGetRegisterSMS(String url, String phone, int companyId, BaseCallBack baseCallBack) {
        HashMap<String, String> params = new HashMap<>();
        params.put("phone", phone);
        params.put("companyId", companyId + "");
        Request request = getInstance().requestBuilder(url, params, HttpMethodType.POST);
        okHttpManager.doRequest(request, baseCallBack, url, params);
    }

    /**
     * 用户修改（忘记）密码
     *
     * @param url
     * @param username
     * @param newpassword
     * @param validateCode
     * @param companyId
     * @param baseCallBack
     */
    public static void postForget(String url, String username, String newpassword, String validateCode, int companyId, BaseCallBack baseCallBack) {
        HashMap<String, String> params = new HashMap<>();
        params.put("username", username);
        params.put("newpassword", newpassword);
        params.put("validateCode", validateCode);
        params.put("companyId", companyId + "");
        Request request = getInstance().requestBuilder(url, params, HttpMethodType.POST);
        okHttpManager.doRequest(request, baseCallBack, url, params);
    }

    /**
     * 获取手机验证码，修改或忘记密码
     *
     * @param url
     * @param phone
     * @param companyId
     * @param baseCallBack
     */
    public static void postGetForgetSMS(String url, String phone, int companyId, BaseCallBack baseCallBack) {
        HashMap<String, String> params = new HashMap<>();
        params.put("phone", phone);
        params.put("companyId", companyId + "");
        Request request = getInstance().requestBuilder(url, params, HttpMethodType.POST);
        okHttpManager.doRequest(request, baseCallBack, url, params);
    }

    /**
     * 用户注册
     *
     * @param url
     * @param username
     * @param password
     * @param validateCode
     * @param code
     * @param companyId
     * @param baseCallBack
     */
    public static void postRegister(String url, String username, String password, String validateCode, String code, int companyId, BaseCallBack baseCallBack) {
        HashMap<String, String> params = new HashMap<>();
        params.put("username", username);
        params.put("password", password);
        params.put("validateCode", validateCode);
        params.put("code", code);
        params.put("companyId", companyId + "");
        Request request = getInstance().requestBuilder(url, params, HttpMethodType.POST);
        okHttpManager.doRequest(request, baseCallBack, url, params);
    }

    /**
     * 用户邀请码获取
     *
     * @param url
     * @param userId
     * @param baseCallBack
     */
    public static void postGetCustomerCode(String url, int userId, BaseCallBack baseCallBack) {
        HashMap<String, String> params = new HashMap<>();
        params.put("userId", userId + "");
        Request request = getInstance().requestBuilder(url, params, HttpMethodType.POST);
        okHttpManager.doRequest(request, baseCallBack, url, params);
    }

    /**
     * 获取用户数据
     *
     * @param url
     * @param userId
     * @param baseCallBack
     */
    public static void postGetCustomerMsg(String url, int userId, BaseCallBack baseCallBack) {
        HashMap<String, String> params = new HashMap<>();
        params.put("userId", userId + "");
        Request request = getInstance().requestBuilder(url, params, HttpMethodType.POST);
        okHttpManager.doRequest(request, baseCallBack, url, params);
    }

    /**
     * 更新用户数据信息
     *
     * @param url          （头像修改和其他数据修改分开）
     * @param params       （photo，realname，sex，birthday，email）
     * @param file
     * @param baseCallBack
     */
    public static void postUpdateCustomerMsg(String url, HashMap<String, String> params, File file, BaseCallBack baseCallBack) {
        String Url;
        Request request;
        if (file != null) {
            Url = url;
            Log.i("文件上传", file.getName());
            MultipartBody.Builder builder = new MultipartBody.Builder();
            builder.setType(MultipartBody.FORM);
            builder.addFormDataPart("userImgFile", file.getName(), RequestBody.create(null, file));
            for (Map.Entry<String, String> entry : params.entrySet())
                builder.addFormDataPart(entry.getKey(), entry.getValue());
            RequestBody requestBody = builder.build();
            request = new Request.Builder().url(Url).post(requestBody).build();
        } else {
            Url = Config.UPDATA_CUSTOMER_MSG_NO_USER_IMG;
            request = getInstance().requestBuilder(Url, params, HttpMethodType.POST);
        }
        getInstance().doRequest(request, baseCallBack, Url, params);
    }

    /**
     * 查询用户购买VIP所需金额
     *
     * @param url
     * @param userId
     * @param baseCallBack
     */
    public static void postQueryCustomerVIPpayPrice(String url, int userId, BaseCallBack baseCallBack) {
        HashMap<String, String> params = new HashMap<>();
        params.put("userId", userId + "");
        Request request = getInstance().requestBuilder(url, params, HttpMethodType.POST);
        okHttpManager.doRequest(request, baseCallBack, url, params);
    }

    /**
     * 创建VIP订单
     *
     * @param url
     * @param userId
     * @param payPrice
     * @param baseCallBack
     */
    public static void postCreateVIPOrder(String url, int userId, double payPrice, BaseCallBack baseCallBack) {
        HashMap<String, String> params = new HashMap<>();
        params.put("userId", userId + "");
        params.put("payPrice", payPrice + "");
        Request request = getInstance().requestBuilder(url, params, HttpMethodType.POST);
        okHttpManager.doRequest(request, baseCallBack, url, params);
    }

    /**
     * 广告详情
     *
     * @param url
     * @param advId
     * @param baseCallBack
     */
    public static void postGetAdvInfo(String url, int advId, BaseCallBack baseCallBack) {
        HashMap<String, String> params = new HashMap<>();
        params.put("advId", advId + "");
        Request request = getInstance().requestBuilder(url, params, HttpMethodType.POST);
        okHttpManager.doRequest(request, baseCallBack, url, params);
    }

    /**
     * 用户收藏增加
     *
     * @param url
     * @param userId
     * @param goodsId
     * @param baseCallBack
     */
    public static void postAddCollection(String url, int userId, int goodsId, BaseCallBack baseCallBack) {
        HashMap<String, String> params = new HashMap<>();
        params.put("userId", userId + "");
        params.put("goodsId", goodsId + "");
        Request request = getInstance().requestBuilder(url, params, HttpMethodType.POST);
        okHttpManager.doRequest(request, baseCallBack, url, params);
    }

    /**
     * 用户获取收藏夹信息
     *
     * @param url
     * @param userId
     * @param baseCallBack
     */
    public static void postCollectionList(String url, int userId, BaseCallBack baseCallBack) {
        HashMap<String, String> params = new HashMap<>();
        params.put("userId", userId + "");
        Request request = getInstance().requestBuilder(url, params, HttpMethodType.POST);
        okHttpManager.doRequest(request, baseCallBack, url, params);
    }

    /**
     * 用户取消收藏
     *
     * @param url
     * @param userId
     * @param goodsId
     * @param baseCallBack
     */
    public static void postCollectionDel(String url, int userId, int goodsId, BaseCallBack baseCallBack) {
        HashMap<String, String> params = new HashMap<>();
        params.put("userId", userId + "");
        params.put("goodsId", goodsId + "");
        Request request = getInstance().requestBuilder(url, params, HttpMethodType.POST);
        okHttpManager.doRequest(request, baseCallBack, url, params);
    }
//用户接口*******************************************************************************************

//商品接口*******************************************************************************************

    /**
     * 用户添加银行卡或支付宝
     *
     * @param url
     * @param userId
     * @param cardType
     * @param cardNo
     * @param cardName
     * @param cardCompany
     * @param cardNetwork
     * @param baseCallBack
     */
    public static void postAddCustomerCard(String url, int userId, int cardType, String cardNo, String cardName, String cardCompany, String cardNetwork, BaseCallBack baseCallBack) {
        HashMap<String, String> params = new HashMap<>();
        params.put("userId", userId + "");
        params.put("cardType", cardType + "");
        params.put("cardNo", cardNo + "");
        params.put("cardName", cardName + "");
        params.put("cardCompany", cardCompany + "");
        params.put("cardNetwork", cardNetwork + "");
        Request request = getInstance().requestBuilder(url, params, HttpMethodType.POST);
        okHttpManager.doRequest(request, baseCallBack, url, params);
    }

    /**
     * 用户选择提现方式
     *
     * @param url
     * @param userId
     * @param cardId
     * @param baseCallBack
     */
    public static void postChoseCustomerCard(String url, int userId, int cardId, BaseCallBack baseCallBack) {
        HashMap<String, String> params = new HashMap<>();
        params.put("userId", userId + "");
        params.put("cardId", cardId + "");
        Request request = getInstance().requestBuilder(url, params, HttpMethodType.POST);
        okHttpManager.doRequest(request, baseCallBack, url, params);
    }

    /**
     * 用户提现首页
     *
     * @param url
     * @param userId
     * @param baseCallBack
     */
    public static void postQueryCashOutIndex(String url, int userId, BaseCallBack baseCallBack) {
        HashMap<String, String> params = new HashMap<>();
        params.put("userId", userId + "");
        Request request = getInstance().requestBuilder(url, params, HttpMethodType.POST);
        okHttpManager.doRequest(request, baseCallBack, url, params);
    }

    /**
     * 查看用户提现历史纪录
     *
     * @param url
     * @param userId
     * @param pagetNo
     * @param pageSize
     * @param baseCallBack
     */
    public static void postQueryCashOutList(String url, int userId, int pagetNo, int pageSize, BaseCallBack baseCallBack) {
        HashMap<String, String> params = new HashMap<>();
        params.put("userId", userId + "");
        params.put("pageNo", pagetNo + "");
        params.put("pageSize", pageSize + "");
        Request request = getInstance().requestBuilder(url, params, HttpMethodType.POST);
        okHttpManager.doRequest(request, baseCallBack, url, params);
    }

    /**
     * 获取绑定银行卡和支付宝列表
     *
     * @param url
     * @param userId
     * @param cardType
     * @param baseCallBack
     */
    public static void postQueryCustomerCardList(String url, int userId, int cardType, BaseCallBack baseCallBack) {
        HashMap<String, String> params = new HashMap<>();
        params.put("userId", userId + "");
        params.put("cardType", cardType + "");
        Request request = getInstance().requestBuilder(url, params, HttpMethodType.POST);
        okHttpManager.doRequest(request, baseCallBack, url, params);
    }

    /**
     * 专题详情
     *
     * @param url
     * @param topicId
     * @param pageNo
     * @param pageSize
     * @param baseCallBack
     */
    public static void postQueryTopicGoods(String url, int topicId, int pageNo, int pageSize, BaseCallBack baseCallBack) {
        HashMap<String, String> params = new HashMap<>();
        params.put("topicId", topicId + "");
        params.put("pageNo", pageNo + "");
        params.put("pageSize", pageSize + "");
        Request request = getInstance().requestBuilder(url, params, HttpMethodType.POST);
        okHttpManager.doRequest(request, baseCallBack, url, params);
    }

    /**
     * 申请提现
     *
     * @param url
     * @param userId
     * @param money
     * @param cardId
     * @param baseCallBack
     */
    public static void postSubmitCashOut(String url, int userId, String money, int cardId, BaseCallBack baseCallBack) {
        HashMap<String, String> params = new HashMap<>();
        params.put("userId", userId + "");
        params.put("money", money);
        params.put("cardId", cardId + "");
        Request request = getInstance().requestBuilder(url, params, HttpMethodType.POST);
        okHttpManager.doRequest(request, baseCallBack, url, params);
    }

    /**
     * 商品查询
     *
     * @param url
     * @param params       必填（userId，firstGoodsClassId）
     *                     商品二级目录，筛选（pageNo，pageSize，smallPrice，bigPrice，smallSalenum，bigSalenum）
     *                     商品关键字，列表搜索（goodsKeywords）
     * @param baseCallBack
     */
    public static void postGetGoodsList(String url, HashMap<String, String> params, BaseCallBack baseCallBack) {
        Request request = getInstance().requestBuilder(url, params, HttpMethodType.POST);
        okHttpManager.doRequest(request, baseCallBack, url, params);
    }

    /**
     * 商品详情
     *
     * @param url
     * @param goodsId
     * @param baseCallBack
     */
    public static void getGetGoodsInfo(String url, int goodsId, BaseCallBack baseCallBack) {
        HashMap<String, String> params = new HashMap<>();
        params.put("goodsId", goodsId + "");
        Request request = getInstance().requestBuilder(url, params, HttpMethodType.GET);
        okHttpManager.doRequest(request, baseCallBack, url, params);
    }

    /**
     * 查看买就送列表信息
     *
     * @param url
     * @param userId
     * @param pageNo
     * @param pageSize
     * @param baseCallBack
     */
    public static void postGetBuySongPage(String url, int userId, int pageNo, int pageSize, BaseCallBack baseCallBack) {
        HashMap<String, String> params = new HashMap<>();
        params.put("userId", userId + "");
        params.put("pageNo", pageNo + "");
        params.put("pageSize", pageSize + "");
        Request request = getInstance().requestBuilder(url, params, HttpMethodType.POST);
        okHttpManager.doRequest(request, baseCallBack, url, params);
    }

//商品接口*******************************************************************************************


//购物车接口*****************************************************************************************

    /**
     * 用户购物车界面信息获取
     *
     * @param url
     * @param userId
     * @param baseCallBack
     */
    public static void postGetUserCartList(String url, int userId, BaseCallBack baseCallBack) {
        HashMap<String, String> params = new HashMap<>();
        params.put("userId", userId + "");
        Request request = getInstance().requestBuilder(url, params, HttpMethodType.POST);
        okHttpManager.doRequest(request, baseCallBack, url, params);
    }

    /**
     * 删除用户购物车内的商品
     *
     * @param url
     * @param cartId
     * @param baseCallBack
     */
    public static void postDeleteCart(String url, HashMap<String, String> cartId, BaseCallBack baseCallBack) {
        FormBody.Builder formBody = new FormBody.Builder();
        for (Map.Entry<String, String> entry : cartId.entrySet()) {
            formBody.add("cartId", entry.getValue());
        }
        Request.Builder request = new Request.Builder();
        request.post(formBody.build()).url(url);
        okHttpManager.doRequest(request.build(), baseCallBack, url, cartId);
    }

    /**
     * 用户添加商品到购物车
     *
     * @param url
     * @param userId
     * @param goodsId
     * @param goodsSpecId
     * @param goodsNum
     * @param baseCallBack
     */
    public static void postSaveGoodsToCart(String url, int userId, int goodsId, int goodsSpecId, int goodsNum, BaseCallBack baseCallBack) {
        HashMap<String, String> params = new HashMap<>();
        params.put("userId", userId + "");
        params.put("goodsId", goodsId + "");
        params.put("goodsSpecId", goodsSpecId + "");
        params.put("goodsNum", goodsNum + "");
        Request request = getInstance().requestBuilder(url, params, HttpMethodType.POST);
        okHttpManager.doRequest(request, baseCallBack, url, params);
    }

    /**
     * 购物车：商品规格、数量编辑
     *
     * @param url
     * @param userId
     * @param cartId
     * @param goodsStorePrice
     * @param specId
     * @param goodsId
     * @param goodsNum
     * @param baseCallBack
     */
    public static void postUpdateCart(String url, int userId, int cartId, double goodsStorePrice, int specId, int goodsId, int goodsNum, int storeId, String goodsImages, BaseCallBack baseCallBack) {
        HashMap<String, String> params = new HashMap<>();
        params.put("userId", userId + "");
        params.put("cartId", cartId + "");
        params.put("goodsStorePrice", goodsStorePrice + "");
        params.put("specId", specId + "");
        params.put("goodsId", goodsId + "");
        params.put("goodsNum", goodsNum + "");
        params.put("storeId", storeId + "");
        params.put("goodsImages", goodsImages);
        Request request = getInstance().requestBuilder(url, params, HttpMethodType.POST);
        okHttpManager.doRequest(request, baseCallBack, url, params);
    }

//购物车接口*****************************************************************************************

//区域及地址管理接口*********************************************************************************

    /**
     * 获取用户收货地址
     *
     * @param url
     * @param userId
     * @param baseCallBack
     */
    public static void postGetAddressListByCustomer(String url, int userId, BaseCallBack baseCallBack) {
        HashMap<String, String> params = new HashMap<>();
        params.put("userId", userId + "");
        Request request = getInstance().requestBuilder(url, params, HttpMethodType.POST);
        okHttpManager.doRequest(request, baseCallBack, url, params);
    }

    /**
     * 用户地址添加
     *
     * @param url
     * @param userId
     * @param trueName
     * @param mobPhone
     * @param zipCode
     * @param provinceName
     * @param cityName
     * @param districtName
     * @param address
     * @param baseCallBack
     */
    public static void postAddAddress(String url, int userId, String trueName, String mobPhone, String zipCode, String provinceName, String cityName, String districtName, String address, BaseCallBack baseCallBack) {
        HashMap<String, String> params = new HashMap<>();
        params.put("userId", userId + "");
        params.put("trueName", trueName);
        params.put("mobPhone", mobPhone);
        params.put("zipCode", zipCode);
        params.put("provinceName", provinceName);
        params.put("cityName", cityName);
        params.put("districtName", districtName);
        params.put("address", address);
        Request request = getInstance().requestBuilder(url, params, HttpMethodType.POST);
        okHttpManager.doRequest(request, baseCallBack, url, params);
    }

    /**
     * 买家收货地址修改
     *
     * @param url
     * @param addressId
     * @param trueName
     * @param mobPhone
     * @param zipCode
     * @param provinceName
     * @param cityName
     * @param districtName
     * @param address
     * @param isDefault
     * @param baseCallBack
     */
    public static void postUpdateAddress(String url, int addressId, String trueName, String mobPhone, String zipCode, String provinceName, String cityName, String districtName, String address, int isDefault, BaseCallBack baseCallBack) {
        HashMap<String, String> params = new HashMap<>();
        params.put("addressId", addressId + "");
        params.put("trueName", trueName);
        params.put("mobPhone", mobPhone);
        params.put("zipCode", zipCode);
        params.put("provinceName", provinceName);
        params.put("cityName", cityName);
        params.put("districtName", districtName);
        params.put("address", address);
        params.put("isDefault", isDefault + "");
        Request request = getInstance().requestBuilder(url, params, HttpMethodType.POST);
        okHttpManager.doRequest(request, baseCallBack, url, params);
    }

    /**
     * 用户默认地址设置
     *
     * @param url
     * @param userId
     * @param addressId
     * @param baseCallBack
     */
    public static void postSetDefaultAddress(String url, int userId, int addressId, BaseCallBack baseCallBack) {
        HashMap<String, String> params = new HashMap<>();
        params.put("userId", userId + "");
        params.put("addressId", addressId + "");
        Request request = getInstance().requestBuilder(url, params, HttpMethodType.POST);
        okHttpManager.doRequest(request, baseCallBack, url, params);
    }

    /**
     * 买家收货地址删除
     *
     * @param url
     * @param addressId
     * @param baseCallBack
     */
    public static void postDeleteAddress(String url, int addressId, BaseCallBack baseCallBack) {
        HashMap<String, String> params = new HashMap<>();
        params.put("addressId", addressId + "");
        Request request = getInstance().requestBuilder(url, params, HttpMethodType.POST);
        okHttpManager.doRequest(request, baseCallBack, url, params);
    }

    /**
     * 买家默认地址获取
     *
     * @param url
     * @param userId
     * @param baseCallBack
     */
    public static void postGetDefaultAddress(String url, int userId, BaseCallBack baseCallBack) {
        HashMap<String, String> params = new HashMap<>();
        params.put("userId", userId + "");
        Request request = getInstance().requestBuilder(url, params, HttpMethodType.POST);
        okHttpManager.doRequest(request, baseCallBack, url, params);
    }

//区域及地址管理接口*********************************************************************************

//订单接口******************************************************************************************

    /**
     * 查看用户订单详情
     *
     * @param url
     * @param userId
     * @param pagetNo
     * @param pageSize
     * @param orderState
     * @param baseCallBack
     */
    public static void postQueryOrderList(String url, int userId, int pagetNo, int pageSize, int orderState, BaseCallBack baseCallBack) {
        HashMap<String, String> params = new HashMap<>();
        params.put("userId", userId + "");
        params.put("pageNo", pagetNo + "");
        params.put("pageSize", pageSize + "");
        params.put("orderState", orderState + "");
        Request request = getInstance().requestBuilder(url, params, HttpMethodType.POST);
        okHttpManager.doRequest(request, baseCallBack, url, params);
    }

    /**
     * @param url
     * @param orderId
     * @param baseCallBack
     */
    public static void postOrder(String url, int orderId, BaseCallBack baseCallBack) {
        HashMap<String, String> params = new HashMap<>();
        params.put("orderId", orderId + "");
        Request request = getInstance().requestBuilder(url, params, HttpMethodType.POST);
        okHttpManager.doRequest(request, baseCallBack, url, params);
    }

//订单接口******************************************************************************************

//我的平台接口***************************************************************************************

    /**
     * 帖子发布
     *
     * @param url
     * @param params       (title 帖子标题)(content 帖子内容)(isAnonymity 是否匿名)(isPublic 表示公开与否)(userId 发布者Id(经销商))
     * @param pics         (pic 图片路劲；字符串数组)
     * @param baseCallBack
     */
    public static void postSavePost(String url, HashMap<String, String> params, List<File> pics, BaseCallBack baseCallBack) {
        Request request;
        if (pics.size() != 0) {
            MultipartBody.Builder builder = new MultipartBody.Builder();
            builder.setType(MultipartBody.FORM);
            for (int i = 0; i < pics.size(); i++) {
                Log.i("文件上传", pics.get(i).toString());
                Log.i("图片压缩后大小：", pics.get(i).length() + "");
                builder.addFormDataPart("pic", pics.get(i) + "" + i, RequestBody.create(null, pics.get(i)));
            }
            for (Map.Entry<String, String> entry : params.entrySet())
                builder.addFormDataPart(entry.getKey(), entry.getValue());
            RequestBody requestBody = builder.build();
            request = new Request.Builder().url(url).post(requestBody).build();
        } else {
            request = getInstance().requestBuilder(url, params, HttpMethodType.POST);
        }
        getInstance().doRequest(request, baseCallBack, url, params);
    }

    /**
     * 交流区帖子列表查询
     *
     * @param url
     * @param pagetNo
     * @param pageSize
     * @param baseCallBack
     */
    public static void postGetMallBbsList(String url, int pagetNo, int pageSize, BaseCallBack baseCallBack) {
        HashMap<String, String> params = new HashMap<>();
        params.put("pageNo", pagetNo + "");
        params.put("pageSize", pageSize + "");
        Request request = getInstance().requestBuilder(url, params, HttpMethodType.POST);
        okHttpManager.doRequest(request, baseCallBack, url, params);
    }

    /**
     * 评论
     *
     * @param url
     * @param baseCallBack
     * @params (bbsId 帖子ID, userId 评论者Id(经销商), replyId 一级评论Id(评论楼主时固定传null);评论层主时传回评论Id,replyContent 评论内容,isAnonymity 是否匿名，默认不匿名)
     */
    public static void postSaveReply(String url, HashMap<String, String> params, BaseCallBack baseCallBack) {
        Request request = getInstance().requestBuilder(url, params, HttpMethodType.POST);
        okHttpManager.doRequest(request, baseCallBack, url, params);
    }

    /**
     * 帖子详情和评论列表
     *
     * @param url
     * @param bbsId
     * @param baseCallBack
     */
    public static void postGetMallBbsReplyList(String url, int bbsId, BaseCallBack baseCallBack) {
        HashMap<String, String> params = new HashMap<>();
        params.put("bbsId", bbsId + "");
        Request request = getInstance().requestBuilder(url, params, HttpMethodType.POST);
        okHttpManager.doRequest(request, baseCallBack, url, params);
    }

    /**
     * 动态列表
     *
     * @param url
     * @param userId
     * @param pagetNo
     * @param pageSize
     * @param baseCallBack
     */
    public static void postGetMallDynamicList(String url, int userId, int pagetNo, int pageSize, BaseCallBack baseCallBack) {
        HashMap<String, String> params = new HashMap<>();
        params.put("userId", userId + "");
        params.put("pageNo", pagetNo + "");
        params.put("pageSize", pageSize + "");
        Request request = getInstance().requestBuilder(url, params, HttpMethodType.POST);
        okHttpManager.doRequest(request, baseCallBack, url, params);
    }

    /**
     * 动态详情
     *
     * @param url
     * @param dynamicId
     * @param baseCallBack
     */
    public static void postGetMallDynamicDetail(String url, int dynamicId, BaseCallBack baseCallBack) {
        HashMap<String, String> params = new HashMap<>();
        params.put("dynamicId", dynamicId + "");
        Request request = getInstance().requestBuilder(url, params, HttpMethodType.POST);
        okHttpManager.doRequest(request, baseCallBack, url, params);
    }

    /**
     * 帖子发布
     *
     * @param url
     * @param params       (goodsZoneTitle 标题)(goodsZoneContent 内容)(goodsZoneTypeId 类型Id)(goodsZoneTypeName 类型名)(goodsZoneMobile 手机)(goodsZoneLinkMan 联系人)(userId 发布者Id)
     * @param pics         (pic 图片路劲；字符串数组)
     * @param baseCallBack
     */
    public static void postSaveMallGoodsZone(String url, HashMap<String, String> params, List<File> pics, BaseCallBack baseCallBack) {
        Request request;
        if (pics.size() != 0) {
            MultipartBody.Builder builder = new MultipartBody.Builder();
            builder.setType(MultipartBody.FORM);
            for (int i = 0; i < pics.size(); i++) {
                Log.i("文件上传", pics.get(i).toString());
                builder.addFormDataPart("pic", pics.get(i) + "" + i, RequestBody.create(null, pics.get(i)));
            }
            for (Map.Entry<String, String> entry : params.entrySet())
                builder.addFormDataPart(entry.getKey(), entry.getValue());
            RequestBody requestBody = builder.build();
            request = new Request.Builder().url(url).post(requestBody).build();
        } else {
            request = getInstance().requestBuilder(url, params, HttpMethodType.POST);
        }
        getInstance().doRequest(request, baseCallBack, url, params);
    }

    /**
     * 商品区列表
     *
     * @param url
     * @param userId
     * @param pageNo
     * @param pageSize
     * @param goodsZoneTypeId
     * @param baseCallBack
     */
    public static void postGetMallGoodsZoneList(String url, int userId, int pageNo, int pageSize, int goodsZoneTypeId, BaseCallBack baseCallBack) {
        HashMap<String, String> params = new HashMap<>();
        params.put("userId", userId + "");
        params.put("pageNo", pageNo + "");
        params.put("pageSize", pageSize + "");
        params.put("goodsZoneTypeId", goodsZoneTypeId + "");
        Request request = getInstance().requestBuilder(url, params, HttpMethodType.POST);
        okHttpManager.doRequest(request, baseCallBack, url, params);
    }

    /**
     * 商品区详情
     *
     * @param url
     * @param goodsZoneId
     * @param baseCallBack
     */
    public static void postGetMallGoodsZoneInfo(String url, int goodsZoneId, BaseCallBack baseCallBack) {
        HashMap<String, String> params = new HashMap<>();
        params.put("goodsZoneId", goodsZoneId + "");
        Request request = getInstance().requestBuilder(url, params, HttpMethodType.POST);
        okHttpManager.doRequest(request, baseCallBack, url, params);
    }

//我的平台接口***************************************************************************************

//*************************************************************************************************************************


    /**
     * OKHTTP请求的主要方法
     *
     * @param request
     * @param baseCallBack
     * @param url
     * @param params
     */
    private void doRequest(final Request request, final BaseCallBack baseCallBack, String url, HashMap<String, String> params) {
        callBackBeforeResponse(baseCallBack, url, params);
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callBackFailure(baseCallBack, call, e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                callBackResponse(baseCallBack);
                if (response.isSuccessful()) {
                    String str = response.body().string();
                    Log.i("数据：", str);
                    if (baseCallBack.type == String.class)
                        callBackSuccess(baseCallBack, call, response, str);
                    else {
                        try {
                            Object object = gson.fromJson(str, baseCallBack.type);
                            callBackSuccess(baseCallBack, call, response, object);
                        } catch (JsonSyntaxException e) {
                            e.printStackTrace();
                            Log.i("解析JSON出错", "解析JSON出错");
                            callBackError(baseCallBack, call, response);
                        }
                    }
                } else {
                    callBackError(baseCallBack, call, response);
                }
            }
        });
    }

    /**
     * 生成Request 并对GET和POST参数的判断
     *
     * @param url
     * @param params
     * @param type
     * @return
     */
    private Request requestBuilder(String url, HashMap<String, String> params, HttpMethodType type) {
        Request.Builder builder = new Request.Builder();
        if (type == HttpMethodType.GET) {
            String str = Config.getUrl(url, params);
            builder.url(str).get();
        } else if (type == HttpMethodType.POST) {
            RequestBody body = requestFormBodyBuilder(params);
            builder.url(url).post(body);
        }
        return builder.build();
    }

    /**
     * POST请求  RequestBody
     *
     * @param params
     * @return
     */
    private RequestBody requestFormBodyBuilder(HashMap<String, String> params) {
        FormBody.Builder formBodyBuilder = new FormBody.Builder();
        for (Map.Entry<String, String> entry : params.entrySet())
            formBodyBuilder.add(entry.getKey(), entry.getValue());
        return formBodyBuilder.build();
    }

    /**
     * 请求前的准备
     * 在日志输出请求的信息（url，参数）
     *
     * @param baseCallBack
     * @param url
     * @param params
     */
    private void callBackBeforeResponse(final BaseCallBack baseCallBack, final String url, final HashMap<String, String> params) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                baseCallBack.onBeforeRequest(url, params);
            }
        });
    }

    /**
     * 请求成功回调方法，主要是取消dialog
     *
     * @param baseCallBack
     */
    private void callBackResponse(final BaseCallBack baseCallBack) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                baseCallBack.onResponse();
            }
        });
    }

    /**
     * (response.isSuccessful())时回调方法
     *
     * @param baseCallBack
     * @param call
     * @param response
     * @param object
     */
    private void callBackSuccess(final BaseCallBack baseCallBack, final Call call, final Response response, final Object object) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                baseCallBack.onSuccess(call, response, object);
            }
        });
    }

    /**
     * (!response.isSuccessful())时回调方法
     *
     * @param baseCallBack
     * @param call
     * @param response
     */
    private void callBackError(final BaseCallBack baseCallBack, final Call call, final Response response) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                baseCallBack.onError(call, response);
            }
        });
    }

    /**
     * 请求失败的回调方法
     *
     * @param baseCallBack
     * @param call
     * @param e
     */
    private void callBackFailure(final BaseCallBack baseCallBack, final Call call, final IOException e) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                baseCallBack.onFailure(call, e);
            }
        });
    }
}
