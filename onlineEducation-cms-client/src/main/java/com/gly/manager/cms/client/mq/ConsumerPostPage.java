/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: ConsumerPostPage
 * Author:   Administrator
 * Date:     2019/9/2 14:12
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.gly.manager.cms.client.mq;

import com.alibaba.fastjson.JSON;
import com.gly.common.model.cms.CmsPage;
import com.gly.manager.cms.client.dao.CmsPageRepository;
import com.gly.manager.cms.client.service.PageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author Administrator
 * @create 2019/9/2
 * @since 1.0.0
 */
@Component
@Slf4j
public class ConsumerPostPage {

    @Autowired
    CmsPageRepository cmsPageRepository;
    @Autowired
    PageService pageService;

    @RabbitListener(queues = {"${onlineEducation.mq.queue}"})
    public void postPage(String msg) {
        //解析消息
        Map map = JSON.parseObject(msg, Map.class);
        log.info("receive cms post page:{}", msg);

        //取出页面id
        String pageId = (String) map.get("pageId");
        //查询页面信息
        Optional<CmsPage> optional = cmsPageRepository.findById(pageId);
        if (!optional.isPresent()) {
            log.error("receive cms post page,cmsPage is null:{}", msg);
            return;
        }
        //将页面保存到服务器物理路径
        pageService.savePageToServerPath(pageId);
    }

}