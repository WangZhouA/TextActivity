package myapplication66.yanglh6.example.com.textactivity.entity;

/**
 * Created by 陈姣姣 on 2018/6/28.
 */
public class LoginBean {


    /**
     * success : true
     * code : 1000
     * message : 登录成功！
     * data : {"id":20,"name":"哈哈","idcard":"421023199309127590","sex":"1","age":25,"balance":0,"weight":0,"height":0,"token":"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHQiOjE1MzAyMzY1MDYwMDAsImlkIjoyMCwiaWF0IjoxNTMwMTUwMTA2MTUyfQ.MMjZfdUcL9f3o4Efl3KXGq8RN3DIENqxWfld23_aB_8"}
     */

    private boolean success;
    private int code;
    private String message;
    /**
     * id : 20
     * name : 哈哈
     * idcard : 421023199309127590
     * sex : 1
     * age : 25
     * balance : 0
     * weight : 0
     * height : 0
     * token : eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHQiOjE1MzAyMzY1MDYwMDAsImlkIjoyMCwiaWF0IjoxNTMwMTUwMTA2MTUyfQ.MMjZfdUcL9f3o4Efl3KXGq8RN3DIENqxWfld23_aB_8
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
        private String idcard;
        private String sex;
        private int age;
        private int balance;
        private int weight;
        private int height;
        private String token;

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

        public String getIdcard() {
            return idcard;
        }

        public void setIdcard(String idcard) {
            this.idcard = idcard;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
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

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
