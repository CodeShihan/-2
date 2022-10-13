package org.jeecg.modules.message.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.segments.MergeSegments;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jeecg.common.api.vo.Result;

import org.jeecg.modules.message.entity.ObstetricsIndex;
import org.jeecg.modules.message.entity.ObstetricsIndexGroup;
import org.jeecg.modules.message.entity.ObstetricsIndexGroupReal;
import org.jeecg.modules.message.mapper.ObstetricsIndexGroupMapper;
import org.jeecg.modules.message.mapper.ObstetricsIndexGroupRealMapper;
import org.jeecg.modules.message.mapper.ObstetricsIndexMapper;
import org.jeecg.modules.message.service.ObstetricsIndexGroupService;
import org.jeecg.modules.message.service.ObstetricsIndexService;
import org.jeecg.modules.message.vo.ObstetricsIndexGroupDTO;
import org.jeecg.modules.message.vo.ObstetricsIndexGroupVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @Description: 指标字典
 * @Author: jeecg-boot
 * @Date:   2022-10-11
 * @Version: V1.0
 */
@Service
public class ObstetricsIndexGroupServiceImpl extends ServiceImpl<ObstetricsIndexGroupMapper, ObstetricsIndexGroup> implements ObstetricsIndexGroupService {

    @Resource
    private ObstetricsIndexGroupMapper obstetricsIndexGroupMapper;

    @Resource
    private ObstetricsIndexGroupRealMapper obstetricsIndexGroupRealMapper;

    @Resource
    private ObstetricsIndexMapper obstetricsIndexMapper;

    @Resource
    private ObstetricsIndexService  obstetricsIndexService;

    @Override
    public IPage<ObstetricsIndexGroupDTO> listPage(Page<ObstetricsIndexGroupDTO> page, ObstetricsIndexGroupVO obstetricsIndexGroupVo) {
        IPage<ObstetricsIndexGroupDTO> list = obstetricsIndexGroupMapper.listPage(page, obstetricsIndexGroupVo.getIndexGroupName(),obstetricsIndexGroupVo.getCrowdIdList(),obstetricsIndexGroupVo.getIndexState());
        return list;
    }

    @Override
    public List<ObstetricsIndexGroup> listCopy(String indexGroupName) {
        List<ObstetricsIndexGroup> obstetricsIndexGroupList = obstetricsIndexGroupMapper.listCopy(indexGroupName);

        return obstetricsIndexGroupList;
    }

    @Override
    public int countCopy(String indexGroupName) {
        int count = obstetricsIndexGroupMapper.countCopy(indexGroupName) -1;
        return count ;
    }


    @Override
    public Result<?> addIndexGroup(ObstetricsIndexGroupVO obstetricsIndexGroupVO) {



            //新增
            ObstetricsIndexGroup obstetricsIndexGroup = new ObstetricsIndexGroup();
            obstetricsIndexGroup.setId(null);
            obstetricsIndexGroup.setIndexGroupName(obstetricsIndexGroupVO.getIndexGroupName());
            if(obstetricsIndexGroupVO.getCrowdIdList() !=null){
                obstetricsIndexGroup.setCrowdIds(JSON.toJSONString(obstetricsIndexGroupVO.getCrowdIdList()));
            }
            obstetricsIndexGroup.setIndexState(obstetricsIndexGroupVO.getIndexState());
            obstetricsIndexGroup.setCreateTime(new Date());
            obstetricsIndexGroup.setUpdateTime(new Date());
            obstetricsIndexGroup.setDelFlag(0);
            obstetricsIndexGroup.setRemark(obstetricsIndexGroup.getRemark());
            if (baseMapper.insert(obstetricsIndexGroup) < 1) {
                return Result.error("新增失败");
            }


         obstetricsIndexGroupVO.getIndexList().forEach(p->{
            p.setIndexGruopId(obstetricsIndexGroup.getId());

        });
          List<ObstetricsIndex> obstetricsIndexList = obstetricsIndexGroupVO.getIndexList();
          obstetricsIndexService.saveBatch(obstetricsIndexList);

            ObstetricsIndexGroupReal obstetricsIndexGroupReal = new ObstetricsIndexGroupReal();
            obstetricsIndexGroupReal.setId(null);
//            obstetricsIndexGroupReal.setIndexGroupId(group.getId());
            obstetricsIndexGroupReal.setIndexGroupId(obstetricsIndexGroup.getId());
//            obstetricsIndexGroupReal.setIndexId(obstetricsIndexGroup.getId());
            obstetricsIndexGroupReal.setCrowdId(JSON.toJSONString(obstetricsIndexGroupVO.getCrowdIdList()));
            obstetricsIndexGroupReal.setCreateTime(new Date());
            obstetricsIndexGroupReal.setUpdateTime(new Date());
            obstetricsIndexGroupReal.setDelFlag(0);
            obstetricsIndexGroupReal.setRemark(null);
            if (obstetricsIndexGroupRealMapper.insert(obstetricsIndexGroupReal) < 1) {
                return Result.error("新增失败");
            }

            return Result.OK("新增成功");
        }

}
