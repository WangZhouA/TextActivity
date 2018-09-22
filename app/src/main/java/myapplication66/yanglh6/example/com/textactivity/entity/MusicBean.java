package myapplication66.yanglh6.example.com.textactivity.entity;

/**
 * Created by 陈姣姣 on 2018/6/13.
 */
public class MusicBean {


    public static final int TYPE_SD = 1;
    public static final int TYPE_INSTER = 0;
    Long id;
    //标题
    String title;
    //专辑
    String album;
    //显示的名称
    String displayName;
    //艺术家
    String artist;
    //长度
    long duration;
    //大小？
    long size;
    //url，用来播放的
    String url;
    //类型，内置，本地，网络
    int type;
    int album_id;

    public MusicBean(Long id, String title, String album, String displayName, String artist, long duration, long size, String url, int type, int album_id) {
        this.id = id;
        this.title = title;
        this.album = album;
        this.displayName = displayName;
        this.artist = artist;
        this.duration = duration;
        this.size = size;
        this.url = url;
        this.type = type;
        this.album_id = album_id;
    }


    public MusicBean() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getAlbum_id() {
        return album_id;
    }

    public void setAlbum_id(int album_id) {
        this.album_id = album_id;
    }
}
