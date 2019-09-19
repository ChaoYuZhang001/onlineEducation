package com.gly.common.model.cms.ext;

import com.gly.common.model.cms.CmsTemplate;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CmsTemplateExt extends CmsTemplate {

    //模版内容
    private String templateValue;

}
