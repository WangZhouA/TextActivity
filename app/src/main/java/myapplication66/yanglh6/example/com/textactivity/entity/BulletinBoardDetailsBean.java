package myapplication66.yanglh6.example.com.textactivity.entity;

/**
 * Created by 陈姣姣 on 2018/7/16.
 */
public class BulletinBoardDetailsBean {

    /**
     * success : true
     * code : 1000
     * message : 查询成功
     * data : {"id":20,"noticeTitle":"标题白哦提","imgUrl":"fileUpload/peritoneal/8f031b96-924f-4a4d-9347-dda6e97b33e6.jpg","noticeCountent":"标题156651243213","pageView":2,"hospitalName":"中医院","titleOfTime":"16:36"}
     */

    private boolean success;
    private int code;
    private String message;
    /**
     * id : 20
     * noticeTitle : 标题白哦提
     * imgUrl : fileUpload/peritoneal/8f031b96-924f-4a4d-9347-dda6e97b33e6.jpg
     * noticeCountent : 标题156651243213
     * pageView : 2
     * hospitalName : 中医院
     * titleOfTime : 16:36
     */

    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private int id;
        private String noticeTitle;
        private String imgUrl;
        private String noticeCountent;
        private int pageView;
        private String hospitalName;
        private String titleOfTime;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getNoticeTitle() {
            return noticeTitle;
        }

        public void setNoticeTitle(String noticeTitle) {
            this.noticeTitle = noticeTitle;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public String getNoticeCountent() {
            return noticeCountent;
        }

        public void setNoticeCountent(String noticeCountent) {
            this.noticeCountent = noticeCountent;
        }

        public int getPageView() {
            return pageView;
        }

        public void setPageView(int pageView) {
            this.pageView = pageView;
        }

        public String getHospitalName() {
            return hospitalName;
        }

        public void setHospitalName(String hospitalName) {
            this.hospitalName = hospitalName;
        }

        public String getTitleOfTime() {
            return titleOfTime;
        }

        public void setTitleOfTime(String titleOfTime) {
            this.titleOfTime = titleOfTime;
        }
    }
}
