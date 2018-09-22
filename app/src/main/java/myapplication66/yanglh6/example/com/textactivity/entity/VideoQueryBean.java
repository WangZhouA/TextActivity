package myapplication66.yanglh6.example.com.textactivity.entity;

import java.util.List;

/**
 * Created by 陈姣姣 on 2018/7/26.
 */
public class VideoQueryBean {


    /**
     * success : true
     * code : 1000
     * message : 查询成功
     * data : [{"name":"精选视频","videoInfos":[{"id":10,"videoUrl":"fileUpload/peritoneal/201807/20180719102818399-484.mp4","title":"广播体操第一节","createTime":"2018-07-17 11:45:57.0","partake":2}]},{"name":"普通视频","videoInfos":[{"id":15,"videoUrl":"fileUpload/peritoneal/201807/20180719102818399-484.mp4","title":"秀你一脸","createTime":"2018-07-18 11:46:15.0","partake":0}]}]
     */

    private boolean success;
    private int code;
    private String message;
    /**
     * name : 精选视频
     * videoInfos : [{"id":10,"videoUrl":"fileUpload/peritoneal/201807/20180719102818399-484.mp4","title":"广播体操第一节","createTime":"2018-07-17 11:45:57.0","partake":2}]
     */

    private List<DataBean> data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private String name;
        /**
         * id : 10
         * videoUrl : fileUpload/peritoneal/201807/20180719102818399-484.mp4
         * title : 广播体操第一节
         * createTime : 2018-07-17 11:45:57.0
         * partake : 2
         */

        private List<VideoInfosBean> videoInfos;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<VideoInfosBean> getVideoInfos() {
            return videoInfos;
        }

        public void setVideoInfos(List<VideoInfosBean> videoInfos) {
            this.videoInfos = videoInfos;
        }

        public static class VideoInfosBean {
            private int id;
            private String videoUrl;
            private String title;
            private String createTime;
            private int partake;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getVideoUrl() {
                return videoUrl;
            }

            public void setVideoUrl(String videoUrl) {
                this.videoUrl = videoUrl;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public int getPartake() {
                return partake;
            }

            public void setPartake(int partake) {
                this.partake = partake;
            }
        }
    }
}
