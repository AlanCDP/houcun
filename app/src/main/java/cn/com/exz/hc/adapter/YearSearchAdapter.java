package cn.com.exz.hc.adapter;

import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.com.exz.hc.R;
import cn.com.exz.hc.entity.YearSearchEntity;

/**
 * Created by weicao on 2018/8/15.
 */

public class YearSearchAdapter extends BaseQuickAdapter<YearSearchEntity, BaseViewHolder> {


    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.content)
    TextView content;
    @BindView(R.id.people)
    TextView people;
    @BindView(R.id.post)
    TextView post;
    @BindView(R.id.point)
    TextView point;
    @BindView(R.id.date)
    TextView date;

    public YearSearchAdapter() {
        super(R.layout.item_search, new ArrayList<YearSearchEntity>());
    }

    @Override
    protected void convert(BaseViewHolder helper, YearSearchEntity item) {
        View convertView = helper.itemView;
        ButterKnife.bind(this, convertView);

        title.setText(item.getExamResult());
        content.setText(String.format("责任单位：%s", item.getDutyUnit().getDepartname()));
        people.setText(String.format("责任人：%s", item.getDutyMan().getRealname()));
        post.setText(String.format("职务：%s", item.getPost().getPost_name()));
        point.setText(String.format("汇总得分：%s", item.getSummary()));
        date.setText(String.format("计算日期：%s", item.getCreateDate()));





    }
}
