package com.example.android.searchcat;

import androidx.annotation.Nullable;
import com.google.auto.value.AutoValue;
import com.google.gson.annotations.SerializedName;
import com.ryanharter.auto.value.gson.GenerateTypeAdapter;
import java.util.List;

@GenerateTypeAdapter
@AutoValue
public abstract class CatsInfo {
    @Nullable
    @SerializedName("id")
    abstract String id();

    @Nullable
    @SerializedName("created_at")
    abstract String dateAndTime();

    @Nullable
    @SerializedName("tags")
    abstract List<String> CatTags();

    public static CatsInfo create(String id , String dateAndTime , List<String> CatTags){
        return new AutoValue_CatsInfo(id , dateAndTime , CatTags);
    }
}
