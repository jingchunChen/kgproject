package com.kgproject.model.entity;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class Connection implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String source;
    private String target;
    private String relation;

}
