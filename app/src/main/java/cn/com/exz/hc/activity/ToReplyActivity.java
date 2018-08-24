package cn.com.exz.hc.activity;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.TimePickerView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import butterknife.BindView;
import butterknife.OnClick;
import cn.com.exz.hc.R;
import cn.com.exz.hc.application.App;
import cn.com.exz.hc.utils.FusionField;
import cn.com.szw.lib.myframework.base.BaseActivity;
import cn.com.szw.lib.myframework.utils.RxBus;
import cn.com.szw.lib.myframework.utils.net.NetEntity;
import cn.com.szw.lib.myframework.utils.net.callback.DialogCallback;
import cn.com.szw.lib.myframework.view.ClearWriteEditText;

/**
 * Created by weicao on 2018/8/21.
 * 任务回复
 */

public class ToReplyActivity extends BaseActivity {
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
    @BindView(R.id.plan_start_time)
    TextView planStartTime;
    @BindView(R.id.plan_end_time)
    TextView planEndTime;
    @BindView(R.id.complete_size)
    TextView completeSize;
    @BindView(R.id.remark)
    ClearWriteEditText remark;
    @BindView(R.id.commit)
    TextView commit;
    @BindView(R.id.start_time)
    TextView startTime;
    @BindView(R.id.end_time)
    TextView endTime;
    @BindView(R.id.unit)
    TextView unit;
    @BindView(R.id.people)
    TextView people;
    @BindView(R.id.miaoshu)
    TextView miaoshu;
    private String id;

    TimePickerView picker;
    TimePickerView picker1;

    OptionsPickerView<String> Options;//责任
    private ArrayList<String> completeList = new ArrayList<>();
    private String complete = "";


    @Override
    public boolean initToolbar() {

        toolbar.setContentInsetsAbsolute(0, 0);
        mTitle.setTextSize(18);
        mTitle.setTextColor(ContextCompat.getColor(this, R.color.white));
        mTitle.setText("任务回复");
        setSupportActionBar(toolbar);
        return false;
    }

    @Override
    public int setInflateId() {
        return R.layout.activity_to_reply;
    }

    @Override
    public void init() throws Exception {
        super.init();

        startTime.setText(getIntent().getStringExtra("startTime"));
        endTime.setText(getIntent().getStringExtra("endTime"));
        unit.setText(getIntent().getStringExtra("unit"));
        people.setText(getIntent().getStringExtra("people"));
        miaoshu.setText(getIntent().getStringExtra("content"));
        id = getIntent().getStringExtra("id");

        initPicker();
    }


    private void initPicker() {
        picker = new TimePickerView(mContext, TimePickerView.Type.YEAR_MONTH_DAY);
        picker.setCyclic(false);
        picker.setRange(2010, Calendar.getInstance().get(Calendar.YEAR));//开始范围
        picker.setTime(new Date());
        picker.setCyclic(false);
        picker.setCancelable(true);

        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, 0);
//        c.set(Calendar.DAY_OF_MONTH, 1);//设置为1号,当前日期既为本月第一天
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
//        startTime = df.format(c.getTime());
//
        picker.setOnTimeSelectListener(new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date) {

                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
                String format = df.format(date);
                planStartTime.setText(format);
                //                dateTime = radioButton2.getText().toString().trim();
                //                onRefresh();

//                examDate = df.format(date);
//                onRefresh();

            }
        });


        picker1 = new TimePickerView(mContext, TimePickerView.Type.YEAR_MONTH_DAY);
        picker1.setCyclic(false);
        picker1.setRange(2010, Calendar.getInstance().get(Calendar.YEAR));//开始范围
        picker1.setTime(new Date());
        picker1.setCyclic(false);
        picker1.setCancelable(true);

        Calendar c1 = Calendar.getInstance();
        c1.add(Calendar.MONTH, 0);
//        c.set(Calendar.DAY_OF_MONTH, 1);//设置为1号,当前日期既为本月第一天
//        SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
//        startTime = df.format(c.getTime());
//
        picker1.setOnTimeSelectListener(new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date) {

                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
                String format = df.format(date);
                planEndTime.setText(format);
                //                dateTime = radioButton2.getText().toString().trim();
                //                onRefresh();

//                examDate = df.format(date);
//                onRefresh();

            }
        });

        completeList.add("已完成");
        completeList.add("未完成");
        Options = new OptionsPickerView<>(mContext);

        Options.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3) {

                completeSize.setText(completeList.get(options1));
                if (options1 == 0) {
                    complete = "1";
                } else {
                    complete = "0";
                }

//
            }
        });


    }


    @OnClick({R.id.mLeftImg, R.id.plan_start_time, R.id.plan_end_time, R.id.complete_size, R.id.commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mLeftImg:
                finish();
                break;
            case R.id.plan_start_time:
                picker.show();
                break;
            case R.id.plan_end_time:
                picker1.show();
                break;
            case R.id.complete_size:
                Options.setPicker(completeList);
                Options.setCyclic(false);
                Options.show();
                break;
            case R.id.commit:
                if (planStartTime.getText().toString().isEmpty()) {
                    Toast.makeText(mContext, "请选择实际时间起", Toast.LENGTH_SHORT).show();
                } else if (planEndTime.getText().toString().isEmpty()) {
                    Toast.makeText(mContext, "请选择实际时间止", Toast.LENGTH_SHORT).show();
                } else if (complete.equals("")) {
                    Toast.makeText(mContext, "请选择完成情况", Toast.LENGTH_SHORT).show();
                } else if (remark.getText().toString().isEmpty()) {
                    remark.setShakeAnimation();
                } else {
                    initPort();
                }
                break;
        }
    }

    private void initPort() {
        HashMap<String, String> params = new HashMap<>();
        params.put("sessionId", App.getLoginUserId());
        params.put("id", id);
        params.put("actualStartDate", planStartTime.getText().toString());
        params.put("actualEndDate", planEndTime.getText().toString());
        params.put("completeStatus", complete);
        params.put("comments", remark.getText().toString());


        OkGo.<NetEntity<String>>post(FusionField.TEMPORARY_REPLY)
                .params(params)
                .tag(this)
                .execute(new DialogCallback<NetEntity<String>>(mContext) {

                    @Override
                    public void onSuccess(Response<NetEntity<String>> response) {

                        if (response.body().getCode() == 202) {
                            Toast.makeText(mContext, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                            RxBus.get().post("refresh", "refresh");
                            finish();

                        }
                    }

                    @Override
                    public void onError(Response<NetEntity<String>> response) {
                        super.onError(response);


                    }
                });
    }
}
