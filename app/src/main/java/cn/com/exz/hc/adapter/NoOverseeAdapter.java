package cn.com.exz.hc.adapter;

import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.com.exz.hc.R;
import cn.com.exz.hc.entity.NoOverseeEntity;

/**
 * Created by weicao on 2018/8/15.
 */

public class NoOverseeAdapter extends BaseQuickAdapter<NoOverseeEntity, BaseViewHolder> {


    @BindView(R.id.content)
    TextView content;
    @BindView(R.id.status)
    TextView status;
    @BindView(R.id.unit)
    TextView unit;
    @BindView(R.id.people)
    TextView people;
    @BindView(R.id.post)
    TextView post;

    public NoOverseeAdapter() {
        super(R.layout.item_no_oversee, new ArrayList<NoOverseeEntity>());
    }

    @Override
    protected void convert(BaseViewHolder helper, NoOverseeEntity item) {
        View convertView = helper.itemView;
        ButterKnife.bind(this, convertView);


        content.setText(item.getExam_content());

        unit.setText(String.format("被考核单位：%s", item.getDuty_unit().getDepartname()));
        people.setText(String.format("被考核人：%s", item.getInspected_person().getRealname()));
        post.setText(String.format("职务：%s", item.getPost().getPost_name()));
        if (item.getSuStatus()==0){
            status.setText("为督办");
        }else {
            status.setText("已督办");
        }


    }
}
