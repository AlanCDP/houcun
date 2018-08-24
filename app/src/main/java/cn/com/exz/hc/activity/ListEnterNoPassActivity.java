package cn.com.exz.hc.activity;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.OptionsPickerView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.com.exz.hc.R;
import cn.com.exz.hc.application.App;
import cn.com.exz.hc.entity.ListEnterNoPassEntity;
import cn.com.exz.hc.entity.ThreeDataModel;
import cn.com.exz.hc.utils.FusionField;
import cn.com.szw.lib.myframework.base.BaseActivity;
import cn.com.szw.lib.myframework.config.Constants;
import cn.com.szw.lib.myframework.utils.RxBus;
import cn.com.szw.lib.myframework.utils.net.NetEntity;
import cn.com.szw.lib.myframework.utils.net.callback.DialogCallback;
import cn.com.szw.lib.myframework.utils.net.callback.JsonCallback;
import cn.com.szw.lib.myframework.view.ClearWriteEditText;

/**
 * Created by weicao on 2018/8/18.
 *
 */

public class ListEnterNoPassActivity extends BaseActivity {
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
    @BindView(R.id.tv_unit)
    TextView tvUnit;
    @BindView(R.id.tv_post)
    TextView tvPost;
    @BindView(R.id.tv_inspection_period)
    TextView tvInspectionPeriod;
    @BindView(R.id.tv_classification)
    TextView tvClassification;
    @BindView(R.id.et_content)
    ClearWriteEditText etContent;
    @BindView(R.id.radio_date)
    RadioButton radioDate;
    @BindView(R.id.radio_current)
    RadioButton radioCurrent;
    @BindView(R.id.radio_group)
    RadioGroup radioGroup;
    @BindView(R.id.radio_date1)
    RadioButton radioDate1;
    @BindView(R.id.radio_current1)
    RadioButton radioCurrent1;
    @BindView(R.id.radio_group1)
    RadioGroup radioGroup1;
    @BindView(R.id.et_point)
    ClearWriteEditText etPoint;
    @BindView(R.id.tv_people)
    TextView tvPeople;
    @BindView(R.id.btn_add)
    TextView btnAdd;


    OptionsPickerView<String> TimeOptions;//考核周期
    ArrayList<String> time_list = new ArrayList<>();//周期list

    OptionsPickerView<String> ClassifyOptions;//分类
    ArrayList<String> classify_list = new ArrayList<>();//分类list
    @BindView(R.id.reason)
    TextView reason;
    @BindView(R.id.layout_reason)
    LinearLayout layoutReason;


    private List<ThreeDataModel.DepartListBean> listUnit = new ArrayList();
    private List<ThreeDataModel.PostListBean> listPost = new ArrayList();
    private List<ThreeDataModel.UserListBean> listPeople = new ArrayList();
    private String dutyUnit = "";//单位
    private String post = "";//职务
    private String examCycle = "";//考核周期
    private String classification = "";//分类
    private String examContent = "";//考核内容
    private String isExtraScore = "";//是否为加分项
    private String isVetoScore = "";//是否为否决项
    private String standardScore = "";//标准分值
    private String reviewer = "";//审核人


    private List<ListEnterNoPassEntity> list;


    @Override
    public boolean initToolbar() {
        toolbar.setContentInsetsAbsolute(0, 0);
        mTitle.setTextSize(18);
        mTitle.setTextColor(ContextCompat.getColor(this, R.color.white));
        mTitle.setText("驳回待上报");
        setSupportActionBar(toolbar);
        return false;
    }

    @Override
    public int setInflateId() {
        return R.layout.activity_list_enter_no_pass;
    }

    @Override
    public void init() throws Exception {
        super.init();

        list = new ArrayList<>();

        list = (List<ListEnterNoPassEntity>) getIntent().getSerializableExtra("list");

        tvUnit.setText(list.get(0).getDuty_unit().getDepartname());//单位
        dutyUnit = list.get(0).getDuty_unit().getId();//单位id

        tvPost.setText(list.get(0).getPost().getPost_name());//职位
        post = list.get(0).getPost().getId();//职位Id

        tvInspectionPeriod.setText(list.get(0).getExam_cycle());//考核周期
        if (list.get(0).getExam_cycle().equals("年")) {
            examCycle = "year";
        } else if (list.get(0).getExam_cycle().equals("季")) {
            examCycle = "quarter";
        } else {
            examCycle = "month";
        }


        tvClassification.setText(list.get(0).getClassification());//分类

        if (list.get(0).getClassification().equals("责任")) {
            classification = "2";
        } else {
            classification = "1";
        }


        etContent.setText(list.get(0).getExam_content());//考核内容

        if (list.get(0).getIs_extra_score().equals("Y")) {
            radioDate.setChecked(true);
        } else {
            radioCurrent.setChecked(true);
        }

        if (list.get(0).getIs_veto_score().equals("Y")) {
            radioDate1.setChecked(true);
        } else {
            radioCurrent1.setChecked(true);
        }


        etPoint.setText(list.get(0).getStandard_score());//标准分值

        tvPeople.setText(list.get(0).getReviewer().getRealname());//审核人
        reviewer = list.get(0).getReviewer().getId();//审核人Id


        reason.setText(list.get(0).getReview_remark());


        initData();

        time_list.add("年");
        time_list.add("季");
        time_list.add("月");


        classify_list.add("责任");
        classify_list.add("任务");


        initPicker();
        initRadio();

    }


    private void initRadio() {
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.radio_date:
                        isExtraScore = "Y";
                        break;
                    case R.id.radio_current:
                        isExtraScore = "N";

                        break;
                }


            }
        });

        radioGroup1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.radio_date1:
                        isVetoScore = "Y";
                        break;
                    case R.id.radio_current1:
                        isVetoScore = "N";

                        break;
                }


            }
        });
    }


    private void initPicker() {

        TimeOptions = new OptionsPickerView<>(mContext);

        TimeOptions.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3) {

                tvInspectionPeriod.setText(time_list.get(options1));
                if (options1 == 0) {
                    examCycle = "year";
                } else if (options1 == 1) {
                    examCycle = "quarter";
                } else if (options1 == 2) {
                    examCycle = "month";
                }
            }
        });


        ClassifyOptions = new OptionsPickerView<>(mContext);

        ClassifyOptions.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3) {

                tvClassification.setText(classify_list.get(options1));

                if (options1 == 0) {
                    classification = "2";
                } else if (options1 == 1) {
                    classification = "1";
                }
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
                            listPeople = response.body().getData().getUserList();


                        }
                    }

                    @Override
                    public void onError(Response<NetEntity<ThreeDataModel>> response) {
                        super.onError(response);


                    }
                });
    }


    private void initPort() {
        standardScore = etPoint.getText().toString();
        examContent = etContent.getText().toString();
        HashMap<String, String> params = new HashMap<>();
        params.put("sessionId", App.getLoginUserId());
        params.put("id", list.get(0).getId());
        params.put("dutyUnit", dutyUnit);
        params.put("post", post);
        params.put("examCycle", examCycle);
        params.put("classification", classification);
        params.put("examContent", examContent);
        params.put("isExtraScore", isExtraScore);
        params.put("isVetoScore", isVetoScore);
        params.put("standardScore", standardScore);
        params.put("reviewer", reviewer);


        OkGo.<NetEntity<String>>post(FusionField.LIST_ENTER)
                .params(params)
                .tag(this)
                .execute(new DialogCallback<NetEntity<String>>(mContext) {

                    @Override
                    public void onSuccess(Response<NetEntity<String>> response) {

                        if (response.body().getCode() == Constants.NetCode.SUCCESS) {
                            Toast.makeText(mContext, "上传成功", Toast.LENGTH_SHORT).show();
//                            dutyUnit = "";
//                            post = "";
//                            examCycle = "";
//                            classification = "";
//                            etContent.setText("");
//                            isExtraScore = "Y";
//                            isVetoScore = "Y";
//                            radioDate.setChecked(true);
//                            radioCurrent.setChecked(false);
//                            radioDate1.setChecked(true);
//                            radioCurrent1.setChecked(false);
//                            etPoint.setText("");
//                            reviewer = "";
//
//                            tvUnit.setText("");
//                            tvPost.setText("");
//                            tvPeople.setText("");
//
//
//                            tvInspectionPeriod.setText("");
//                            tvClassification.setText("");
                            RxBus.get().post("refresh","refresh");
                            finish();


                        }
                    }

                    @Override
                    public void onError(Response<NetEntity<String>> response) {
                        super.onError(response);


                    }
                });
    }


    @OnClick({R.id.tv_unit, R.id.tv_post, R.id.tv_people, R.id.btn_add, R.id.tv_inspection_period, R.id.tv_classification,R.id.mLeftImg})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_unit:

                Intent intent = new Intent(mContext, UnitActivity.class);
                intent.putExtra("list", (Serializable) listUnit);
                startActivityForResult(intent, 100);
                overridePendingTransition(R.anim.show, R.anim.hide);
                break;
            case R.id.tv_post:

                Intent intent1 = new Intent(mContext, PostActivity.class);
                intent1.putExtra("list", (Serializable) listPost);
                startActivityForResult(intent1, 200);
                overridePendingTransition(R.anim.show, R.anim.hide);

                break;
            case R.id.tv_people:

                Intent intent2 = new Intent(mContext, PeopleActivity.class);
                intent2.putExtra("list", (Serializable) listPeople);
                startActivityForResult(intent2, 300);
                overridePendingTransition(R.anim.show, R.anim.hide);

                break;
            case R.id.tv_inspection_period:

                TimeOptions.setPicker(time_list);
                TimeOptions.setCyclic(false);
                TimeOptions.show();
                break;
            case R.id.tv_classification:

                ClassifyOptions.setPicker(classify_list);
                ClassifyOptions.setCyclic(false);
                ClassifyOptions.show();

                break;
            case R.id.btn_add:
                if (dutyUnit.isEmpty() && dutyUnit.equals("")) {
                    Toast.makeText(mContext, "请选择单位", Toast.LENGTH_SHORT).show();
                } else if (post.isEmpty() && post.equals("")) {
                    Toast.makeText(mContext, "请选择职位", Toast.LENGTH_SHORT).show();
                } else if (tvInspectionPeriod.getText().toString().equals("")) {
                    Toast.makeText(mContext, "请选择考核周期", Toast.LENGTH_SHORT).show();
                } else if (tvClassification.getText().toString().equals("")) {
                    Toast.makeText(mContext, "请选择分类", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(etContent.getText().toString())) {
                    etContent.setShakeAnimation();
                } else if (TextUtils.isEmpty(etPoint.getText().toString())) {
                    etPoint.setShakeAnimation();
                } else if (reviewer.isEmpty() && post.equals("")) {
                    Toast.makeText(mContext, "请选择审核人", Toast.LENGTH_SHORT).show();
                } else {
                    initPort();
                }
                break;

            case R.id.mLeftImg:
                finish();
                break;


        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == 100) {
            tvUnit.setText(data.getStringExtra("name"));
            dutyUnit = data.getStringExtra("id");
        } else if (resultCode == 200) {
            tvPost.setText(data.getStringExtra("name"));
            post = data.getStringExtra("id");

        } else if (resultCode == 300) {
            tvPeople.setText(data.getStringExtra("name"));
            reviewer = data.getStringExtra("id");

        }
    }

}
