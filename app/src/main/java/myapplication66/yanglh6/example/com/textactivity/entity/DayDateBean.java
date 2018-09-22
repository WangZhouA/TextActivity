package myapplication66.yanglh6.example.com.textactivity.entity;

import java.util.List;

/**
 * Created by 陈姣姣 on 2018/7/24.
 */
public class DayDateBean {


    /**
     * success : true
     * code : 1000
     * message : 查询成功
     * data : {"balance":0,"weight":0,"height":0,"dryWeight":1,"equDatas":[{"fluid":701,"urineVolume":90,"weight":150,"bloodPressureHeight":50,"bloodPressureLow":555,"heartRate":555,"createtime":"2018-08-09 00:00","volumeDate":"2018-08-09 23:45","weightDate":"2018-08-09 16:18","bloodHeartDate":"2018-08-09 16:18","week":"星期四"}]}
     */

    private boolean success;
    private int code;
    private String message;
    /**
     * balance : 0
     * weight : 0
     * height : 0
     * dryWeight : 1
     * equDatas : [{"fluid":701,"urineVolume":90,"weight":150,"bloodPressureHeight":50,"bloodPressureLow":555,"heartRate":555,"createtime":"2018-08-09 00:00","volumeDate":"2018-08-09 23:45","weightDate":"2018-08-09 16:18","bloodHeartDate":"2018-08-09 16:18","week":"星期四"}]
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
        private int dryWeight;
        /**
         * fluid : 701
         * urineVolume : 90
         * weight : 150
         * bloodPressureHeight : 50
         * bloodPressureLow : 555
         * heartRate : 555
         * createtime : 2018-08-09 00:00
         * volumeDate : 2018-08-09 23:45
         * weightDate : 2018-08-09 16:18
         * bloodHeartDate : 2018-08-09 16:18
         * week : 星期四
         */

        private List<EquDatasBean> equDatas;

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

        public int getDryWeight() {
            return dryWeight;
        }

        public void setDryWeight(int dryWeight) {
            this.dryWeight = dryWeight;
        }

        public List<EquDatasBean> getEquDatas() {
            return equDatas;
        }

        public void setEquDatas(List<EquDatasBean> equDatas) {
            this.equDatas = equDatas;
        }

        public static class EquDatasBean {
            private int fluid;
            private int urineVolume;
            private int weight;
            private int bloodPressureHeight;
            private int bloodPressureLow;
            private int heartRate;
            private String createtime;
            private String volumeDate;
            private String weightDate;
            private String bloodHeartDate;
            private String week;

            public int getFluid() {
                return fluid;
            }

            public void setFluid(int fluid) {
                this.fluid = fluid;
            }

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

            public String getCreatetime() {
                return createtime;
            }

            public void setCreatetime(String createtime) {
                this.createtime = createtime;
            }

            public String getVolumeDate() {
                return volumeDate;
            }

            public void setVolumeDate(String volumeDate) {
                this.volumeDate = volumeDate;
            }

            public String getWeightDate() {
                return weightDate;
            }

            public void setWeightDate(String weightDate) {
                this.weightDate = weightDate;
            }

            public String getBloodHeartDate() {
                return bloodHeartDate;
            }

            public void setBloodHeartDate(String bloodHeartDate) {
                this.bloodHeartDate = bloodHeartDate;
            }

            public String getWeek() {
                return week;
            }

            public void setWeek(String week) {
                this.week = week;
            }
        }
    }
}
