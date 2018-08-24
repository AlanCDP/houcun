package cn.com.exz.hc.activity;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
import cn.com.szw.lib.myframework.utils.net.NetEntity;
import cn.com.szw.lib.myframework.utils.net.callback.DialogCallback;
import cn.com.szw.lib.myframework.view.ClearWriteEditText;

/**
 * Created by weicao on 2018/8/18.
 * 清单审核审核界面
 */

public class ListEnterCheckActivity extends BaseActivity {
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
    @BindView(R.id.radio_date)
    RadioButton radioDate;
    @BindView(R.id.radio_current)
    RadioButton radioCurrent;
    @BindView(R.id.radio_group)
    RadioGroup radioGroup;
    @BindView(R.id.et_content)
    ClearWriteEditText etContent;
    @BindView(R.id.btn_add)
    TextView btnAdd;
    private String ids;
    private String result="Y";

    @Override
    public boolean initToolbar() {

        toolbar.setContentInsetsAbsolute(0, 0);
        mTitle.setTextSize(18);
        mTitle.setTextColor(ContextCompat.getColor(this, R.color.white));
        mTitle.setText("清单审核");
        setSupportActionBar(toolbar);
        return false;
    }


    @Override
    public int setInflateId() {
        return R.layout.activity_list_enter_check;
    }


    @Override
    public void init() throws Exception {
        super.init();

        ids = getIntent().getStringExtra("id");


        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.radio_date:
                        result = "Y";
                        break;
                    case R.id.radio_current:
                        result = "N";

                        break;
                }


            }
        });

    }

    private void initPort() {
        HashMap<String, String> params = new HashMap<>();
        params.put("sessionId", App.getLoginUserId());
        params.put("ids", ids);
        params.put("result", result);
        params.put("remark", etContent.getText().toString());



        OkGo.<NetEntity<String>>post(FusionField.LIST_CHECK)
                .params(params)
                .tag(this)
                .execute(new DialogCallback<NetEntity<String >>(mContext) {

                    @Override
                    public void onSuccess(Response<NetEntity<String>> response) {

                        if (response.body().getCode() == Constants.NetCode.SUCCESS){
                            finish();
                            setResult(100);
                        }
                    }

                    @Override
                    public void onError(Response<NetEntity<String>> response) {
                        super.onError(response);


                    }
                });
    }

    @OnClick({R.id.mLeftImg, R.id.btn_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mLeftImg:
                finish();
                break;
            case R.id.btn_add:
                if (etContent.getText().toString().isEmpty()) {
                    etContent.setShakeAnimation();
                }else {
                    initPort();
                }
                break;
        }
    }
}
