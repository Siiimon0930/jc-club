package com.jc.subject.domain.service.impl;

import com.jc.subject.common.entity.PageResult;
import com.jc.subject.common.enums.IsDeletedFlagEnum;
import com.jc.subject.domain.convert.SubjectInfoConverter;
import com.jc.subject.domain.entity.SubjectInfoBO;
import com.jc.subject.domain.entity.SubjectOptionBO;
import com.jc.subject.domain.handler.subject.SubjectTypeHandler;
import com.jc.subject.domain.handler.subject.SubjectTypeHandlerFactory;
import com.jc.subject.domain.service.SubjectInfoDomainService;
import com.jc.subject.infra.basic.entity.SubjectInfo;
import com.jc.subject.infra.basic.entity.SubjectLabel;
import com.jc.subject.infra.basic.entity.SubjectMapping;
import com.jc.subject.infra.basic.service.SubjectInfoService;
import com.jc.subject.infra.basic.service.SubjectLabelService;
import com.jc.subject.infra.basic.service.SubjectMappingService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description:
 * @Author: Siiimon
 * @Date: 2024/03/28/22:38
 */
@Service
public class SubjectInfoDomainServiceImpl implements SubjectInfoDomainService {

    @Resource
    private SubjectInfoService subjectInfoService;

    @Resource
    private SubjectTypeHandlerFactory subjectTypeHandlerFactory;

    @Resource
    private SubjectMappingService subjectMappingService;

    @Resource
    private SubjectLabelService subjectLabelService;

    @Override
    public void add(SubjectInfoBO subjectInfoBO) {
        // 1.插入subject_info表
        SubjectInfo subjectInfo = SubjectInfoConverter.INSTANCE.converteBOToInfo(subjectInfoBO);
        subjectInfo.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.getCode());
        subjectInfoService.insert(subjectInfo);
        // 插入后subjectInfo带有id，将其插入至BO，方便后续radio和brief等表的插入
        subjectInfoBO.setId(subjectInfo.getId());
        // 2.根据类型从工厂获取具体策略进行插入
        SubjectTypeHandler handler = subjectTypeHandlerFactory.getHandler(subjectInfoBO.getSubjectType());
        handler.add(subjectInfoBO);
        // 3.插入mapping表，category和label是多对多的，所以通过两个循环插入至mapping
        List<Integer> categoryIds = subjectInfoBO.getCategoryIds();
        List<Integer> labelIds = subjectInfoBO.getLabelIds();
        List<SubjectMapping> subjectMappingList = new LinkedList<>();
        for (Integer categoryId : categoryIds){
            for(Integer labelId: labelIds){
                SubjectMapping mapping = new SubjectMapping();
                mapping.setSubjectId(subjectInfoBO.getId());
                mapping.setCategoryId(Long.valueOf(categoryId));
                mapping.setLabelId(Long.valueOf(labelId));
                mapping.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.getCode());
                subjectMappingList.add(mapping);
            }
        }
        subjectMappingService.batchInsert(subjectMappingList);
    }

    @Override
    public PageResult<SubjectInfoBO> getSubjectPage(SubjectInfoBO subjectInfoBO) {
        // 1.根据前端传过来的参数设置部分PageResult需要返回的参数
        PageResult<SubjectInfoBO> pageResult = new PageResult<>();
        pageResult.setPageNo(subjectInfoBO.getPageNo());
        pageResult.setPageSize(subjectInfoBO.getPageSize());
        int start = (subjectInfoBO.getPageNo() - 1) * subjectInfoBO.getPageSize();
        SubjectInfo subjectInfo = SubjectInfoConverter.INSTANCE.converteBOToInfo(subjectInfoBO);
        // 2.查询总页数
        int count = subjectInfoService.countByCondition(subjectInfo, subjectInfoBO.getCategoryId()
                , subjectInfoBO.getLabelId());
        if (count == 0) {
            return pageResult;
        }
        // 3.查询具体页
        List<SubjectInfo> subjectInfoList = subjectInfoService.queryPage(subjectInfo, subjectInfoBO.getCategoryId()
                , subjectInfoBO.getLabelId(), start, subjectInfoBO.getPageSize());
        List<SubjectInfoBO> subjectInfoBOS = SubjectInfoConverter.INSTANCE.converteInfoToBOList(subjectInfoList);
        // 4.查询每道题目的标签，每道题目可能包含多个标签
        subjectInfoBOS.forEach(info->{
            SubjectMapping subjectMapping = new SubjectMapping();
            subjectMapping.setSubjectId(info.getId());
            List<SubjectMapping> mappingList = subjectMappingService.queryLabelId(subjectMapping);
            List<Long> labelIds = mappingList.stream().map(SubjectMapping::getLabelId).collect(Collectors.toList());
            List<SubjectLabel> labelList = subjectLabelService.batchQueryByIds(labelIds);
            List<String> labelNames = labelList.stream().map(SubjectLabel::getLabelName).collect(Collectors.toList());
            info.setLabelName(labelNames);
        });
        pageResult.setRecords(subjectInfoBOS);
        pageResult.setTotal(count);
        return pageResult;
    }

    @Override
    public SubjectInfoBO querySubjectInfo(SubjectInfoBO subjectInfoBO) {
        // 1.根据id查询info主题信息
        SubjectInfo subjectInfo = subjectInfoService.queryById(subjectInfoBO.getId());
        // 2.根据题目类型选择策略
        SubjectTypeHandler handler = subjectTypeHandlerFactory.getHandler(subjectInfo.getSubjectType());
        // 3.查询选项或答案
        SubjectOptionBO optionBO = handler.query(subjectInfo.getId());
        SubjectInfoBO bo = SubjectInfoConverter.INSTANCE.convertOptionAndInfoToBo(optionBO, subjectInfo);
        // 4.查询每道题的标签名
        SubjectMapping subjectMapping = new SubjectMapping();
        subjectMapping.setSubjectId(subjectInfo.getId());
        subjectMapping.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.getCode());
        List<SubjectMapping> mappingList = subjectMappingService.queryLabelId(subjectMapping);
        List<Long> labelIdList = mappingList.stream().map(SubjectMapping::getLabelId).collect(Collectors.toList());
        List<SubjectLabel> labelList = subjectLabelService.batchQueryByIds(labelIdList);
        List<String> labelNameList = labelList.stream().map(SubjectLabel::getLabelName).collect(Collectors.toList());
        bo.setLabelName(labelNameList);
        return bo;
    }
}
