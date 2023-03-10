package cn.mrcsh.Entity;

import lombok.Data;

@Data
public class Menu {
    private int id;
    private int level;
    private String name;
    private String value;
    private String path;
    private boolean enable;
    private String icon;
}
