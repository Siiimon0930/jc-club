package com.jc.subject.domain.service;

import com.jc.subject.domain.entity.SubjectLabelBO;

import java.util.List;

/**
 * @Description: Domain层的题目标签接口
 * @Author: Siiimon
 * @Date: 2023/12/19/20:54
 */
public interface SubjectLabelDomainService {
    /**
     * 新增标签
     */
    Boolean add(SubjectLabelBO subjectLabelBO);

    /**
     * 更新标签
     */
    Boolean update(SubjectLabelBO subjectLabelBO);

    /**
     * 删除标签
     */
    Boolean delete(SubjectLabelBO subjectLabelBO);

    /**
     * 根据分类获取标签
     */
    List<SubjectLabelBO> queryLabelByCategoryId(SubjectLabelBO subjectLabelBO);
}
