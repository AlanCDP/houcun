package cn.com.exz.hc.activity;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.TimePickerView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.OnClick;
import cn.com.exz.hc.R;
import cn.com.exz.hc.application.App;
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
 */

public class LeaveCheckActivity extends BaseActivity {
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
    @BindView(R.id.date)
    TextView dateTime;
    @BindView(R.id.radio_date)
    RadioButton radioDate;
    @BindView(R.id.radio_current)
    RadioButton radioCurrent;
    @BindView(R.id.radio_group)
    RadioGroup radioGroup;
    @BindView(R.id.people)
    TextView people;
    @BindView(R.id.et_content)
    ClearWriteEditText etContent;
    @BindView(R.id.btn_add)
    TextView btnAdd;

    private String id;


    TimePickerView StartTime;//开始日期
    private List<ThreeDataModel.UserListBean> listPeople = new ArrayList<>();
    private String peopleId= "";
    private String operateDate;
    private String isAgree = "1";

    private String type;

    @Override
    public boolean initToolbar() {

        toolbar.setContentInsetsAbsolute(0, 0);
        mTitle.setTextSize(18);
        mTitle.setTextColor(ContextCompat.getColor(this, R.color.white));
        mTitle.setText("请假审批");
        setSupportActionBar(toolbar);
        return false;
    }


    @Override
    public int setInflateId() {
        return R.layout.activity_leave_check;
    }


    @Override
    public void init() throws Exception {
        super.init();

        initData();

        id = getIntent().getStringExtra("id");
        type = getIntent().getStringExtra("type");
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
        dateTime.setText(format.format(date));
        operateDate = format.format(date);

          /*
         * 开始日期
         */
        StartTime = new TimePickerView(mContext, TimePickerView.Type.ALL);
        StartTime.setRange(1950, Calendar.getInstance().get(Calendar.YEAR));
        StartTime.setTime(new Date());
        StartTime.setCyclic(false);
        StartTime.setCancelable(true);
        //时间选择后回调
        StartTime.setOnTimeSelectListener(new TimePickerView.OnTimeSelectListener() {

            @Override
            public void onTimeSelect(Date date) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
                dateTime.setText(format.format(date));
                operateDate = format.format(date);
            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.radio_date:
                        isAgree = "1";
                        break;
                    case R.id.radio_current:
                        isAgree = "0";

                        break;
                }


            }
        });

    }


    @OnClick({R.id.mLeftImg, R.id.date, R.id.people, R.id.btn_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mLeftImg:
                finish();
                break;
            case R.id.date:
                StartTime.show();
                break;
            case R.id.people:
                Intent intent = new Intent(mContext, PeopleActivity.class);
                intent.putExtra("list", (Serializable) listPeople);
                startActivityForResult(intent, 300);
                overridePendingTransition(R.anim.show, R.anim.hide);
                break;
            case R.id.btn_add:

                if (peopleId.equals("")) {
                    Toast.makeText(mContext, "请选择审批人", Toast.LENGTH_SHORT).show();
                }else {
                    initPort();
                }

                break;
        }
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
        HashMap<String, String> params = new HashMap<>();
        params.put("leaveId", id);
        params.put("sessionId", App.getLoginUserId());
        params.put("operateDate", operateDate);
        params.put("examMan", peopleId);
        params.put("remark", etContent.getText().toString());
        params.put("isAgree", isAgree);
        params.put("HandleType", type);



        OkGo.<NetEntity<String>>post(FusionField.LeaveCheck)
                .params(params)
                .tag(this)
                .execute(new DialogCallback<NetEntity<String>>(mContext) {

                    @Override
                    public void onSuccess(Response<NetEntity<String>> response) {

                        if (response.body().getCode() == Constants.NetCode.SUCCESS) {
                            finish();
                            RxBus.get().post("refresh", "refresh");

                        }
                    }

                    @Override
                    public void onError(Response<NetEntity<String>> response) {
                        super.onError(response);


                    }
                });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == 300) {
            people.setText(data.getStringExtra("name"));
            peopleId = data.getStringExtra("id");

        }

    }

}
