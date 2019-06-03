//package cn.gog.conmentmanage.ui.adapter;
//
//import android.app.Activity;
//import android.content.Intent;
//import android.os.Bundle;
//import android.support.v4.content.ContextCompat;
//import android.view.View;
//
//import com.chad.library.adapter.base.BaseQuickAdapter;
//import com.chad.library.adapter.base.BaseViewHolder;
//
//import java.util.List;
//
//import cn.gog.conmentmanage.R;
//import common.utils.ConstanceValue;
//import common.utils.DateUtils;
//import de.hdodenhof.circleimageview.CircleImageView;
//
///**
// * author:gujin
// * mail:1002606871@qq.com
// * date：2017/4/21 on 14:24
// * desc:
// */
//
//public class AudioAdapter extends BaseQuickAdapter<AudioNews,BaseViewHolder> {
//    protected Activity context;
//
//    CircleImageView btnStatus;
//
//    public String getAudioId() {
//        return audioId;
//    }
//
//    public void setAudioId(String audioId) {
//        this.audioId = audioId;
//    }
//
//    private String audioId;
//    private boolean isPlaying = false;
//
//    public AudioAdapter(Activity context, List<AudioNews> data) {
//        super(R.layout.item_list_audio, data);
//        this.context = context;
//    }
//
//
//    @Override
//    protected void convert(BaseViewHolder baseViewHolder, final AudioNews news) {
//
//        baseViewHolder.setText(R.id.title_text, String.format("%s", news.getTitle()));
//        String detail = "";
////        detail = String.format("%s 时长 %d %d", DateUtils.getShortTime(news.getCreateTime()), news.getDuration(), news.getSize());
//        detail = String.format("%s", DateUtils.getShortTime(news.getCreateTime()));
//
//        baseViewHolder.setText(R.id.detail_text, detail);
//        baseViewHolder.setOnClickListener(R.id.detail_btn, new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent mIntent = new Intent(context, NewsDetailActivity.class);
//                Bundle mBundle = new Bundle();
//                mBundle.putSerializable(ConstanceValue.AUDIO_NEWS, news);
//                mIntent.putExtras(mBundle);
//                context.startActivity(mIntent);
//            }
//        });
//
//        AudioNews audio = AudioNewsManager.getInstance().getAudioNews(news.getId());
//        BuildListenAudio(baseViewHolder, audio);
//        baseViewHolder.addOnClickListener(R.id.root);
//    }
//
//    private void BuildListenAudio(BaseViewHolder baseViewHolder, AudioNews audio) {
//        boolean listened = false;
//        if (null == audio) {
//            return;
//        } else {
//            listened = audio.isListened();
//        }
//        if (listened) {
//            baseViewHolder.setTextColor(R.id.title_text, ContextCompat.getColor(context, R.color.gray_font))
//                    .setTextColor(R.id.detail_text, ContextCompat.getColor(context, R.color.gray_font));
//        } else {
//            baseViewHolder.setTextColor(R.id.title_text, ContextCompat.getColor(context, R.color.common_font))
//                    .setTextColor(R.id.detail_text, ContextCompat.getColor(context, R.color.common_font));
//        }
//        if (audio.getId().equals(this.audioId)) {
//            baseViewHolder.setTextColor(R.id.title_text, ContextCompat.getColor(context, R.color.colorPrimary));
//            baseViewHolder.setVisible(R.id.img_status_disable, false).setVisible(R.id.img_status_pause, !isPlaying)
//                    .setVisible(R.id.img_status_playing_in, isPlaying).setVisible(R.id.img_status_playing_out, isPlaying);
//        } else {
//            baseViewHolder.setVisible(R.id.img_status_disable, true).setVisible(R.id.img_status_pause, true)
//                    .setVisible(R.id.img_status_playing_in, false).setVisible(R.id.img_status_playing_out, false);
//        }
//    }
//
//    public void setPlayId(String audioId) {
//        this.audioId = audioId;
//        this.isPlaying = true;
//    }
//
//    public void setPlayStatus(boolean playStatus) {
//        this.isPlaying = playStatus;
//    }
//}
