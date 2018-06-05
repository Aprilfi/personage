package cn.lovepi.service.impl;

import cn.lovepi.dao.BaseMapper;
import cn.lovepi.dao.UserMapper;
import cn.lovepi.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;

/**
 * @Author: WuHongLin
 * @Description: 使用initBaseMapper()将baseMapper实例化，service实现类是basemapper的类型
 * @Date: 14:27 2018\5\31 0031
 */
public class BaseServiceImpl<T> implements BaseService<T> {

    protected BaseMapper<T> baseMapper;

    @Autowired
    protected UserMapper userMapper;

    @PostConstruct
    private void initBaseMapper() throws Exception{

        //获取泛型的信息
        ParameterizedType type =(ParameterizedType) this.getClass().getGenericSuperclass();
        Class clazz = (Class)type.getActualTypeArguments()[0];

        //拼接成“泛型”Mapper字符串
        /**
         * *************************
         * 按照全称获取mapper的引用名称
         * *************************
         */
        String localField = clazz.getSimpleName().substring(0,1).toLowerCase()+clazz.getSimpleName().substring(1)+"Mapper";
        System.out.println("localField:" + localField);

        //通过反射来获取成员变量的值
        Field field=this.getClass().getSuperclass().getDeclaredField(localField);
        Field baseField = this.getClass().getSuperclass().getDeclaredField("baseMapper");

        //将baseDao来进行实例化
        baseField.set(this, field.get(this));

    }

    public int insert(T entity) {
        return baseMapper.insert(entity);
    }

    public int insertSelective(T entity) {
        return baseMapper.insertSelective(entity);
    }

    public int deleteByPrimaryKey(String id) {
        return baseMapper.deleteByPrimaryKey(id);
    }

    public T selectByPrimaryKey(String id) {
        return baseMapper.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(T entity) {
        return baseMapper.updateByPrimaryKeySelective(entity);
    }

    public int updateByPrimaryKey(T entity) {
        return baseMapper.updateByPrimaryKey(entity);
    }

}

