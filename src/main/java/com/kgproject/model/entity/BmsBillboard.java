package com.kgproject.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;


@Data
@Builder
@Accessors(chain = true)
@TableName("bms_billboard")
@NoArgsConstructor
@AllArgsConstructor
public class BmsBillboard implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 作者
     */
    @TableField("author")
    private String author;
    /**
     * 公告标题
     */
    @TableField("title")
    private String title;
    /**
     * 公告牌
     */
    @TableField("content")
    private String content;

    /**
     * 公告时间
     * 数据库插入数据时会自动插入当前时间（数据库默认）
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 1：展示中，0：过期
     */
//    lombok中的@Builder.Default注解为成员变量赋默认值，不加注解只对成员变量设置默认值，builder构造默认值是无效的
//    启用@Builder.Default， Student.builder().build()为默认值，用new出来的对象不带默认值
    @Builder.Default
    @TableField("`show`")
    private boolean show = false;

}
