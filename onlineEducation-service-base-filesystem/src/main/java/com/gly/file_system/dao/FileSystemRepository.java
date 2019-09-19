package com.gly.file_system.dao;

import com.gly.common.model.filesystem.FileSystem;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author zcy
 * @create 2019/9/18
 * @since 1.0.0
 */
public interface FileSystemRepository extends MongoRepository<FileSystem, String> {

}