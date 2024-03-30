package com.jc.subject.domain.convert;

import com.jc.subject.domain.entity.SubjectCategoryBO;
import com.jc.subject.domain.entity.SubjectLabelBO;
import com.jc.subject.infra.basic.entity.SubjectCategory;
import com.jc.subject.infra.basic.entity.SubjectLabel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SubjectLabelConverter {

    SubjectLabelConverter INSTANCE = Mappers.getMapper(SubjectLabelConverter.class);

    SubjectLabel convertBoToLabel(SubjectLabelBO subjectLabelBO);

    SubjectLabelBO convertLabelToBO(SubjectLabel subjectLabel);

    List<SubjectLabelBO> convertLabelToBO(List<SubjectLabel> labelList);

}
