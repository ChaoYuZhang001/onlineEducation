package com.gly.common.model.cms.response;

import com.gly.common.model.response.ResponseResult;
import com.gly.common.model.response.ResultCode;
import lombok.Data;

@Data
public class GenerateHtmlResult extends ResponseResult {
    String html;
    public GenerateHtmlResult(ResultCode resultCode, String html) {
        super(resultCode);
        this.html = html;
    }
}
