package org.jeecg.modules.message.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import org.jeecg.modules.message.entity.ObstetricsIndex;


/**
 * @Description: 指标
 * @Author: jeecg-boot
 * @Date:   2022-10-11
 * @Version: V1.0
 */
public interface ObstetricsIndexMapper extends BaseMapper<ObstetricsIndex> {

    boolean update(ObstetricsIndex obstetricsIndex);
}
