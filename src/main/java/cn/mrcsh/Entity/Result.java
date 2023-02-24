package cn.mrcsh.Entity;

import lombok.Data;

@Data
public class Result {
    private int code;
    private String msg;
    private Object data;

    public static Result success(Object data){
        Result result = new Result();
        result.setCode(200);
        result.setMsg("操作成功");
        result.setData(data);
        return result;
    }

    public static Result fail(String msg){
        Result result = new Result();
        result.setCode(400);
        result.setMsg(msg);
        result.setData(null);
        return result;
    }

    public static Result fail(int code,String msg){
        Result result = new Result();
        result.setData(null);
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }
    public static Result fail(int code,String msg,Object data){
        Result result = new Result();
        result.setData(null);
        result.setCode(code);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }
}
