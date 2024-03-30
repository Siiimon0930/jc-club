package com.jc.subject.domain.service;

import com.jc.subject.domain.entity.SubjectCategoryBO;

import java.util.List;

/**
 * @Description: Domain层的刷题分类接口
 * @Author: Siiimon
 * @Date: 2023/12/15/22:06
 */
public interface SubjectCategoryDomainService {

    /**
     * 新增分类
     */
    void add(SubjectCategoryBO subjectCategoryBO);

    /**
     * 查询分类
     */
    List<SubjectCategoryBO> queryCategory(SubjectCategoryBO subjectCategoryBO);

    /**
     * 更新分类
     */
    Boolean update(SubjectCategoryBO subjectCategoryBO);

    /**
     * 删除分类
     */
    Boolean delete(SubjectCategoryBO subjectCategoryBO);
}
