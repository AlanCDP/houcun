package cn.com.exz.hc.activity;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.com.exz.hc.R;
import cn.com.szw.lib.myframework.base.BaseActivity;

/**
 * Created by weicao on 2018/8/18.
 */

public class LeaveDetailActivity extends BaseActivity {


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
        return R.layout.activity_leave_detail;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.mLeftImg)
    public void onViewClicked() {
        finish();
    }
}
