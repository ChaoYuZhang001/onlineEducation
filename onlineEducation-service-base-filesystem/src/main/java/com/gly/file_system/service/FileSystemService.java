package com.gly.file_system.service;

import com.alibaba.fastjson.JSON;
import com.gly.common.exception.ExceptionCast;
import com.gly.common.model.filesystem.FileSystem;
import com.gly.common.model.filesystem.response.FileSystemCode;
import com.gly.common.model.filesystem.response.UploadFileResult;
import com.gly.common.model.response.CommonCode;
import com.gly.file_system.dao.FileSystemRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.csource.fastdfs.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;
import java.util.Objects;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author zcy
 * @create 2019/9/18
 * @since 1.0.0
 */
@Service
@Slf4j
public class FileSystemService {

    @Autowired
    FileSystemRepository fileSystemRepository;

    @Value("${onlineEducation.fastDFS.tracker_servers}")
    String tracker_servers;
    @Value("${onlineEducation.fastDFS.connect_timeout_in_seconds}")
    int connect_timeout_in_seconds;
    @Value("${onlineEducation.fastDFS.network_timeout_in_seconds}")
    int network_timeout_in_seconds;
    @Value("${onlineEducation.fastDFS.charset}")
    String charset;

    //上传文件
    public UploadFileResult upload(MultipartFile file, String filetag, String businesskey, String metadata) {
        log.debug("upload file: {},filetag: {}, businesskey: {}, metadata: {}", file, filetag, businesskey, metadata);
        if (Objects.isNull(file)) {
            ExceptionCast.cast(FileSystemCode.FS_UPLOADFILE_FILEISNULL);
        }
        //上传到fdfs
        String fileId = fastDFSUpload(file);
        if (StringUtils.isEmpty(fileId)) {
            log.error("fastDFSUpload ERROR");
            return null;
        }

        //创建文件信息对象
        FileSystem fileSystem = new FileSystem();
        //文件id
        fileSystem.setFileId(fileId);
        //文件在文件系统中的路径
        fileSystem.setFilePath(fileId);
        //业务标识
        fileSystem.setBusinesskey(businesskey);
        //标签
        fileSystem.setFiletag(filetag);
        //元数据
        if (StringUtils.isNotEmpty(metadata)) {
            try {
                Map map = JSON.parseObject(metadata, Map.class);
                fileSystem.setMetadata(map);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //名称
        fileSystem.setFileName(file.getOriginalFilename());
        //大小
        fileSystem.setFileSize(file.getSize());
        //文件类型
        fileSystem.setFileType(file.getContentType());
        fileSystemRepository.save(fileSystem);
        return new UploadFileResult(CommonCode.SUCCESS, fileSystem);
    }

    //上传文件到fdfs，返回文件id
    private String fastDFSUpload(MultipartFile file) {
        try {
            //加载fdfs的配置
            log.debug("initFastDFSConfig");
            initFastDFSConfig();

            //创建tracker client
            TrackerClient trackerClient = new TrackerClient();
            //获取trackerServer
            TrackerServer trackerServer = trackerClient.getConnection();
            //获取storage
            StorageServer storeStorage = trackerClient.getStoreStorage(trackerServer);
            //创建storage client
            StorageClient1 storageClient1 = new StorageClient1(trackerServer, storeStorage);

            //上传文件
            //文件字节
            byte[] bytes = file.getBytes();
            //文件原始名称
            String originalFilename = file.getOriginalFilename();
            //文件扩展名
            String extName = originalFilename;
            if (originalFilename.contains(".")) {
                extName = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
            }
            //文件id
            String file1 = storageClient1.upload_file1(bytes, extName, null);
            log.debug("fastDFS Upload file:{}, fileId: {}", file, file1);
            return file1;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //加载fdfs的配置
    public void initFastDFSConfig() {
        try {
            ClientGlobal.initByTrackers(tracker_servers);
            ClientGlobal.setG_connect_timeout(connect_timeout_in_seconds);
            ClientGlobal.setG_network_timeout(network_timeout_in_seconds);
            ClientGlobal.setG_charset(charset);
        } catch (Exception e) {
            e.printStackTrace();
            //初始化文件系统出错
            ExceptionCast.cast(FileSystemCode.FS_INITFDFSERROR);
        }
    }
}