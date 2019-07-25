package cn.com.exz.hc.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.com.exz.hc.R;
import cn.com.exz.hc.application.App;
import cn.com.exz.hc.entity.RemindBean;
import cn.com.exz.hc.entity.UserBean;
import cn.com.exz.hc.utils.FusionField;
import cn.com.exz.hc.utils.NotificationsUtils;
import cn.com.szw.lib.myframework.base.BaseActivity;
import cn.com.szw.lib.myframework.config.Constants;
import cn.com.szw.lib.myframework.utils.Utils;
import cn.com.szw.lib.myframework.utils.net.NetEntity;
import cn.com.szw.lib.myframework.utils.net.callback.DialogCallback;
import cn.com.szw.lib.myframework.view.ClearWriteEditText;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.RuntimePermissions;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.ACCESS_NOTIFICATION_POLICY;
import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.READ_PHONE_STATE;

/**
 * Created by weicao on 2018/8/21.
 * 登录页
 */
@RuntimePermissions
public class LoginActivity extends BaseActivity {
    @BindView(R.id.account)
    ClearWriteEditText account;
    @BindView(R.id.pwd)
    ClearWriteEditText pwd;
    @BindView(R.id.btn_login)
    TextView btnLogin;

    public static List<RemindBean> list = new ArrayList<>();

    @Override
    public boolean initToolbar() {
        return false;
    }

    @Override
    public int setInflateId() {
        return R.layout.activity_login;
    }

    @NeedsPermission({READ_PHONE_STATE, ACCESS_NOTIFICATION_POLICY})
    void readPhoneState() {
//        权限申请完毕被调用
    }

    @OnPermissionDenied({READ_PHONE_STATE, ACCESS_NOTIFICATION_POLICY})
    void readPhoneStateDenied() {
        //用户未授予权限时调用的方法


    }

    @OnNeverAskAgain({READ_PHONE_STATE, ACCESS_NOTIFICATION_POLICY})
    void readPhoneStateNever() {
        //永不再询问”权限，调用的方法

    }

    @Override
    public void init() throws Exception {
        super.init();

        LoginActivityPermissionsDispatcher.readPhoneStateWithPermissionCheck(this);

        if (!TextUtils.isEmpty(SPUtils.getInstance().getString("account"))) {
            account.setText(SPUtils.getInstance().getString("account"));
        }
        if (!TextUtils.isEmpty(SPUtils.getInstance().getString("pwd"))) {
            pwd.setText(SPUtils.getInstance().getString("pwd"));
        }


        if (!NotificationsUtils.isNotificationEnabled(this)) {

            android.support.v7.app.AlertDialog.Builder builder;
            builder = new android.support.v7.app.AlertDialog.Builder(this)
                    .setMessage("检测到您没有打开通知,震动，铃声权限，是否去打开").setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            //ToDo: 你想做的事情

                            Intent localIntent = new Intent();
                            localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            if (Build.VERSION.SDK_INT >= 9) {
                                localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
                                localIntent.setData(Uri.fromParts("package", LoginActivity.this.getPackageName(), null));
                            } else if (Build.VERSION.SDK_INT <= 8) {
                                localIntent.setAction(Intent.ACTION_VIEW);

                                localIntent.setClassName("com.android.settings",
                                        "com.android.settings.InstalledAppDetails");

                                localIntent.putExtra("com.android.settings.ApplicationPkgName",
                                        LoginActivity.this.getPackageName());
                            }
                            startActivity(localIntent);


                            dialogInterface.dismiss();

                        }
                    }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            //ToDo: 你想做的事情

                            dialogInterface.dismiss();
                        }
                    });
            builder.create().show();


        }


    }

    private void initPort() {
        HashMap<String, String> params = new HashMap<>();
        params.put("username", account.getText().toString());
        params.put("password", pwd.getText().toString());


        OkGo.<NetEntity<UserBean>>post(FusionField.Login)
                .params(params)
                .tag(this)
                .execute(new DialogCallback<NetEntity<UserBean>>(mContext) {

                    @Override
                    public void onSuccess(Response<NetEntity<UserBean>> response) {

                        if (response.body().getCode() == Constants.NetCode.SUCCESS) {
                            App.setUser(response.body().getData());

                            SPUtils.getInstance().put("account", account.getText().toString());
                            SPUtils.getInstance().put("pwd", pwd.getText().toString());

//                            Utils.startActivity(mContext,MainActivity.class);


                            initPort1();
                        }
                    }

                    @Override
                    public void onError(Response<NetEntity<UserBean>> response) {
                        super.onError(response);


                    }
                });
    }


    private void initPort1() {
        HashMap<String, String> params = new HashMap<>();
        params.put("sessionId", App.getLoginUserId());


        OkGo.<NetEntity<List<RemindBean>>>post(FusionField.REMIND)
                .params(params)
                .tag(this)
                .execute(new DialogCallback<NetEntity<List<RemindBean>>>(mContext) {

                    @Override
                    public void onSuccess(Response<NetEntity<List<RemindBean>>> response) {

                        if (response.body().getCode() == Constants.NetCode.SUCCESS) {
                            list = response.body().getData();
                            Utils.startActivity(mContext, MainActivity.class);

                        }
                    }

                    @Override
                    public void onError(Response<NetEntity<List<RemindBean>>> response) {
                        super.onError(response);


                    }
                });
    }

    @OnClick(R.id.btn_login)
    public void onViewClicked() {
        if (TextUtils.isEmpty(account.getText().toString())) {
            account.setShakeAnimation();
        } else if (TextUtils.isEmpty(pwd.getText().toString())) {
            pwd.setShakeAnimation();
        } else {
            initPort();
        }
//        Utils.startActivity(mContext, MainActivity.class);
    }
}
