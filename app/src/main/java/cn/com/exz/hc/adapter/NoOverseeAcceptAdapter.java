package cn.com.exz.hc.adapter;

import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.com.exz.hc.R;
import cn.com.exz.hc.entity.NoOverseeAcceptEntity;

/**
 * Created by weicao on 2018/8/15.
 *
 */

public class NoOverseeAcceptAdapter extends BaseQuickAdapter<NoOverseeAcceptEntity, BaseViewHolder> {

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.status)
    TextView status;
    @BindView(R.id.content)
    TextView content;
    @BindView(R.id.people)
    TextView people;
    @BindView(R.id.post)
    TextView post;
    @BindView(R.id.limit_time)
    TextView limitTime;

    public NoOverseeAcceptAdapter() {
        super(R.layout.item_oversee_accept, new ArrayList<NoOverseeAcceptEntity>());
    }

    @Override
    protected void convert(BaseViewHolder helper, NoOverseeAcceptEntity item) {
        View convertView = helper.itemView;
        ButterKnife.bind(this, convertView);



        title.setText(item.getExam_content());
        if (item.getStatus().equals("1")){
            status.setText("已回复");
        }else {
            status.setText("待回复");
        }


        content.setText(String.format("督办内容：%s", item.getSupervise_remark()));
        people.setText(String.format("督办人：%s", item.getSupervise_man().getRealname()));
        content.setText(String.format("督办内容：%s", item.getSupervise_remark()));
        if (item.getSupervise_remark().equals("1")) {
            post.setText(String.format("督办等级：%s","部门领导" ));
        }else if (item.getSupervise_remark().equals("2")) {
            post.setText(String.format("督办等级：%s","矿分管领导" ));
        }else if (item.getSupervise_remark().equals("3")) {
            post.setText(String.format("督办等级：%s","矿长" ));
        }

        limitTime.setText(String.format("限制时间：%s", item.getLimit_date()));



    }
}
