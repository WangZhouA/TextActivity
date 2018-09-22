package myapplication66.yanglh6.example.com.textactivity.entity;

import java.util.List;

/**
 * Created by 陈姣姣 on 2018/6/29.
 */
public class OrderDetailsBean {


    /**
     * success : true
     * code : 1000
     * message : 查询成功
     * data : [{"id":19,"balance":0,"weight":0,"height":0,"serialNumber":"2018072421001004410578508246","amount":"0.01","type":"支付宝","expiryTime":"2018-07-24"},{"id":20,"balance":0,"weight":0,"height":0,"serialNumber":"2018072421001004410576314662","amount":"0.01","type":"支付宝","expiryTime":"2018-07-24"},{"id":21,"balance":0,"weight":0,"height":0,"serialNumber":"2018072421001004410577744600","amount":"0.01","type":"支付宝","expiryTime":"2018-07-24"},{"id":22,"balance":0,"weight":0,"height":0,"serialNumber":"2018072421001004410578261419","amount":"0.01","type":"支付宝","expiryTime":"2018-07-24"},{"id":23,"balance":0,"weight":0,"height":0,"serialNumber":"2018072521001004600555217289","amount":"0.01","type":"支付宝","expiryTime":"2018-07-25"}]
     */

    private boolean success;
    private int code;
    private String message;
    /**
     * id : 19
     * balance : 0
     * weight : 0
     * height : 0
     * serialNumber : 2018072421001004410578508246
     * amount : 0.01
     * type : 支付宝
     * expiryTime : 2018-07-24
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
        private int balance;
        private int weight;
        private int height;
        private String serialNumber;
        private String amount;
        private String type;
        private String expiryTime;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

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

        public String getSerialNumber() {
            return serialNumber;
        }

        public void setSerialNumber(String serialNumber) {
            this.serialNumber = serialNumber;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getExpiryTime() {
            return expiryTime;
        }

        public void setExpiryTime(String expiryTime) {
            this.expiryTime = expiryTime;
        }
    }
}
