package com.jc.subject.domain.handler.subject;

import com.jc.subject.common.enums.IsDeletedFlagEnum;
import com.jc.subject.common.enums.SubjectTypeEnum;
import com.jc.subject.domain.convert.MultipleSubjectConverter;
import com.jc.subject.domain.convert.RadioSubjectConverter;
import com.jc.subject.domain.entity.SubjectAnswerBO;
import com.jc.subject.domain.entity.SubjectInfoBO;
import com.jc.subject.domain.entity.SubjectOptionBO;
import com.jc.subject.infra.basic.entity.SubjectMultiple;
import com.jc.subject.infra.basic.entity.SubjectRadio;
import com.jc.subject.infra.basic.service.SubjectMappingService;
import com.jc.subject.infra.basic.service.SubjectMultipleService;
import com.jc.subject.infra.basic.service.SubjectRadioService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: Siiimon
 * @Date: 2024/03/29/9:01
 */
@Component
public class MultipleTypeHandler implements SubjectTypeHandler{

    @Resource
    private SubjectMultipleService subjectMultipleService;

    @Override
    public SubjectTypeEnum getHandlerType() {
        return SubjectTypeEnum.MULTIPLE;
    }

    @Override
    public void add(SubjectInfoBO subjectInfoBO) {
        List<SubjectAnswerBO> optionList = subjectInfoBO.getOptionList();
        List<SubjectMultiple> subjectMultipleList = new ArrayList<>();
        for (SubjectAnswerBO subjectAnswerBO: optionList){
            SubjectMultiple subjectMultiple = MultipleSubjectConverter.INSTANCE.convertBoToEntity(subjectAnswerBO);
            subjectMultiple.setSubjectId(subjectInfoBO.getId());
            subjectMultiple.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.getCode());
            subjectMultipleList.add(subjectMultiple);
        }
        subjectMultipleService.batchInsert(subjectMultipleList);
    }

    @Override
    public SubjectOptionBO query(Long subjectId) {
        SubjectMultiple subjectMultiple = new SubjectMultiple();
        subjectMultiple.setSubjectId(subjectId);
        List<SubjectMultiple> result = subjectMultipleService.queryByCondition(subjectMultiple);
        List<SubjectAnswerBO> subjectAnswerBOList = MultipleSubjectConverter.INSTANCE.convertEntityToBoList(result);
        SubjectOptionBO subjectOptionBO = new SubjectOptionBO();
        subjectOptionBO.setOptionList(subjectAnswerBOList);
        return subjectOptionBO;
    }
}
