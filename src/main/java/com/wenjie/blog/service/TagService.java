package com.wenjie.blog.service;

import com.wenjie.blog.pojo.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @className: TypeService
 * @description: TODO 类描述
 * @author: Wenjie FU
 * @date: 26/03/2022
 **/

public interface TagService {

    Tag saveTag(Tag tag);

    Tag getTag(Long id);

    Tag getTagByName(String name);

    Page<Tag> listTag(Pageable pageable);

    Tag updateTag(Long id, Tag tag);

    void deleteTag(Long id);
}
