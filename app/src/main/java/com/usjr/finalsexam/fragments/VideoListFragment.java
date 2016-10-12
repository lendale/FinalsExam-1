package com.usjr.finalsexam.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.usjr.finalsexam.R;
import com.usjr.finalsexam.adapters.VideoListAdapter;
import com.usjr.finalsexam.entity.Video;

import java.util.ArrayList;

public class VideoListFragment extends Fragment implements AdapterView.OnItemClickListener {

    public interface OnVideoSelectedListener {
        void videoSelectedListener(Video video);
    }

    private VideoListAdapter        mAdapter;
    private OnVideoSelectedListener mOnVideoSelectedListener;

    private DatabaseReference mRootDb;
    private DatabaseReference mVideosDb;

    public VideoListFragment() {
        // Required empty public constructor
    }

    public OnVideoSelectedListener getOnVideoSelectedListener() {
        return mOnVideoSelectedListener;
    }

    public VideoListFragment setOnVideoSelectedListener(OnVideoSelectedListener onVideoSelectedListener) {
        mOnVideoSelectedListener = onVideoSelectedListener;
        return this;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mRootDb = FirebaseDatabase.getInstance().getReference();
        mVideosDb = mRootDb.child("videos");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_video_list, container, false);
        ListView listView = (ListView) view.findViewById(R.id.listView);

        mAdapter = new VideoListAdapter(
                getContext(),
                android.R.layout.simple_list_item_1,
                new ArrayList<Video>());
        listView.setAdapter(mAdapter);

        listView.setOnItemClickListener(this);

        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (mOnVideoSelectedListener == null) {
            return;
        }
    }
}
