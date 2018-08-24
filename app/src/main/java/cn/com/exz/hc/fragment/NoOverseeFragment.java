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

import com.blankj.utilcode.util.SizeUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.com.exz.hc.R;
import cn.com.exz.hc.activity.OverseeLuoShiActivity;
import cn.com.exz.hc.adapter.NoOverseeAdapter;
import cn.com.exz.hc.application.App;
import cn.com.exz.hc.entity.NoOverseeEntity;
import cn.com.exz.hc.utils.FusionField;
import cn.com.szw.lib.myframework.base.MyBaseFragment;
import cn.com.szw.lib.myframework.config.Constants;
import cn.com.szw.lib.myframework.utils.RecycleViewDivider;
import cn.com.szw.lib.myframework.utils.net.NetEntity;
import cn.com.szw.lib.myframework.utils.net.callback.DialogCallback;
import cn.com.szw.lib.myframework.view.CustomLoadMoreView;

/**
 * Created by weicao on 2018/7/13.
 * 待督办
 */

public class NoOverseeFragment extends MyBaseFragment implements SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refresh)
    SwipeRefreshLayout refresh;
    private String mTitle;
    Unbinder unbinder;

    private int page = 1;
    public int refreshState = 0;
    private NoOverseeAdapter adapter;
//    private List<NoOverseeEntity>list;


    public static NoOverseeFragment getInstance(String title) {
        NoOverseeFragment sf = new NoOverseeFragment();
        sf.mTitle = title;
        return sf;
    }


//    @Subscribe(thread = EventThread.MAIN_THREAD, tags = {@Tag("refresh")})
//    public void ReFresh(String tag) {
//        onRefresh();
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_no_oversee, container, false);
        }
        unbinder = ButterKnife.bind(this, rootView);


        return rootView;
    }


    @Override
    public void initView() {


        adapter = new NoOverseeAdapter();
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


        recyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
//                Utils.startActivity(getContext(),OverseeLuoShiActivity.class);

                Intent intent = new Intent(getContext(), OverseeLuoShiActivity.class);
                intent.putExtra("type", mTitle);
                intent.putExtra("id", ((NoOverseeEntity) adapter.getData().get(position)).getId());

                startActivity(intent);
            }
        });

        initPort();

    }

    private void initPort() {

        HashMap<String, String> params = new HashMap<>();
        params.put("sessionId", App.getLoginUserId());
        params.put("page", String.valueOf(page));
        params.put("rows", "10");
        params.put("status", mTitle);


        OkGo.<NetEntity<List<NoOverseeEntity>>>post(FusionField.OVERSEE_WORKABLE)
                .params(params)
                .tag(this)
                .execute(new DialogCallback<NetEntity<List<NoOverseeEntity>>>(getActivity()) {

                    @Override
                    public void onSuccess(Response<NetEntity<List<NoOverseeEntity>>> response) {
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
                    public void onError(Response<NetEntity<List<NoOverseeEntity>>> response) {
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
}
