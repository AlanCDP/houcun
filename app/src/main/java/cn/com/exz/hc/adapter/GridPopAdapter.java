package cn.com.exz.hc.adapter;

import android.util.DisplayMetrics;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.com.exz.hc.R;
import q.rorbin.badgeview.QBadgeView;

import static cn.com.exz.hc.activity.LoginActivity.list;


public class GridPopAdapter extends BaseQuickAdapter<String, BaseViewHolder> {


    @BindView(R.id.pic)
    TextView pic;
    @BindView(R.id.text)
    TextView text;
    @BindView(R.id.year_search)
    LinearLayout yearSearch;

    public GridPopAdapter() {
        super(R.layout.item_grid, new ArrayList<String>());
    }

    @Override
    protected void convert(BaseViewHolder helper, String entity) {
        View convertView = helper.itemView;
        ButterKnife.bind(this, convertView);

        DisplayMetrics dm = mContext.getResources().getDisplayMetrics();
        text.setWidth(dm.widthPixels / 5);



        if (entity.equals("AQ001")) {
            text.setText("清单录入");
            pic.setBackground(mContext.getResources().getDrawable(R.mipmap.main_qingdan_luru));

        } else if (entity.equals("AQ002")) {
            text.setText("清单审核");
            pic.setBackground(mContext.getResources().getDrawable(R.mipmap.main_qingdan_shenhe));
        } else if (entity.equals("AQ003")) {
            text.setText("考核创建");
            pic.setBackground(mContext.getResources().getDrawable(R.mipmap.main_kaohe_chuangjian));
        } else if (entity.equals("AQ004")) {
            text.setText("责任考核");
            pic.setBackground(mContext.getResources().getDrawable(R.mipmap.main_zeren_kaohe));
            new QBadgeView(mContext).bindTarget(yearSearch).setBadgeNumber(Integer.parseInt(list.get(0).getDaikaohe()));

        } else if (entity.equals("AQ005")) {
            text.setText("责任审核");
            pic.setBackground(mContext.getResources().getDrawable(R.mipmap.main_zeren_shenhe));
            new QBadgeView(mContext).bindTarget(yearSearch).setBadgeNumber(Integer.parseInt(list.get(0).getDaishenhe()));

        } else if (entity.equals("AQ006")) {
            text.setText("督办落实");
            pic.setBackground(mContext.getResources().getDrawable(R.mipmap.main_duban_luoshi));
        } else if (entity.equals("AQ007")) {
            text.setText("督办受理");
            pic.setBackground(mContext.getResources().getDrawable(R.mipmap.main_duban_shouli));
        } else if (entity.equals("AQ008")) {
            text.setText("年度查询");
            pic.setBackground(mContext.getResources().getDrawable(R.mipmap.main_niandu_chaxun));
        } else if (entity.equals("AQ009")) {
            text.setText("月度查询");
            pic.setBackground(mContext.getResources().getDrawable(R.mipmap.main_yuedu_chaxun));
        } else if (entity.equals("AQ")) {
            yearSearch.setVisibility(View.GONE);
        } else if (entity.equals("LS")) {
            yearSearch.setVisibility(View.GONE);
        } else if (entity.equals("LS001")) {
            yearSearch.setVisibility(View.GONE);
        } else if (entity.equals("LS002")) {
            yearSearch.setVisibility(View.GONE);
        } else if (entity.equals("ZD")) {
            yearSearch.setVisibility(View.GONE);
        } else if (entity.equals("ZD001")) {
            yearSearch.setVisibility(View.GONE);
        }


        helper.addOnClickListener(R.id.year_search);
//        date.setText(item.getValue());
//        convertView.setBackground(item.isCheck() ?ContextCompat.getDrawable(mContext,R.drawable.pop_grid_bg_black_yellow_line):ContextCompat.getDrawable(mContext,R.drawable.pop_grid_bg_black_gary_line));
//        date.setTextColor(item.isCheck() ? ContextCompat.getColor(mContext, R.color.yellow) : ContextCompat.getColor(mContext, R.color.white));
    }
}