package cn.com.exz.hc.adapter;

import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.com.exz.hc.R;
import cn.com.exz.hc.entity.LeaveListBean;

/**
 * Created by weicao on 2018/8/15.
 */

public class LeaveAdapter extends BaseQuickAdapter<LeaveListBean, BaseViewHolder> {


    @BindView(R.id.time)
    TextView time;
    @BindView(R.id.reason)
    TextView reason;
    @BindView(R.id.type)
    TextView type;
    @BindView(R.id.post)
    TextView post;
    @BindView(R.id.status)
    TextView status;

    public LeaveAdapter() {
        super(R.layout.item_leave, new ArrayList<LeaveListBean>());
    }

    @Override
    protected void convert(BaseViewHolder helper, LeaveListBean item) {

        View convertView = helper.itemView;
        ButterKnife.bind(this, convertView);


        time.setText(String.format("%s-%s", item.getStart_date(), item.getEnd_date()));
        reason.setText(item.getLeave_reason());
        type.setText(String.format("请假类型：%s", item.getCategory()));
        post.setText(String.format("请假人：%s", item.getLeave_man()));


        if (item.getStatus().equals("1")){
            status.setText("状态：提交");
        } else if (item.getStatus().equals("2")) {
            status.setText("状态：驳回");
        }else if (item.getStatus().equals("3")) {
            status.setText("状态：主管已批准");
        }else if (item.getStatus().equals("4")) {
            status.setText("状态：销假");
        }else if (item.getStatus().equals("5")) {
            status.setText("状态：上级领导批准");
        }else if (item.getStatus().equals("6")) {
            status.setText("状态：上级领导审批不通过");
        }

    }
}
