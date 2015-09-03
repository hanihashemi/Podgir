package io.github.hanihashemi.podgir.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.android.volley.Request;
import com.android.volley.Response;
import com.orm.dsl.Ignore;

import io.github.hanihashemi.podgir.network.request.GsonRequest;

/**
 * Created by hani on 8/21/15.
 */
public class Feed extends BaseModel<Feed> implements Parcelable {
    public static final Parcelable.Creator<Feed> CREATOR = new Parcelable.Creator<Feed>() {
        public Feed createFromParcel(Parcel source) {
            return new Feed(source);
        }

        public Feed[] newArray(int size) {
            return new Feed[size];
        }
    };
    private String objectId;
    private String parent;
    private String title;
    private String url;
    private String summary;
    @Ignore
    private boolean downloaded;

    public Feed() {
    }

    protected Feed(Parcel in) {
        this.objectId = in.readString();
        this.parent = in.readString();
        this.title = in.readString();
        this.url = in.readString();
        this.summary = in.readString();
        this.downloaded = in.readByte() != 0;
    }

    public GsonRequest<FeedResultResponse> findAll(String parent, Response.Listener<FeedResultResponse> onSuccess, Response.ErrorListener onFailed) {
        String argument = "{\"parent\":\"" + parent + "\"}";

        return new GsonRequest<>(
                Request.Method.GET,
                getHostUrl("classes/feed?where=" +
                        argument),
                null,
                FeedResultResponse.class,
                onSuccess,
                onFailed
        );
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public boolean isDownloaded() {
        return downloaded;
    }

    public void setDownloaded(boolean downloaded) {
        this.downloaded = downloaded;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.objectId);
        dest.writeString(this.parent);
        dest.writeString(this.title);
        dest.writeString(this.url);
        dest.writeString(this.summary);
        dest.writeByte(downloaded ? (byte) 1 : (byte) 0);
    }
}
