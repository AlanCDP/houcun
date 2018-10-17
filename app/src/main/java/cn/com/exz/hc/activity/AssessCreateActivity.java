package cn.com.exz.hc.activity;

import android.content.Intent;
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
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.com.exz.hc.R;
import cn.com.exz.hc.adapter.AssessCreateAdapter;
import cn.com.exz.hc.application.App;
import cn.com.exz.hc.entity.AssessCreateEntity;
import cn.com.exz.hc.entity.ThreeDataModel;
import cn.com.exz.hc.utils.FusionField;
import cn.com.szw.lib.myframework.base.BaseActivity;
import cn.com.szw.lib.myframework.config.Constants;
import cn.com.szw.lib.myframework.utils.RecycleViewDivider;
import cn.com.szw.lib.myframework.utils.net.NetEntity;
import cn.com.szw.lib.myframework.utils.net.callback.JsonCallback;
import cn.com.szw.lib.myframework.view.CustomLoadMoreView;

/**
 * Created by weicao on 2018/8/21.
 * 考核创建
 */

public class AssessCreateActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {

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
    @BindView(R.id.unit)
    TextView unit;
    @BindView(R.id.post)
    TextView post;
    @BindView(R.id.btn_search)
    TextView btnSearch;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refresh)
    SwipeRefreshLayout refresh;
    private AssessCreateAdapter adapter;
    private List<AssessCreateEntity> list;


    private int page = 1;
    public int refreshState = 0;

    private List<ThreeDataModel.DepartListBean> listUnit = new ArrayList();
    private List<ThreeDataModel.PostListBean> listPost = new ArrayList();

    private String unitId = "";//单位
    private String postId = "";//职务

    @Override
    public int setInflateId() {
        return R.layout.activity_assess_create;
    }


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
    public void init() throws Exception {
        super.init();

        initData();
        initRecycler();


    }


    private void initRecycler() {
        list = new ArrayList<>();



        adapter = new AssessCreateAdapter();
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
//        adapter.setNewData(list);


        recyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
//                Utils.startActivity(getContext(),OverseeLuoShiActivity.class);

                Intent intent = new Intent(mContext, AssessCreateDetailActivity.class);
                intent.putExtra("id",((AssessCreateEntity)adapter.getData().get(position)).getId());
                startActivity(intent);


            }
        });
    }


    private void initData() {
        HashMap<String, String> params = new HashMap<>();
        params.put("sessionId", App.getLoginUserId());

        OkGo.<NetEntity<ThreeDataModel>>post(FusionField.syncData)
                .params(params)
                .tag(this)
                .execute(new JsonCallback<NetEntity<ThreeDataModel>>() {

                    @Override
                    public void onSuccess(Response<NetEntity<ThreeDataModel>> response) {

                        if (response.body().getCode() == Constants.NetCode.SUCCESS) {
                            listUnit = response.body().getData().getDepartList();
                            listPost = response.body().getData().getPostList();


                        }
                    }

                    @Override
                    public void onError(Response<NetEntity<ThreeDataModel>> response) {
                        super.onError(response);


                    }
                });
    }

    private void initPort() {

        HashMap<String, String> params = new HashMap<>();
        params.put("sessionId", App.getLoginUserId());
        params.put("page", String.valueOf(page));
        params.put("rows", "10");
        params.put("unitId", unitId);
        params.put("dutyId", postId);
        OkGo.<NetEntity<List<AssessCreateEntity>>>post(FusionField.ASSESS_CREATE_SEARCH)
                .params(params)
                .tag(this)
                .execute(new JsonCallback<NetEntity<List<AssessCreateEntity>>>() {

                    @Override
                    public void onSuccess(Response<NetEntity<List<AssessCreateEntity>>> response) {
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
                    public void onError(Response<NetEntity<List<AssessCreateEntity>>> response) {
                        super.onError(response);

                        refresh.setEnabled(true);
                        refresh.isRefreshing();
                        adapter.loadMoreFail();
                    }
                });
    }


    @OnClick({R.id.mLeftImg, R.id.unit, R.id.post, R.id.btn_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mLeftImg:
                finish();
                break;
            case R.id.unit:

                Intent intent = new Intent(mContext, UnitActivity.class);
                intent.putExtra("list", (Serializable) listUnit);
                startActivityForResult(intent, 100);
                overridePendingTransition(R.anim.show, R.anim.hide);

                break;
            case R.id.post:
                Intent intent1 = new Intent(mContext, PostActivity.class);
                intent1.putExtra("list", (Serializable) listPost);
                startActivityForResult(intent1, 200);
                overridePendingTransition(R.anim.show, R.anim.hide);
                break;
            case R.id.btn_search:

               onRefresh();


                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 100) {
            unit.setText(data.getStringExtra("name"));
            unitId = data.getStringExtra("id");
        } else if (resultCode == 200) {
            post.setText(data.getStringExtra("name"));
            postId = data.getStringExtra("id");

        }
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
