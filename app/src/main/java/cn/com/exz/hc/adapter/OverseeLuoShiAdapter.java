package cn.com.exz.hc.adapter;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.com.exz.hc.R;
import cn.com.exz.hc.entity.OverseeLuoShiEntity;

/**
 * Created by weicao on 2018/8/15.
 */

public class OverseeLuoShiAdapter extends BaseQuickAdapter<OverseeLuoShiEntity, BaseViewHolder> {

    @BindView(R.id.oversee_time)
    TextView overseeTime;
    @BindView(R.id.status)
    TextView status;
    @BindView(R.id.content)
    TextView content;
    @BindView(R.id.tv_remark)
    TextView tvRemark;
    @BindView(R.id.remark_time)
    TextView remarkTime;
    @BindView(R.id.layout)
    LinearLayout layout;

    public OverseeLuoShiAdapter() {
        super(R.layout.item_oversee_luo_shi, new ArrayList<OverseeLuoShiEntity>());
    }

    @Override
    protected void convert(BaseViewHolder helper, OverseeLuoShiEntity item) {
        View convertView = helper.itemView;
        ButterKnife.bind(this, convertView);

        overseeTime.setText(item.getCreate_date());
        content.setText(String.format("督办内容：%s", item.getSupervise_remark()));
        if (item.getStatus().equals("0")){
            status.setText("待回复");
        }else {
            status.setText("已回复");
            layout.setVisibility(View.VISIBLE);
            tvRemark.setText(item.getReply());
            remarkTime.setText("");
        }



    }
}
