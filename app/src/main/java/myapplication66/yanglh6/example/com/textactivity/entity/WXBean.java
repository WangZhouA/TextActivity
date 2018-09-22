package myapplication66.yanglh6.example.com.textactivity.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by 陈姣姣 on 2018/9/4.
 */
public class WXBean {


    /**
     * success : true
     * code : 1000
     * message : 操作成功！
     * data : {"package":"Sign=WXPay","appid":"wxe1b1532d80eb2353","sign":"538A0E46506E2DA1FBEAC8FD25FC4A5EF81AF3B6E19902029A43138C09A372C0","partnerid":"1511755441","prepayid":"wx0516300722441290324a413d0551181556","noncestr":"64eb20d67c444755824b310a43b5e762","timestamp":"1536136204"}
     */

    private boolean success;
    private int code;
    private String message;
    /**
     * package : Sign=WXPay
     * appid : wxe1b1532d80eb2353
     * sign : 538A0E46506E2DA1FBEAC8FD25FC4A5EF81AF3B6E19902029A43138C09A372C0
     * partnerid : 1511755441
     * prepayid : wx0516300722441290324a413d0551181556
     * noncestr : 64eb20d67c444755824b310a43b5e762
     * timestamp : 1536136204
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
        @SerializedName("package")
        private String packageX;
        private String appid;
        private String sign;
        private String partnerid;
        private String prepayid;
        private String noncestr;
        private String timestamp;

        public String getPackageX() {
            return packageX;
        }

        public void setPackageX(String packageX) {
            this.packageX = packageX;
        }

        public String getAppid() {
            return appid;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }

        public String getPartnerid() {
            return partnerid;
        }

        public void setPartnerid(String partnerid) {
            this.partnerid = partnerid;
        }

        public String getPrepayid() {
            return prepayid;
        }

        public void setPrepayid(String prepayid) {
            this.prepayid = prepayid;
        }

        public String getNoncestr() {
            return noncestr;
        }

        public void setNoncestr(String noncestr) {
            this.noncestr = noncestr;
        }

        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }
    }
}
