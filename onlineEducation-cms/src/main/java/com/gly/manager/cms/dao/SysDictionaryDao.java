package com.gly.manager.cms.dao;

import com.gly.common.model.system.SysDictionary;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author zcy
 * @create 2019/9/16
 * @since 1.0.0
 */
public interface SysDictionaryDao extends MongoRepository<SysDictionary, String> {

    SysDictionary findBydType(String type);
}