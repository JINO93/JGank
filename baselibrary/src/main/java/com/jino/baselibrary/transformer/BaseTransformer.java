package com.jino.baselibrary.transformer;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 实体转换的基类，负责将一个实体转换为另一个实体
 * Created by JINO on 2018/1/30.
 */

public abstract class BaseTransformer<Entity, T> implements IEntityTransformer<Entity, T> {
    @Override
    public Collection<T> transform(Collection<Entity> entities) {
        ArrayList<T> res = new ArrayList<>();
        if (entities != null && entities.size() != 0) {
            for (Entity entity : entities) {
                res.add(transform(entity));
            }
        }
        return res;
    }
}
