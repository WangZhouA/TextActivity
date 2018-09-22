package myapplication66.yanglh6.example.com.textactivity.entity;

/**
 * Created by 陈姣姣 on 2018/7/19.
 */
public class DerviceBean {


    /**
     * success : true
     * code : 1000
     * message : 查询成功
     * data : {"id":7,"mac":"ABC12345679A","online":1,"rqname":"超级无敌腹透机"}
     */

    private boolean success;
    private int code;
    private String message;
    /**
     * id : 7
     * mac : ABC12345679A
     * online : 1
     * rqname : 超级无敌腹透机
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
        private String mac;
        private int online;
        private String rqname;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getMac() {
            return mac;
        }

        public void setMac(String mac) {
            this.mac = mac;
        }

        public int getOnline() {
            return online;
        }

        public void setOnline(int online) {
            this.online = online;
        }

        public String getRqname() {
            return rqname;
        }

        public void setRqname(String rqname) {
            this.rqname = rqname;
        }
    }
}
