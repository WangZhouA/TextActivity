package myapplication66.yanglh6.example.com.textactivity.entity;

import java.util.List;

/**
 * Created by 陈姣姣 on 2018/7/11.
 */
public class TestSummaryBean {


    /**
     * success : true
     * code : 1000
     * message : 查询成功
     * data : [{"id":7,"question":"1111.0","answerA":"A","answerB":"B","answerC":"C","answerD":"D","realAnswer":"B"},{"id":8,"question":"2222.0","answerA":"A","answerB":"B","answerC":"C","answerD":"D","realAnswer":"D"}]
     */

    private boolean success;
    private int code;
    private String message;
    /**
     * id : 7
     * question : 1111.0
     * answerA : A
     * answerB : B
     * answerC : C
     * answerD : D
     * realAnswer : B
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
        private String question;
        private String answerA;
        private String answerB;
        private String answerC;
        private String answerD;
        private String realAnswer;
        private String isCheck;


        public String getIsCheck() {
            return isCheck;
        }
        public void setCheck(String check) {
            isCheck = check;
        }
        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getQuestion() {
            return question;
        }

        public void setQuestion(String question) {
            this.question = question;
        }

        public String getAnswerA() {
            return answerA;
        }

        public void setAnswerA(String answerA) {
            this.answerA = answerA;
        }

        public String getAnswerB() {
            return answerB;
        }

        public void setAnswerB(String answerB) {
            this.answerB = answerB;
        }

        public String getAnswerC() {
            return answerC;
        }

        public void setAnswerC(String answerC) {
            this.answerC = answerC;
        }

        public String getAnswerD() {
            return answerD;
        }

        public void setAnswerD(String answerD) {
            this.answerD = answerD;
        }

        public String getRealAnswer() {
            return realAnswer;
        }

        public void setRealAnswer(String realAnswer) {
            this.realAnswer = realAnswer;
        }
    }
}
