package com.jc.subject.application.convert;

import com.jc.subject.application.dto.SubjectCategoryDTO;
import com.jc.subject.application.dto.SubjectLabelDTO;
import com.jc.subject.domain.entity.SubjectCategoryBO;
import com.jc.subject.domain.entity.SubjectLabelBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @Description:
 * @Author: Siiimon
 * @Date: 2023/12/15/22:16
 */
@Mapper
public interface SubjectLabelDTOConverter {

    SubjectLabelDTOConverter INSTANCE = Mappers.getMapper(SubjectLabelDTOConverter.class);

    List<SubjectLabelDTO> convertBoToLabelDTOList(List<SubjectLabelBO> subjectLabelBOList);

    SubjectLabelBO convertDtoToLabelBO(SubjectLabelDTO subjectLabelDTO);

    SubjectLabelDTO convertBoToLabelDTO(SubjectLabelBO subjectLabelBO);
}
