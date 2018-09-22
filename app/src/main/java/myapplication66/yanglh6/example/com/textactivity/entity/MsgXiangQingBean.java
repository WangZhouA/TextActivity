package myapplication66.yanglh6.example.com.textactivity.entity;

/**
 * Created by 陈姣姣 on 2018/9/5.
 */
public class MsgXiangQingBean {


    /**
     * success : true
     * code : 1000
     * message : 查询成功
     * data : {"hospitalName":"宝安人民医院","remindingTime":"2018年08月16号","messageContent":"复诊","creationTime":"2018/08/29/10:21"}
     */

    private boolean success;
    private int code;
    private String message;
    /**
     * hospitalName : 宝安人民医院
     * remindingTime : 2018年08月16号
     * messageContent : 复诊
     * creationTime : 2018/08/29/10:21
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
        private String hospitalName;
        private String remindingTime;
        private String messageContent;
        private String creationTime;

        public String getHospitalName() {
            return hospitalName;
        }

        public void setHospitalName(String hospitalName) {
            this.hospitalName = hospitalName;
        }

        public String getRemindingTime() {
            return remindingTime;
        }

        public void setRemindingTime(String remindingTime) {
            this.remindingTime = remindingTime;
        }

        public String getMessageContent() {
            return messageContent;
        }

        public void setMessageContent(String messageContent) {
            this.messageContent = messageContent;
        }

        public String getCreationTime() {
            return creationTime;
        }

        public void setCreationTime(String creationTime) {
            this.creationTime = creationTime;
        }
    }
}
