package cn.com.exz.hc.activity;

import android.content.Intent;
import android.os.Environment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.FileCallback;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.model.Response;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.com.exz.hc.R;
import cn.com.exz.hc.entity.NoticeEntity;
import cn.com.exz.hc.utils.OpenFileUtil;
import cn.com.szw.lib.myframework.base.BaseActivity;

/**
 * Created by weicao on 2018/9/29.
 */

public class NoticeDetailActivity extends BaseActivity {
    @BindView(R.id.mLeft)
    TextView mLeft;
    @BindView(R.id.mTitle)
    TextView mTitle;
    @BindView(R.id.mRight)
    TextView mRight;
    @BindView(R.id.mRightImg)
    ImageView mRightImg;
    @BindView(R.id.mLeftImg)
    ImageView mLeftImg;
    @BindView(R.id.parent_lay)
    RelativeLayout parentLay;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.downLoad)
    TextView downLoad;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.type)
    TextView type;
    @BindView(R.id.content)
    TextView content;
    @BindView(R.id.time)
    TextView time;
    @BindView(R.id.status)
    TextView status;
    private List<NoticeEntity> list;

    @Override
    public boolean initToolbar() {

        toolbar.setContentInsetsAbsolute(0, 0);
        mTitle.setTextSize(18);
        mTitle.setTextColor(ContextCompat.getColor(this, R.color.white));
        mTitle.setText("公告");
        return false;
    }

    @Override
    public void init() throws Exception {
        super.init();

        list = (List<NoticeEntity>) getIntent().getSerializableExtra("list");


        name.setText(list.get(0).getNotice_name());
        type.setText(list.get(0).getNotice_type());
        time.setText(list.get(0).getRelease_time());
        status.setText(list.get(0).getNotice_status());
        downLoad.setText(list.get(0).getNotice_path());



    }

    @Override
    public int setInflateId() {
        return R.layout.activity_notice_detail;
    }


    @OnClick({R.id.mLeftImg, R.id.downLoad})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mLeftImg:
                finish();
                break;
            case R.id.downLoad:

//
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        downloadFromStream(list.get(0).getNotice_path());
//                    }
//                }).start();
                initPort();
                break;
        }
    }


    private void downloadFromStream(String path){
        try {

            URL url = new URL(path);

            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            //urlConnection.setRequestProperty("User-Agent", " Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/37.0.2062.120 Safari/537.36");
            urlConnection.setRequestProperty("User-Agent", " Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/37.0.2062.120 Safari/537.36");
            InputStream inputStream = urlConnection.getInputStream();

            File file = new File(Environment.getExternalStorageDirectory(),path.substring(path.lastIndexOf("/",path.length())));
            System.out.println(file.getAbsolutePath());
            int length = urlConnection.getContentLength();
            System.out.println(length);
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            byte[] bytes = new byte[1024];
            int len;
            long downloadLength = 0;
            while ((len = inputStream.read(bytes))!=-1){
                fileOutputStream.write(bytes,0,len);
                downloadLength += len;
                final int progress = (int) (downloadLength*1.0f/length*100);
                System.out.println(progress);
            }
            inputStream.close();
            fileOutputStream.flush();
            fileOutputStream.close();

            Intent intent =  OpenFileUtil.openFile(file.getAbsolutePath(),mContext);
            startActivity(intent);

        } catch (IOException e) {
            e.printStackTrace();
        }finally {

        }


    }

    private void initPort() {

        OkGo.<File>get(list.get(0).getNotice_path())//"http://119.37.192.234:9079/JDZJ/Resource/988202f2-5161-4bb5-a055-b89b328d620b.doc"
                .tag(this)//
                .execute(new FileCallback() {
                    @Override
                        public void onSuccess(Response<File> response) {


//                        File docFile = new File(response.body().getPath());
//                        Intent in = new Intent("android.intent.action.VIEW");
//                        in.addCategory("android.intent.category.DEFAULT");
//                        Uri data;
//                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//                            // "net.csdn.blog.ruancoder.fileprovider"即是在清单文件中配置的authorities
//                            data = FileProvider.getUriForFile(mContext, "cn.com.exz.hc.fileProvider", docFile);
//                            // 给目标应用一个临时授权
//                            in.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//                        } else {
//                            data = Uri.fromFile(docFile);
//                        }
//                        in.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                        in.setDataAndType(data, "application/msword");
//                        startActivity(in);


                       Intent intent =  OpenFileUtil.openFile(response.body().getPath(),mContext);
                       startActivity(intent);

                    }  //文件下载时，可以指定下载的文件目录和文件名



                    @Override
                    public void downloadProgress(Progress progress) {
                        super.downloadProgress(progress);
                    }
                });
    }


}
