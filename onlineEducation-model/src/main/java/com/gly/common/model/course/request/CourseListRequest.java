package com.gly.common.model.course.request;

import com.gly.common.model.request.RequestData;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CourseListRequest extends RequestData {
    //公司Id
    private String companyId;
}
