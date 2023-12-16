package com.jc.subject.application.controller;

import com.jc.subject.infra.basic.entity.SubjectCategory;
import com.jc.subject.infra.basic.service.SubjectCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 刷题controller
 * @Author: Siiimon
 * @Date: 2023/12/15/15:36
 */
@RestController
public class SubjectController {
    @Autowired
    private SubjectCategoryService subjectCategoryService;

    @GetMapping("test")
    public String test(){
        SubjectCategory subjectCategory = subjectCategoryService.queryById(1L);
        return subjectCategory.getCategoryName();
    }
}
