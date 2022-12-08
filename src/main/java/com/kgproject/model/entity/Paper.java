package com.kgproject.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class Paper {
    private Integer id;
    private String name;
    private Integer quesCount;
    private Date createTime;
    private String createUser;
}
