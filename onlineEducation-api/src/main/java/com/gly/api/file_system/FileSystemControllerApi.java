package com.gly.api.file_system;

import com.gly.common.model.filesystem.response.UploadFileResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.multipart.MultipartFile;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author zcy
 * @create 2019/9/18
 * @since 1.0.0
 */
@Api(value = "文件管理接口", description = "文件管理接口，提供页面的增、删、改、查")
public interface FileSystemControllerApi {

    /**
     * 上传文件
     *
     * @param multipartFile 文件
     * @param filetag       文件标签
     * @param businesskey   业务key
     * @param metadata      元信息,json格式
     * @return
     */
    @ApiOperation("上传文件")
    UploadFileResult upload(MultipartFile multipartFile, String filetag, String businesskey, String metadata);
}