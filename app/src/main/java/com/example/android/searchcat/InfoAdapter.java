package com.example.android.searchcat;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import com.bumptech.glide.Glide;

import java.util.List;

public class InfoAdapter extends ArrayAdapter<CatsInfo> {
    public InfoAdapter(@NonNull Context context, List<CatsInfo> catsInfo) {
        super(context, 0, catsInfo);
    }
    @SuppressLint("SetTextI18n")
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.cats_detail, parent, false);
        }
        CatsInfo currentCatInfo = getItem(position);

        ImageView imageView = listItemView.findViewById(R.id.img);
        String imgUrl = "https://cataas.com/cat/" + currentCatInfo.id();
        Glide.with(parent).load(imgUrl).error(R.drawable.no_data).into(imageView);

        TextView dateAndTime = listItemView.findViewById(R.id.date_time);
        dateAndTime.setText(currentCatInfo.dateAndTime());

        TextView tags = listItemView.findViewById(R.id.tags);
        List<String> tagsList = currentCatInfo.CatTags();
        StringBuilder tag = new StringBuilder(" ");
        for(int i = 0; i < (tagsList != null ? tagsList.size() : 0); i++){
            tag.append(tagsList.get(i)).append(",");
        }
        tags.setText("Tags:" + tag.toString());

        return listItemView;
    }
}