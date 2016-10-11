package nqc.henry.youlish.frament;

import android.app.Activity;
import android.os.Bundle;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;

import nqc.henry.youlish.model.DeveloperKey;

/**
 * Created by hinh1 on 10/5/2016.
 */
public class VideoFragment extends YouTubePlayerFragment implements YouTubePlayer.OnInitializedListener {
    private Activity activity;
    private YouTubePlayer player;
    private String videoId;
    private boolean isplay = true;


    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public static VideoFragment newInstance() {
        return new VideoFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initialize(DeveloperKey.DEVELOPER_KEY, this);

    }


    public  int timeVideo(){
        if (player.isPlaying()){
            return player.getCurrentTimeMillis();
        }
        return 0;
    }
    public boolean isIsplayVideo(){
        if (player.isPlaying())
            return true;
        return false;
    }
    @Override
    public void onDestroy() {

        if (player != null) {
            player.release();
        }
        super.onDestroy();
    }

    public void setVideoId(String videoId) {
        if (videoId != null && !videoId.equals(this.videoId)) {
            this.videoId = videoId;
            if (player != null) {
                player.loadVideo(videoId, 5000);


            }
        }
    }

    public void pause() {
        if (player != null) {
            player.pause();
        }
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player, boolean restored) {

        this.player = player;
        player.addFullscreenControlFlag(YouTubePlayer.FULLSCREEN_FLAG_CUSTOM_LAYOUT);
        player.setOnFullscreenListener((YouTubePlayer.OnFullscreenListener) activity);
        if (!restored && videoId != null) {
            player.cueVideo(videoId);
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult result) {
        this.player = null;
    }
}
