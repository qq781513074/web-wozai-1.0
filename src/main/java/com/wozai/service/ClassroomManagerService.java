package com.wozai.service;

import com.wozai.controller.classroom.DTO.ClassroomInfoParamDTO;
import com.wozai.controller.classroom.DTO.ClassroomInfoResultDTO;
import com.wozai.dao.SuperDao;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zengzihao on 14-3-19.
 */
@Service
public class ClassroomManagerService {
    private static final Logger logger = Logger.getLogger("com.wozai.service.ClassroomManagerService");
    @Resource
    SuperDao superDao;

    /**
     * 跟移动端接口不同 这里查询出所有数据包括已删和 未删 默认查询出未删结果
     *
     * @param paramDTO
     * @return
     */
    public List<ClassroomInfoResultDTO> getClassroomInfo(ClassroomInfoParamDTO paramDTO){
       logger.info("[web] classroomInfo begin query param"+paramDTO);
       List<ClassroomInfoResultDTO> list =  superDao.getList("ClassroomMapper.getList",paramDTO);
       if (list != null){
           return list;
       }
        return new ArrayList<ClassroomInfoResultDTO>();
    }

    /**
     * 普通的更新操作 以及删除数据的恢复
     * @param paramDTO
     * @return
     */
    public Boolean updateClassroom(ClassroomInfoParamDTO paramDTO){
       logger.info("[web] classroomInfo begin update param"+paramDTO);
        //等于8就做1-7的迭代 如果不是就更新一条字段
        if (paramDTO.getClass_date() == 8){
            for(int j = 1 ;j < 8 ;j++){
                paramDTO.setClass_date(j);
                Integer i   =  superDao.update("ClassroomMapper.updateInfo",paramDTO);
                if (i == null || i<=0){
                    return Boolean.FALSE;
                }
            }
        }else {
            Integer i   =  superDao.update("ClassroomMapper.updateInfo",paramDTO);
            if (i == null || i<=0){
                return Boolean.FALSE;
            }
        }
        return Boolean.TRUE;
    }

    /**
     * 做真的删除操作 物理删除
     * @param paramDTO
     * @return
     */
    public Boolean deleteClassroom(ClassroomInfoParamDTO paramDTO){
        logger.info("[web] classroomInfo begin delete param"+paramDTO);
        if (paramDTO.getClass_date() == 8){
            for(int j = 1 ;j < 8 ;j++){
                paramDTO.setClass_date(j);
                Integer i =  superDao.delete("ClassroomMapper.doRealdelete", paramDTO);
                if (i == null || i<=0){
                    return Boolean.FALSE;
                }
            }
        }else {
            Integer i   =  superDao.update("ClassroomMapper.doRealdelete",paramDTO);
            if (i == null || i<=0){
                return Boolean.FALSE;
            }
        }
        return Boolean.TRUE;
    }

    /**
     * 普通的插入数据将接口
     * @param paramDTO
     * @return
     */
    public Boolean insertClassroom(ClassroomInfoParamDTO paramDTO){
        logger.info("[web] classroomInfo begin insert param"+paramDTO);
        if (paramDTO.getClass_date() == 8){
            for(int j = 1 ;j < 8 ;j++){
                paramDTO.setClass_date(j);
                Integer i =  superDao.insert("ClassroomMapper.insertInfo", paramDTO);
                if (i == null || i<=0){
                    return Boolean.FALSE;
                }
            }
        }else {
            Integer i =  superDao.insert("ClassroomMapper.insertInfo", paramDTO);
            if (i == null || i<=0){
                return Boolean.FALSE;
            }
        }
        return Boolean.TRUE;
   }

}
