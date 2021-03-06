package org.xxx.livertmp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.avxxx.RendererCommon;
import org.avxxx.api.DyncLivePlayer;
import org.avxxx.core.AvApp;
import org.avxxx.core.PlayHelper;
import org.avxxx.core.VideoView;
import org.xxx.livertmp.R;

/**
 * 拉流播放demo
 * Created by Skyline on 2016/6/24.
 */
public class PullActivity extends AppCompatActivity implements PlayHelper {

    private RelativeLayout rl_videos;
    private TextView txt_staus;
    private VideoView videoView;
    private String mUrl = "rtmp://www.teameeting.cn/live/f001";
    private boolean mStarted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull);
        {
            rl_videos = (RelativeLayout) findViewById(R.id.rl_videos);
            txt_staus = (TextView) findViewById(R.id.txt_status);

            /**
             * 初始化 VideoView 时
             * RendererCommon.ScalingType.SCALE_ASPECT_FIT（适应屏幕大小填充）,
             * RendererCommon.ScalingType.SCALE_ASPECT_FILL（根据图像大小填充）,
             * RendererCommon.ScalingType.SCALE_ASPECT_BALANCED（平衡FIT和FILL）
             */
            videoView = new VideoView(this, AvApp.Inst().Egl(), 0, 0, 0, 100, 100, RendererCommon.ScalingType.SCALE_ASPECT_FIT);
            rl_videos.addView(videoView.mLayout);
        }
        mUrl = getIntent().getExtras().getString("url");
        DyncLivePlayer.Instance().init(this, videoView, this);
    }

    @Override
    protected void onDestroy() {
 	   super.onDestroy();
 	   DyncLivePlayer.Instance().destroy();
    }

    public void OnBtnClick(View view) {
        Button btn = (Button) view;
        if (btn.getId() == R.id.btn_click) {
            if(mStarted) {
                DyncLivePlayer.Instance().stop();
                ((Button) view).setText("播放");
            } else {
                DyncLivePlayer.Instance().play(mUrl);
                ((Button) view).setText("停止");
            }
            mStarted = !mStarted;
        }
    }

    /**
     * Implements for PlayHelper
     */
    /**
     * 播放OK
     */
    @Override
    public void OnRtmplayerOK() {

    }

    /**
     * 当前的播放状态
     * @param cacheTime 已缓冲的时间
     * @param curBitrate    当前的网络比特率
     */
    @Override
    public void OnRtmplayerStatus(int cacheTime, int curBitrate) {

    }

    /**
     * 正在进行缓冲
     * @param time  缓冲的时间
     */
    @Override
    public void OnRtmplayerCache(int time) {

    }

    /**
     * 播放已结束
     * @param errcode 0:正常结束
     */
    @Override
    public void OnRtmplayerClose(int errcode) {
	
    }
}

