package cn.com.exz.hc.activity;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;
import cn.com.exz.hc.R;
import cn.com.exz.hc.application.App;
import cn.com.exz.hc.utils.FusionField;
import cn.com.szw.lib.myframework.base.BaseActivity;
import cn.com.szw.lib.myframework.utils.RxBus;
import cn.com.szw.lib.myframework.utils.net.NetEntity;
import cn.com.szw.lib.myframework.utils.net.callback.DialogCallback;

/**
 * Created by weicao on 2018/8/21.
 * 重点工程任务处理---待受理
 */

public class NoAcceptActivity1 extends BaseActivity {
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
    @BindView(R.id.start_time)
    TextView startTime;
    @BindView(R.id.end_time)
    TextView endTime;
    @BindView(R.id.unit)
    TextView unit;
    @BindView(R.id.people)
    TextView people;
    @BindView(R.id.project_name)
    TextView projectName;
    @BindView(R.id.steps)
    TextView steps;
    @BindView(R.id.commit)
    TextView commit;


    @Override
    public boolean initToolbar() {
        toolbar.setContentInsetsAbsolute(0, 0);
        mTitle.setTextSize(18);
        mTitle.setTextColor(ContextCompat.getColor(this, R.color.white));
        mTitle.setText("任务受理");
        setSupportActionBar(toolbar);
        return false;
    }

    @Override
    public int setInflateId() {
        return R.layout.activity_no_accept1;
    }

    @Override
    public void init() throws Exception {
        super.init();


        startTime.setText(getIntent().getStringExtra("startTime"));
        endTime.setText(getIntent().getStringExtra("endTime"));
        unit.setText(getIntent().getStringExtra("people"));
        people.setText(getIntent().getStringExtra("unit"));
        projectName.setText(getIntent().getStringExtra("projectName"));
        steps.setText(getIntent().getStringExtra("buzou"));


    }

    @OnClick({R.id.mLeftImg, R.id.commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mLeftImg:
                finish();
                break;
            case R.id.commit:
                initPort();
                break;
        }
    }

    private void initPort() {
        HashMap<String, String> params = new HashMap<>();
        params.put("sessionId", App.getLoginUserId());
        params.put("ids", getIntent().getStringExtra("id"));


        OkGo.<NetEntity<String>>post(FusionField.POINT_TEMPORARY_DO_shouli)
                .params(params)
                .tag(this)
                .execute(new DialogCallback<NetEntity<String>>(mContext) {

                    @Override
                    public void onSuccess(Response<NetEntity<String>> response) {

                        if (response.body().getCode() == 202) {
                            Toast.makeText(mContext, response.body().getMessage(), Toast.LENGTH_SHORT).show();
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
}
