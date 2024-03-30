package com.jc.subject.domain.convert;

import com.jc.subject.domain.entity.SubjectInfoBO;
import com.jc.subject.infra.basic.entity.SubjectBrief;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @Description:
 * @Author: Siiimon
 * @Date: 2024/03/29/8:39
 */
@Mapper
public interface BriefSubjectConverter {
    BriefSubjectConverter INSTANCE = Mappers.getMapper(BriefSubjectConverter.class);

    SubjectBrief converteBOToBrief(SubjectInfoBO subjectInfoBO);

    SubjectInfoBO converteBriefToBO(SubjectBrief subjectBrief);

}
