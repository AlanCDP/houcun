package cn.com.exz.hc.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.com.exz.hc.R;
import cn.com.exz.hc.activity.AssessCreateActivity;
import cn.com.exz.hc.activity.DistributingActivity;
import cn.com.exz.hc.activity.FocusDistributingActivity;
import cn.com.exz.hc.activity.ListCheckActivity;
import cn.com.exz.hc.activity.ListEnterActivity;
import cn.com.exz.hc.activity.MonthSearchActivity;
import cn.com.exz.hc.activity.OverseeAcceptActivity;
import cn.com.exz.hc.activity.OverseeWorkableActivity;
import cn.com.exz.hc.activity.ResponsibilityAssessActivity;
import cn.com.exz.hc.activity.ResponsibilityCheckActivity;
import cn.com.exz.hc.activity.TaskAcceptActivity;
import cn.com.exz.hc.activity.YearSearchActivity;
import cn.com.szw.lib.myframework.base.MyBaseFragment;
import cn.com.szw.lib.myframework.utils.Utils;

/**
 * Created by weicao on 2018/8/15.
 */

public class MainFragment extends MyBaseFragment {

    Unbinder unbinder;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_main, container, false);
        }
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void initView() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.list_enter, R.id.list_check, R.id.assess_create, R.id.responsibility_assess, R.id.responsibility_check, R.id.du_ban_luo_shi, R.id.du_ban_shou_li, R.id.year_search, R.id.read_search
            , R.id.task_accept, R.id.task_accept1, R.id.task_distributing})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.list_enter://清单录入
                Utils.startActivity(getContext(), ListEnterActivity.class);

                break;
            case R.id.list_check://清单审核
                Utils.startActivity(getContext(), ListCheckActivity.class);

                break;
            case R.id.assess_create://考核创建
                Utils.startActivity(getContext(), AssessCreateActivity.class);

                break;
            case R.id.responsibility_assess://责任考核
                Utils.startActivity(getContext(), ResponsibilityAssessActivity.class);

                break;
            case R.id.responsibility_check://责任审核
                Utils.startActivity(getContext(), ResponsibilityCheckActivity.class);

                break;
            case R.id.du_ban_luo_shi://督办落实

                Utils.startActivity(getContext(), OverseeWorkableActivity.class);

                break;
            case R.id.du_ban_shou_li://督办受理
                Utils.startActivity(getContext(), OverseeAcceptActivity.class);

                break;
            case R.id.year_search://个人年度考核查询
                Utils.startActivity(getContext(), YearSearchActivity.class);
                break;

            case R.id.read_search://个人月度考核查询
                Utils.startActivity(getContext(), MonthSearchActivity.class);
                break;

            case R.id.task_distributing://临时任务管理--任务派发
                Utils.startActivity(getContext(), DistributingActivity.class);

                break;
            case R.id.task_accept://临时任务管理--任务受理
                Utils.startActivity(getContext(), TaskAcceptActivity.class);

                break;

            case R.id.task_accept1://重点工程管理--任务受理
                Utils.startActivity(getContext(), FocusDistributingActivity.class);


                break;
        }
    }
}
