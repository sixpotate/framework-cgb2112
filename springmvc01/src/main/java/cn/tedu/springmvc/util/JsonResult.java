package cn.tedu.springmvc.util;

public class JsonResult<T> {

    private Integer state; // 业务返回码
    private String message; // 消息
    private T data; // 数据

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
