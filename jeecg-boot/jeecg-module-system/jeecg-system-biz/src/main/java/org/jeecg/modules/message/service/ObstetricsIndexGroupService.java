package org.jeecg.modules.message.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.common.api.vo.Result;

import org.jeecg.modules.message.entity.ObstetricsIndexGroup;
import org.jeecg.modules.message.vo.ObstetricsIndexGroupDTO;
import org.jeecg.modules.message.vo.ObstetricsIndexGroupVO;

import java.util.List;

/**
 * @Description: 指标组
 * @Author: hanshj
 * @Date:   2022-10-11
 * @Version: V1.0
 */
public interface ObstetricsIndexGroupService extends IService<ObstetricsIndexGroup> {

    IPage<ObstetricsIndexGroupDTO> listPage(Page<ObstetricsIndexGroupDTO> page, ObstetricsIndexGroupVO obstetricsIndexGroupVo);

    List<ObstetricsIndexGroup> listCopy(String indexGroupName);

    int countCopy(String indexGroupName);

    Result<?> addIndexGroup(ObstetricsIndexGroupVO obstetricsIndexGroupVO);
}
