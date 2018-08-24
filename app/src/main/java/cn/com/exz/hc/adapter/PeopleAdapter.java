package cn.com.exz.hc.adapter;

import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.com.exz.hc.R;
import cn.com.exz.hc.entity.ThreeDataModel;

/**
 * Created by weicao on 2018/8/22.
 */

public class PeopleAdapter extends BaseQuickAdapter<ThreeDataModel.UserListBean, BaseViewHolder> {

    @BindView(R.id.name)
    TextView name;

    public PeopleAdapter() {
        super(R.layout.item_unit, new ArrayList<ThreeDataModel.UserListBean>());
    }

    @Override
    protected void convert(BaseViewHolder helper, ThreeDataModel.UserListBean item) {
        View convertView = helper.itemView;
        ButterKnife.bind(this, convertView);

        name.setText(item.getRealname());

    }
}
