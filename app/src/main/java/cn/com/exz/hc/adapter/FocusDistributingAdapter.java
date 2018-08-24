package cn.com.exz.hc.adapter;

import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.com.exz.hc.R;
import cn.com.exz.hc.entity.FocusDistributingEntity;

/**
 * Created by weicao on 2018/8/15.
 */

public class FocusDistributingAdapter extends BaseQuickAdapter<FocusDistributingEntity, BaseViewHolder> {

    @BindView(R.id.time)
    TextView time;
    @BindView(R.id.content)
    TextView content;
    @BindView(R.id.unit)
    TextView unit;
    @BindView(R.id.people)
    TextView people;
    @BindView(R.id.project_name)
    TextView projectName;
    @BindView(R.id.status)
    TextView status;

    public FocusDistributingAdapter() {
        super(R.layout.item_focus_distributing, new ArrayList<FocusDistributingEntity>());
    }

    @Override
    protected void convert(BaseViewHolder helper, FocusDistributingEntity item) {
        View convertView = helper.itemView;
        ButterKnife.bind(this, convertView);

        time.setText(String.format("计划时间：%s——%s", item.getPlanStartDate(), item.getPlanEndDate()));
        content.setText(item.getStep());
        unit.setText(String.format("受理单位：%s", item.getAcceptDepart().getDepartname()));
        people.setText(String.format("受理人：%s", item.getAcceptMan().getRealname()));
        projectName.setText(String.format("项目名称：%s", item.getProjectName()));


        if (item.getTaskStatus().equals("1")){
            status.setText("待受理");
        }else  if (item.getTaskStatus().equals("2")){
            status.setText("待回复");
        }else  if (item.getTaskStatus().equals("3")){
            status.setText("已回复");
        }



    }
}
