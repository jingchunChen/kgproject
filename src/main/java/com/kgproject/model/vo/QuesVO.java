package com.kgproject.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuesVO {
    Integer id;
    String question;
    String type;
    String A,B,C,D,E;
    String status;
    String answer;
}
