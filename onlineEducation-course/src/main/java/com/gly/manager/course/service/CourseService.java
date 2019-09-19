package com.gly.manager.course.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.gly.common.exception.ExceptionCast;
import com.gly.common.model.course.CourseBase;
import com.gly.common.model.course.CourseMarket;
import com.gly.common.model.course.CoursePic;
import com.gly.common.model.course.Teachplan;
import com.gly.common.model.course.ext.CourseInfo;
import com.gly.common.model.course.ext.TeachplanNode;
import com.gly.common.model.course.request.CourseListRequest;
import com.gly.common.model.course.response.AddCourseResult;
import com.gly.common.model.response.CommonCode;
import com.gly.common.model.response.QueryResponseResult;
import com.gly.common.model.response.QueryResult;
import com.gly.common.model.response.ResponseResult;
import com.gly.manager.course.dao.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author zcy
 * @create 2019/9/12
 * @since 1.0.0
 */
@Slf4j
@Service
public class CourseService {

    @Autowired
    TeachplanMapper teachplanMapper;
    @Autowired
    CourseBaseRepository courseBaseRepository;
    @Autowired
    TeachplanRepository teachplanRepository;
    @Autowired
    CourseMapper courseMapper;
    @Autowired
    CourseMarketRepository courseMarketRepository;
    @Autowired
    CoursePicRepository coursePicRepository;

    //查询课程计划
    public TeachplanNode findTeachplanList(String courseId) {
        log.debug("find  TeachplanList courseId: {}", courseId);
        if (StringUtils.isEmpty(courseId)) {
            return null;
        }
        TeachplanNode teachplanNode = teachplanMapper.selectList(courseId);
        log.debug("find  TeachplanList courseId: {}, teachplanNode: {}", courseId, teachplanNode);
        return teachplanNode;
    }

    @Transactional
    public ResponseResult addTeachplan(Teachplan teachplan) {
        log.debug("Add teachplan: {}", teachplan);
        //校验课程id和课程计划名称
        if (Objects.isNull(teachplan) || StringUtils.isEmpty(teachplan.getCourseid()) || StringUtils.isEmpty(teachplan.getPname())) {
            ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }
        //取出课程id
        String courseId = teachplan.getCourseid();
        //取出父结点id
        String parentId = teachplan.getParentid();
        if (StringUtils.isEmpty(parentId)) {
            //如果父结点为空则获取根结点
            log.debug("Get the root node if the parent node is empty ");
            parentId = this.getTeachPlanRoot(courseId);
        }

        log.debug("Extract parent node information parentId: {}", parentId);
        //取出父结点信息
        Optional<Teachplan> teachplanOptional = teachplanRepository.findById(parentId);
        if (!teachplanOptional.isPresent()) {
            ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }
        //父结点
        Teachplan teachPlanParent = teachplanOptional.get();
        log.debug("Extract parent node information parentId: {}, teachPlanParent: {}", parentId, teachPlanParent);

        //父结点级别
        String parentGrade = teachPlanParent.getGrade();
        //设置父结点
        teachplan.setParentid(parentId);
        teachplan.setStatus("0");//未发布

        //子结点的级别，根据父结点来判断
        if (parentGrade.equals("1")) {
            teachplan.setGrade("2");
        } else {
            teachplan.setGrade("3");
        }

        //设置课程id
        teachplan.setCourseid(teachPlanParent.getCourseid());
        log.debug("save teachplan: {}", teachplan);
        teachplanRepository.save(teachplan);
        return new ResponseResult(CommonCode.SUCCESS);
    }

    private String getTeachPlanRoot(String courseId) {
        log.debug("getTeachPlanRoot courseId: {}", courseId);
        //校验课程id
        Optional<CourseBase> optional = courseBaseRepository.findById(courseId);
        if (!optional.isPresent()) {
            return null;
        }
        CourseBase courseBase = optional.get();
        log.debug("getTeachPlanRoot courseId: {}, courseBase: {}", courseId, courseBase);

        //取出课程计划根结点
        log.debug("find teachplanList By courseId: {}, parentId: 0", courseId);
        List<Teachplan> teachplanList = teachplanRepository.findByCourseidAndParentid(courseId, "0");
        log.debug("find teachplanList By courseId: {}, parentId: 0, listSize: {}", courseId, teachplanList.size());

        if (CollectionUtils.isEmpty(teachplanList)) {
            log.debug("teachplanList size: 0  save teachplan");
            //新增一个根结点
            Teachplan teachPlanRoot = new Teachplan();
            teachPlanRoot.setCourseid(courseId);
            teachPlanRoot.setPname(courseBase.getName());
            teachPlanRoot.setParentid("0");
            teachPlanRoot.setGrade("1");//1级
            teachPlanRoot.setStatus("0");//未发布
            log.debug("teachplanList size: 0  save teachplan, teachPlanRoot: {}", teachPlanRoot);
            teachplanRepository.save(teachPlanRoot);
            return teachPlanRoot.getId();
        }
        Teachplan teachplan = teachplanList.get(0);
        return teachplan.getId();

    }

    public QueryResponseResult<CourseInfo> findCourseList(int page, int size, CourseListRequest courseListRequest) {
        if (courseListRequest == null) {
            courseListRequest = new CourseListRequest();
        }
        if (page <= 0) {
            page = 0;
        }
        if (size <= 0) {
            size = 20;
        }
        //设置分页参数
        PageHelper.startPage(page, size);
        //分页查询
        Page<CourseInfo> courseListPage = courseMapper.findCourseListPage(courseListRequest);
        //查询列表
        List<CourseInfo> list = courseListPage.getResult();
        //总记录数
        long total = courseListPage.getTotal();
        //查询结果集
        QueryResult<CourseInfo> courseIncfoQueryResult = new QueryResult<CourseInfo>();
        courseIncfoQueryResult.setList(list);
        courseIncfoQueryResult.setTotal(total);
        return new QueryResponseResult<CourseInfo>(CommonCode.SUCCESS, courseIncfoQueryResult);
    }

    //添加课程提交
    @Transactional
    public AddCourseResult addCourseBase(CourseBase courseBase) {
        //课程状态默认为未发布
        courseBase.setStatus("202001");
        courseBaseRepository.save(courseBase);
        return new AddCourseResult(CommonCode.SUCCESS, courseBase.getId());
    }

    public CourseBase getCoursebaseById(String courseId) {
        Optional<CourseBase> optional = courseBaseRepository.findById(courseId);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    @Transactional
    public ResponseResult updateCoursebase(String id, CourseBase courseBase) {
        CourseBase one = this.getCoursebaseById(id);
        if (one == null) {
            //抛出异常..
        }
        //修改课程信息
        one.setName(courseBase.getName());
        one.setMt(courseBase.getMt());
        one.setSt(courseBase.getSt());
        one.setGrade(courseBase.getGrade());
        one.setStudymodel(courseBase.getStudymodel());
        one.setUsers(courseBase.getUsers());
        one.setDescription(courseBase.getDescription());
        CourseBase save = courseBaseRepository.save(one);
        return new ResponseResult(CommonCode.SUCCESS);
    }

    public CourseMarket getCourseMarketById(String courseId) {
        Optional<CourseMarket> optional = courseMarketRepository.findById(courseId);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    @Transactional
    public CourseMarket updateCourseMarket(String id, CourseMarket courseMarket) {
        CourseMarket one = this.getCourseMarketById(id);
        if (one != null) {
            one.setCharge(courseMarket.getCharge());
            one.setStartTime(courseMarket.getStartTime());//课程有效期，开始时间
            one.setEndTime(courseMarket.getEndTime());//课程有效期，结束时间
            one.setPrice(courseMarket.getPrice());
            one.setQq(courseMarket.getQq());
            one.setValid(courseMarket.getValid());
            courseMarketRepository.save(one);
        } else {
            //添加课程营销信息
            one = new CourseMarket();
            BeanUtils.copyProperties(courseMarket, one);
            //设置课程id
            one.setId(id);
            courseMarketRepository.save(one);
        }
        return one;
    }

    //添加课程图片 一个课程只能有一个课程图片
    @Transactional
    public ResponseResult saveCoursePic(String courseId, String pic) {
        //查询课程图片
        Optional<CoursePic> picOptional = coursePicRepository.findById(courseId);
        CoursePic coursePic = null;
        if (picOptional.isPresent()) {
            coursePic = picOptional.get();
        }
        //没有课程图片则新建对象
        if (Objects.isNull(coursePic)) {
            coursePic = new CoursePic();
        }
        coursePic.setCourseid(courseId);
        coursePic.setPic(pic);
        //保存课程图片
        coursePicRepository.save(coursePic);
        return new ResponseResult(CommonCode.SUCCESS);
    }

    public CoursePic findCoursePic(String courseId) {
        Optional<CoursePic> picOptional = coursePicRepository.findById(courseId);
        CoursePic coursePic = null;
        if (picOptional.isPresent()) {
            coursePic = picOptional.get();
        }
        return coursePic;
    }

    @Transactional
    public ResponseResult deleteCoursePic(String courseId) {
        //执行删除，返回1表示删除成功，返回0表示删除失败
        long result = coursePicRepository.deleteByCourseid(courseId);
        if(result>0){
            return new ResponseResult(CommonCode.SUCCESS);
        }
        return new ResponseResult(CommonCode.FAIL);
    }
}