package com.linxiangqu.shop;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.net.Uri;
import android.util.Log;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by linxiangqu on 2016/7/27.
 */
public class Config {
    public static final String KEY_TITLE = "title";
    public static final String FILENAME = "user";
    public static final String USERID = "userId";
    public static final String USERNAME = "userName";
    public static final String USERMOBILE = "userMobile";

    //    public static final String IP = "http://10.221.204.100:8080";
    public static final String IP = "http://symall.sunruncn.com:8787";

//全局接口*******************************************************************************************

    //首界面信息
    public static final String APPINDEX = IP + "/XRMall/rest/AppIndex.json";

//全局接口*******************************************************************************************

//用户接口*******************************************************************************************

    //获取用户主页信息
    public static final String GET_USER_MAIN_MAS = IP + "/XRMall/rest/getUserMainMsg.json";

    //用户当日签到信息
    public static final String USER_CHECK_IN = IP + "/XRMall/rest/userCheckIn.json";

    //用户当月签到日志信息
    public static final String QUERY_USER_CHECK_IN_LOG_LIST = IP + "/XRMall/rest/queryUserCheckInLogList.json";

    //查询用户提成日志信息
    public static final String QUERY_CUSTOMER_CUT_LOG = IP + "/XRMall/rest/queryCustomerCutLog.json";

    //查询用户提成信息
    public static final String QUERY_CUSTOMER_CUT = IP + "/XRMall/rest/queryCustomerCut.json";

    //用户登录
    public static final String LOGIN = IP + "/XRMall/rest/login.json";

    //获取手机验证码，用户注册
    public static final String GET_REGISTER_SMS = IP + "/XRMall/rest/getRegisterSMS.json";

    //用户修改（忘记）密码
    public static final String FORGET = IP + "/XRMall/rest/forget.json";

    //获取手机验证码，修改或忘记密码
    public static final String GET_FORGET_SMS = IP + "/XRMall/rest/getForgetSMS.json";

    //用户注册
    public static final String REGISTER = IP + "/XRMall/rest/register.json";

    //用户邀请码获取
    public static final String GET_CUSTOMER_CODE = IP + "/XRMall/rest/getCustomerCode.json";

    //获取用户数据
    public static final String GET_CUSTOMER_MAS = IP + "/XRMall/rest/getCustomerMsg.json";

    //更新用户数据信息(头像修改)
    public static final String UPDATA_CUSTOMER_MSG = IP + "/XRMall/rest/updateCustomerMsg.json";

    //更新用户数据信息(非头像修改)
    public static final String UPDATA_CUSTOMER_MSG_NO_USER_IMG = IP + "/XRMall/rest/updateCustomerMsgNoUserImg.json";

    //查询用户购买VIP所需金额
    public static final String QUERY_CUSTOMER_VIP_PAY_PRICE = IP + "/XRMall/rest/queryCustomerVIPpayPrice.json";

    //创建VIP订单
    public static final String CREATE_VIP_ORDER = IP + "/XRMall/rest/createVIPOrder.json";

    //广告详情
    public static final String GET_ADV_INFO = IP + "/XRMall/rest/getAdvInfo.json";

    //用户收藏增加
    public static final String ADD_COLLECTION = IP + "/XRMall/rest/addCollection.json";

    //用户获取收藏夹信息
    public static final String COLLECTION_LIST = IP + "/XRMall/rest/collectionList.json";

    //用户取消收藏
    public static final String COLLECTION_DEL = IP + "/XRMall/rest/collectionDel.json";

//用户接口*******************************************************************************************

//商品接口*******************************************************************************************

    //用户添加银行卡或支付宝
    public static final String ADD_CUSTOMER_CARD = IP + "/XRMall/rest/addCustomerCard.json";

    //用户选择提现方式
    public static final String CHOSE_CUSTOMER_CARD = IP + "/XRMall/rest/choseCustomerCard.json";

    //用户删除银行卡
    public static final String DETELE_CUSTOMER_CARD = IP + "/XRMall/rest/deleteCustomerCard.json";

    //用户提现首页
    public static final String QUERY_CASH_OUT_INDEX = IP + "/XRMall/rest/queryCashOutIndex.json";

    //用户提现历史纪录
    public static final String QUERY_CASH_OUT_LIST = IP + "/XRMall/rest/queryCashOutList.json";

    //获取绑定银行卡和支付宝列表
    public static final String QUERY_CUSTOMER_CARD_LIST = IP + "/XRMall/rest/queryCustomerCardList.json";

    //获取专题信息
    public static final String QUERY_TOPIC_GOODS = IP + "/XRMall/rest/queryTopicGoods.json";

    //申请提现
    public static final String SUBMIT_CASH_OUT = IP + "/XRMall/rest/submitCashOut.json";

    //商品分类信息查询
    public static final String GET_GOODS_LIST = IP + "/XRMall/rest/getGoodsList.json";

    //商品详情
    public static final String GET_GOODS_INFO = IP + "/XRMall/rest/getGoodsInfo.json";

    //买就送列表
    public static final String BUY_SONG_PAGE = IP + "/XRMall/rest/getBuySongPage.json";

//商品接口*******************************************************************************************

//购物车接口*****************************************************************************************

    //用户购物车和猜你喜欢信息
    public static final String GET_USER_CART_LIST = IP + "/XRMall/rest/getUserCartList.json";

    //删除用户购物车内的商品
    public static final String DELETE_CART = IP + "/XRMall/rest/deleteCart.json";

    //添加商品到购物车
    public static final String SAVE_GOODS_TO_CART = IP + "/XRMall/rest/saveGoodsToCart.json";

    //购物车：商品规格、数量编辑
    public static final String UPDATE_CART = IP + "/XRMall/rest/updateCart.json";

//购物车接口*****************************************************************************************

//区域及地址管理接口*********************************************************************************

    //获取用户收货地址
    public static final String GET_ADDRESS_LIST_BY_CUSTOMER = IP + "/XRMall/rest/getAddressListByCustomer.json";

    //用户地址添加
    public static final String ADD_ADDRESS = IP + "/XRMall/rest/addAddress.json";

    //买家收货地址修改
    public static final String UPDATE_ADDRESS = IP + "/XRMall/rest/updateAddress.json";

    //用户默认地址设置
    public static final String SET_DEFAULT_ADDRESS = IP + "/XRMall/rest/setDefaultAddress.json";

    //买家收货地址删除
    public static final String DELETE_ADDRESS = IP + "/XRMall/rest/deleteAddress.json";

    //买家默认地址获取
    public static final String GET_DEFAULT_ADDRESS = IP + "/XRMall/rest/getDefaultAddress.json";

//区域及地址管理接口*********************************************************************************

//订单接口******************************************************************************************

    //商品下单（创建订单）
    public static final String CREATE_GODS_ORDER = IP + "/XRMall/rest/createGoodsOrder.json";

    //用户订单界面订单状态：0.查询全部订单：10(默认):未付款;20:已付款;30:已发货;40:已收货;50:已提交;60已确认;70:已完成;80:退货退款申请中;90:退货、退款中",
    public static final String QUERY_ORDER_LIST = IP + "/XRMall/rest/queryOrderList.json";

    //获取订单详情
    public static final String QUERY_ORDER_DETIAL = IP + "/XRMall/rest/queryOrderDetial.json";

    //订单支付
    public static final String ORDER_PAY = IP + "/XRMall/rest/orderPay.json";

    //立即评价
    public static final String ADD_EVALUATION = IP + "/XRMall/rest/addEvaluation.json";

    //订单确认收货
    public static final String CONFIRM_BUYER_GOODS = IP + "/XRMall/rest/confirmBuyerGoods.json";

    //获取订单物流单号
    public static final String QUERY_ORDER_SHIPPING_CODE = IP + "/XRMall/rest/queryOrderShippingCode.json";

    //订单取消
    public static final String CANCEL_ORDER = IP + "/XRMall/rest/cancelOrder.json";

    //订单删除
    public static final String DELETE_ORDER = IP + "/XRMall/rest/deleteOrder.json";

    //退款退货申请
    public static final String APPLY_REFUND_OR_RETURN = IP + "/XRMall/rest/applyRefundOrReturn.json";

    //退快退货详情及协商详情
    public static final String GET_REFUND_OR_RETURN_INFO = IP + "/XRMall/rest/getRefundOrReturnInfo.json";


//订单接口******************************************************************************************

//我的平台接口***************************************************************************************

    //帖子发布
    public static final String SAVE_POST = IP + "/XRMall/rest/savePost.json";

    //交流区帖子列表查询
    public static final String GET_MALL_BBS_LIST = IP + "/XRMall/rest/getMallBbsList.json";

    //评论
    public static final String SAVE_REPLY = IP + "/XRMall/rest/saveReply.json";

    //帖子详情和评论列表
    public static final String GET_MALL_BBS_REPLY_LIST = IP + "/XRMall/rest/getMallBbsReplyList.json";

    //动态列表
    public static final String GET_MALL_DYNAMIC_LIST = IP + "/XRMall/rest/getMallDynamicList.json";

    //动态详情
    public static final String GET_MALL_DYNAMIC_DETAIL = IP + "/XRMall/rest/getMallDynamicDetail.json";

    //商品区发布
    public static final String SAVE_MALL_GOODS_ZONE = IP + "/XRMall/rest/saveMallGoodsZone.json";

    //商品区列表
    public static final String GET_MALL_GOODS_ZONE_LIST = IP + "/XRMall/rest/getMallGoodsZoneList.json";

    //商品区详情
    public static final String GET_MALL_GOODS_ZONE_INFO = IP + "/XRMall/rest/getMallGoodsZoneInfo.json";

//我的平台接口***************************************************************************************


    public static int getCacheUserId(Context context) {
        return context.getSharedPreferences(FILENAME, Context.MODE_PRIVATE).getInt(USERID, 33);
    }

    public static void setCacheUserId(Context context, int userId) {
        SharedPreferences.Editor editor = context.getSharedPreferences(FILENAME, Context.MODE_PRIVATE).edit();
        editor.putInt(USERID, userId);
        editor.commit();
    }

    public static String getCacheUsername(Context context) {
        return context.getSharedPreferences(FILENAME, Context.MODE_PRIVATE).getString(USERNAME, "");
    }

    public static void setCacheUsername(Context context, String username) {
        SharedPreferences.Editor editor = context.getSharedPreferences(FILENAME, Context.MODE_PRIVATE).edit();
        editor.putString(USERNAME, username);
        editor.commit();
    }

    public static String getCacheUserMobile(Context context) {
        return context.getSharedPreferences(FILENAME, Context.MODE_PRIVATE).getString(USERMOBILE, "");
    }

    public static void setCacheUserMobile(Context context, String username) {
        SharedPreferences.Editor editor = context.getSharedPreferences(FILENAME, Context.MODE_PRIVATE).edit();
        editor.putString(USERMOBILE, username);
        editor.commit();
    }

    /**
     * 判断手机格式是否正确
     *
     * @param mobiles
     * @return
     */
    public static boolean isMobileNO(String mobiles) {
        Pattern p = Pattern
                .compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }

    /**
     * 判断email格式是否正确
     *
     * @param email
     * @return
     */
    public static boolean isEmail(String email) {
        String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(email);
        return m.matches();
    }

    /**
     * 判断邮编
     *
     * @param zipString
     * @return
     */
    public static boolean isZipNO(String zipString) {
        String str = "^[1-9][0-9]{5}$";
        return Pattern.compile(str).matcher(zipString).matches();
    }

    /**
     * 读取文本数据
     *
     * @param context  程序上下文
     * @param fileName 文件名
     * @return String, 读取到的文本内容，失败返回null
     */
    public static String readAssets(Context context, String fileName) {
        InputStream is = null;
        String content = null;
        try {
            is = context.getAssets().open(fileName);
            if (is != null) {
                byte[] buffer = new byte[1024];
                ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
                while (true) {
                    int readLength = is.read(buffer);
                    if (readLength == -1) break;
                    arrayOutputStream.write(buffer, 0, readLength);
                }
                is.close();
                arrayOutputStream.close();
                content = new String(arrayOutputStream.toByteArray());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            content = null;
        } finally {
            try {
                if (is != null) is.close();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
        return content;
    }

    /**
     * 地址拼接
     */
    public static String getUrl(String url, HashMap<String, String> params) {
        //此处URL为web给你的链接地址
        String str = url;
        // 添加url参数
        if (params != null) {
            Iterator<String> it = params.keySet().iterator();
            StringBuffer sb = null;
            while (it.hasNext()) {
                String key = it.next();
                String value = params.get(key);
                if (sb == null) {
                    sb = new StringBuffer();
                    sb.append("?");
                } else {
                    sb.append("&");
                }
                sb.append(key);
                sb.append("=");
                sb.append(value);
            }
            str += sb.toString();
        }
        return str;
    }

    public static CharSequence getCodeMsg(Context context, int code) {
        Log.i("getCodeMsg", code + "");
        CharSequence text = "";
        switch (code) {
            case 400:
                text = context.getResources().getString(R.string.ico_network_connect_400);
                break;
            case 404:
                text = context.getResources().getString(R.string.ico_network_connect_404);
                break;
            case 405:
                text = context.getResources().getString(R.string.ico_network_connect_405);
                break;
            case 408:
                text = context.getResources().getString(R.string.ico_network_request_timesout);
                break;
            case 500:
                text = context.getResources().getString(R.string.ico_network_internal_server_error);
                break;
            case -1:
                text = context.getResources().getString(R.string.ico_network_parameter_analysis_error);
                break;
            default:
                text = context.getResources().getString(R.string.ico_network_connect_unknown_error);
                break;
        }
        return text;
    }

    public static String url = "https://wsq.umeng.com/";
    public static String text = "友盟微社区sdk，多终端一社区，为您的app添加社区就是这么简单";
    public static String title = "友盟微社区";
    public static String imageurl = "http://dev.umeng.com/images/tab2_1.png";
    public static String videourl = "http://video.sina.com.cn/p/sports/cba/v/2013-10-22/144463050817.html";
    public static String musicurl = "http://music.huoxing.com/upload/20130330/1364651263157_1085.mp3";

}
