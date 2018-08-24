package cn.com.exz.hc.adapter;

import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.com.exz.hc.R;
import cn.com.exz.hc.entity.AssessCreateEntity;

/**
 * Created by weicao on 2018/8/15.
 */

public class AssessCreateAdapter extends BaseQuickAdapter<AssessCreateEntity, BaseViewHolder> {

    @BindView(R.id.time)
    TextView time;
    @BindView(R.id.status)
    TextView status;
    @BindView(R.id.unit)
    TextView unit;
    @BindView(R.id.post)
    TextView post;
    @BindView(R.id.num)
    TextView num;

    public AssessCreateAdapter() {
        super(R.layout.item_assess_create, new ArrayList<AssessCreateEntity>());
    }

    @Override
    protected void convert(BaseViewHolder helper, AssessCreateEntity item) {
        View convertView = helper.itemView;
        ButterKnife.bind(this, convertView);


        time.setText(item.getCreate_date());
        if (item.getIs_report().equals("0")){
            status.setText("未完成");
        }else {
            status.setText("已完成");
        }

        unit.setText(String.format("单位:%s", item.getUnit().getDepartname()));
        post.setText(String.format("职位:%s", item.getDuty().getPost_name()));
        num.setText(String.format("考核数:%s", item.getCountNum()));

    }
}
