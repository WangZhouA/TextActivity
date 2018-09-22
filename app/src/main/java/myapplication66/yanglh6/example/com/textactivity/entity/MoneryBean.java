package myapplication66.yanglh6.example.com.textactivity.entity;

/**
 * Created by 陈姣姣 on 2018/9/20.
 */
public class MoneryBean {


    /**
     * success : true
     * code : 1000
     * message : 查询成功
     * data : {"sixMonths":"500","twelvemonth":"900","countDown":"15","unitPrice":"2000"}
     */

    private boolean success;
    private int code;
    private String message;
    /**
     * sixMonths : 500
     * twelvemonth : 900
     * countDown : 15
     * unitPrice : 2000
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
        private String sixMonths;
        private String twelvemonth;
        private String countDown;
        private String unitPrice;

        public String getSixMonths() {
            return sixMonths;
        }

        public void setSixMonths(String sixMonths) {
            this.sixMonths = sixMonths;
        }

        public String getTwelvemonth() {
            return twelvemonth;
        }

        public void setTwelvemonth(String twelvemonth) {
            this.twelvemonth = twelvemonth;
        }

        public String getCountDown() {
            return countDown;
        }

        public void setCountDown(String countDown) {
            this.countDown = countDown;
        }

        public String getUnitPrice() {
            return unitPrice;
        }

        public void setUnitPrice(String unitPrice) {
            this.unitPrice = unitPrice;
        }
    }
}
