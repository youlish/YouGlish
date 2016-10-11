package nqc.henry.youlish.frament;

import android.app.Activity;
import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import nqc.henry.youlish.adapter.PageAdapter;
import nqc.henry.youlish.model.VideoEntry;
import nqc.henry.youlish.ui.VideoActivity;

/**
 * Created by hinh1 on 10/6/2016.
 */
public class VideoListFrament extends ListFragment{
    private Activity activity;
    private static final int ANIMATION_DURATION_MILLIS = 300;

    private static final List<VideoEntry> VIDEO_LIST;
    static {
        List<VideoEntry> list = new ArrayList<VideoEntry>();
        list.add(new VideoEntry("YouTube Collection", "6yXA7HMMNxs"));
        list.add(new VideoEntry("Chrome Multitask", "UiLSiqyDf4Y"));
        list.add(new VideoEntry("Google Fiber", "re0VRK6ouwI"));
        list.add(new VideoEntry("Autocompleter", "blB_X38YSxQ"));
        list.add(new VideoEntry("GMail Motion", "Bu927_ul_X0"));
        list.add(new VideoEntry("Translate for Animals", "3I24bSteJpw"));
        VIDEO_LIST = Collections.unmodifiableList(list);
    }

    private PageAdapter adapter;
    private View videoBox;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new PageAdapter(getActivity(), VIDEO_LIST);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //videoBox = getActivity().findViewById(R.id.video_box);
        getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        setListAdapter(adapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        String videoId = VIDEO_LIST.get(position).getVideoId();


        Intent intent = new Intent(getActivity(),VideoActivity.class);
        intent.putExtra("videoId",videoId);
        startActivity(intent);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        adapter.releaseLoaders();
    }
    public void setActivity(Activity activity){
        this.activity=activity;
    }
    public void setLabelVisibility(boolean visible) {
        adapter.setLabelVisibility(visible);
    }

}
