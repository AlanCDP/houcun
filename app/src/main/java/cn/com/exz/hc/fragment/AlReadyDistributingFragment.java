package cn.com.exz.hc.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.TimePickerView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import java.io.Serializable;
import java.text.SimpleDateFormat;
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
import cn.com.exz.hc.activity.UnitActivity;
import cn.com.exz.hc.application.App;
import cn.com.exz.hc.entity.ThreeDataModel;
import cn.com.exz.hc.utils.FusionField;
import cn.com.szw.lib.myframework.base.MyBaseFragment;
import cn.com.szw.lib.myframework.config.Constants;
import cn.com.szw.lib.myframework.utils.RxBus;
import cn.com.szw.lib.myframework.utils.net.NetEntity;
import cn.com.szw.lib.myframework.utils.net.callback.DialogCallback;
import cn.com.szw.lib.myframework.utils.net.callback.JsonCallback;
import cn.com.szw.lib.myframework.view.ClearWriteEditText;

/**
 * Created by weicao on 2018/8/18.
 * 临时任务任务派发
 */

public class AlReadyDistributingFragment extends MyBaseFragment {

    Unbinder unbinder;
    @BindView(R.id.start_time)
    TextView startTime;
    @BindView(R.id.end_time)
    TextView endTime;
    @BindView(R.id.unit)
    TextView unit;
    @BindView(R.id.people)
    TextView people;
    @BindView(R.id.et_content)
    ClearWriteEditText etContent;
    @BindView(R.id.commit)
    TextView commit;

    private String mTitle;
    TimePickerView picker;
    TimePickerView picker1;
    private List<ThreeDataModel.DepartListBean> listUnit;
    private List<ThreeDataModel.UserListBean> listPeople;
    private String acceptDepart = "";//受理单位id
    private String acceptMan = "";//受理人id


    public static AlReadyDistributingFragment getInstance(String title) {
        AlReadyDistributingFragment sf = new AlReadyDistributingFragment();
        sf.mTitle = title;
        return sf;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_task_accept, container, false);
        }
        unbinder = ButterKnife.bind(this, rootView);


        return rootView;
    }

    @Override
    public void initView() {


        initData();
        initPicker();
    }


    private void initPicker() {
        picker = new TimePickerView(getContext(), TimePickerView.Type.YEAR_MONTH_DAY);
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
                startTime.setText(format);
                //                dateTime = radioButton2.getText().toString().trim();
                //                onRefresh();

//                examDate = df.format(date);
//                onRefresh();

            }
        });


        picker1 = new TimePickerView(getContext(), TimePickerView.Type.YEAR_MONTH_DAY);
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
                endTime.setText(format);
                //                dateTime = radioButton2.getText().toString().trim();
                //                onRefresh();

//                examDate = df.format(date);
//                onRefresh();

            }
        });
    }


    @OnClick({R.id.start_time, R.id.end_time, R.id.unit, R.id.people, R.id.commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.start_time:
                picker.show();

                break;
            case R.id.end_time:
                picker1.show();

                break;
            case R.id.unit:
                Intent intent = new Intent(getContext(), UnitActivity.class);
                intent.putExtra("list", (Serializable) listUnit);
                startActivityForResult(intent, 100);
                getActivity().overridePendingTransition(R.anim.show, R.anim.hide);
                break;
            case R.id.people:
                Intent intent2 = new Intent(getContext(), PeopleActivity.class);
                intent2.putExtra("list", (Serializable) listPeople);
                startActivityForResult(intent2, 300);
                getActivity().overridePendingTransition(R.anim.show, R.anim.hide);
                break;
            case R.id.commit:
                if (startTime.getText().toString().isEmpty()) {
                    Toast.makeText(getContext(), "请选择计划时间起", Toast.LENGTH_SHORT).show();
                } else if (endTime.getText().toString().isEmpty()) {
                    Toast.makeText(getContext(), "请选择计划时间止", Toast.LENGTH_SHORT).show();
                } else if (acceptDepart.equals("")) {
                    Toast.makeText(getContext(), "请选择受理单位", Toast.LENGTH_SHORT).show();
                } else if (acceptMan.equals("")) {
                    Toast.makeText(getContext(), "请选择受理人", Toast.LENGTH_SHORT).show();
                } else if (etContent.getText().toString().equals("")) {
                    etContent.setShakeAnimation();
                } else {
                    initPort();
                }
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == 100) {
            unit.setText(data.getStringExtra("name"));
            acceptDepart = data.getStringExtra("id");
        } else if (resultCode == 300) {
            people.setText(data.getStringExtra("name"));
            acceptMan = data.getStringExtra("id");

        }
    }


    private void initPort() {
        HashMap<String, String> params = new HashMap<>();
        params.put("sessionId", App.getLoginUserId());
        params.put("taskDesc", etContent.getText().toString());
        params.put("planStartDate", startTime.getText().toString());
        params.put("planEndDate", endTime.getText().toString());
        params.put("acceptDepart", acceptDepart);
        params.put("acceptMan", acceptMan);


        OkGo.<NetEntity<String>>post(FusionField.TEMPORARY_SEND)
                .params(params)
                .tag(this)
                .execute(new DialogCallback<NetEntity<String>>(getContext()) {

                    @Override
                    public void onSuccess(Response<NetEntity<String>> response) {

                        if (response.body().getCode() == 202) {
                            Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            RxBus.get().post("refresh", "refresh");
                            etContent.setText("");
                            startTime.setText("");
                            endTime.setText("");
                            acceptDepart = "";
                            acceptMan = "";

                            unit.setText("");
                            people.setText("");


                        }
                    }

                    @Override
                    public void onError(Response<NetEntity<String>> response) {
                        super.onError(response);


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
                            listPeople = response.body().getData().getUserList();


                        }
                    }

                    @Override
                    public void onError(Response<NetEntity<ThreeDataModel>> response) {
                        super.onError(response);


                    }
                });
    }
}
