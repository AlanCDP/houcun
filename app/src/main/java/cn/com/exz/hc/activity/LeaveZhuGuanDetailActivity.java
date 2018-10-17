package cn.com.exz.hc.activity;

import android.content.DialogInterface;
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
import cn.com.exz.hc.view.Dialog;
import cn.com.szw.lib.myframework.base.BaseActivity;
import cn.com.szw.lib.myframework.config.Constants;
import cn.com.szw.lib.myframework.utils.RxBus;
import cn.com.szw.lib.myframework.utils.net.NetEntity;
import cn.com.szw.lib.myframework.utils.net.callback.JsonCallback;

/**
 * Created by weicao on 2018/8/18.
 */

public class LeaveZhuGuanDetailActivity extends BaseActivity {


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
    @BindView(R.id.leave_type)
    TextView leaveType;
    @BindView(R.id.start_time)
    TextView startTime;
    @BindView(R.id.end_time)
    TextView endTime;
    @BindView(R.id.reason)
    TextView reason;
    @BindView(R.id.zhuguan_people)
    TextView zhuguanPeople;
    @BindView(R.id.shangji_people)
    TextView shangjiPeople;
    @BindView(R.id.remark)
    TextView remark;
    @BindView(R.id.commit)
    TextView commit;

    private Dialog.Builder ibuilder;

    @Override
    public boolean initToolbar() {

        toolbar.setContentInsetsAbsolute(0, 0);
        mTitle.setTextSize(18);
        mTitle.setTextColor(ContextCompat.getColor(this, R.color.white));
        mTitle.setText("请假详情");
        setSupportActionBar(toolbar);
        return false;
    }

    @Override
    public int setInflateId() {
        return R.layout.activity_leave_zhuguan_detail;
    }


    @Override
    public void init() throws Exception {
        super.init();

        leaveType.setText(getIntent().getStringExtra("type"));
        startTime.setText(getIntent().getStringExtra("startTime"));
        endTime.setText(getIntent().getStringExtra("endTime"));
        reason.setText(getIntent().getStringExtra("reason"));
        zhuguanPeople.setText(getIntent().getStringExtra("zhuguan"));
        shangjiPeople.setText(getIntent().getStringExtra("shangji"));
        remark.setText(getIntent().getStringExtra("remark"));


    }




    @OnClick({R.id.mLeftImg, R.id.commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mLeftImg:
                finish();
                break;
            case R.id.commit:
                ibuilder = new Dialog.Builder(this);
                ibuilder.setMessage("你确定要销假？");
                ibuilder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                        initData();
                        dialog.dismiss();

                    }
                });
                ibuilder.setNegativeButton("取消", null);
                ibuilder.create().show();
                break;
        }
    }

    private void initData() {
        HashMap<String, String> params = new HashMap<>();
        params.put("sessionId", App.getLoginUserId());
        params.put("id", getIntent().getStringExtra("id"));

        OkGo.<NetEntity<String >>post(FusionField.LeaveMiss)
                .params(params)
                .tag(this)
                .execute(new JsonCallback<NetEntity<String>>() {

                    @Override
                    public void onSuccess(Response<NetEntity<String>> response) {

                        if (response.body().getCode() == Constants.NetCode.SUCCESS) {

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
