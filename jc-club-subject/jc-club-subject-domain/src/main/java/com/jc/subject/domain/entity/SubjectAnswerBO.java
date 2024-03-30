package com.jc.subject.domain.entity;

import lombok.Data;

/**
 * @Description:
 * @Author: Siiimon
 * @Date: 2024/03/28/22:49
 */
@Data
public class SubjectAnswerBO {
    /**
     * 答案选项标识
     */
    private Integer optionType;

    /**
     * 答案
     */
    private String optionContent;

    /**
     * 是否正确
     */
    private Integer isCorrect;
}
