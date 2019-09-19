package com.gly.manager.cms.service;

import com.gly.common.model.system.SysDictionary;
import com.gly.manager.cms.dao.SysDictionaryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author Administrator
 * @create 2019/9/16
 * @since 1.0.0
 */
@Service
public class SysDictionaryService {

    @Autowired
    SysDictionaryDao sysDictionaryDao;

    //根据字典分类type查询字典信息
    public SysDictionary findDictionaryByType(String type){
        return sysDictionaryDao.findBydType(type);
    }
}