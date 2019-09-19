package com.gly.common.model.portalview;

import com.gly.common.model.course.CourseBase;
import com.gly.common.model.course.CourseMarket;
import com.gly.common.model.course.CoursePic;
import com.gly.common.model.course.ext.TeachplanNode;
import com.gly.common.model.report.ReportCourse;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@ToString
@Document(collection = "pre_view_course")
public class PreViewCourse implements Serializable{

    @Id
    private String id;
    private CourseBase courseBase;
    private CourseMarket courseMarket;
    private CoursePic coursePic;
    private TeachplanNode teachplan;
    //课程统计信息
    private ReportCourse reportCourse;

}
