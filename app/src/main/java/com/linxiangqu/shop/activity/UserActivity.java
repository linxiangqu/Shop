package com.linxiangqu.shop.activity;

import android.app.DatePickerDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.linxiangqu.shop.Config;
import com.linxiangqu.shop.R;
import com.linxiangqu.shop.bean.user.GetCustomerMsg;
import com.linxiangqu.shop.bean.user.UpdateCustomerMsg;
import com.linxiangqu.shop.common.IDefineView;
import com.linxiangqu.shop.common.base.BaseActivity;
import com.linxiangqu.shop.okhttp.OKHttpManager;
import com.linxiangqu.shop.okhttp.SpotsCallBack;
import com.linxiangqu.shop.utils.PicassoTransformationUtils;
import com.linxiangqu.shop.widget.DialogButtonHandler;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import okhttp3.Call;
import okhttp3.Response;

public class UserActivity extends BaseActivity implements IDefineView, View.OnClickListener {

    private LinearLayout yhm_l, sex_l, csrq_l, email_l, sjh_l, dzgl_l, zhaq_l;
    private ImageView img;
    private TextView yhm, sex, csrq, email, sjh, mToolbartv;
    private ImageButton mToolbarback;
    private int year, month, day, selector;
    private AlertDialog yhmAlertDialog, sexAlertDialog, emailAlertDialog;
    private boolean isGender, gender;
    private String firstTime, firstName, firstEmail;
    private HashMap<String, String> params;
    private Uri uri;
    private Date date;

    //调用照相机返回图片临时文件
    private File tempFile;
    //请求相机
    private static final int REQUEST_CAPTURE = 100;
    //请求相册
    private static final int REQUEST_PICK = 101;
    //请求截图
    private static final int REQUEST_CROP_PHOTO = 102;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createCameraTempFile(savedInstanceState);
        setContentView(R.layout.activity_user);
        initToolbar();
        initView();
        initData();
        initListener();
        bindData();
        setStatusBar();
    }

    @Override
    public void initView() {
        img = (ImageView) findViewById(R.id.activity_user_photo);
        yhm_l = (LinearLayout) findViewById(R.id.activity_user_yhm);
        sex_l = (LinearLayout) findViewById(R.id.activity_user_sex);
        csrq_l = (LinearLayout) findViewById(R.id.activity_user_csrq);
        email_l = (LinearLayout) findViewById(R.id.activity_user_email);
        sjh_l = (LinearLayout) findViewById(R.id.activity_user_sjh);
        dzgl_l = (LinearLayout) findViewById(R.id.activity_user_dzgl);
        zhaq_l = (LinearLayout) findViewById(R.id.activity_user_zhaq);
        yhm = (TextView) findViewById(R.id.activity_user_yhm_tv);
        sex = (TextView) findViewById(R.id.activity_user_sex_tv);
        csrq = (TextView) findViewById(R.id.activity_user_csrq_tv);
        email = (TextView) findViewById(R.id.activity_user_email_tv);
        sjh = (TextView) findViewById(R.id.activity_user_sjh_tv);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {
        img.setOnClickListener(this);
        yhm_l.setOnClickListener(this);
        sex_l.setOnClickListener(this);
        csrq_l.setOnClickListener(this);
        email_l.setOnClickListener(this);
        sjh_l.setOnClickListener(this);
        dzgl_l.setOnClickListener(this);
        zhaq_l.setOnClickListener(this);
    }

    @Override
    public void bindData() {
        OKHttpManager.postGetCustomerMsg(Config.GET_CUSTOMER_MAS, Config.getCacheUserId(this), new SpotsCallBack<GetCustomerMsg>(this) {
            @Override
            public void onSuccess(Call call, Response response, GetCustomerMsg getCustomerMsg) {
                if (getCustomerMsg.getStateCode() == 0) {
                    firstName = getCustomerMsg.getRealname();
                    isGender = getCustomerMsg.isGender();
                    firstTime = getCustomerMsg.getBirthday() + "";
                    firstEmail = getCustomerMsg.getEmail();

                    Picasso.with(UserActivity.this).load(Config.IP + getCustomerMsg.getUserImg()).transform(new PicassoTransformationUtils()).fit().centerCrop().into(img);
                    yhm.setText(getCustomerMsg.getRealname());
                    sex.setText(getCustomerMsg.isGender() ? "男" : "女");
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd", Locale.CHINA);
                    String[] time = simpleDateFormat.format(getCustomerMsg.getBirthday()).split("-");
                    year = Integer.valueOf(time[0]);
                    month = Integer.valueOf(time[1]);
                    day = Integer.valueOf(time[2]);
                    csrq.setText(year + "-" + (month + 1) + "-" + day);
                    email.setText(getCustomerMsg.getEmail());
                    sjh.setText(getCustomerMsg.getMobile());
                } else
                    showToasts(getCustomerMsg.getMsg());
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
        mToolbartv = (TextView) findViewById(R.id.toolbar_tv);
        mToolbarback = (ImageButton) findViewById(R.id.toolbar_back);
        mToolbartv.setText(R.string.wdzh);
        mToolbarback.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.toolbar_back:
                finish();
                break;
            case R.id.activity_user_photo:
                uploadHeadImage();
                break;
            case R.id.activity_user_yhm:
                int yhmUpdateType = Integer.valueOf(view.getTag().toString());
                initNameDialog(yhmUpdateType);
                break;
            case R.id.activity_user_sex:
                int sexUpdateType = Integer.valueOf(view.getTag().toString());
                initSexDialog(sexUpdateType);
                break;
            case R.id.activity_user_csrq:
                final int csrqUpdateType = Integer.valueOf(view.getTag().toString());
                initBirthdayDialog(csrqUpdateType);
                break;
            case R.id.activity_user_email:
                int emailUpdateType = Integer.valueOf(view.getTag().toString());
                initEmailDialog(emailUpdateType);
                break;
            case R.id.activity_user_sjh:
                break;
            case R.id.activity_user_dzgl:
                openActivity(AddressActivity.class);
                break;
            case R.id.activity_user_zhaq:
                openActivity(SafetyActivity.class);
                break;
            default:
                break;
        }
    }

    private void initDialog(AlertDialog alertDialog) {
        /**
         * 该反射主要通过 AlertController类下  私有字段mHandler来实现Dialog关闭事件，
         * 其中的   mHandler = new ButtonHandler(di);   ButtonHandler为其内部类，
         * 该类中通过  handleMessage下  通过接收点击事件后收到的信息
         *  case MSG_DISMISS_DIALOG:
         *  ((DialogInterface) msg.obj).dismiss();
         *  需要重写   ButtonHandler 类来让其失效。
         *
         */
        try {
            Class alterDialogClass = alertDialog.getClass();
            Field mAlert = alterDialogClass.getDeclaredField("mAlert");
            mAlert.setAccessible(true);
            Object alertControllerClass = mAlert.get(alertDialog);
            Field mHandler = alertControllerClass.getClass().getDeclaredField("mHandler");
            mHandler.setAccessible(true);
            mHandler.set(alertControllerClass, new DialogButtonHandler(alertDialog));
            Log.d("handler", alterDialogClass + "---" + mAlert + "+++++" + alertControllerClass + "_____" + mHandler);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 用户名Dialog
     *
     * @param yhmUpdateType
     */
    private void initNameDialog(final int yhmUpdateType) {
        if (yhmAlertDialog == null) {
            final EditText name = new EditText(this);
            name.setSingleLine();
            name.setEllipsize(TextUtils.TruncateAt.END);
            AlertDialog.Builder builder = new AlertDialog.Builder(UserActivity.this);
            builder.setTitle("请输入新的用户名！");
            builder.setView(name);
            builder.setCancelable(true);
            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    name.setText("");
                    dialog.dismiss();
                }
            });
            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (firstName == null)
                        firstName = "";
                    String realname = name.getText().toString().trim();
                    if (realname.length() == 0) {
                        showToasts("请输入用户名");
                    } else if (firstName.equals(realname)) {
                        showToasts("此用户名与修改之前相同，请更换用户名重新输入！");
                    } else {
                        params = new HashMap<>();
                        params.put("userId", Config.getCacheUserId(UserActivity.this) + "");
                        params.put("updateType", yhmUpdateType + "");
                        params.put("realname", realname);
                        request(params, null, yhmUpdateType);
                        name.setText("");
                        dialog.dismiss();
                    }
                }
            });
            yhmAlertDialog = builder.create();
            initDialog(yhmAlertDialog);
        }
        yhmAlertDialog.show();
    }

    /**
     * 性别Dialog
     *
     * @param sexUpdateType
     */
    private void initSexDialog(final int sexUpdateType) {
        String string[] = {"男", "女"};
        if (sexAlertDialog == null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(UserActivity.this);
            builder.setTitle("请选择性别！");
            builder.setSingleChoiceItems(string, selector, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    selector = i;
                }
            });
            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    gender = selector == 0;
                    if (isGender != gender) {
                        params = new HashMap<>();
                        params.put("userId", Config.getCacheUserId(UserActivity.this) + "");
                        params.put("updateType", sexUpdateType + "");
                        params.put("gender", gender + "");
                        request(params, null, sexUpdateType);
                    }
                    selector = 0;
                    sexAlertDialog = null;
                    dialog.dismiss();
                }
            });
            sexAlertDialog = builder.create();
        }
        sexAlertDialog.show();
    }

    /**
     * 出生日期Dialog
     *
     * @param csrqUpdateType
     */
    private void initBirthdayDialog(final int csrqUpdateType) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(UserActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                UserActivity.this.year = year;
                UserActivity.this.month = month;
                UserActivity.this.day = day;
                csrq.setText(year + "-" + (UserActivity.this.month + 1) + "-" + day);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    date = simpleDateFormat.parse(year + "-" + month + "-" + day);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if (!(date.getTime() + "").equals(firstTime)) {
                    params = new HashMap<>();
                    params.put("userId", Config.getCacheUserId(UserActivity.this) + "");
                    params.put("updateType", csrqUpdateType + "");
                    params.put("birthday", date.getTime() + "");
                    request(params, null, csrqUpdateType);
                }
            }
        }, year, month, day);
        datePickerDialog.show();
    }

    /**
     * 邮箱Dialog
     *
     * @param emailUpdateType
     */
    private void initEmailDialog(final int emailUpdateType) {
        if (emailAlertDialog == null) {
            final EditText email = new EditText(this);
            email.setSingleLine();
            email.setEllipsize(TextUtils.TruncateAt.END);
            AlertDialog.Builder builder = new AlertDialog.Builder(UserActivity.this);
            builder.setTitle("请新的邮箱！");
            builder.setView(email);
            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    email.setText("");
                    dialog.dismiss();
                }
            });
            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    String firstemail = email.getText().toString().trim();
                    if (firstemail.length() == 0) {
                        showToasts("请输入邮箱");
                    } else if (firstEmail.equals(firstemail)) {
                        showToasts("此邮箱与修改之前相同，请更换邮箱重新输入！");
                    } else if (!Config.isEmail(firstemail)) {
                        showToasts("请输入正确的邮箱！");
                    } else {
                        params = new HashMap<>();
                        params.put("userId", Config.getCacheUserId(UserActivity.this) + "");
                        params.put("updateType", emailUpdateType + "");
                        params.put("email", firstemail);
                        request(params, null, emailUpdateType);
                        email.setText("");
                        dialog.dismiss();
                    }
                }
            });
            emailAlertDialog = builder.create();
            initDialog(emailAlertDialog);
        }
        emailAlertDialog.show();
    }

    /**
     * 更新用户信息请求
     *
     * @param params
     * @param file
     * @param updateType
     */
    private void request(final HashMap<String, String> params, File file, final int updateType) {
        OKHttpManager.postUpdateCustomerMsg(Config.UPDATA_CUSTOMER_MSG, params, file, new SpotsCallBack<UpdateCustomerMsg>(this) {
            @Override
            public void onSuccess(Call call, Response response, UpdateCustomerMsg updateCustomerMsg) {
                if (updateCustomerMsg.getStateCode() == 0) {
                    if (updateType == 1) {
                        Picasso.with(UserActivity.this).load(uri).transform(new PicassoTransformationUtils()).fit().centerCrop().into(img);
                        EventBus.getDefault().post("Photo");
                    }
                    if (updateType == 2) {
                        yhm.setText(params.get("realname"));
                        firstName = params.get("realname");
                    }
                    showToasts(updateCustomerMsg.getMsg());
                    if (updateType == 3) {
                        sex.setText(params.get("gender").equals("true") ? "男" : "女");
                        isGender = gender;
                    }
                    if (updateType == 4) {
                        csrq.setText(year + "-" + (month + 1) + "-" + day);
                        firstTime = date.getTime() + "";
                    }
                    if (updateType == 5) {
                        email.setText(params.get("email"));
                        firstEmail = params.get("email");
                    }
                }
                showToasts(updateCustomerMsg.getMsg());
                call.cancel();
            }

            @Override
            public void onError(Call call, Response response) {
                showErrorToasts(call, response);
            }
        });
    }

    /**
     * 上传头像
     */
    private void uploadHeadImage() {
        View view = LayoutInflater.from(this).inflate(R.layout.activity_user_photo_popupwindow, null);
        TextView btnCarema = (TextView) view.findViewById(R.id.btn_camera);
        TextView btnPhoto = (TextView) view.findViewById(R.id.btn_photo);
        TextView btnCancel = (TextView) view.findViewById(R.id.btn_cancel);
        final PopupWindow popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setBackgroundDrawable(getResources().getDrawable(android.R.color.transparent));
        popupWindow.setOutsideTouchable(true);
        popupWindow.setAnimationStyle(R.style.popwin_anim_style);
        View parent = LayoutInflater.from(this).inflate(R.layout.activity_user, null);
        popupWindow.showAtLocation(parent, Gravity.BOTTOM, 0, 0);

        btnCarema.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到调用系统相机
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(tempFile));
                startActivityForResult(intent, REQUEST_CAPTURE);
                popupWindow.dismiss();
            }
        });
        btnPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到调用系统图库
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(Intent.createChooser(intent, "请选择图片"), REQUEST_PICK);
                popupWindow.dismiss();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
    }

    /**
     * 创建调用系统照相机待存储的临时文件
     *
     * @param savedInstanceState
     */
    private void createCameraTempFile(Bundle savedInstanceState) {
        if (savedInstanceState != null && savedInstanceState.containsKey("tempFile")) {
            tempFile = (File) savedInstanceState.getSerializable("tempFile");
        } else {
            tempFile = new File(checkDirPath(Environment.getExternalStorageDirectory().getPath() + "/image/"),
                    System.currentTimeMillis() + ".jpg");
        }
    }

    /**
     * 检查文件是否存在
     */
    private static String checkDirPath(String dirPath) {
        if (TextUtils.isEmpty(dirPath)) {
            return "";
        }
        File dir = new File(dirPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return dirPath;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("tempFile", tempFile);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        switch (requestCode) {
            case REQUEST_CAPTURE: //调用系统相机返回
                if (resultCode == RESULT_OK) {
                    gotoClipActivity(Uri.fromFile(tempFile));
                }
                break;
            case REQUEST_PICK:  //调用系统相册返回
                if (resultCode == RESULT_OK) {
                    Uri uri = intent.getData();
                    gotoClipActivity(uri);
                }
                break;
            case REQUEST_CROP_PHOTO:  //剪切图片返回
                if (resultCode == RESULT_OK) {
                    uri = intent.getData();
                    if (uri == null) {
                        return;
                    }
                    String cropImagePath = getRealFilePathFromUri(getApplicationContext(), uri);
                    Bitmap bitMap = BitmapFactory.decodeFile(cropImagePath);
                    //此处后面可以将bitMap转为二进制上传后台网络
                    //......
                    HashMap<String, String> params = new HashMap<>();
                    params.put("userId", Config.getCacheUserId(UserActivity.this) + "");
                    params.put("updateType", 1 + "");
                    File file = new File(cropImagePath);
                    Log.d("文件信息", "长度:" + file.length() + ";  文件路径:" + file.getAbsolutePath() + "文件名" + file.getName());
                    request(params, file, 1);
                }
                break;
        }
    }

    /**
     * 打开截图界面
     *
     * @param uri
     */
    public void gotoClipActivity(Uri uri) {
        if (uri == null) {
            return;
        }
        Intent intent = new Intent();
        intent.setClass(this, ClipImageActivity.class);
        intent.setData(uri);
        startActivityForResult(intent, REQUEST_CROP_PHOTO);
    }

    /**
     * Try to return the absolute file path from the given Uri  兼容了file:///开头的 和 content://开头的情况
     *
     * @param context
     * @param uri
     * @return the file path or null
     */
    public static String getRealFilePathFromUri(final Context context, final Uri uri) {
        if (null == uri) return null;
        final String scheme = uri.getScheme();
        String data = null;
        if (scheme == null)
            data = uri.getPath();
        else if (ContentResolver.SCHEME_FILE.equals(scheme)) {
            data = uri.getPath();
        } else if (ContentResolver.SCHEME_CONTENT.equals(scheme)) {
            Cursor cursor = context.getContentResolver().query(uri, new String[]{MediaStore.Images.ImageColumns.DATA}, null, null, null);
            if (null != cursor) {
                if (cursor.moveToFirst()) {
                    int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    if (index > -1) {
                        data = cursor.getString(index);
                    }
                }
                cursor.close();
            }
        }
        return data;
    }
}
