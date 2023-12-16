package com.jc.subject.domain.service.impl;

import com.alibaba.fastjson.JSON;
import com.jc.subject.domain.convert.SubjectCategoryConverter;
import com.jc.subject.domain.entity.SubjectCategoryBO;
import com.jc.subject.domain.service.SubjectCategoryDomainService;
import com.jc.subject.infra.basic.entity.SubjectCategory;
import com.jc.subject.infra.basic.service.SubjectCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Description: Domain层的刷题分类Service实现类
 * @Author: Siiimon
 * @Date: 2023/12/15/22:08
 */
@Service
@Slf4j
public class SubjectCategoryDomainServiceImpl implements SubjectCategoryDomainService {

    @Autowired
    private SubjectCategoryService subjectCategoryService;

    @Override
    public void add(SubjectCategoryBO subjectCategoryBO) {
        if(log.isInfoEnabled()){
            log.info("SubjectCategoryDomainServiceImpl.add.bo:{}", JSON.toJSONString(subjectCategoryBO));
        }
        SubjectCategory subjectCategory = SubjectCategoryConverter.INSTANCE.convertBoToCategory(subjectCategoryBO);
        subjectCategoryService.insert(subjectCategory);
    }
}
