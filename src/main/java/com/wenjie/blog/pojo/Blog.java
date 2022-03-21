package com.wenjie.blog.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @className: Blog
 * @description: TODO 类描述
 * @author: Wenjie FU
 * @date: 20/03/2022
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_blog")
public class Blog {
    @Id
    @GeneratedValue
    private Long id; // 主键
    private String title; // 标题
    private String content; // 博客的内容
    private String firstPicture; // 首图
    private String flag; // 标记
    private Integer views; // 浏览次数
    private boolean appreciation; // 赞赏开关
    private boolean shareStatement; // 转载声明开关
    private boolean commentTable; // 评论开关
    private boolean recommentd; // 是否推荐
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime; // 创建时间
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime; // 更新时间

    @ManyToOne
    private Type type;

    @ManyToMany(cascade = {CascadeType.PERSIST}) // 级联新增，Blog 中增加了一个新的 tag，它也会被保存到 Tag数据库中
    private List<Tag> tags = new ArrayList<>();

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "blog")
    private List<Comment> comments = new ArrayList<>();
}
