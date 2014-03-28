package com.wozai.DTO;

/**
 * Created with IntelliJ IDEA.
 * User: wyzengzihao
 * Date: 13-12-19
 * Time: 下午1:36
 * To change this template use File | Settings | File Templates.
 */
public interface JsonDTO {
    public String toJson();
    public Object createFromJson(String ajax);
}
