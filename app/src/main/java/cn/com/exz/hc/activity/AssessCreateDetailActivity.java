package cn.com.exz.hc.activity;

import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.SizeUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.com.exz.hc.R;
import cn.com.exz.hc.adapter.AssessCreateDetailAdapter;
import cn.com.exz.hc.application.App;
import cn.com.exz.hc.entity.AssessCreateDetailEntity;
import cn.com.exz.hc.utils.FusionField;
import cn.com.szw.lib.myframework.base.BaseActivity;
import cn.com.szw.lib.myframework.config.Constants;
import cn.com.szw.lib.myframework.utils.RecycleViewDivider;
import cn.com.szw.lib.myframework.utils.net.NetEntity;
import cn.com.szw.lib.myframework.utils.net.callback.JsonCallback;
import cn.com.szw.lib.myframework.view.CustomLoadMoreView;

/**
 * Created by weicao on 2018/8/21.
 * 考核创建详情
 */

public class AssessCreateDetailActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {
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
    @BindView(R.id.refresh)
    SwipeRefreshLayout refresh;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private int page = 1;
    public int refreshState = 0;
    private AssessCreateDetailAdapter adapter;
    private List<AssessCreateDetailEntity> list;

    private String id;


    @Override
    public boolean initToolbar() {
        toolbar.setContentInsetsAbsolute(0, 0);
        mTitle.setTextSize(18);
        mTitle.setTextColor(ContextCompat.getColor(this, R.color.white));
        mTitle.setText("考核创建");
        setSupportActionBar(toolbar);
        return false;
    }

    @Override
    public int setInflateId() {
        return R.layout.activity_assess_create_detail;
    }


    @Override
    public void init() throws Exception {
        super.init();
        id = getIntent().getStringExtra("id");
        initData();
        onRefresh();
    }

    private void initData() {



        adapter = new AssessCreateDetailAdapter();
        adapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        adapter.setLoadMoreView(new CustomLoadMoreView());
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.addItemDecoration(new RecycleViewDivider(mContext, LinearLayoutManager.HORIZONTAL, 20, ContextCompat.getColor(mContext, R.color.background)));
        recyclerView.setAdapter(adapter);
        recyclerView.setClipToPadding(false);
        recyclerView.setPadding(SizeUtils.dp2px(0), SizeUtils.dp2px(10), SizeUtils.dp2px(0), SizeUtils.dp2px(0));
        adapter.setOnLoadMoreListener(this, recyclerView);
        refresh.setOnRefreshListener(this);


    }


    private void initPort() {

        HashMap<String, String> params = new HashMap<>();
        params.put("sessionId", App.getLoginUserId());
        params.put("page", String.valueOf(page));
        params.put("rows", "10");
        params.put("id", id);
        OkGo.<NetEntity<List<AssessCreateDetailEntity>>>post(FusionField.ASSESS_CREATE_DETAIL_SEARCH)
                .params(params)
                .tag(this)
                .execute(new JsonCallback<NetEntity<List<AssessCreateDetailEntity>>>() {

                    @Override
                    public void onSuccess(Response<NetEntity<List<AssessCreateDetailEntity>>> response) {
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
                    public void onError(Response<NetEntity<List<AssessCreateDetailEntity>>> response) {
                        super.onError(response);

                        refresh.setEnabled(true);
                        refresh.isRefreshing();
                        adapter.loadMoreFail();
                    }
                });
    }

    @OnClick(R.id.mLeftImg)
    public void onViewClicked() {
        finish();
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
}
