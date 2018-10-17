package cn.com.exz.hc.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.OptionsPickerView;
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
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.com.exz.hc.R;
import cn.com.exz.hc.activity.PeopleActivity;
import cn.com.exz.hc.activity.ShangjiPeopleActivity;
import cn.com.exz.hc.application.App;
import cn.com.exz.hc.entity.ThreeDataModel;
import cn.com.exz.hc.utils.FusionField;
import cn.com.szw.lib.myframework.base.MyBaseFragment;
import cn.com.szw.lib.myframework.config.Constants;
import cn.com.szw.lib.myframework.utils.RxBus;
import cn.com.szw.lib.myframework.utils.net.NetEntity;
import cn.com.szw.lib.myframework.utils.net.callback.JsonCallback;
import cn.com.szw.lib.myframework.view.ClearWriteEditText;

/**
 * Created by weicao on 2018/8/18.
 */

public class LeaveFragment extends MyBaseFragment {

    Unbinder unbinder;
    @BindView(R.id.leave_type)
    TextView leaveType;
    @BindView(R.id.start_time)
    TextView startTime;
    @BindView(R.id.end_time)
    TextView endTime;
    @BindView(R.id.reason)
    ClearWriteEditText reason;
    @BindView(R.id.zhuguan_people)
    TextView zhuguanPeople;
    @BindView(R.id.shangji_people)
    TextView shangjiPeople;
    @BindView(R.id.remark)
    ClearWriteEditText remark;
    @BindView(R.id.btn_add)
    TextView btnAdd;


    TimePickerView StartTime;//开始日期
    TimePickerView EndTime;//开始日期

    OptionsPickerView<String> Options;//请假类型
    ArrayList<String> option_list = new ArrayList<>();//请假类型list


    private List<ThreeDataModel.UserListBean> zhuGuanPeople = new ArrayList();
    private List<ThreeDataModel.UserListBean> ShangJiPeople = new ArrayList();

    private String ZhuGuanId = "";
    private String ShangJiId = "";


    private String mTitle;
    private String start_date = "";
    private String category = "";
    private String end_date = "";


    public static LeaveFragment getInstance(String title) {
        LeaveFragment sf = new LeaveFragment();
        sf.mTitle = title;
        return sf;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_leave, container, false);
        }
        unbinder = ButterKnife.bind(this, rootView);


        return rootView;
    }

    @Override
    public void initView() {

        initpicker();

    }

    private void initpicker() {

        initData();
        /*
         * 请假类型
         */

        option_list.add("事假");
        option_list.add("病假");
        option_list.add("婚假");
        option_list.add("丧假");
        option_list.add("产假");
        option_list.add("探亲假");
        Options = new OptionsPickerView<>(getActivity());

        Options.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3) {

                leaveType.setText(option_list.get(options1));

                if (options1 == 0) {
                    category = "1";
                } else if (options1 == 1) {
                    category = "2";
                } else if (options1 == 2) {
                    category = "3";
                } else if (options1 == 3) {
                    category = "4";
                } else if (options1 == 4) {
                    category = "5";
                } else if (options1 == 5) {
                    category = "6";
                }


            }
        });




        /*
         * 开始日期
         */
        StartTime = new TimePickerView(getContext(), TimePickerView.Type.ALL);
        StartTime.setRange(1950, Calendar.getInstance().get(Calendar.YEAR));
        StartTime.setTime(new Date());
        StartTime.setCyclic(false);
        StartTime.setCancelable(true);
        //时间选择后回调
        StartTime.setOnTimeSelectListener(new TimePickerView.OnTimeSelectListener() {

            @Override
            public void onTimeSelect(Date date) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
                startTime.setText(format.format(date));
                start_date = format.format(date);
            }
        });


         /*
         * 结束日期
         */
        EndTime = new TimePickerView(getContext(), TimePickerView.Type.ALL);
        EndTime.setRange(1950, Calendar.getInstance().get(Calendar.YEAR));
        EndTime.setTime(new Date());
        EndTime.setCyclic(false);
        EndTime.setCancelable(true);
        //时间选择后回调
        EndTime.setOnTimeSelectListener(new TimePickerView.OnTimeSelectListener() {

            @Override
            public void onTimeSelect(Date date) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
                endTime.setText(format.format(date));
                end_date = format.format(date);

            }
        });


    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @OnClick({R.id.leave_type, R.id.start_time, R.id.end_time, R.id.zhuguan_people, R.id.shangji_people, R.id.btn_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.leave_type:
                Options.setPicker(option_list);
                Options.setCyclic(false);
                Options.show();
                break;
            case R.id.start_time:
                StartTime.show();

                break;
            case R.id.end_time:
                EndTime.show();

                break;
            case R.id.zhuguan_people:
                Intent intent = new Intent(getContext(), PeopleActivity.class);
                intent.putExtra("list", (Serializable) zhuGuanPeople);
                startActivityForResult(intent, 300);
                getActivity().overridePendingTransition(R.anim.show, R.anim.hide);

                break;
            case R.id.shangji_people:
                Intent intent2 = new Intent(getContext(), ShangjiPeopleActivity.class);
                intent2.putExtra("list", (Serializable) ShangJiPeople);
                startActivityForResult(intent2, 200);
                getActivity().overridePendingTransition(R.anim.show, R.anim.hide);

                break;
            case R.id.btn_add:
                if (category.isEmpty()) {
                    Toast.makeText(getContext(), "请选择请假类型", Toast.LENGTH_SHORT).show();
                } else if (start_date.isEmpty()) {
                    Toast.makeText(getContext(), "请选择开始时间", Toast.LENGTH_SHORT).show();
                } else if (end_date.isEmpty()) {
                    Toast.makeText(getContext(), "请选择结束时间", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(reason.getText().toString())) {
                    reason.setShakeAnimation();
                    Toast.makeText(getContext(), "请填写事假理由", Toast.LENGTH_SHORT).show();

                } else if (ZhuGuanId.isEmpty()) {
                    Toast.makeText(getContext(), "请选择主管领导", Toast.LENGTH_SHORT).show();
                } else if (ShangJiId.isEmpty()) {
                    Toast.makeText(getContext(), "请选择上级领导", Toast.LENGTH_SHORT).show();
                } else {
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
                            zhuGuanPeople = response.body().getData().getUserList();
                            ShangJiPeople = response.body().getData().getUserList();


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

        params.put("category", category);
        params.put("start_date", start_date);
        params.put("end_date", end_date);
        params.put("leave_reason", reason.getText().toString());
        params.put("supervisor", ZhuGuanId);
        params.put("leader", ShangJiId);
        params.put("remark", remark.getText().toString());


        OkGo.<NetEntity<String>>post(FusionField.LeaveEnter)
                .params(params)
                .tag(this)
                .execute(new JsonCallback<NetEntity<String>>() {

                    @Override
                    public void onSuccess(Response<NetEntity<String>> response) {

                        if (response.body().getCode() == Constants.NetCode.SUCCESS) {

                            category = "";
                            leaveType.setText("");

                            start_date = "";
                            end_date = "";
                            startTime.setText("");
                            endTime.setText("");


                            reason.setText("");

                            ShangJiId = "";
                            ZhuGuanId = "";
                            shangjiPeople.setText("");
                            zhuguanPeople.setText("");

                            remark.setText("");


                            Toast.makeText(getContext(), "录入成功", Toast.LENGTH_SHORT).show();

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

        if (resultCode == 200) {
            shangjiPeople.setText(data.getStringExtra("name"));
            ShangJiId = data.getStringExtra("id");
        } else if (resultCode == 300) {
            zhuguanPeople.setText(data.getStringExtra("name"));
            ZhuGuanId = data.getStringExtra("id");

        }

    }
}
