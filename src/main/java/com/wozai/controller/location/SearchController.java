package com.wozai.controller.location;

import com.wozai.service.LoctionManagerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 * User: wyzengzihao
 * Date: 13-12-25
 * Time: 上午10:56
 * To change this template use File | Settings | File Templates.
 */
@RequestMapping("/location")
@Controller
public class SearchController {
//    @Resource
//    private LoctionManagerService loctionManagerService;
//    @RequestMapping(value = "/search.htm",method = RequestMethod.GET)
//    public String checkUserAccount(String lng,String lat,String condition,String startIdx,String endIdx,Model model,HttpServletRequest request, HttpServletResponse response) {
//        model.addAttribute("jsonDTO",loctionManagerService.getClassroom(lng,lat,condition,startIdx,endIdx).toJson());
//        return "json";
//    }
}
