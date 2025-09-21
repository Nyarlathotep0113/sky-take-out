package com.sky.service;

import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.entity.Employee;
import com.sky.result.PageResult;

import java.util.List;

public interface CategoryService {
    void addCategory(CategoryDTO categoryDTO);

    void deleteCategory(Long id);

    void updateCategory(CategoryDTO categoryDTO);

    PageResult pageQuery(CategoryPageQueryDTO categoryPageQueryDTO);

    void stopOrStartCategory(Integer status, Long id);

    List<Category> list(Integer type);
}
