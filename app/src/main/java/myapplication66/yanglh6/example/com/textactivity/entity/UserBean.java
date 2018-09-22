package myapplication66.yanglh6.example.com.textactivity.entity;

/**
 * Created by 陈姣姣 on 2018/7/16.
 */
public class UserBean {


    /**
     * success : true
     * code : 1000
     * message : 查询成功
     * data : {"id":20,"name":"哈哈","sex":"1","age":25,"address":"湖南省","balance":5400,"weight":1,"height":1,"primaryDisease":"肾虚","telephone":"123456","telephone2":"1234567","account":"421023199309127590","headUrl":"fileUpload/peritoneal/86611ca6-bdc3-49e0-a342-d9f9976f8e9f.jpg","mac":"ABC12345679A","rqname":"大帅哥","status":"1"}
     */

    private boolean success;
    private int code;
    private String message;
    /**
     * id : 20
     * name : 哈哈
     * sex : 1
     * age : 25
     * address : 湖南省
     * balance : 5400
     * weight : 1
     * height : 1
     * primaryDisease : 肾虚
     * telephone : 123456
     * telephone2 : 1234567
     * account : 421023199309127590
     * headUrl : fileUpload/peritoneal/86611ca6-bdc3-49e0-a342-d9f9976f8e9f.jpg
     * mac : ABC12345679A
     * rqname : 大帅哥
     * status : 1
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
        private String name;
        private String sex;
        private String age;
        private String address;
        private String balance;
        private String weight;
        private String height;
        private String primaryDisease;
        private String telephone;
        private String telephone2;
        private String account;
        private String headUrl;
        private String mac;
        private String rqname;
        private String status;
        private String online;
        private String hospitalId;
        private String expiryTime;

        public String getExpiryTime() {
            return expiryTime;
        }

        public void setExpiryTime(String expiryTime) {
            this.expiryTime = expiryTime;
        }

        public String getHospitalId() {
            return hospitalId;
        }

        public void setHospitalId(String hospitalId) {
            this.hospitalId = hospitalId;
        }

        public String getOnline() {
            return online;
        }

        public void setOnline(String online) {
            this.online = online;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getBalance() {
            return balance;
        }

        public void setBalance(String balance) {
            this.balance = balance;
        }

        public String getWeight() {
            return weight;
        }

        public void setWeight(String weight) {
            this.weight = weight;
        }

        public String getHeight() {
            return height;
        }

        public void setHeight(String height) {
            this.height = height;
        }

        public String getPrimaryDisease() {
            return primaryDisease;
        }

        public void setPrimaryDisease(String primaryDisease) {
            this.primaryDisease = primaryDisease;
        }

        public String getTelephone() {
            return telephone;
        }

        public void setTelephone(String telephone) {
            this.telephone = telephone;
        }

        public String getTelephone2() {
            return telephone2;
        }

        public void setTelephone2(String telephone2) {
            this.telephone2 = telephone2;
        }

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
        }

        public String getHeadUrl() {
            return headUrl;
        }

        public void setHeadUrl(String headUrl) {
            this.headUrl = headUrl;
        }

        public String getMac() {
            return mac;
        }

        public void setMac(String mac) {
            this.mac = mac;
        }

        public String getRqname() {
            return rqname;
        }

        public void setRqname(String rqname) {
            this.rqname = rqname;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
