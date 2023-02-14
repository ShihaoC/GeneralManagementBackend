package cn.mrcsh.Entity.Factory;

import lombok.Data;

@Data
public class Pages<E> {
    private long page;
    private long count;
    private E limit_data;
}
