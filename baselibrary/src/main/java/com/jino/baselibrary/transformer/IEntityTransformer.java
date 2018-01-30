package com.jino.baselibrary.transformer;

import java.util.Collection;

/**
 * Created by JINO on 2018/1/30.
 */

public interface IEntityTransformer<Entity, T> {

    /**
     * 将实体转换成目标实体
     *
     * @param entity 待转实体
     * @return 返回目标实体
     */
    T transform(Entity entity);

    /**
     * 将实体列表转换为目标实体的列表
     *
     * @param entities 待转实体列表
     * @return 目标实体的列表
     */
    Collection<T> transform(Collection<Entity> entities);
}
