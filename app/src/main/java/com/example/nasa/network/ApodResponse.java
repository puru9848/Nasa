package com.example.nasa.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ApodResponse extends BaseResponse implements Serializable {

    @SerializedName("copyright")
    @Expose
    private String copyright;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("explanation")
    @Expose
    private String explanation;
    @SerializedName("hdurl")
    @Expose
    private String hdurl;
    @SerializedName("media_type")
    @Expose
    private String mediaType;
    @SerializedName("service_version")
    @Expose
    private String serviceVersion;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("url")
    @Expose
    private String url;
    private final static long serialVersionUID = 792007916169497281L;

    /**
     * No args constructor for use in serialization
     */
    public ApodResponse() {
    }

    /**
     * @param date
     * @param serviceVersion
     * @param copyright
     * @param mediaType
     * @param hdurl
     * @param explanation
     * @param title
     * @param url
     */
    public ApodResponse(String copyright, String date, String explanation, String hdurl, String mediaType, String serviceVersion, String title, String url) {
        super();
        this.copyright = copyright;
        this.date = date;
        this.explanation = explanation;
        this.hdurl = hdurl;
        this.mediaType = mediaType;
        this.serviceVersion = serviceVersion;
        this.title = title;
        this.url = url;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public String getHdurl() {
        return hdurl;
    }

    public void setHdurl(String hdurl) {
        this.hdurl = hdurl;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public String getServiceVersion() {
        return serviceVersion;
    }

    public void setServiceVersion(String serviceVersion) {
        this.serviceVersion = serviceVersion;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(ApodResponse.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("copyright");
        sb.append('=');
        sb.append(((this.copyright == null) ? "<null>" : this.copyright));
        sb.append(',');
        sb.append("date");
        sb.append('=');
        sb.append(((this.date == null) ? "<null>" : this.date));
        sb.append(',');
        sb.append("explanation");
        sb.append('=');
        sb.append(((this.explanation == null) ? "<null>" : this.explanation));
        sb.append(',');
        sb.append("hdurl");
        sb.append('=');
        sb.append(((this.hdurl == null) ? "<null>" : this.hdurl));
        sb.append(',');
        sb.append("mediaType");
        sb.append('=');
        sb.append(((this.mediaType == null) ? "<null>" : this.mediaType));
        sb.append(',');
        sb.append("serviceVersion");
        sb.append('=');
        sb.append(((this.serviceVersion == null) ? "<null>" : this.serviceVersion));
        sb.append(',');
        sb.append("title");
        sb.append('=');
        sb.append(((this.title == null) ? "<null>" : this.title));
        sb.append(',');
        sb.append("url");
        sb.append('=');
        sb.append(((this.url == null) ? "<null>" : this.url));
        sb.append(',');
        if (sb.charAt((sb.length() - 1)) == ',') {
            sb.setCharAt((sb.length() - 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
