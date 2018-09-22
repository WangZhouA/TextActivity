package myapplication66.yanglh6.example.com.textactivity.entity;

import java.util.List;

/**
 * Created by 陈姣姣 on 2018/7/25.
 */
public class DoctorPrescribedBean  {


    /**
     * success : true
     * code : 1000
     * message : 查询成功
     * data : [{"balance":0,"weight":0,"height":0,"prescriptionTime":"10/05","prescriptions":[{"frequency":"1","hour":"4","concentration":"1.50"},{"frequency":"2","hour":"3","concentration":"2.50"},{"frequency":"3","hour":"4","concentration":"1.50"},{"frequency":"4","hour":"2","concentration":"4.25"}]}]
     */

    private boolean success;
    private int code;
    private String message;
    /**
     * balance : 0
     * weight : 0
     * height : 0
     * prescriptionTime : 10/05
     * prescriptions : [{"frequency":"1","hour":"4","concentration":"1.50"},{"frequency":"2","hour":"3","concentration":"2.50"},{"frequency":"3","hour":"4","concentration":"1.50"},{"frequency":"4","hour":"2","concentration":"4.25"}]
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
        private int balance;
        private int weight;
        private int height;
        private String prescriptionTime;
        /**
         * frequency : 1
         * hour : 4
         * concentration : 1.50
         */

        private List<PrescriptionsBean> prescriptions;

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

        public String getPrescriptionTime() {
            return prescriptionTime;
        }

        public void setPrescriptionTime(String prescriptionTime) {
            this.prescriptionTime = prescriptionTime;
        }

        public List<PrescriptionsBean> getPrescriptions() {
            return prescriptions;
        }

        public void setPrescriptions(List<PrescriptionsBean> prescriptions) {
            this.prescriptions = prescriptions;
        }

        public static class PrescriptionsBean {
            private String frequency;
            private String hour;
            private String concentration;

            public String getFrequency() {
                return frequency;
            }

            public void setFrequency(String frequency) {
                this.frequency = frequency;
            }

            public String getHour() {
                return hour;
            }

            public void setHour(String hour) {
                this.hour = hour;
            }

            public String getConcentration() {
                return concentration;
            }

            public void setConcentration(String concentration) {
                this.concentration = concentration;
            }
        }
    }
}
