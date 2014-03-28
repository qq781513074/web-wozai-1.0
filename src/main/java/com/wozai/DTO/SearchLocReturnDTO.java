package com.wozai.DTO;

import com.google.gson.Gson;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: wyzengzihao
 * Date: 14-1-10
 * Time: 下午1:19
 * To change this template use File | Settings | File Templates.
 */
public class SearchLocReturnDTO implements JsonDTO {
    private Gson gson = new Gson();
    private List<Classroom> list;

    public List<Classroom> getList() {
        return list;
    }

    public void setList(List<Classroom> list) {
        this.list = list;
    }

    public String toJson(){
        return gson.toJson(this,SearchLocReturnDTO.class);
    }

    public SearchLocReturnDTO createFromJson(String ajax){
        return gson.fromJson(ajax,SearchLocReturnDTO.class);
    }

}
