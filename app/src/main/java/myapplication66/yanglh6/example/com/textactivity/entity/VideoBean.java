package myapplication66.yanglh6.example.com.textactivity.entity;

import java.util.List;

/**
 * Created by 陈姣姣 on 2018/7/18.
 */
public class VideoBean {


    /**
     * success : true
     * code : 1000
     * message : 查询成功
     * data : [{"name":"顶部视频","videoInfos":[{"id":9,"videoUrl":"1.png","title":"全国中学生广播体操","createTime":"2018-07-18 11:42:33.0","partake":0}]},{"name":"精选视频","videoInfos":[{"id":10,"videoUrl":"2.png","title":"广播体操第一节","createTime":"2018-07-17 11:45:57.0","partake":0},{"id":11,"videoUrl":"3.png","title":"广播体操第二节","createTime":"2018-07-16 11:46:02.0","partake":0},{"id":12,"videoUrl":"4.png","title":"广播体操第三节","createTime":"2018-07-20 11:46:05.0","partake":0},{"id":13,"videoUrl":"5.png","title":"广播体操第四节","createTime":"2018-07-17 11:46:08.0","partake":0}]},{"name":"普通视频","videoInfos":[{"id":14,"videoUrl":"6.png","title":"看我神操作","createTime":"2018-07-17 11:46:12.0","partake":0},{"id":15,"videoUrl":"7.png","title":"秀你一脸","createTime":"2018-07-18 11:46:15.0","partake":0}]}]
     */

    private boolean success;
    private int code;
    private String message;
    /**
     * name : 顶部视频
     * videoInfos : [{"id":9,"videoUrl":"1.png","title":"全国中学生广播体操","createTime":"2018-07-18 11:42:33.0","partake":0}]
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
         * id : 9
         * videoUrl : 1.png
         * title : 全国中学生广播体操
         * createTime : 2018-07-18 11:42:33.0
         * partake : 0
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
