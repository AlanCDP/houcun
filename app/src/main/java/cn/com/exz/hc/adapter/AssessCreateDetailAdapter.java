package cn.com.exz.hc.adapter;

import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.com.exz.hc.R;
import cn.com.exz.hc.entity.AssessCreateDetailEntity;

/**
 * Created by weicao on 2018/8/15.
 */

public class AssessCreateDetailAdapter extends BaseQuickAdapter<AssessCreateDetailEntity, BaseViewHolder> {

    @BindView(R.id.limitDate)
    TextView limitDate;
    @BindView(R.id.content)
    TextView content;
    @BindView(R.id.unit)
    TextView unit;
    @BindView(R.id.post)
    TextView post;
    @BindView(R.id.time)
    TextView time;

    public AssessCreateDetailAdapter() {
        super(R.layout.item_assess_create_detail, new ArrayList<AssessCreateDetailEntity>());
    }

    @Override
    protected void convert(BaseViewHolder helper, AssessCreateDetailEntity item) {
        View convertView = helper.itemView;
        ButterKnife.bind(this, convertView);


        limitDate.setText(String.format("限制日期：%s", item.getLimit_date()));
        content.setText(item.getExam_content());
        unit.setText(String.format("单位：%s", item.getDuty_unit().getDepartname()));
        post.setText(String.format("职位：%s", item.getPost().getPost_name()));
        time.setText(String.format("周期%s年", item.getExam_cycle()));


    }
}
