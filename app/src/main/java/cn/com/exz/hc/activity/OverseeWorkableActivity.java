package cn.com.exz.hc.activity;

import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.flyco.tablayout.SlidingTabLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
import cn.com.exz.hc.R;
import cn.com.exz.hc.fragment.NoOverseeFragment;
import cn.com.szw.lib.myframework.base.BaseActivity;

/**
 * Created by weicao on 2018/8/15.
 * 督办落实activity
 */

public class OverseeWorkableActivity extends BaseActivity {
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
    @BindView(R.id.tl_4)
    SlidingTabLayout tabStub;
    @BindView(R.id.vp)
    ViewPager pager;


    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private final String[] mTitles = {
            "待督办", "已督办"
    };


    @Override
    public boolean initToolbar() {

        toolbar.setContentInsetsAbsolute(0, 0);
        mTitle.setTextSize(18);
        mTitle.setTextColor(ContextCompat.getColor(this, R.color.white));
        mTitle.setText("督办落实");
        setSupportActionBar(toolbar);
        return false;
    }


    @Override
    public void init() throws Exception {
        super.init();

        mFragments.add(NoOverseeFragment.getInstance("0"));
        mFragments.add(NoOverseeFragment.getInstance("1"));

        tabStub.setViewPager(pager, mTitles, this, mFragments);
    }

    @Override
    public int setInflateId() {
        return R.layout.activity_top_bar;
    }



    @OnClick(R.id.mLeftImg)
    public void onViewClicked() {
        finish();
    }
}