package myapplication66.yanglh6.example.com.textactivity.entity;

/**
 * Created by 陈姣姣 on 2018/6/29.
 */
public class BillingDetailsBean {


    /**
     * success : true
     * code : 1000
     * message : 查询成功
     * data : {"balance":0,"weight":0,"height":0,"outtradeNo":"20180725112949323","tradeno":"2018072521001004010500176896","paymentMethod":"支付宝","subject":"华康智信","amount":"0.01","expiryTime":"2018-07-25 11:30"}
     */

    private boolean success;
    private int code;
    private String message;
    /**
     * balance : 0
     * weight : 0
     * height : 0
     * outtradeNo : 20180725112949323
     * tradeno : 2018072521001004010500176896
     * paymentMethod : 支付宝
     * subject : 华康智信
     * amount : 0.01
     * expiryTime : 2018-07-25 11:30
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
        private int balance;
        private int weight;
        private int height;
        private String outtradeNo;
        private String tradeno;
        private String paymentMethod;
        private String subject;
        private String amount;
        private String expiryTime;

        public int getBalance() {
            return balance;
        }

        public void setBalance(int balance) {
            this.balance = balance;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public String getOuttradeNo() {
            return outtradeNo;
        }

        public void setOuttradeNo(String outtradeNo) {
            this.outtradeNo = outtradeNo;
        }

        public String getTradeno() {
            return tradeno;
        }

        public void setTradeno(String tradeno) {
            this.tradeno = tradeno;
        }

        public String getPaymentMethod() {
            return paymentMethod;
        }

        public void setPaymentMethod(String paymentMethod) {
            this.paymentMethod = paymentMethod;
        }

        public String getSubject() {
            return subject;
        }

        public void setSubject(String subject) {
            this.subject = subject;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getExpiryTime() {
            return expiryTime;
        }

        public void setExpiryTime(String expiryTime) {
            this.expiryTime = expiryTime;
        }
    }
}
