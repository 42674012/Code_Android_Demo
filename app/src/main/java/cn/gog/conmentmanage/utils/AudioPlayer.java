package cn.gog.conmentmanage.utils;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import cn.gog.conmentmanage.ui.view.TextSeekBar;
import common.utils.DateUtils;
import common.utils.LogUtil;

/**
 * author:gujin
 * mail:1002606871@qq.com
 * date：2017/4/22 on 17:52
 * desc:
 */

public class AudioPlayer implements MediaPlayer.OnBufferingUpdateListener, MediaPlayer.OnCompletionListener, MediaPlayer.OnPreparedListener, SeekBar.OnSeekBarChangeListener {
    public MediaPlayer mediaPlayer;
    private TextSeekBar skbProgress;
    private TextView mTxtDuration;
    private Timer mTimer;
    private static AudioPlayer mAudioPlayerInstance = null;

    public static AudioPlayer createPlayer(TextSeekBar skbProgress, TextView txtDuration) {
        if (null == mAudioPlayerInstance) {
            mAudioPlayerInstance = new AudioPlayer();
        }
        mAudioPlayerInstance.initPlayer(skbProgress, txtDuration);
        return mAudioPlayerInstance;
    }

    public AudioPlayer() {
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mediaPlayer.setOnBufferingUpdateListener(this);
        mediaPlayer.setOnPreparedListener(this);
        mTimer = new Timer();
        mTimer.schedule(mTimerTask, 0, 1000);
    }


    private void initPlayer(TextSeekBar skbProgress, TextView txtDuration) {
        try {
            this.mTxtDuration = txtDuration;
            this.skbProgress = skbProgress;
            this.skbProgress.setOnSeekBarChangeListener(this);
            this.reset();
        } catch (Exception e) {
            Log.e("mediaPlayer", "error", e);
        }

    }

    /*******************************************************
     * 通过定时器和Handler来更新进度条
     ******************************************************/
    TimerTask mTimerTask = new TimerTask() {
        @Override
        public void run() {
            if (mediaPlayer == null)
                return;
            if (mediaPlayer.isPlaying() && skbProgress.isPressed() == false) {
                handleProgress.sendEmptyMessage(0);
            }
        }
    };

    Handler handleProgress = new Handler() {
        public void handleMessage(Message msg) {

            int position = mediaPlayer.getCurrentPosition();
            int duration = mediaPlayer.getDuration();
            LogUtil.e("liveGz", "position:" + position, true);
            if (duration > 0) {
                long pos = skbProgress.getMax() * position / duration;
                skbProgress.setProgress((int) pos);
                skbProgress.setCurrentTime(position / 1000);
                skbProgress.invalidate();
                //skbProgress.setText(String.valueOf(progress));
            }
        }

    };
    //*****************************************************

    public void play() {
        mediaPlayer.start();
    }

    public void playUrl(String videoUrl, int totalSeconds) {
        try {
            this.reset();
            mediaPlayer.setDataSource(videoUrl);
            //skbProgress.setTotal(mediaPlayer.getDuration());
            //mediaPlayer.prepare();//prepare之后自动播放
            mediaPlayer.prepareAsync();
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer player) {
                    int duration = player.getDuration() / 1000;
                    skbProgress.setTotal(duration);
                    if (null != mTxtDuration) {
                        String str = DateUtils.generateTimeBySeconds(duration);
                        mTxtDuration.setText(str);
                    }
                    mediaPlayer.start();
                }
            });
            //mediaPlayer.start();
        } catch (IllegalArgumentException e) {

            e.printStackTrace();
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    public void pause() {
        mediaPlayer.pause();
    }

    public boolean pauseOrPlay() {
        if (null == mediaPlayer) {
            return false;
        }
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        } else {
            mediaPlayer.start();
        }
        return mediaPlayer.isPlaying();
    }

    public void stop() {
        if (null != mediaPlayer) {
            mediaPlayer.stop();
            //mediaPlayer.release();
            // mediaPlayer = null;
        }
//        if (null!=this.skbProgress){
//            skbProgress.setOnClickListener(null);
//        }
    }

    @Override
    /**
     * 通过onPrepared播放
     */
    public void onPrepared(MediaPlayer arg0) {
        arg0.start();
        Log.e("mediaPlayer", "onPrepared");
    }

    @Override
    public void onCompletion(MediaPlayer arg0) {
        Log.e("mediaPlayer", "onCompletion");
    }

    @Override
    public void onBufferingUpdate(MediaPlayer arg0, int bufferingProgress) {
        skbProgress.setSecondaryProgress(bufferingProgress);
        // mediaPlayer.getDuration() 可能为零；
        int currentProgress = 0;
        if (mediaPlayer.getDuration() != 0) {
            try {
                currentProgress = skbProgress.getMax() * mediaPlayer.getCurrentPosition() / mediaPlayer.getDuration();
            }catch (ArithmeticException e){
                e.printStackTrace();
            }
        }


        Log.e(currentProgress + "% play", bufferingProgress + "% buffer");
    }

    public void reset() {
        if (null != this.mediaPlayer) {
            this.mediaPlayer.seekTo(0);
            this.skbProgress.setProgress(0);
            this.mTxtDuration.setText("00:00");
            mediaPlayer.reset();
        }
    }

    int progress;

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        System.out.println("progress:" + String.valueOf(progress));
        this.progress = progress * this.mediaPlayer.getDuration()
                / seekBar.getMax();
//        skbProgress.setSeekBarText(String.valueOf(progress));
//        skbProgress.setSeekBarText("mana"+"\t\n"+progress);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        this.mediaPlayer.seekTo(progress);
    }
}
