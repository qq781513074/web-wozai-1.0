package com.wozai.controller.classroom;

import com.wozai.common.BaseAjaxController;
import com.wozai.common.utils.StringUtils;
import com.wozai.controller.classroom.DTO.ClassroomInfoParamDTO;
import com.wozai.controller.classroom.DTO.ClassroomInfoResultDTO;
import com.wozai.dao.SuperDao;
import com.wozai.service.ClassroomManagerService;
import org.apache.log4j.Logger;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by zengzihao on 14-3-17.
 */
@RequestMapping("/web/classroom")
@Controller
public class ClassroomController extends BaseAjaxController {

    private static final Logger logger = Logger.getLogger("com.wozai.controller.classroom.ClassroomController");
    @Resource
    ClassroomManagerService classroomManagerService;

    @RequestMapping(value = "/search.htm",method = RequestMethod.GET)
    public String getClassroomPage(HttpServletRequest request, HttpServletResponse response){
        super.printOptInfo(logger,request);
        return "classroom/classroomSearch";
    }

    @RequestMapping(value = "/search.htm" ,method = RequestMethod.POST)
    public String getClassroom(Model model,HttpServletRequest request, HttpServletResponse response){
       super.printOptInfo(logger,request);
       ClassroomInfoParamDTO paramDTO = new ClassroomInfoParamDTO();
       super.bindRequestParam(request,paramDTO);
       try {
           List<ClassroomInfoResultDTO> list  = classroomManagerService.getClassroomInfo(paramDTO);
           if (list == null){
               model.addAttribute("errorMsg","服务器异常查询失败");
           }else{
               model.addAttribute("classList",list);
           }
       }catch (Exception e){
           logger.info("[web]查询教室调用服务器异常",e);
           model.addAttribute("errorMsg","服务器异常查询失败");
       }
        return "classroom/classroomSearch";
    }
    @RequestMapping(value ="/update.htm" ,method = RequestMethod.GET)
    public String getUpdatePage(Model model,HttpServletRequest request, HttpServletResponse response){
        super.printOptInfo(logger,request);
        ClassroomInfoParamDTO paramDTO = new ClassroomInfoParamDTO();
        super.bindRequestParam(request,paramDTO);
        //没有查询条件直接返回视图
        if (paramDTO.getClassroom_name() == null|| paramDTO.getClassroom_name().trim().equals("")){
            return "classroom/classroomUpdate";
        }
        try {
            List<ClassroomInfoResultDTO> list  = classroomManagerService.getClassroomInfo(paramDTO);
            if (list == null){
                model.addAttribute("errorMsg","服务器异常查询失败");
            }else{
                model.addAttribute("classList",list);
            }
        }catch (Exception e){
            logger.info("[web]查询教室调用服务器异常",e);
            model.addAttribute("errorMsg","服务器异常查询失败");
        }
        return "classroom/classroomUpdate";
    }

    @RequestMapping(value ="/update.htm" ,method = RequestMethod.POST)
    public String doUpdate(Model model,HttpServletRequest request, HttpServletResponse response,String class_val){
        super.printOptInfo(logger,request);
        ClassroomInfoParamDTO paramDTO = new ClassroomInfoParamDTO();
        super.bindRequestParam(request,paramDTO);
        String view = (paramDTO.getDel_flag() != null) ? "classroom/classroomDelete" : "classroom/classroomUpdate";
        //保留星期8 用来更新全部字段
        if (paramDTO.getClassroom_name() == null ||
                paramDTO.getClassroom_name().trim().equals("") ||
                paramDTO.getClass_date() == null ||
                paramDTO.getClass_date() <= 0 ||
                paramDTO.getClass_date() > 9){
            model.addAttribute("errorMsg","参数错误,至少填入教室信息和星期");
            return view;
        }
        setClassVal(class_val,paramDTO);
        try {
            Boolean result = classroomManagerService.updateClassroom(paramDTO);
            if (Boolean.TRUE.equals(result)){
                model.addAttribute("infoMsg","更改教室信息成功!");
            }else {
                model.addAttribute("errorMsg","更改教室信息操作失败！");
            }
        }catch (Exception e){
            logger.info("[web]更新教室信息调用服务器异常",e);
            model.addAttribute("errorMsg","更改教室信息操作失败");
        }
        return view;
    }

    @RequestMapping(value ="/delete.htm" ,method = RequestMethod.GET)
    public String getDeletePage(Model model,HttpServletRequest request, HttpServletResponse response){
        super.printOptInfo(logger,request);
        ClassroomInfoParamDTO paramDTO = new ClassroomInfoParamDTO();
        super.bindRequestParam(request,paramDTO);
        try {
            List<ClassroomInfoResultDTO> list  = classroomManagerService.getClassroomInfo(paramDTO);
            if (list == null){
                model.addAttribute("errorMsg","服务器异常查询失败");
            }else{
                model.addAttribute("classList",list);
            }
        }catch (Exception e){
            logger.info("[web]查询教室调用服务器异常",e);
            model.addAttribute("errorMsg","服务器异常查询失败");
        }
        return "classroom/classroomDelete";
    }

    @RequestMapping(value ="/delete.htm" ,method = RequestMethod.POST)
    public String doRealDelete(Model model,HttpServletRequest request, HttpServletResponse response,String class_val){
        //调用系统操作日志
        super.printOptInfo(logger,request);
        //绑定参数
        ClassroomInfoParamDTO paramDTO = new ClassroomInfoParamDTO();
        super.bindRequestParam(request,paramDTO);
        //校验参数 插入教室 8 全部插入
        //必填字段 星期 [1,8] 教室名称 class01值 地点id
        if(paramDTO.getClass_date() == null ||
                paramDTO.getClass_date() <= 0 ||
                paramDTO.getClass_date()>8 ||
                StringUtils.isNull(paramDTO.getClassroom_name())){
            model.addAttribute("errorMsg","参数错误,至少填入教室信息和星期");
            return "classroom/classroomDelete";
        }
        //trycatch 块 调用service的insert
        //判断并设置model
        try {
            //todo 物理操作
            Boolean result = classroomManagerService.deleteClassroom(paramDTO);
            if (Boolean.TRUE.equals(result)){
                model.addAttribute("infoMsg","删除教室信息成功!");
            }else {
                model.addAttribute("errorMsg","删除教室信息操作失败！");
            }
        }catch (Exception e){
            logger.info("[web]插入教室信息调用服务器异常",e);
            model.addAttribute("errorMsg","插入教室信息操作失败");
        }
        return "classroom/classroomDelete";
    }


    @RequestMapping(value ="/insert.htm" ,method = RequestMethod.GET)
    public String getInsertPage(Model model,HttpServletRequest request, HttpServletResponse response){
        super.printOptInfo(logger,request);
        ClassroomInfoParamDTO paramDTO = new ClassroomInfoParamDTO();
        super.bindRequestParam(request,paramDTO);
        //没有查询条件直接返回视图
        if (paramDTO.getClassroom_name() == null|| paramDTO.getClassroom_name().trim().equals("")){
            return "classroom/classroomInsert";
        }
        try {
            List<ClassroomInfoResultDTO> list  = classroomManagerService.getClassroomInfo(paramDTO);
            if (list == null){
                model.addAttribute("errorMsg","服务器异常查询失败");
            }else{
                model.addAttribute("classList",list);
            }
        }catch (Exception e){
            logger.info("[web]查询教室调用服务器异常",e);
            model.addAttribute("errorMsg","服务器异常查询失败");
        }
        return "classroom/classroomInsert";
    }
    @RequestMapping(value ="/insert.htm" ,method = RequestMethod.POST)
    public String doInsert(Model model,HttpServletRequest request, HttpServletResponse response,String class_val){
        //调用系统操作日志
        super.printOptInfo(logger,request);
        //绑定参数
        ClassroomInfoParamDTO paramDTO = new ClassroomInfoParamDTO();
        super.bindRequestParam(request,paramDTO);
        //校验参数 插入教室 8 全部插入
        //必填字段 星期 [1,8] 教室名称 class01值 地点id
        if(paramDTO.getClass_date() == null ||
                paramDTO.getClass_date() <= 0 ||
                paramDTO.getClass_date()>8 ||
                StringUtils.isNull(paramDTO.getClassroom_name(),class_val,paramDTO.getLoc_id())){
            model.addAttribute("errorMsg","参数错误,至少填入教室信息和星期,地点以及class01值");
            return "classroom/classroomInsert";
        }
        setClassVal(class_val,paramDTO);
        //trycatch 块 调用service的insert
        //判断并设置model
        try {
            Boolean result = classroomManagerService.insertClassroom(paramDTO);
            if (Boolean.TRUE.equals(result)){
                model.addAttribute("infoMsg","插入教室信息成功!");
            }else {
                model.addAttribute("errorMsg","插入教室信息操作失败！");
            }
        }catch (Exception e){
            logger.info("[web]插入教室信息调用服务器异常",e);
            model.addAttribute("errorMsg","插入教室信息操作失败");
        }
        return "classroom/classroomInsert";
    }




    private void setClassVal(String class_val,ClassroomInfoParamDTO paramDTO){
        if(class_val != null && !class_val.trim().equals("")){
            paramDTO.setClass1(Integer.valueOf(class_val.trim().charAt(0)));
            paramDTO.setClass2(Integer.valueOf(class_val.trim().charAt(1)));
            paramDTO.setClass3(Integer.valueOf(class_val.trim().charAt(2)));
            paramDTO.setClass4(Integer.valueOf(class_val.trim().charAt(3)));
            paramDTO.setClass5(Integer.valueOf(class_val.trim().charAt(4)));
            paramDTO.setClass6(Integer.valueOf(class_val.trim().charAt(5)));
            paramDTO.setClass7(Integer.valueOf(class_val.trim().charAt(6)));
            paramDTO.setClass8(Integer.valueOf(class_val.trim().charAt(7)));
            paramDTO.setClass9(Integer.valueOf(class_val.trim().charAt(8)));
            paramDTO.setClass10(Integer.valueOf(class_val.trim().charAt(9)));
            paramDTO.setClass11(Integer.valueOf(class_val.trim().charAt(10)));
            paramDTO.setClass12(Integer.valueOf(class_val.trim().charAt(11)));
        }
    }
}
