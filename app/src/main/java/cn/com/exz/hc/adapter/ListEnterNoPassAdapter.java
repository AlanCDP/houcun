package cn.com.exz.hc.adapter;

import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.com.exz.hc.R;
import cn.com.exz.hc.entity.ListEnterNoPassEntity;

/**
 * Created by weicao on 2018/8/15.
 */

public class ListEnterNoPassAdapter extends BaseQuickAdapter<ListEnterNoPassEntity, BaseViewHolder> {

    @BindView(R.id.time)
    TextView time;
    @BindView(R.id.content)
    TextView content;
    @BindView(R.id.unit)
    TextView unit;
    @BindView(R.id.post)
    TextView post;
    @BindView(R.id.zhouqi)
    TextView zhouqi;

    public ListEnterNoPassAdapter() {
        super(R.layout.item_list_enter_no_pass, new ArrayList<ListEnterNoPassEntity>());
    }

    @Override
    protected void convert(BaseViewHolder helper, ListEnterNoPassEntity item) {

        View convertView = helper.itemView;
        ButterKnife.bind(this, convertView);

        time.setText(item.getCreate_date());
        content.setText(item.getExam_content());
        unit.setText(String.format("单位：%s", item.getDuty_unit().getDepartname()));
        post.setText(String.format("职务：%s", item.getPost().getPost_name()));
        zhouqi.setText(String.format("周期：%s", item.getExam_cycle()));


    }
}
