package com.wozai.controller;

import com.wozai.common.BaseAjaxController;
import com.wozai.common.FileDownloadConfig;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * Created by zengzihao on 2014/4/12.
 */
@Controller
@RequestMapping("/appweb")
public class AppWebController extends BaseAjaxController{
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(AndroidController.class);

    @Resource
    private FileDownloadConfig fileDownloadConfig;

    @RequestMapping(value = "/download.htm")
    public void downloadFile(Long locId,HttpServletResponse response,HttpServletRequest request){
        logger.info("[用户下载]：locId={}",locId);
        response.setCharacterEncoding("utf-8");
        response.setContentType("multipart/form-data");
        response.setHeader("Content-Disposition", "attachment;fileName="+fileDownloadConfig.getFileName());
        try {
            File file=new File(fileDownloadConfig.getPath());
            response.setHeader("Content-Length",file.length()+"");
            InputStream inputStream=new FileInputStream(file);
            OutputStream os=response.getOutputStream();
            byte[] b=new byte[4096];
            int length;
            while((length=inputStream.read(b))>0){
                os.write(b,0,length);
                os.flush();
            }
            inputStream.close();
           } catch (Exception e) {
        }
    }
}
