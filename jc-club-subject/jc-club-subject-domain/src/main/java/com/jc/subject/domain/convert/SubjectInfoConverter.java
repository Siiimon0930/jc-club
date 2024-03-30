package com.jc.subject.domain.convert;

import com.jc.subject.domain.entity.SubjectInfoBO;
import com.jc.subject.domain.entity.SubjectOptionBO;
import com.jc.subject.infra.basic.entity.SubjectInfo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @Description:
 * @Author: Siiimon
 * @Date: 2024/03/29/8:39
 */
@Mapper
public interface SubjectInfoConverter {
    SubjectInfoConverter INSTANCE = Mappers.getMapper(SubjectInfoConverter.class);

    SubjectInfo converteBOToInfo(SubjectInfoBO subjectInfoBO);

    SubjectInfoBO converteInfoToBO(SubjectInfo subjectInfo);

    SubjectInfoBO converteOptionToBO(SubjectOptionBO subjectOptionBO);

    SubjectInfoBO convertOptionAndInfoToBo(SubjectOptionBO subjectOptionBO,SubjectInfo subjectInfo);

    List<SubjectInfoBO> converteInfoToBOList(List<SubjectInfo> subjectInfoList);
}
