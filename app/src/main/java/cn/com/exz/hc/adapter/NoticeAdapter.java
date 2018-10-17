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
import cn.com.exz.hc.entity.NoticeEntity;

/**
 * Created by weicao on 2018/8/15.
 */

public class NoticeAdapter extends BaseQuickAdapter<NoticeEntity, BaseViewHolder> {


    @BindView(R.id.look)
    LinearLayout look;
    @BindView(R.id.num)
    TextView num;
    @BindView(R.id.time)
    TextView time;
    @BindView(R.id.content)
    TextView content;

    public NoticeAdapter() {
        super(R.layout.item_notice, new ArrayList<NoticeEntity>());
    }

    @Override
    protected void convert(BaseViewHolder helper, NoticeEntity item) {
        View convertView = helper.itemView;
        ButterKnife.bind(this, convertView);

        time.setText(String.format("发布时间：%s", item.getRelease_time()));
        content.setText(String.format("公告名称：%s", item.getNotice_name()));

        num.setText(String.format("%s", helper.getAdapterPosition() + 1));


    }
}
