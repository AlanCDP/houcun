package cn.com.exz.hc.adapter;

import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.com.exz.hc.R;
import cn.com.exz.hc.entity.ResponsibilityAssessEntity1;

/**
 * Created by weicao on 2018/8/15.
 */

public class ResponsibilityAssessAdapter extends BaseQuickAdapter<ResponsibilityAssessEntity1, BaseViewHolder> {

    @BindView(R.id.people)
    TextView people;
    @BindView(R.id.content)
    TextView content;
    @BindView(R.id.point)
    TextView point;
    @BindView(R.id.classify)
    TextView classify;
    @BindView(R.id.data)
    TextView data;
    @BindView(R.id.status)
    TextView status;

    public ResponsibilityAssessAdapter() {
        super(R.layout.item_responsibility_assess, new ArrayList<ResponsibilityAssessEntity1>());
    }

    @Override
    protected void convert(BaseViewHolder helper, ResponsibilityAssessEntity1 item) {
        View convertView = helper.itemView;
        ButterKnife.bind(this, convertView);


        people.setText(String.format("审核人：%s", item.getReviewer().getRealname()));
        if (item.getStatus().equals("1")){
            status.setText("待考核");
        }else if (item.getStatus().equals("3")){
            status.setText("已考核");
        }
        content.setText(item.getExamContent());
        point.setText(String.format("标准的分：%s", item.getStandardScore()));
        if (item.getClassification().equals("1")){
            classify.setText("分类：任务");
        }else {
            classify.setText("分类：责任");
        }

        data.setText(String.format("限制日期：%s", item.getLimitDate()));
    }
}
