package cn.tedu.springmvc.util;

public class JsonResult<T> {

    private Integer state; // 业务返回码
    private String message; // 消息
    private T data; // 数据

    private JsonResult() { }

    public static JsonResult<Void> ok() {
        return ok(null);
    }

    public static <T> JsonResult<T> ok(T data) {
        JsonResult<T> jsonResult = new JsonResult<>();
        jsonResult.state = State.OK.getValue();
        jsonResult.data = data;
        return jsonResult;
    }

    public static JsonResult<Void> fail(State state, String message) {
        JsonResult<Void> jsonResult = new JsonResult<>();
        jsonResult.state = state.getValue();
        jsonResult.message = message;
        return jsonResult;
    }

   public enum State {
       OK(20000),
       ERR_USERNAME(40400),
       ERR_PASSWORD(40600);

       Integer value;

       State(Integer value) {
           this.value = value;
       }

       public Integer getValue() {
           return value;
       }
   }

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
