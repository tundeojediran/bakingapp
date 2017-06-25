package udacity.alc.dannytee.bakingapp.models;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONObject;

/**
 * Created by dannytee on 25/06/2017.
 */

public class Step implements Parcelable {

    private Integer id;
    private String shortDescription;
    private String description;
    private String videoURL;
    private String thumbnailURL;
    public final static Parcelable.Creator<Step> CREATOR = new Creator<Step>() {


        @Override
        public Step createFromParcel(Parcel parcel) {
            return null;
        }

        public Step[] newArray(int size) {
            return (new Step[size]);
        }

    }
            ;

    /**
     * No args constructor for use in serialization
     *
     * @param jsonObject
     */
    public Step(JSONObject jsonObject) {
    }

    /**
     *
     * @param id
     * @param shortDescription
     * @param description
     * @param videoURL
     * @param thumbnailURL
     */
    public Step(Integer id, String shortDescription, String description, String videoURL, String thumbnailURL) {
        super();
        this.id = id;
        this.shortDescription = shortDescription;
        this.description = description;
        this.videoURL = videoURL;
        this.thumbnailURL = thumbnailURL;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVideoURL() {
        return videoURL;
    }

    public void setVideoURL(String videoURL) {
        this.videoURL = videoURL;
    }

    public String getThumbnailURL() {
        return thumbnailURL;
    }

    public void setThumbnailURL(String thumbnailURL) {
        this.thumbnailURL = thumbnailURL;
    }



    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(shortDescription);
        dest.writeValue(description);
        dest.writeValue(videoURL);
        dest.writeValue(thumbnailURL);
    }

    public int describeContents() {
        return  0;
    }
}
