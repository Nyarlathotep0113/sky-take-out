package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.annotation.AutoFill;
import com.sky.entity.Setmeal;
import com.sky.vo.SetmealVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SetmealMapper {
    @Select("select count(id) from setmeal where category_id = #{categoryId}")
    Integer countByCategoryId(Long categoryId);

    @Insert("insert into setmeal(category_id,name, price, status, description, image, create_time,update_time,create_user,update_user) " +
            "values(#{categoryId}, #{name}, #{price}, #{status}, #{description}, #{image}, #{createTime},#{updateTime},#{createUser},#{updateUser})")
    @Options(useGeneratedKeys = true,keyProperty = "id")
    @AutoFill(com.sky.enumeration.OperationType.INSERT)
    void insert(Setmeal setmeal);

    Page<SetmealVO> queryPage(SetmealVO setmeal);

    void deleteBatch(List<Long> ids);

    @Select("select * from setmeal where id = #{id}")
    Setmeal getById(Long id);

    @AutoFill(com.sky.enumeration.OperationType.UPDATE)
    void update(Setmeal setmeal);
}
