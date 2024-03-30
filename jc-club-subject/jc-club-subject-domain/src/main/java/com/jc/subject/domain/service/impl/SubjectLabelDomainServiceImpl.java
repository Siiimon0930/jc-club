package com.jc.subject.domain.service.impl;

import com.alibaba.fastjson.JSON;
import com.jc.subject.common.enums.IsDeletedFlagEnum;
import com.jc.subject.domain.convert.SubjectLabelConverter;
import com.jc.subject.domain.entity.SubjectLabelBO;
import com.jc.subject.domain.service.SubjectLabelDomainService;
import com.jc.subject.infra.basic.entity.SubjectLabel;
import com.jc.subject.infra.basic.entity.SubjectMapping;
import com.jc.subject.infra.basic.service.SubjectLabelService;
import com.jc.subject.infra.basic.service.SubjectMappingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description: Domain层的题目标签Service实现类
 * @Author: Siiimon
 * @Date: 2023/12/19/20:54
 */
@Service
@Slf4j
public class SubjectLabelDomainServiceImpl implements SubjectLabelDomainService {

    @Autowired
    private SubjectLabelService subjectLabelService;

    @Autowired
    private SubjectMappingService subjectMappingService;

    /**
     * 新增标签
     */
    @Override
    public Boolean add(SubjectLabelBO subjectLabelBO) {
        if (log.isInfoEnabled()) {
            log.info("SubjectLabelDomainServiceImpl.add.bo:{}", JSON.toJSONString(subjectLabelBO));
        }
        SubjectLabel subjectLabel = SubjectLabelConverter.INSTANCE.convertBoToLabel(subjectLabelBO);
        subjectLabel.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.getCode());
        int count = subjectLabelService.insert(subjectLabel);
        return count > 0;
    }

    /**
     * 更新标签
     */
    @Override
    public Boolean update(SubjectLabelBO subjectLabelBO) {
        if (log.isInfoEnabled()) {
            log.info("SubjectLabelDomainServiceImpl.update.bo:{}", JSON.toJSONString(subjectLabelBO));
        }
        SubjectLabel subjectLabel = SubjectLabelConverter.INSTANCE.convertBoToLabel(subjectLabelBO);
        int count = subjectLabelService.update(subjectLabel);
        return count > 0;
    }

    /**
     * 删除标签
     */
    @Override
    public Boolean delete(SubjectLabelBO subjectLabelBO) {
        if (log.isInfoEnabled()) {
            log.info("SubjectLabelDomainServiceImpl.delete.bo:{}", JSON.toJSONString(subjectLabelBO));
        }
        SubjectLabel subjectLabel = SubjectLabelConverter.INSTANCE.convertBoToLabel(subjectLabelBO);
        subjectLabel.setIsDeleted(IsDeletedFlagEnum.DELETED.getCode());
        int count = subjectLabelService.update(subjectLabel);
        return count > 0;
    }

    /**
     * 根据分类获取标签，keypoint 严格来说是查询已有题目中根据分类获取标签
     */
    @Override
    public List<SubjectLabelBO> queryLabelByCategoryId(SubjectLabelBO subjectLabelBO) {
        // keypoint label和category是多对多的关系，通过subject_mapping表关联映射
        // 1.根据categoryId查询subject_mapping表
        Long categoryId = subjectLabelBO.getCategoryId();
        SubjectMapping subjectMapping = new SubjectMapping();
        subjectMapping.setCategoryId(categoryId);
        subjectMapping.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.getCode());
        List<SubjectMapping> mappingList = subjectMappingService.queryLabelId(subjectMapping);
        List<Long> labelIdList = mappingList.stream().map(SubjectMapping::getLabelId).collect(Collectors.toList());
        // 2.查询label表
        List<SubjectLabel> subjectLabelList = subjectLabelService.batchQueryByIds(labelIdList);
        // 3.PO转换为BO
        List<SubjectLabelBO> subjectLabelBOList = SubjectLabelConverter.INSTANCE.convertLabelToBO(subjectLabelList);
        return subjectLabelBOList;
    }
}
