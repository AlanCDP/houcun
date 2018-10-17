package cn.com.exz.hc.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.blankj.utilcode.util.SizeUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.hwangjr.rxbus.annotation.Tag;
import com.hwangjr.rxbus.thread.EventThread;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.com.exz.hc.R;
import cn.com.exz.hc.activity.LeaveCheckActivity;
import cn.com.exz.hc.activity.LeaveDetailActivity;
import cn.com.exz.hc.activity.LeaveZhuGuanDetailActivity;
import cn.com.exz.hc.adapter.LeaveAdapter;
import cn.com.exz.hc.application.App;
import cn.com.exz.hc.entity.LeaveListBean;
import cn.com.exz.hc.utils.FusionField;
import cn.com.szw.lib.myframework.base.MyBaseFragment;
import cn.com.szw.lib.myframework.config.Constants;
import cn.com.szw.lib.myframework.utils.RecycleViewDivider;
import cn.com.szw.lib.myframework.utils.net.NetEntity;
import cn.com.szw.lib.myframework.utils.net.callback.DialogCallback;
import cn.com.szw.lib.myframework.view.CustomLoadMoreView;

/**
 * Created by weicao on 2018/8/18.
 */

public class ZhuGuanLraderFragment extends MyBaseFragment implements SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {

    Unbinder unbinder;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refresh)
    SwipeRefreshLayout refresh;
    @BindView(R.id.leave_type)
    TextView leaveType;

    private String mTitle;
    private String type = "";


    private int page = 1;
    public int refreshState = 0;
    private LeaveAdapter adapter;
    private List<LeaveListBean> list;


    OptionsPickerView<String> Options;//请假类型
    ArrayList<String> option_list = new ArrayList<>();//请假类型list
    private String category="";


    public static ZhuGuanLraderFragment getInstance(String title) {
        ZhuGuanLraderFragment sf = new ZhuGuanLraderFragment();
        sf.mTitle = title;
        return sf;
    }


    @Subscribe(thread = EventThread.MAIN_THREAD, tags = {@Tag("refresh")})
    public void ReFresh(String tag) {
        onRefresh();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (rootView == null) {
            rootView = inflater.inflate(R.layout.leave_list, container, false);
        }
        unbinder = ButterKnife.bind(this, rootView);


        return rootView;
    }

    @Override
    public void initView() {


          /*
         * 请假类型
         */
        option_list.add("全部");
        option_list.add("事假");
        option_list.add("病假");
        option_list.add("婚假");
        option_list.add("丧假");
        option_list.add("产假");
        option_list.add("探亲假");
        Options = new OptionsPickerView<>(getActivity());

        Options.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3) {

                leaveType.setText(option_list.get(options1));
                if (options1 == 0) {
                    category = "";
                }else {
                    category = options1+"";
                }
                onRefresh();

            }
        });




        adapter = new LeaveAdapter();
        adapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        adapter.setLoadMoreView(new CustomLoadMoreView());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new RecycleViewDivider(getActivity(), LinearLayoutManager.HORIZONTAL, 20, ContextCompat.getColor(getActivity(), R.color.background)));
        recyclerView.setAdapter(adapter);
        recyclerView.setClipToPadding(false);
        recyclerView.setPadding(SizeUtils.dp2px(0), SizeUtils.dp2px(10), SizeUtils.dp2px(0), SizeUtils.dp2px(0));
        adapter.setOnLoadMoreListener(this, recyclerView);
        refresh.setOnRefreshListener(this);
        adapter.setEmptyView(LayoutInflater.from(getActivity()).inflate(R.layout.view_empty1, new RelativeLayout(getActivity()), false));


        onRefresh();


        recyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {

                if (mTitle.equals("N")){
                    Intent intent = new Intent(getContext(),LeaveCheckActivity.class);
                    intent.putExtra("id",((LeaveListBean)adapter.getData().get(position)).getId());
                    intent.putExtra("type","1");
                    startActivity(intent);
                }else if (mTitle.equals("Y")){

                    Intent intent = new Intent(getContext(),LeaveZhuGuanDetailActivity.class);
                    intent.putExtra("type",((LeaveListBean)adapter.getData().get(position)).getCategory());
                    intent.putExtra("startTime",((LeaveListBean)adapter.getData().get(position)).getStart_date());
                    intent.putExtra("endTime",((LeaveListBean)adapter.getData().get(position)).getEnd_date());
                    intent.putExtra("reason",((LeaveListBean)adapter.getData().get(position)).getLeave_reason());
                    intent.putExtra("zhuguan",((LeaveListBean)adapter.getData().get(position)).getSupervisor());
                    intent.putExtra("shangji",((LeaveListBean)adapter.getData().get(position)).getLeader());
                    intent.putExtra("remark",((LeaveListBean)adapter.getData().get(position)).getRemark());                    intent.putExtra("remark",((LeaveListBean)adapter.getData().get(position)).getRemark());
                    intent.putExtra("id",((LeaveListBean)adapter.getData().get(position)).getId());

                    startActivity(intent);
                }else {
                    Intent intent = new Intent(getContext(),LeaveDetailActivity.class);
                    intent.putExtra("type",((LeaveListBean)adapter.getData().get(position)).getCategory());
                    intent.putExtra("startTime",((LeaveListBean)adapter.getData().get(position)).getStart_date());
                    intent.putExtra("endTime",((LeaveListBean)adapter.getData().get(position)).getEnd_date());
                    intent.putExtra("reason",((LeaveListBean)adapter.getData().get(position)).getLeave_reason());
                    intent.putExtra("zhuguan",((LeaveListBean)adapter.getData().get(position)).getSupervisor());
                    intent.putExtra("shangji",((LeaveListBean)adapter.getData().get(position)).getLeader());
                    intent.putExtra("remark",((LeaveListBean)adapter.getData().get(position)).getRemark());
                    startActivity(intent);
                }



            }
        });

    }

    private void initPort() {

        HashMap<String, String> params = new HashMap<>();
        params.put("sessionId", App.getLoginUserId());
        params.put("page", String.valueOf(page));
        params.put("rows", "10");
        params.put("seachType", "2");
        params.put("queryStatus", mTitle);
        params.put("category", category);


        OkGo.<NetEntity<List<LeaveListBean>>>post(FusionField.LeaveList)
                .params(params)
                .tag(this)
                .execute(new DialogCallback<NetEntity<List<LeaveListBean>>>(getActivity()) {

                    @Override
                    public void onSuccess(Response<NetEntity<List<LeaveListBean>>> response) {
                        refresh.setEnabled(true);
                        refresh.setRefreshing(false);
                        if (response.body().getCode() == 200) {
                            if (refreshState == 0) {
                                adapter.setNewData(response.body().getData());
                            } else {
                                adapter.addData(response.body().getData());
                            }

                            if (response.body().getData().isEmpty()) {
                                adapter.loadMoreEnd();
                            } else {
                                adapter.loadMoreComplete();
                                page++;
                            }
                        }
                    }

                    @Override
                    public void onError(Response<NetEntity<List<LeaveListBean>>> response) {
                        super.onError(response);

                        refresh.setEnabled(true);
                        refresh.isRefreshing();
                        adapter.loadMoreFail();
                    }
                });
    }


    @Override
    public void onRefresh() {
        refresh.setRefreshing(false);
        page = 1;
        refreshState = Constants.RefreshState.STATE_REFRESH;
        initPort();
    }

    @Override
    public void onLoadMoreRequested() {
        refresh.setEnabled(false);
        refreshState = Constants.RefreshState.STATE_LOAD_MORE;
        initPort();


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.leave_type)
    public void onViewClicked() {
        Options.setPicker(option_list);
        Options.setCyclic(false);
        Options.show();
    }
}
