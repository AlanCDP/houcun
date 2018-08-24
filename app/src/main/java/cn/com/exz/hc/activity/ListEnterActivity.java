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
import cn.com.exz.hc.fragment.ListEnterFragment;
import cn.com.exz.hc.fragment.ListEnterNoPassFragment;
import cn.com.szw.lib.myframework.base.BaseActivity;

/**
 * Created by weicao on 2018/8/18.
 * 清单录入界面
 */

public class ListEnterActivity extends BaseActivity {

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
            "清单录入", "驳回待上报","已上报"
    };

    @Override
    public boolean initToolbar() {

        toolbar.setContentInsetsAbsolute(0, 0);
        mTitle.setTextSize(18);
        mTitle.setTextColor(ContextCompat.getColor(this, R.color.white));
        mTitle.setText("清单录入");
        setSupportActionBar(toolbar);
        return false;
    }

    @Override
    public int setInflateId() {
        return R.layout.activity_list_enter;
    }


    @Override
    public void init() throws Exception {
        super.init();


        mFragments.add(ListEnterFragment.getInstance("0"));
        mFragments.add(ListEnterNoPassFragment.getInstance("-4"));
        mFragments.add(ListEnterNoPassFragment.getInstance("2"));


        tabStub.setViewPager(pager, mTitles, this, mFragments);
    }

    @OnClick(R.id.mLeftImg)
    public void onViewClicked() {
        finish();
    }
}
