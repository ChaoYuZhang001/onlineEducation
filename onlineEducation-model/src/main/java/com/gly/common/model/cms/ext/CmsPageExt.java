package com.gly.common.model.cms.ext;

import com.gly.common.model.cms.CmsPage;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CmsPageExt extends CmsPage {
    private String htmlValue;

}
