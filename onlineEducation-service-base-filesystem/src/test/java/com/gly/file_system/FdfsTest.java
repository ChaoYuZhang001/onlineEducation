package com.gly.file_system;

import com.gly.file_system.service.FileSystemService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.apache.bcel.classfile.annotation.NameValuePair;
import org.csource.fastdfs.*;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileOutputStream;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author zcy
 * @create 2019/9/17
 * @since 1.0.0
 */
@Slf4j
public class FdfsTest extends BaseFileSystemApplicationTest {

    @Autowired
    FileSystemService fileSystemService;

    @Test
    public void upload() {
        try {
            //加载fastdfs-client.properties
//            ClientGlobal.initByProperties("config/fastdfs-client.properties");
            fileSystemService.initFastDFSConfig();
//            log.debug("network_timeout=" + ClientGlobal.g_network_timeout + "ms");
//            log.debug("charset=" + ClientGlobal.g_charset);
            //创建客户端
            TrackerClient trackerClient = new TrackerClient();
            //连接tracker Server
            TrackerServer trackerServer = trackerClient.getConnection();
            if (trackerServer == null) {
                log.debug("getConnection return null");
                return;
            }
            //获取一个storage server
            StorageServer storageServer = trackerClient.getStoreStorage(trackerServer);
            if (storageServer == null) {
                log.debug("getStoreStorage return null");
            }
            //创建一个storage存储客户端
            StorageClient1 storageClient1 = new StorageClient1(trackerServer, storageServer);
//            NameValuePair[] meta_list = new NameValuePair[0];
            String item = "D:\\code\\onlineEducation-ui\\onlineEducation-ui-pc\\img\\asset-banner.png";
            String fileid;
            fileid = storageClient1.upload_file1(item, "png", null);
            log.debug("Upload local file " + item + " ok, fileid=" + fileid);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @Test
    public void download() {
        try {
            //加载fastdfs-client.properties
//            ClientGlobal.initByProperties("config/fastdfs-client.properties");
            fileSystemService.initFastDFSConfig();
//            log.debug("network_timeout=" + ClientGlobal.g_network_timeout + "ms");
//            log.debug("charset=" + ClientGlobal.g_charset);
            //创建客户端
            TrackerClient trackerClient = new TrackerClient();
            //连接tracker Server
            TrackerServer trackerServer = trackerClient.getConnection();
            if (trackerServer == null) {
                log.debug("getConnection return null");
                return;
            }
            //获取一个storage server
            StorageServer storageServer = trackerClient.getStoreStorage(trackerServer);
            if (storageServer == null) {
                log.debug("getStoreStorage return null");
            }
            //创建一个storage存储客户端group1/M00/00/00/wKglgl2AplqAYo6gAANO8mq9dHE373.png
            StorageClient1 storageClient1 = new StorageClient1(trackerServer, storageServer);
            String fileId = "group1/M00/00/00/wKglgl2AplqAYo6gAANO8mq9dHE373.png";
            byte[] bytes = storageClient1.download_file1(fileId);
            FileOutputStream fileOutputStream = new FileOutputStream(new File("C:\\Users\\Administrator\\Desktop\\fastDFS_test.png"));
            fileOutputStream.write(bytes);
            fileOutputStream.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}