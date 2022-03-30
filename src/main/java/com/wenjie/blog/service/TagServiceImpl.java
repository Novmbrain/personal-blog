package com.wenjie.blog.service;

import com.wenjie.blog.dao.TagRepository;
import com.wenjie.blog.exception.NotFoundException;
import com.wenjie.blog.pojo.Tag;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @className: TypeServiceImpl
 * @description: TODO 类描述
 * @author: Wenjie FU
 * @date: 26/03/2022
 **/
@Service
public class TagServiceImpl implements TagService {

    @Autowired
    TagRepository tagRepository;

    @Transactional
    @Override
    public Tag saveTag(Tag tag) {
        return tagRepository.save(tag);
    }

    @Override
    public Tag getTag(Long id) {
        return tagRepository.getById(id);
    }

    @Override
    public Tag getTagByName(String name) {
        return tagRepository.findByName(name);
    }

    @Override
    public Page<Tag> listTag(Pageable pageable) {
        return tagRepository.findAll(pageable);
    }

    @Transactional
    @Override
    public Tag updateTag(Long id, Tag tag) {
        Tag t = tagRepository.getById(id);
        if (t == null) {
            throw new NotFoundException("不存在类型");
        }
        BeanUtils.copyProperties(tag, t);
        return tagRepository.save(t);

    }

    @Transactional
    @Override
    public void deleteTag(Long id) {
        tagRepository.deleteById(id);
    }


    //@Autowired
    //TypeRepository typeRepository;
    //
    //@Transactional
    //@Override
    //public Type saveType(Type type) {
    //    return typeRepository.save(type);
    //}
    //
    //@Override
    //public Type getType(Long id) {
    //    return typeRepository.getById(id);
    //}
    //
    //@Override
    //public Type getTypeByName(String name) {
    //    return typeRepository.findByName(name);
    //}
    //
    //@Override
    //public Page<Type> listType(Pageable pageable) {
    //    return typeRepository.findAll(pageable);
    //}
    //
    //@Transactional
    //@Override
    //public Type updateType(Long id, Type type) {
    //    Type t = typeRepository.getById(id);
    //    if (t == null) {
    //        throw new NotFoundException("不存在该类型");
    //    }
    //    BeanUtils.copyProperties(type, t);
    //    return typeRepository.save(t);
    //}
    //@Transactional
    //@Override
    //public void deleteType(Long id) {
    //    typeRepository.deleteById(id);
    //}
}
