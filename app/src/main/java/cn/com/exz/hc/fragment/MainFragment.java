package cn.com.exz.hc.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.com.exz.hc.R;
import cn.com.exz.hc.activity.AssessCreateActivity;
import cn.com.exz.hc.activity.DistributingActivity;
import cn.com.exz.hc.activity.FocusDistributingActivity;
import cn.com.exz.hc.activity.LeaveActivity;
import cn.com.exz.hc.activity.ListCheckActivity;
import cn.com.exz.hc.activity.ListEnterActivity;
import cn.com.exz.hc.activity.MonthSearchActivity;
import cn.com.exz.hc.activity.NoticeActivity;
import cn.com.exz.hc.activity.OverseeAcceptActivity;
import cn.com.exz.hc.activity.OverseeWorkableActivity;
import cn.com.exz.hc.activity.ResponsibilityAssessActivity;
import cn.com.exz.hc.activity.ResponsibilityCheckActivity;
import cn.com.exz.hc.activity.ShangJiLeaderActivity;
import cn.com.exz.hc.activity.TaskAcceptActivity;
import cn.com.exz.hc.activity.YearSearchActivity;
import cn.com.exz.hc.activity.ZhuGuanLeaderActivity;
import cn.com.exz.hc.adapter.GridPopAdapter;
import cn.com.exz.hc.application.App;
import cn.com.szw.lib.myframework.base.MyBaseFragment;
import cn.com.szw.lib.myframework.utils.Utils;
import cn.com.szw.lib.myframework.view.CustomLoadMoreView;
import cn.jpush.android.api.JPushInterface;
import q.rorbin.badgeview.QBadgeView;

import static cn.com.exz.hc.activity.LoginActivity.list;

/**
 * Created by weicao on 2018/8/15.
 */

public class MainFragment extends MyBaseFragment {

    Unbinder unbinder;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.list_enter)
    RelativeLayout listEnter;
    @BindView(R.id.list_check)
    RelativeLayout listCheck;
    @BindView(R.id.assess_create)
    RelativeLayout assessCreate;
    @BindView(R.id.responsibility_assess)
    RelativeLayout responsibilityAssess;
    @BindView(R.id.responsibility_check)
    RelativeLayout responsibilityCheck;
    @BindView(R.id.du_ban_luo_shi)
    RelativeLayout duBanLuoShi;
    @BindView(R.id.du_ban_shou_li)
    RelativeLayout duBanShouLi;
    @BindView(R.id.year_search)
    RelativeLayout yearSearch;
    @BindView(R.id.read_search)
    RelativeLayout readSearch;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.task_distributing)
    RelativeLayout taskDistributing;
    @BindView(R.id.task_accept)
    RelativeLayout taskAccept;
    @BindView(R.id.task_accept1)
    RelativeLayout taskAccept1;
    @BindView(R.id.layout)
    LinearLayout layout;
    @BindView(R.id.layout1)
    LinearLayout layout1;


    private GridPopAdapter adapter;


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

        JPushInterface.setAlias(getContext(), 0, App.userBean.getUsername());


        List<String> strings = new ArrayList<>();
        for (int i = 0; i < App.userBean.getFunctions().size(); i++) {
            if (!App.userBean.getFunctions().get(i).equals("AQ")) {
                strings.add(App.userBean.getFunctions().get(i));
            }
        }


        for (int i = 0; i < App.userBean.getFunctions().size(); i++) {

            if (App.userBean.getFunctions().get(i).equals("LS")) {
                layout.setVisibility(View.VISIBLE);
            }


            if (App.userBean.getFunctions().get(i).equals("ZD")) {
                layout1.setVisibility(View.VISIBLE);
            }

            if (App.userBean.getFunctions().get(i).equals("LS001")) {
                taskDistributing.setVisibility(View.VISIBLE);
            }

            if (App.userBean.getFunctions().get(i).equals("LS002")) {
                taskAccept.setVisibility(View.VISIBLE);
                new QBadgeView(getContext()).bindTarget(taskAccept).setBadgeNumber(Integer.parseInt(list.get(0).getLinshirenwu()));

            }

            if (App.userBean.getFunctions().get(i).equals("ZD001")) {
                taskAccept1.setVisibility(View.VISIBLE);
                new QBadgeView(getContext()).bindTarget(taskAccept1).setBadgeNumber(Integer.parseInt(list.get(0).getZhongdiangongcheng()));

            }
        }


        adapter = new GridPopAdapter();
        adapter.bindToRecyclerView(recyclerView);
        adapter.setEmptyView(LayoutInflater.from(getContext()).inflate(R.layout.view_empty, null));
        adapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        adapter.setLoadMoreView(new CustomLoadMoreView());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 5));
//        recyclerView.addItemDecoration(new RecycleViewDivider(getContext(), LinearLayoutManager.VERTICAL, 10, ContextCompat.getColor(getContext(), R.color.trans)));
//        DividerGridItemDecoration decor = new DividerGridItemDecoration(getContext());
//        recyclerView.addItemDecoration(decor);

        adapter.setNewData(strings);

        recyclerView.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.year_search:

                        String s = (String) adapter.getData().get(position);

                        if (s.equals("AQ001")) {
                            Utils.startActivity(getContext(), ListEnterActivity.class);

                        } else if (s.equals("AQ002")) {
                            Utils.startActivity(getContext(), ListCheckActivity.class);

                        } else if (s.equals("AQ003")) {
                            Utils.startActivity(getContext(), AssessCreateActivity.class);

                        } else if (s.equals("AQ004")) {
                            Utils.startActivity(getContext(), ResponsibilityAssessActivity.class);

                        } else if (s.equals("AQ005")) {
                            Utils.startActivity(getContext(), ResponsibilityCheckActivity.class);

                        } else if (s.equals("AQ006")) {
                            Utils.startActivity(getContext(), OverseeWorkableActivity.class);

                        } else if (s.equals("AQ007")) {
                            Utils.startActivity(getContext(), OverseeAcceptActivity.class);

                        } else if (s.equals("AQ008")) {
                            Utils.startActivity(getContext(), YearSearchActivity.class);

                        } else if (s.equals("AQ009")) {
                            Utils.startActivity(getContext(), MonthSearchActivity.class);

                        }

                        break;
                }
            }
        });


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.list_enter, R.id.list_check, R.id.assess_create, R.id.responsibility_assess, R.id.responsibility_check, R.id.du_ban_luo_shi, R.id.du_ban_shou_li, R.id.year_search, R.id.read_search
            , R.id.task_accept, R.id.task_accept1, R.id.task_distributing,
            R.id.leave, R.id.zhuguan, R.id.shangji, R.id.notice})
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
            case R.id.leave://请假申请
                Utils.startActivity(getContext(), LeaveActivity.class);


                break;
            case R.id.zhuguan://主管领导审批
                Utils.startActivity(getContext(), ZhuGuanLeaderActivity.class);


                break;
            case R.id.shangji://上级领导审批上级
                Utils.startActivity(getContext(), ShangJiLeaderActivity.class);
                break;

            case R.id.notice://公告
                Utils.startActivity(getContext(), NoticeActivity.class);

                break;
        }
    }


}
