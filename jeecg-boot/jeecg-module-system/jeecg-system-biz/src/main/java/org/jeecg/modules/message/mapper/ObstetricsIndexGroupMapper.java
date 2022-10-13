package org.jeecg.modules.message.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import org.jeecg.modules.message.entity.ObstetricsIndexGroup;
import org.jeecg.modules.message.vo.ObstetricsIndexGroupDTO;

import java.util.List;


/**
 * @Description: 指标字典
 * @Author: jeecg-boot
 * @Date:   2022-10-11
 * @Version: V1.0
 */
public interface ObstetricsIndexGroupMapper extends BaseMapper<ObstetricsIndexGroup> {

    IPage<ObstetricsIndexGroupDTO> listPage(Page<ObstetricsIndexGroupDTO> page, String indexGroupName, List<String> crowdIdList, Integer indexState);

    List<ObstetricsIndexGroup> listCopy(String indexGroupName);

    int countCopy(String indexGroupName);
}
