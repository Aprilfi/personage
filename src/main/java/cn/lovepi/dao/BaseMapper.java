package cn.lovepi.dao;

/**
 * @Author: WuHongLin
 * @Description: 将通用的方法抽取在BaseMapper中，
 * @Date: 16:23 2018\5\31 0031
 */
public interface BaseMapper<T> {

    int insert(T entity);

    int insertSelective(T entity);

    int deleteByPrimaryKey(String id);

    T selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(T entity);

    int updateByPrimaryKey(T entity);

}
