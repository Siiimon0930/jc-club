package com.jc.subject.domain.service;

import com.jc.subject.domain.entity.SubjectCategoryBO;

/**
 * @Description: Domain层的刷题分类接口
 * @Author: Siiimon
 * @Date: 2023/12/15/22:06
 */
public interface SubjectCategoryDomainService {

    /**
     * decription: 新增分类
     * @param subjectCategoryBO
     * @return void
     */
    void add(SubjectCategoryBO subjectCategoryBO);
}
