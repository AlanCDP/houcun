package cn.com.exz.hc.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.com.exz.hc.R;
import cn.com.exz.hc.activity.LoginActivity;
import cn.com.exz.hc.application.App;
import cn.com.exz.hc.entity.UserBean;
import cn.com.exz.hc.utils.FusionField;
import cn.com.szw.lib.myframework.base.MyBaseFragment;
import cn.com.szw.lib.myframework.config.Constants;
import cn.com.szw.lib.myframework.utils.Utils;
import cn.com.szw.lib.myframework.utils.net.NetEntity;
import cn.com.szw.lib.myframework.utils.net.callback.DialogCallback;

/**
 * Created by weicao on 2018/8/15.
 */

public class MineFragment extends MyBaseFragment {

    Unbinder unbinder;
    @BindView(R.id.btn_exit)
    TextView btnExit;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_mine, container, false);
        }
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void initView() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.btn_exit)
    public void onViewClicked() {

            initPort();
    }

    private void initPort() {
        HashMap<String, String> params = new HashMap<>();
        params.put("sessionId", App.getLoginUserId());


        OkGo.<NetEntity<UserBean>>post(FusionField.LOGOUT)
                .params(params)
                .tag(this)
                .execute(new DialogCallback<NetEntity<UserBean>>(getContext()) {

                    @Override
                    public void onSuccess(Response<NetEntity<UserBean>> response) {

                        if (response.body().getCode() == Constants.NetCode.SUCCESS){

                            getActivity().finish();
                            Utils.startActivity(getActivity(),LoginActivity.class);
                        }
                    }

                    @Override
                    public void onError(Response<NetEntity<UserBean>> response) {
                        super.onError(response);


                    }
                });
    }
}
