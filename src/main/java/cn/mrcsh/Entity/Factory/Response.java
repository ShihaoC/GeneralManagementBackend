package cn.mrcsh.Entity.Factory;

import lombok.Data;

@Data
public class Response<E> {
    private int code;
    private E data;
    private String message;
}
