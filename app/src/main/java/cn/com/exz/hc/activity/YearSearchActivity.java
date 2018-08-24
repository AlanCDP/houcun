package cn.com.exz.hc.activity;

import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.SizeUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.OnClick;
import cn.com.exz.hc.R;
import cn.com.exz.hc.adapter.YearSearchAdapter;
import cn.com.exz.hc.application.App;
import cn.com.exz.hc.entity.YearSearchEntity;
import cn.com.exz.hc.utils.FusionField;
import cn.com.szw.lib.myframework.base.BaseActivity;
import cn.com.szw.lib.myframework.config.Constants;
import cn.com.szw.lib.myframework.utils.RecycleViewDivider;
import cn.com.szw.lib.myframework.utils.net.NetEntity;
import cn.com.szw.lib.myframework.utils.net.callback.DialogCallback;
import cn.com.szw.lib.myframework.view.CustomLoadMoreView;

/**
 * Created by weicao on 2018/8/24.
 */

public class YearSearchActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refresh)
    SwipeRefreshLayout refresh;
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
    @BindView(R.id.commit)
    TextView commit;


    private int page = 1;
    public int refreshState = 0;

    private YearSearchAdapter adapter;

    @Override
    public boolean initToolbar() {

        toolbar.setContentInsetsAbsolute(0, 0);
        mTitle.setTextSize(18);
        mTitle.setTextColor(ContextCompat.getColor(this, R.color.white));
        mTitle.setText("个人年度综合查询");
        setSupportActionBar(toolbar);
        return false;
    }

    @Override
    public int setInflateId() {
        return R.layout.activity_search;
    }

    @Override
    public void init() throws Exception {
        super.init();

        initRecyclerView();
        onRefresh();
    }

    private void initRecyclerView() {

        adapter = new YearSearchAdapter();
        adapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        adapter.setLoadMoreView(new CustomLoadMoreView());
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.addItemDecoration(new RecycleViewDivider(mContext, LinearLayoutManager.HORIZONTAL, 20, ContextCompat.getColor(mContext, R.color.background)));
        recyclerView.setAdapter(adapter);
        recyclerView.setClipToPadding(false);
        recyclerView.setPadding(SizeUtils.dp2px(0), SizeUtils.dp2px(10), SizeUtils.dp2px(0), SizeUtils.dp2px(0));
        adapter.setOnLoadMoreListener(this, recyclerView);
        refresh.setOnRefreshListener(this);
        adapter.setEmptyView(LayoutInflater.from(mContext).inflate(R.layout.view_empty1, new RelativeLayout(mContext), false));

    }



    private void initPort() {

        HashMap<String, String> params = new HashMap<>();
        params.put("sessionId", App.getLoginUserId());
        params.put("page", String.valueOf(page));
        params.put("rows", "10");
        params.put("examCycle", "year");


        OkGo.<NetEntity<List<YearSearchEntity>>>post(FusionField.YEAR_SEARCH)
                .params(params)
                .tag(this)
                .execute(new DialogCallback<NetEntity<List<YearSearchEntity>>>(mContext) {

                    @Override
                    public void onSuccess(Response<NetEntity<List<YearSearchEntity>>> response) {
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
                    public void onError(Response<NetEntity<List<YearSearchEntity>>> response) {
                        super.onError(response);

                        refresh.setEnabled(true);
                        refresh.isRefreshing();
                        adapter.loadMoreFail();
                    }
                });
    }

    //计算
    private void initCount() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, 0);
        SimpleDateFormat df = new SimpleDateFormat("yyyy", Locale.CHINA);
        String queryDate = df.format(c.getTime());

        HashMap<String, String> params = new HashMap<>();

        params.put("sessionId", App.getLoginUserId());
        params.put("examCycle", "year");
        params.put("queryDate", queryDate);



        OkGo.<NetEntity<String >>post(FusionField.COUNT)
                .params(params)
                .tag(this)
                .execute(new DialogCallback<NetEntity<String>>(mContext) {

                    @Override
                    public void onSuccess(Response<NetEntity<String>> response) {

                        if (response.body().getCode() == Constants.NetCode.SUCCESS) {
                            onRefresh();
                        }
                    }

                    @Override
                    public void onError(Response<NetEntity<String>> response) {
                        super.onError(response);


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

    @OnClick({R.id.mLeftImg, R.id.commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mLeftImg:
                finish();
                break;
            case R.id.commit:
                initCount();
                break;
        }
    }
}
