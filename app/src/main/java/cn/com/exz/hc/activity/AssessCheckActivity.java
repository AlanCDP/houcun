package cn.com.exz.hc.activity;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.OptionsPickerView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;
import cn.com.exz.hc.R;
import cn.com.exz.hc.application.App;
import cn.com.exz.hc.utils.FusionField;
import cn.com.szw.lib.myframework.base.BaseActivity;
import cn.com.szw.lib.myframework.config.Constants;
import cn.com.szw.lib.myframework.utils.RxBus;
import cn.com.szw.lib.myframework.utils.net.NetEntity;
import cn.com.szw.lib.myframework.utils.net.callback.DialogCallback;

/**
 * Created by weicao on 2018/8/18.
 * 责任考核考核界面
 */

public class AssessCheckActivity extends BaseActivity {

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
    @BindView(R.id.complete_size)
    TextView completeSize;
    @BindView(R.id.et_point)
    TextView etPoint;
    @BindView(R.id.btn_add)
    TextView btnAdd;
    @BindView(R.id.check_content)
    TextView checkContent;
    @BindView(R.id.time)
    TextView time;
    @BindView(R.id.classfy)
    TextView classfy;
    @BindView(R.id.isExtraScore)
    TextView isExtraScore;
    @BindView(R.id.isVetoScore)
    TextView isVetoScore;
    @BindView(R.id.point)
    TextView point;


    OptionsPickerView<String> zeren_Options;//责任
    ArrayList<String> zeren_list = new ArrayList<>();//责任list

    OptionsPickerView<String> renwu_Options;//任务
    ArrayList<String> renwu_list = new ArrayList<>();//任务list


    private String StrScore;

    private double score;


    private String detailId;
    private String classify;


    private String completion11;//完成情况
    private String scoreId;


    @Override
    public boolean initToolbar() {

        toolbar.setContentInsetsAbsolute(0, 0);
        mTitle.setTextSize(18);
        mTitle.setTextColor(ContextCompat.getColor(this, R.color.white));
        mTitle.setText("责任考核");
        setSupportActionBar(toolbar);
        return false;
    }

    @Override
    public int setInflateId() {
        return R.layout.activity_assess_check;
    }


    @Override
    public void init() throws Exception {
        super.init();


        zeren_list.add("A");
        zeren_list.add("B");
        zeren_list.add("C");
        zeren_list.add("D");

        renwu_list.add("是");
        renwu_list.add("否");


        initPicker();

        scoreId = getIntent().getStringExtra("scoreId");
        checkContent.setText(getIntent().getStringExtra("content"));
        time.setText(getIntent().getStringExtra("limitTime"));

        if (getIntent().getStringExtra("classify").equals("1")) {
            classfy.setText("任务");
            classify = "0";
        } else {
            classfy.setText("责任");
            classify = "1";

        }

        if (getIntent().getStringExtra("isExtraScore").equals("Y")) {
            isExtraScore.setText("是");
        } else {
            isExtraScore.setText("否");
        }


        if (getIntent().getStringExtra("isVetoScore").equals("Y")) {
            isVetoScore.setText("是");
        } else {
            isVetoScore.setText("否");
        }

        point.setText(getIntent().getStringExtra("point"));

        score = Double.parseDouble(getIntent().getStringExtra("point"));

        detailId = getIntent().getStringExtra("detailId");

    }


    private void initPicker() {

        zeren_Options = new OptionsPickerView<>(mContext);

        zeren_Options.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3) {

                completeSize.setText(zeren_list.get(options1));
                if (options1 == 0) {// 1   0.8  0.5  0
                    completion11 ="A";
                    etPoint.setText(String.format("%s", score * 1));
                    StrScore = String.format("%s", score * 1) + "";

                } else if (options1 == 1) {
                    completion11 ="B";

                    etPoint.setText(String.format("%s", score * 0.8));
                    StrScore = String.format("%s", score * 0.8) + "";


                } else if (options1 == 2) {
                    completion11 ="C";

                    etPoint.setText(String.format("%s", score * 0.5));
                    StrScore = String.format("%s", score * 0.5) + "";


                } else {
                    completion11 ="D";

                    etPoint.setText(String.format("%s", score * 0));
                    StrScore = String.format("%s", score * 0) + "";


                }
            }
        });


        renwu_Options = new OptionsPickerView<>(mContext);

        renwu_Options.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3) {

                completeSize.setText(renwu_list.get(options1));
//
                if (options1 == 0) {
                    completion11 ="1";

                    etPoint.setText(String.format("%s", score * 1));
                    StrScore = String.format("%s", score * 1) + "";


                } else if (options1 == 1) {
                    completion11 ="0";

                    etPoint.setText(String.format("%s", score * 0));
                    StrScore = String.format("%s", score * 0) + "";

                }
            }
        });

    }


    private void initPort() {
        HashMap<String, String> params = new HashMap<>();
        params.put("sessionId", App.getLoginUserId());
        params.put("detailId", detailId);
        params.put("score", StrScore);
        params.put("completion", completion11);
        params.put("scoreId", scoreId);



        OkGo.<NetEntity<String>>post(FusionField.ResponsibilityAssess_Commit)
                .params(params)
                .tag(this)
                .execute(new DialogCallback<NetEntity<String >>(mContext) {

                    @Override
                    public void onSuccess(Response<NetEntity<String>> response) {

                        if (response.body().getCode() == Constants.NetCode.SUCCESS){
                            finish();
                            RxBus.get().post("refresh","refresh");

                        }
                    }

                    @Override
                    public void onError(Response<NetEntity<String>> response) {
                        super.onError(response);


                    }
                });
    }


    @OnClick({R.id.mLeftImg, R.id.complete_size, R.id.btn_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mLeftImg:
                finish();
                break;
            case R.id.complete_size:

                if (classify.equals("1")) {
                    zeren_Options.setPicker(zeren_list);
                    zeren_Options.setCyclic(false);
                    zeren_Options.show();
                } else {
                    renwu_Options.setPicker(renwu_list);
                    renwu_Options.setCyclic(false);
                    renwu_Options.show();
                }

                break;
            case R.id.btn_add:
                if (TextUtils.isEmpty(completeSize.getText())) {
                    Toast.makeText(mContext, "请选择完成情况", Toast.LENGTH_SHORT).show();
                } else {
                    initPort();
                }
                break;
        }
    }
}
