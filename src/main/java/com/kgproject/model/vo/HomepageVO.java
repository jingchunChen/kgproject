package com.kgproject.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HomepageVO {
    private String username;
    private String alias;
    private String email;
    private String gender;
    private String birth;
    private String avatar;
    private String profile;
}
