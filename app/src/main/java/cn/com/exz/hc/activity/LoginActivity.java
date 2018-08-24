package cn.com.exz.hc.activity;

import android.text.TextUtils;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;
import cn.com.exz.hc.R;
import cn.com.exz.hc.application.App;
import cn.com.exz.hc.entity.UserBean;
import cn.com.exz.hc.utils.FusionField;
import cn.com.szw.lib.myframework.base.BaseActivity;
import cn.com.szw.lib.myframework.config.Constants;
import cn.com.szw.lib.myframework.utils.Utils;
import cn.com.szw.lib.myframework.utils.net.NetEntity;
import cn.com.szw.lib.myframework.utils.net.callback.DialogCallback;
import cn.com.szw.lib.myframework.view.ClearWriteEditText;

/**
 * Created by weicao on 2018/8/21.
 * 登录页
 */

public class LoginActivity extends BaseActivity {
    @BindView(R.id.account)
    ClearWriteEditText account;
    @BindView(R.id.pwd)
    ClearWriteEditText pwd;
    @BindView(R.id.btn_login)
    TextView btnLogin;

    @Override
    public boolean initToolbar() {
        return false;
    }

    @Override
    public int setInflateId() {
        return R.layout.activity_login;
    }


    @Override
    public void init() throws Exception {
        super.init();

        if (!TextUtils.isEmpty(SPUtils.getInstance().getString("account"))) {
            account.setText(SPUtils.getInstance().getString("account"));
        }
        if (!TextUtils.isEmpty(SPUtils.getInstance().getString("pwd"))) {
            pwd.setText(SPUtils.getInstance().getString("pwd"));
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

                        if (response.body().getCode() == Constants.NetCode.SUCCESS){
                            App.setUser(response.body().getData());

                            SPUtils.getInstance().put("account",account.getText().toString());
                            SPUtils.getInstance().put("pwd",pwd.getText().toString());

                            Utils.startActivity(mContext,MainActivity.class);
                        }
                    }

                    @Override
                    public void onError(Response<NetEntity<UserBean>> response) {
                        super.onError(response);


                    }
                });
    }

    @OnClick(R.id.btn_login)
    public void onViewClicked() {
        if (TextUtils.isEmpty(account.getText().toString())){
            account.setShakeAnimation();
        }else if (TextUtils.isEmpty(pwd.getText().toString())){
            pwd.setShakeAnimation();
        }else {
            initPort();
        }
//        Utils.startActivity(mContext, MainActivity.class);
    }
}
