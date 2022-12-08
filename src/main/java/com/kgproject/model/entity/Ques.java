package com.kgproject.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class Ques {
    private Integer id;
    private String question;
    private String type;
    private String correctAnswer;
    private String parse;
    private String tag;
    private String A,B,C,D,E;
}
