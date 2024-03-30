package com.jc.subject.domain.service;

import com.jc.subject.common.entity.PageResult;
import com.jc.subject.domain.entity.SubjectInfoBO;

/**
 * @Description:
 * @Author: Siiimon
 * @Date: 2024/03/28/22:33
 */
public interface SubjectInfoDomainService {

    /**
     * 新增题目
     */
    void add(SubjectInfoBO subjectInfoBO);

    /**
     * 分页查询
     */
    PageResult<SubjectInfoBO> getSubjectPage(SubjectInfoBO subjectInfoBO);

    /**
     * 查询题目详情
     */
    SubjectInfoBO querySubjectInfo(SubjectInfoBO subjectInfoBO);
}
