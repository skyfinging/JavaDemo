package demo.util;

public enum ResponseCode {
    ILLEGAL_ARGUMENT("参数错误"),
    REPETITIVE_OPERATION("重复操作");

    private String msg;
    ResponseCode(String msg){
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
