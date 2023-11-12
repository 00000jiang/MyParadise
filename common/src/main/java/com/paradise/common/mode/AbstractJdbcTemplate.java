package com.paradise.common.mode;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.Map;

/**
 * @author hc
 * @date 2023-4-25 15:21
 */
public interface AbstractJdbcTemplate<T> {

    /**
     * 添加
     * @param o
     * @return
     */
    public T addData(Object o);

    /**
     * 根据ID修改对象
     * @param id
     * @param map
     * @return
     */
    public T updateData(String id, Map<String,Object> map);

    /**
     * 修改对象
     * @param o
     * @return
     */
    public T updateData(Object o);

    /**
     * 根据ID删除记录
     * @param id
     * @return
     */
    public boolean deleteData(String id);


    /**
     * 根据ID查询对象
     * @param id
     * @return
     */
    public Object getByIdData(String id);


    /**
     * 根据ID查询对象
     * @param map
     * @return
     */
    public Object getSelectData(Map<String,Object> map);


    /**
     * 根据ID查询对象
     * @param map
     * @return
     */
    public Object getSelectPage(Page<Object> page, Map<String,Object> map);

}
