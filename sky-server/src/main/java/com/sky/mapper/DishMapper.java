package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.annotation.AutoFill;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.enumeration.OperationType;
import com.sky.vo.DishVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DishMapper {
    /**
     * 统计某个分类下的菜品数量
     * @param categoryId
     * @return
     */
    @Select("select count(id) from dish where category_id = #{categoryId}")
    Integer countByCategoryId(Long categoryId);

    /**
     *
     * @param dish
     */
    @AutoFill(value= OperationType.INSERT)
    void insert(Dish dish);

    Page<DishVO> pageQuery(DishPageQueryDTO dishPageQueryDTO);

    @Select("select * from dish where id = #{id}")
    Dish getById(Long id);
    @Delete("delete from dish where id = #{id}")
    void deleteById(Long id);

    void deleteBatchByIds(List<Long> ids);
    @AutoFill(value= OperationType.UPDATE)
    void update(Dish dish);

    List<Dish> list(Dish dish);
    List<Dish> getDishesBySetmealId(Long setmealId);
}
