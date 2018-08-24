package cn.com.exz.hc.activity;

import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.com.exz.hc.R;
import cn.com.exz.hc.adapter.OverseeLuoShiAdapter;
import cn.com.exz.hc.application.App;
import cn.com.exz.hc.entity.OverseeLuoShiEntity;
import cn.com.exz.hc.utils.FusionField;
import cn.com.szw.lib.myframework.base.BaseActivity;
import cn.com.szw.lib.myframework.config.Constants;
import cn.com.szw.lib.myframework.utils.RecycleViewDivider;
import cn.com.szw.lib.myframework.utils.net.NetEntity;
import cn.com.szw.lib.myframework.utils.net.callback.DialogCallback;
import cn.com.szw.lib.myframework.view.ClearWriteEditText;
import cn.com.szw.lib.myframework.view.CustomLoadMoreView;

/**
 * Created by weicao on 2018/8/18.
 * 督办落实界面
 */

public class OverseeLuoShiActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener{


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
    @BindView(R.id.et_content)
    ClearWriteEditText etContent;
    @BindView(R.id.ed_remark)
    ClearWriteEditText edRemark;
    @BindView(R.id.btn_add)
    TextView btnAdd;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refresh)
    SwipeRefreshLayout refresh;

    private OverseeLuoShiAdapter adapter;
    private List<OverseeLuoShiEntity> list;
    private String scoreId;
    private int page = 1;
    public int refreshState = 0;


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
    public int setInflateId() {
        return R.layout.activity_oversee_luo_shi;
    }

    @Override
    public void init() throws Exception {
        super.init();

        scoreId = getIntent().getStringExtra("id");

        if (getIntent().getStringExtra("type").equals("1")) {
            initRecycler();
        }


    }

    private void initRecycler() {



        adapter = new OverseeLuoShiAdapter();
        adapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        adapter.setLoadMoreView(new CustomLoadMoreView());
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.addItemDecoration(new RecycleViewDivider(mContext, LinearLayoutManager.HORIZONTAL, 20, ContextCompat.getColor(mContext, R.color.background)));
        recyclerView.setAdapter(adapter);
        adapter.setOnLoadMoreListener(this, recyclerView);
        refresh.setOnRefreshListener(this);
        recyclerView.setClipToPadding(false);

        initPort();

    }


    private void initPort() {

        HashMap<String, String> params = new HashMap<>();
        params.put("sessionId", App.getLoginUserId());
        params.put("page", String.valueOf(page));
        params.put("rows", "10");
        params.put("scoreId", scoreId);

//        params.put("queryHandleStatus", mTitle);


        OkGo.<NetEntity<List<OverseeLuoShiEntity>>>post(FusionField.OVERSEE_WORKABLE_ALREADY_WORK)
                .params(params)
                .tag(this)
                .execute(new DialogCallback<NetEntity<List<OverseeLuoShiEntity>>>(mContext) {

                    @Override
                    public void onSuccess(Response<NetEntity<List<OverseeLuoShiEntity>>> response) {
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
                    public void onError(Response<NetEntity<List<OverseeLuoShiEntity>>> response) {
                        super.onError(response);

                        refresh.setEnabled(true);
                        refresh.isRefreshing();
                        adapter.loadMoreFail();
                    }
                });
    }

    @OnClick({R.id.mLeftImg, R.id.btn_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mLeftImg:
                finish();
                break;
            case R.id.btn_add:
                if (etContent.getText().toString().isEmpty()) {
                    etContent.setShakeAnimation();
                } else if (edRemark.getText().toString().isEmpty()) {
                    edRemark.setShakeAnimation();
                }else {
                    initAdd();
                }
                break;
        }
    }


    private void initAdd() {
        HashMap<String, String> params = new HashMap<>();
        params.put("sessionId", App.getLoginUserId());
        params.put("examScoreId", scoreId);
        params.put("superviseRemark", etContent.getText().toString());
        params.put("comments", edRemark.getText().toString());


        OkGo.<NetEntity<String>>post(FusionField.OVERSEE_WORKABLE_ENTER_ADD)
                .params(params)
                .tag(this)
                .execute(new DialogCallback<NetEntity<String>>(mContext) {

                    @Override
                    public void onSuccess(Response<NetEntity<String >> response) {

                        if (response.body().getCode() == Constants.NetCode.SUCCESS){
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

}
