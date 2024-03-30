package com.jc.subject.application.convert;

import com.jc.subject.application.dto.SubjectAnswerDTO;
import com.jc.subject.application.dto.SubjectCategoryDTO;
import com.jc.subject.application.dto.SubjectInfoDTO;
import com.jc.subject.domain.entity.SubjectAnswerBO;
import com.jc.subject.domain.entity.SubjectCategoryBO;
import com.jc.subject.domain.entity.SubjectInfoBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @Description:
 * @Author: Siiimon
 * @Date: 2023/12/15/22:16
 */
@Mapper
public interface SubjectAnswerDTOConverter {

    SubjectAnswerDTOConverter INSTANCE = Mappers.getMapper(SubjectAnswerDTOConverter.class);

    List<SubjectAnswerBO> convertDTOToBOList(List<SubjectAnswerDTO> subjectAnswerDTOList);

    SubjectAnswerBO convertDtoToBO(SubjectAnswerDTO subjectAnswerDTO);

}
