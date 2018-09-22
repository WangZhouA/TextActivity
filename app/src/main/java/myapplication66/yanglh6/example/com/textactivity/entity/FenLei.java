package myapplication66.yanglh6.example.com.textactivity.entity;

import java.util.List;

/**
 * Created by 陈姣姣 on 2018/6/28.
 */
public class FenLei {
    /**
     * success : true
     * code : 1000
     * message : 查询成功
     * data : [{"id":1,"name":"分类一"},{"id":3,"name":"分类二"},{"id":4,"name":"分类三"},{"id":5,"name":"分类四"},{"id":6,"name":"分类五"},{"id":7,"name":"分类六"},{"id":10,"name":"分类分类"}]
     */

    private boolean success;
    private int code;
    private String message;
    /**
     * id : 1
     * name : 分类一
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
        private String name;

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
    }
}
