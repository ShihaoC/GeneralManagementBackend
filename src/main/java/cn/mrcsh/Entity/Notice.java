package cn.mrcsh.Entity;

import lombok.Data;

@Data
public class Notice {
    private int id;
    private String context;
    private Long Timeout;
    private String type;
}
