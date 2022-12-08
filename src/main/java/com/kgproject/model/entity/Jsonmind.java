package com.kgproject.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Jsonmind implements Serializable {
    private static final long serialVersionUID = 1L;
    private String userid;
    private String name;
    private String content;
}
