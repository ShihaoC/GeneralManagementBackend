package cn.mrcsh.Entity.Factory;

public class PagesFactory<E> {
    public Pages<E> getInstance(long page,long count,E limit_data){
        Pages<E> pages = new Pages<>();
        pages.setPage(page);
        pages.setCount(count);
        pages.setLimit_data(limit_data);
        return pages;
    }
}
