package cn.mrcsh.Entity.Factory;

public class ResponseFactory<E> {
    public Response<E> getInstance(E e,String message,int code){
        Response<E> response = new Response<>();
        response.setCode(code);
        response.setData(e);
        response.setMessage(message);
        return response;
    }
}
