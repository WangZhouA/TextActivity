package myapplication66.yanglh6.example.com.textactivity.entity;

import java.util.List;

/**
 * Created by 陈姣姣 on 2018/9/4.
 */
public class MsgBean {


    /**
     * success : true
     * code : 1000
     * message : 查询成功
     * data : [{"id":3,"notificationContent":"快来复诊","creationTime":"2018/08/29/10:21"}]
     */

    private boolean success;
    private int code;
    private String message;
    /**
     * id : 3
     * notificationContent : 快来复诊
     * creationTime : 2018/08/29/10:21
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
        private String notificationContent;
        private String creationTime;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getNotificationContent() {
            return notificationContent;
        }

        public void setNotificationContent(String notificationContent) {
            this.notificationContent = notificationContent;
        }

        public String getCreationTime() {
            return creationTime;
        }

        public void setCreationTime(String creationTime) {
            this.creationTime = creationTime;
        }
    }
}
