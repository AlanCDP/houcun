package cn.com.exz.hc.adapter;

import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.com.exz.hc.R;
import cn.com.exz.hc.entity.AlreadyDistributingEntity;

/**
 * Created by weicao on 2018/8/15.
 */

public class AlreadyDistributingAdapter extends BaseQuickAdapter<AlreadyDistributingEntity, BaseViewHolder> {

    @BindView(R.id.time)
    TextView time;
    @BindView(R.id.content)
    TextView content;
    @BindView(R.id.unit)
    TextView unit;
    @BindView(R.id.people)
    TextView people;
    @BindView(R.id.status)
    TextView status;

    public AlreadyDistributingAdapter() {
        super(R.layout.item_already_task_accept, new ArrayList<AlreadyDistributingEntity>());
    }

    @Override
    protected void convert(BaseViewHolder helper, AlreadyDistributingEntity item) {

        View convertView = helper.itemView;
        ButterKnife.bind(this, convertView);

        time.setText(String.format("%s——%s", item.getPlan_start_date(), item.getPlan_end_date()));
        content.setText(item.getTask_desc());
        unit.setText(String.format("受理单位:%s", item.getAccept_depart().getDepartname()));
        people.setText(String.format("受理人:%s", item.getAccept_man().getRealname()));

        if (item.getTask_status().equals("0")){
            status.setText("待分派");
        }else if (item.getTask_status().equals("1")){
            status.setText("待受理");
        }else if (item.getTask_status().equals("2")){
            status.setText("待回复");
        }else if (item.getTask_status().equals("3")){
            status.setText("已回复");
        }


    }
}
