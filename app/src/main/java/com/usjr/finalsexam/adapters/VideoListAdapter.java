package com.usjr.finalsexam.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.usjr.finalsexam.R;
import com.usjr.finalsexam.entity.Video;

import java.util.List;

public class VideoListAdapter extends ArrayAdapter<Video> {

    private Context     mContext;
    private int         mLayoudId;
    private List<Video> mVideos;

    public VideoListAdapter(Context context, int resource, List<Video> videos) {
        super(context, resource);
        mContext = context;
        mLayoudId = resource;
        mVideos = videos;
    }

    @Override
    public int getCount() {
        return mVideos == null ? 0 : mVideos.size();
    }

    @Override
    public Video getItem(int position) {
        return mVideos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(mLayoudId, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Video video = mVideos.get(position);
        if (video != null) {
            if (holder.imgThumbnail != null) {
                Glide.with(mContext).load(video.thumbnailUrl).into(holder.imgThumbnail);
            }
            if (holder.tvTitle != null) {
                holder.tvTitle.setText(video.title);
            }
        }

        return convertView;
    }

    public void add(Video video) {
        if (video == null) {
            return;
        }

        mVideos.add(video);
    }

    public class ViewHolder {
        ImageView imgThumbnail;
        TextView  tvTitle;

        public ViewHolder(View itemView) {
            imgThumbnail = (ImageView) itemView.findViewById(R.id.imgThumbnail);
            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
        }
    }
}
