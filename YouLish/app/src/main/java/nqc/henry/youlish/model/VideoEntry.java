package nqc.henry.youlish.model;

/**
 * Created by hinh1 on 10/6/2016.
 */
public class VideoEntry {
    private final String text;
    private final String videoId;

    public VideoEntry(String text, String videoId) {
        this.text = text;
        this.videoId = videoId;
    }

    public String getText() {
        return text;
    }

    public String getVideoId() {
        return videoId;
    }
}
