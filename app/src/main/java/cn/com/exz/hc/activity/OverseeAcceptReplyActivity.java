package cn.com.exz.hc.activity;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;
import cn.com.exz.hc.R;
import cn.com.exz.hc.application.App;
import cn.com.exz.hc.utils.FusionField;
import cn.com.szw.lib.myframework.base.BaseActivity;
import cn.com.szw.lib.myframework.config.Constants;
import cn.com.szw.lib.myframework.utils.RxBus;
import cn.com.szw.lib.myframework.utils.net.NetEntity;
import cn.com.szw.lib.myframework.utils.net.callback.DialogCallback;
import cn.com.szw.lib.myframework.view.ClearWriteEditText;

/**
 * Created by weicao on 2018/8/21.
 * 督办受理——回复
 */

public class OverseeAcceptReplyActivity extends BaseActivity {
    @BindView(R.id.mLeft)
    TextView mLeft;
    @BindView(R.id.mTitle)
    TextView mTitle;
    @BindView(R.id.mRight)
    TextView mRight;
    @BindView(R.id.mRightImg)
    ImageView mRightImg;
    @BindView(R.id.mLeftImg)
    ImageView mLeftImg;
    @BindView(R.id.parent_lay)
    RelativeLayout parentLay;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.et_content)
    ClearWriteEditText etContent;
    @BindView(R.id.btn_reply)
    TextView btnReply;
    @BindView(R.id.content)
    TextView content;
    @BindView(R.id.remark)
    TextView remark;
    private String id;

    @Override
    public boolean initToolbar() {

        toolbar.setContentInsetsAbsolute(0, 0);
        mTitle.setTextSize(18);
        mTitle.setTextColor(ContextCompat.getColor(this, R.color.white));
        mTitle.setText("督办受理");
        setSupportActionBar(toolbar);
        return false;
    }

    @Override
    public int setInflateId() {
        return R.layout.activity_oversee_accept_reply;
    }


    @Override
    public void init() throws Exception {
        super.init();

        content.setText(getIntent().getStringExtra("neirong"));
        remark.setText(getIntent().getStringExtra("beizhu"));
        id = getIntent().getStringExtra("id");
    }



    private void initPort() {
        HashMap<String, String> params = new HashMap<>();
        params.put("sessionId", App.getLoginUserId());
        params.put("id", id);
        params.put("reply", etContent.getText().toString());



        OkGo.<NetEntity<String >>post(FusionField.OVERSEE_ACCEPT_UP_SEND)
                .params(params)
                .tag(this)
                .execute(new DialogCallback<NetEntity<String>>(mContext) {

                    @Override
                    public void onSuccess(Response<NetEntity<String>> response) {

                        if (response.body().getCode() == Constants.NetCode.SUCCESS){
                            finish();
                            RxBus.get().post("refresh", "refresh");

                        }
                    }

                    @Override
                    public void onError(Response<NetEntity<String>> response) {
                        super.onError(response);


                    }
                });
    }

    @OnClick({R.id.mLeftImg, R.id.btn_reply})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mLeftImg:
                finish();
                break;
            case R.id.btn_reply:
                if (etContent.getText().toString().isEmpty()) {
                    etContent.setShakeAnimation();
                }else {
                    initPort();
                }
                break;
        }
    }
}
