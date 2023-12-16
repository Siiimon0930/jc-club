package com.jc.subject.domain.convert;

import com.jc.subject.domain.entity.SubjectCategoryBO;
import com.jc.subject.infra.basic.entity.SubjectCategory;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-12-16T15:14:45+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_352 (Azul Systems, Inc.)"
)
public class SubjectCategoryConverterImpl implements SubjectCategoryConverter {

    @Override
    public SubjectCategory convertBoToCategory(SubjectCategoryBO subjectCategoryBO) {
        if ( subjectCategoryBO == null ) {
            return null;
        }

        SubjectCategory subjectCategory = new SubjectCategory();

        subjectCategory.setId( subjectCategoryBO.getId() );
        subjectCategory.setCategoryName( subjectCategoryBO.getCategoryName() );
        subjectCategory.setCategoryType( subjectCategoryBO.getCategoryType() );
        subjectCategory.setImageUrl( subjectCategoryBO.getImageUrl() );
        subjectCategory.setParentId( subjectCategoryBO.getParentId() );
        subjectCategory.setCreatedBy( subjectCategoryBO.getCreatedBy() );
        subjectCategory.setCreatedTime( subjectCategoryBO.getCreatedTime() );
        subjectCategory.setUpdateBy( subjectCategoryBO.getUpdateBy() );
        subjectCategory.setUpdateTime( subjectCategoryBO.getUpdateTime() );
        subjectCategory.setIsDeleted( subjectCategoryBO.getIsDeleted() );

        return subjectCategory;
    }

    @Override
    public List<SubjectCategoryBO> convertBoToCategory(List<SubjectCategory> categoryList) {
        if ( categoryList == null ) {
            return null;
        }

        List<SubjectCategoryBO> list = new ArrayList<SubjectCategoryBO>( categoryList.size() );
        for ( SubjectCategory subjectCategory : categoryList ) {
            list.add( subjectCategoryToSubjectCategoryBO( subjectCategory ) );
        }

        return list;
    }

    protected SubjectCategoryBO subjectCategoryToSubjectCategoryBO(SubjectCategory subjectCategory) {
        if ( subjectCategory == null ) {
            return null;
        }

        SubjectCategoryBO subjectCategoryBO = new SubjectCategoryBO();

        subjectCategoryBO.setId( subjectCategory.getId() );
        subjectCategoryBO.setCategoryName( subjectCategory.getCategoryName() );
        subjectCategoryBO.setCategoryType( subjectCategory.getCategoryType() );
        subjectCategoryBO.setImageUrl( subjectCategory.getImageUrl() );
        subjectCategoryBO.setParentId( subjectCategory.getParentId() );
        subjectCategoryBO.setCreatedBy( subjectCategory.getCreatedBy() );
        subjectCategoryBO.setCreatedTime( subjectCategory.getCreatedTime() );
        subjectCategoryBO.setUpdateBy( subjectCategory.getUpdateBy() );
        subjectCategoryBO.setUpdateTime( subjectCategory.getUpdateTime() );
        subjectCategoryBO.setIsDeleted( subjectCategory.getIsDeleted() );

        return subjectCategoryBO;
    }
}
