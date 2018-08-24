package cn.com.exz.hc.activity;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import cn.com.exz.hc.R;
import cn.com.szw.lib.myframework.base.BaseActivity;

/**
 * Created by weicao on 2018/8/21.
 * 月度个人查询
 */

public class MonthSearchDetailActivity extends BaseActivity {
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
    @BindView(R.id.unit)
    TextView unit;
    @BindView(R.id.people)
    TextView people;
    @BindView(R.id.duty)
    TextView duty;
    @BindView(R.id.point)
    TextView point;
    @BindView(R.id.result)
    TextView result;
    @BindView(R.id.date)
    TextView date;
    @BindView(R.id.btn_commit)
    TextView btnCommit;

    @Override
    public boolean initToolbar() {
        toolbar.setContentInsetsAbsolute(0, 0);
        mTitle.setTextSize(18);
        mTitle.setTextColor(ContextCompat.getColor(this, R.color.white));
        mTitle.setText("个人月度考核查询");
        setSupportActionBar(toolbar);
        return false;
    }

    @Override
    public int setInflateId() {
        return R.layout.activity_year_search;
    }


    @OnClick({R.id.mLeftImg, R.id.btn_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mLeftImg:
                finish();
                break;
            case R.id.btn_commit:
                break;
        }
    }
}
