package cn.lovepi.service;

/**
 * @Author: WuHongLin
 * @Description: 将通用的业务方法抽取到BaseService中
 * @Date: 16:25 2018\5\31 0031
 */
public interface BaseService<T> {

    int insert(T entity);

    int insertSelective(T entity);

    int deleteByPrimaryKey(String id);

    T selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(T entity);

    int updateByPrimaryKey(T entity);

}
