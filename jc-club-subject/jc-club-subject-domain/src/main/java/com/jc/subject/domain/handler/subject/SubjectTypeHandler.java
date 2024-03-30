package com.jc.subject.domain.handler.subject;

import com.jc.subject.common.enums.SubjectTypeEnum;
import com.jc.subject.domain.entity.SubjectInfoBO;
import com.jc.subject.domain.entity.SubjectOptionBO;

/**
 * @Description:
 * @Author: Siiimon
 * @Date: 2024/03/29/8:58
 */
public interface SubjectTypeHandler {

    /**
     * 获取题目枚举类别
     */
    SubjectTypeEnum getHandlerType();

    /**
     * 实际题目选项的插入，操作radio、brief等四个表
     */
    void add(SubjectInfoBO subjectInfoBO);

    /**
     * 查询题目选项详情
     */
    SubjectOptionBO query(Long subjectId);
}
