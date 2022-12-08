package com.kgproject.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KnowledgeNode implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String node;
    private String content;

}
