package myapplication66.yanglh6.example.com.textactivity.entity;

import java.util.List;

/**
 * Created by 陈姣姣 on 2018/7/10.
 */
public class BullinXiangQingBean {


    /**
     * success : true
     * code : 1000
     * message : 查询成功
     * data : [{"id":5,"noticeTitle":"标题一","imgUrl":"http://localhost/imgs/苹果.png","noticeCountent":"洗洗洗洗洗洗洗洗手","ddId":10,"pageView":23,"hospitalName":"中华人民医院","titleOfTime":"10:20"},{"id":7,"noticeTitle":"标题三","imgUrl":"http://localhost/imgs/苹果.png","noticeCountent":"标题15665","ddId":10,"pageView":11,"hospitalName":"宝安人民医院","titleOfTime":"17:12"}]
     */

    private boolean success;
    private int code;
    private String message;
    /**
     * id : 5
     * noticeTitle : 标题一
     * imgUrl : http://localhost/imgs/苹果.png
     * noticeCountent : 洗洗洗洗洗洗洗洗手
     * ddId : 10
     * pageView : 23
     * hospitalName : 中华人民医院
     * titleOfTime : 10:20
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
        private int id;
        private String noticeTitle;
        private String imgUrl;
        private String noticeCountent;
        private int ddId;
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

        public int getDdId() {
            return ddId;
        }

        public void setDdId(int ddId) {
            this.ddId = ddId;
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
