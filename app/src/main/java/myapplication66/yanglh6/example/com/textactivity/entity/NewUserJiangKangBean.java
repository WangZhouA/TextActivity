package myapplication66.yanglh6.example.com.textactivity.entity;

/**
 * Created by 陈姣姣 on 2018/9/5.
 */
public class NewUserJiangKangBean {


    /**
     * success : true
     * code : 1000
     * message : 查询成功
     * data : {"urineVolume":200,"weight":177,"bloodPressureHeight":120,"bloodPressureLow":80,"heartRate":90}
     */

    private boolean success;
    private int code;
    private String message;
    /**
     * urineVolume : 200
     * weight : 177
     * bloodPressureHeight : 120
     * bloodPressureLow : 80
     * heartRate : 90
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
        private int urineVolume;
        private int weight;
        private int bloodPressureHeight;
        private int bloodPressureLow;
        private int heartRate;

        public int getUrineVolume() {
            return urineVolume;
        }

        public void setUrineVolume(int urineVolume) {
            this.urineVolume = urineVolume;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        public int getBloodPressureHeight() {
            return bloodPressureHeight;
        }

        public void setBloodPressureHeight(int bloodPressureHeight) {
            this.bloodPressureHeight = bloodPressureHeight;
        }

        public int getBloodPressureLow() {
            return bloodPressureLow;
        }

        public void setBloodPressureLow(int bloodPressureLow) {
            this.bloodPressureLow = bloodPressureLow;
        }

        public int getHeartRate() {
            return heartRate;
        }

        public void setHeartRate(int heartRate) {
            this.heartRate = heartRate;
        }
    }
}
