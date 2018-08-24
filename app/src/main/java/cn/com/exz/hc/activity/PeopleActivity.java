package cn.com.exz.hc.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.blankj.utilcode.util.SizeUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.com.exz.hc.R;
import cn.com.exz.hc.adapter.PeopleAdapter;
import cn.com.exz.hc.entity.ThreeDataModel;
import cn.com.szw.lib.myframework.utils.RecycleViewDivider;
import cn.com.szw.lib.myframework.view.CustomLoadMoreView;

/**
 * Created by weicao on 2018/8/22.
 * 人员列表
 */

public class PeopleActivity extends Activity {
    @BindView(R.id.viewLine)
    View viewLine;
    @BindView(R.id.ed_search)
    EditText edSearch;
    @BindView(R.id.layout)
    LinearLayout layout;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;


    private PeopleAdapter adapter;
    private List<ThreeDataModel.UserListBean> list = new ArrayList<>();
    private List<ThreeDataModel.UserListBean> data = new ArrayList<>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unit);
        ButterKnife.bind(this);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        edSearch.setHint("请选择审核人");
        list = (List<ThreeDataModel.UserListBean>) getIntent().getSerializableExtra("list");

        initView();

    }


    private void initView() {


        adapter = new PeopleAdapter();
        adapter.bindToRecyclerView(recyclerView);
        adapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        adapter.setLoadMoreView(new CustomLoadMoreView());
        final LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLinearLayoutManager);
        recyclerView.addItemDecoration(new RecycleViewDivider(this, LinearLayoutManager.HORIZONTAL, 1, ContextCompat.getColor(this, R.color.lin_gray)));
        recyclerView.setAdapter(adapter);
        recyclerView.setClipToPadding(false);
        recyclerView.setPadding(SizeUtils.dp2px(0), SizeUtils.dp2px(10), SizeUtils.dp2px(0), SizeUtils.dp2px(0));
        adapter.setNewData(list);


        recyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {


                String s = ((ThreeDataModel.UserListBean) adapter.getData().get(position)).getRealname();
                Intent intent = new Intent();
                intent.putExtra("name", s);
                intent.putExtra("id", ((ThreeDataModel.UserListBean) adapter.getData().get(position)).getId());
                setResult(300, intent);


                finish();
            }
        });


        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (getCurrentFocus() != null) {
                    ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(getCurrentFocus()
                                    .getWindowToken(),
                            InputMethodManager.HIDE_NOT_ALWAYS);
                }
            }
        });


        edSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() <= 0) {
                    recyclerView.setAdapter(adapter);
                    adapter.setNewData(list);
                } else {
                    data.clear();


                    for (ThreeDataModel.UserListBean userListBean : list) {

                        if (userListBean.getRealname().contains(s)) {
                            data.add(userListBean);
                        }

                    }
                    recyclerView.setAdapter(adapter);
                    adapter.setNewData(data);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }


    @OnClick(R.id.viewLine)
    public void onViewClicked() {
        finish();
    }
}
