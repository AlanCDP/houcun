package cn.com.exz.hc.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.com.exz.hc.R;
import cn.com.exz.hc.entity.TabEntity;
import cn.com.exz.hc.fragment.MainFragment;
import cn.com.exz.hc.fragment.MineFragment;
import cn.com.szw.lib.myframework.base.BaseActivity;

public class MainActivity extends BaseActivity {
    @BindView(R.id.main_container)
    FrameLayout mainContainer;
    @BindView(R.id.mainTabBar)
    CommonTabLayout mainTabBar;
    @BindView(R.id.activity_main)
    LinearLayout activityMain;
    private String[] mTitles = {"首页", "我的"};
    private int[] mIconSelectIds = {
            R.mipmap.home_blue, R.mipmap.mine
    };
    private int[] mIconUnSelectIds = {
            R.mipmap.home, R.mipmap.mine_line
    };
    //    public CommonTabLayout mainTabBar;
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private ArrayList<Fragment> mFragments = new ArrayList<>();

    @Override
    public boolean initToolbar() {
        return false;
    }

    @Override
    public int setInflateId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        initView();


    }

    private void initView() {
        if ((getIntent().getFlags() & Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT) != 0) {
            finish();
            return;
        }
        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnSelectIds[i]));
        }
        mFragments.add(new MainFragment());
        mFragments.add(new MineFragment());
        mainTabBar.setTabData(mTabEntities, this, R.id.main_container, mFragments);



    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addCategory(Intent.CATEGORY_HOME);
            startActivity(intent);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
//    @Subscribe(thread = EventThread.MAIN_THREAD, tags = {@Tag("currentTab")})
//    public void setCurrentTab(String currentTab) {
//        mainTabBar.setCurrentTab(Integer.parseInt(currentTab));
//
//        mainTabBar.showMsg(2, 2);
//    }
//    @Subscribe(thread = EventThread.MAIN_THREAD, tags = {@Tag("ShopCarNum")})
//    public void setShopCarNum(String ShopCarNum) {
//
//        mainTabBar.showMsg(1, Integer.parseInt(ShopCarNum));
//        if(TextUtils.isEmpty(ShopCarNum)||ShopCarNum.equals("0"))mainTabBar.hideMsg(1);
//    }

}
