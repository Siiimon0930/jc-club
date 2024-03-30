package com.jc.subject.application.convert;

import com.jc.subject.application.dto.SubjectCategoryDTO;
import com.jc.subject.application.dto.SubjectInfoDTO;
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
public interface SubjectInfoDTOConverter {

    SubjectInfoDTOConverter INSTANCE = Mappers.getMapper(SubjectInfoDTOConverter.class);

    List<SubjectInfoDTO> convertBoToInfoDTOList(List<SubjectInfoBO> subjectInfoBOList);

    SubjectInfoBO convertDtoToInfoBO(SubjectInfoDTO subjectInfoDTO);

    SubjectInfoDTO convertBoToInfoDTO(SubjectInfoBO subjectInfoBO);
}
