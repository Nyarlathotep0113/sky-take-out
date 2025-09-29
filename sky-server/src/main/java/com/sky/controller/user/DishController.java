package com.sky.controller.user;

import com.sky.constant.StatusConstant;
import com.sky.entity.Dish;
import com.sky.result.Result;
import com.sky.service.DishService;
import com.sky.vo.DishVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("userDishController")
@RequestMapping("/use/dish")
@Slf4j
@Api(tags = "菜品管理")
public class DishController {
    @Autowired
    private DishService dishService;
    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping("/list")
    @ApiOperation("查询菜品列表")
    public Result<List<DishVO>> list(Long categoryId){

        //查询redis中是否存在菜品数据
        String key="dish_"+categoryId;
        List<DishVO> list=(List<DishVO>) redisTemplate.opsForValue().get(key);
        if(list!=null&&list.size()>0){
            return Result.success(list);
        }

        Dish dish=new Dish();
        dish.setCategoryId(categoryId);
        dish.setStatus(StatusConstant.ENABLE);
        List<DishVO> dishVOList=dishService.listWithFlavor(dish);
        //将查询到的菜品数据存入redis中
        redisTemplate.opsForValue().set(key,dishVOList);
        return Result.success(dishVOList);
    }

}
