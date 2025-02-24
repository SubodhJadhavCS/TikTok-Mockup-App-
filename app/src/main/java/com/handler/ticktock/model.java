package com.handler.ticktock;

public class model {

    private String video;
    private String profile_image;
    private String Username;
    private String Video_description;

    public model(String video, String profile_image, String username, String video_description) {
        this.video = video;
        this.profile_image = profile_image;
        this.Username = username;
        this.Video_description = video_description;
    }


    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getProfile_image() {
        return profile_image;
    }

    public void setProfile_image(String profile_image) {
        this.profile_image = profile_image;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getVideo_description() {
        return Video_description;
    }

    public void setVideo_description(String video_description) {
        Video_description = video_description;
    }
}
