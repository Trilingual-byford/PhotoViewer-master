package com.peace.love.photoviewer.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * ProjectName:PhotoViewer
 * Date:2018/3/2 16:39
 *
 * @author CBF
 */

public class PhotoData {

    private String stat;
    private InfoBean info;

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

    public InfoBean getInfo() {
        return info;
    }

    public void setInfo(InfoBean info) {
        this.info = info;
    }

    public static class InfoBean {

        private boolean CONTENTS_VIEW_LANG;
        private boolean NO_VIEW_FCEBOOK_TWITTER;
        private int photo_num;
        private List<PhotoBean> photo;

        public boolean isCONTENTS_VIEW_LANG() {
            return CONTENTS_VIEW_LANG;
        }

        public void setCONTENTS_VIEW_LANG(boolean CONTENTS_VIEW_LANG) {
            this.CONTENTS_VIEW_LANG = CONTENTS_VIEW_LANG;
        }

        public boolean isNO_VIEW_FCEBOOK_TWITTER() {
            return NO_VIEW_FCEBOOK_TWITTER;
        }

        public void setNO_VIEW_FCEBOOK_TWITTER(boolean NO_VIEW_FCEBOOK_TWITTER) {
            this.NO_VIEW_FCEBOOK_TWITTER = NO_VIEW_FCEBOOK_TWITTER;
        }

        public int getPhoto_num() {
            return photo_num;
        }

        public void setPhoto_num(int photo_num) {
            this.photo_num = photo_num;
        }

        public List<PhotoBean> getPhoto() {
            return photo;
        }

        public void setPhoto(List<PhotoBean> photo) {
            this.photo = photo;
        }

        public static class PhotoBean implements Parcelable {



            private int photo_id;
            private int user_id;
            private int album_id;
            private String photo_title;
            private int favorite_num;
            private int comment_num;
            private int view_num;
            private String copyright;
            private int original_height;
            private int original_width;
            private String date;
            private String regist_time;
            private String url;
            private String image_url;
            private String original_image_url;
            private String thumbnail_image_url;
            private String large_tag;
            private String medium_tag;

            public int getPhoto_id() {
                return photo_id;
            }

            public void setPhoto_id(int photo_id) {
                this.photo_id = photo_id;
            }

            public int getUser_id() {
                return user_id;
            }

            public void setUser_id(int user_id) {
                this.user_id = user_id;
            }

            public int getAlbum_id() {
                return album_id;
            }

            public void setAlbum_id(int album_id) {
                this.album_id = album_id;
            }

            public String getPhoto_title() {
                return photo_title;
            }

            public void setPhoto_title(String photo_title) {
                this.photo_title = photo_title;
            }

            public int getFavorite_num() {
                return favorite_num;
            }

            public void setFavorite_num(int favorite_num) {
                this.favorite_num = favorite_num;
            }

            public int getComment_num() {
                return comment_num;
            }

            public void setComment_num(int comment_num) {
                this.comment_num = comment_num;
            }

            public int getView_num() {
                return view_num;
            }

            public void setView_num(int view_num) {
                this.view_num = view_num;
            }

            public String getCopyright() {
                return copyright;
            }

            public void setCopyright(String copyright) {
                this.copyright = copyright;
            }

            public int getOriginal_height() {
                return original_height;
            }

            public void setOriginal_height(int original_height) {
                this.original_height = original_height;
            }

            public int getOriginal_width() {
                return original_width;
            }

            public void setOriginal_width(int original_width) {
                this.original_width = original_width;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getRegist_time() {
                return regist_time;
            }

            public void setRegist_time(String regist_time) {
                this.regist_time = regist_time;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getImage_url() {
                return image_url;
            }

            public void setImage_url(String image_url) {
                this.image_url = image_url;
            }

            public String getOriginal_image_url() {
                return original_image_url;
            }

            public void setOriginal_image_url(String original_image_url) {
                this.original_image_url = original_image_url;
            }

            public String getThumbnail_image_url() {
                return thumbnail_image_url;
            }

            public void setThumbnail_image_url(String thumbnail_image_url) {
                this.thumbnail_image_url = thumbnail_image_url;
            }

            public String getLarge_tag() {
                return large_tag;
            }

            public void setLarge_tag(String large_tag) {
                this.large_tag = large_tag;
            }

            public String getMedium_tag() {
                return medium_tag;
            }

            public void setMedium_tag(String medium_tag) {
                this.medium_tag = medium_tag;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeInt(this.photo_id);
                dest.writeInt(this.user_id);
                dest.writeInt(this.album_id);
                dest.writeString(this.photo_title);
                dest.writeInt(this.favorite_num);
                dest.writeInt(this.comment_num);
                dest.writeInt(this.view_num);
                dest.writeString(this.copyright);
                dest.writeInt(this.original_height);
                dest.writeInt(this.original_width);
                dest.writeString(this.date);
                dest.writeString(this.regist_time);
                dest.writeString(this.url);
                dest.writeString(this.image_url);
                dest.writeString(this.original_image_url);
                dest.writeString(this.thumbnail_image_url);
                dest.writeString(this.large_tag);
                dest.writeString(this.medium_tag);
            }

            public PhotoBean() {
            }

            protected PhotoBean(Parcel in) {
                this.photo_id = in.readInt();
                this.user_id = in.readInt();
                this.album_id = in.readInt();
                this.photo_title = in.readString();
                this.favorite_num = in.readInt();
                this.comment_num = in.readInt();
                this.view_num = in.readInt();
                this.copyright = in.readString();
                this.original_height = in.readInt();
                this.original_width = in.readInt();
                this.date = in.readString();
                this.regist_time = in.readString();
                this.url = in.readString();
                this.image_url = in.readString();
                this.original_image_url = in.readString();
                this.thumbnail_image_url = in.readString();
                this.large_tag = in.readString();
                this.medium_tag = in.readString();
            }

            public static final Parcelable.Creator<PhotoBean> CREATOR = new Parcelable.Creator<PhotoBean>() {
                @Override
                public PhotoBean createFromParcel(Parcel source) {
                    return new PhotoBean(source);
                }

                @Override
                public PhotoBean[] newArray(int size) {
                    return new PhotoBean[size];
                }
            };
        }
    }
}
