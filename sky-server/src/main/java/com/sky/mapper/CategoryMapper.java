package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.annotation.AutoFill;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.enumeration.OperationType;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CategoryMapper {
    @Insert("insert into category(id,type,name,sort,status,create_time,update_time,create_user,update_user) " +
            "values(#{id}, #{type}, #{name}, #{sort}, #{status}, #{createTime}, #{updateTime}, #{createUser}, #{updateUser})")
    @AutoFill(value = OperationType.INSERT)
    void insertCategory(Category category);

    @Delete("delete from category where id=#{id}")
    void deleteCategory(Long id);
    @AutoFill(value = OperationType.UPDATE)
    void updateCategory(Category category);

    List<Category> pageQuery(CategoryPageQueryDTO categoryPageQueryDTO);

    @Select("select * from category where type=#{type} and status=#{status}")
    List<Category> list(Category category);
}
