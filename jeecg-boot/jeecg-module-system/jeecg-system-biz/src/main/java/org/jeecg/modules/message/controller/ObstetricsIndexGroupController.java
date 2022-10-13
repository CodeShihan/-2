package org.jeecg.modules.message.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;

import org.jeecg.modules.message.entity.ObstetricsIndex;
import org.jeecg.modules.message.entity.ObstetricsIndexGroup;
import org.jeecg.modules.message.service.ObstetricsIndexGroupService;
import org.jeecg.modules.message.service.ObstetricsIndexService;
import org.jeecg.modules.message.vo.ObstetricsIndexGroupDTO;
import org.jeecg.modules.message.vo.ObstetricsIndexGroupVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


/**
* @Description: 指标字典
* @Author: jeecg-boot
* @Date:   2022-10-11
* @Version: V1.0
*/
@Slf4j
@Api(tags="指标组")
@RestController
@RequestMapping("/indexGroup")
public class ObstetricsIndexGroupController {
   @Autowired
   private ObstetricsIndexGroupService obstetricsIndexGroupService;

   @Resource
   private ObstetricsIndexService obstetricsIndexService;

   /**
    * 分页列表查询
    *
    * @param obstetricsIndexGroupVo
    * @param pageNo
    * @param pageSize
    * @return
    */
   @ApiOperation(value="指标组-分页列表查询", notes="指标组-分页列表查询")
   @GetMapping(value = "/indexGroupListPage")
   public Result<IPage<ObstetricsIndexGroupDTO>> indexGroupListPage(ObstetricsIndexGroupVO obstetricsIndexGroupVo,
                                                                    @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                                                                    @RequestParam(name="pageSize", defaultValue="10") Integer pageSize) {
//       QueryWrapper<ObstetricsIndexGroup> queryWrapper = QueryGenerator.initQueryWrapper(bstetricsIndexGroup, req.getParameterMap());
       Page<ObstetricsIndexGroupDTO> page = new Page<ObstetricsIndexGroupDTO>(pageNo, pageSize);
       IPage<ObstetricsIndexGroupDTO> pageList = obstetricsIndexGroupService.listPage(page, obstetricsIndexGroupVo);
       return Result.OK(pageList);
   }



   /**
    * 添加
    *
    * @param obstetricsIndexGroupVO
    * @return
    */
   @ApiOperation(value="指标组添加")
   @RequestMapping(value = "/addIndexGroup",method = RequestMethod.POST)
   public Result addIndexGroup(@RequestBody ObstetricsIndexGroupVO obstetricsIndexGroupVO) {
       return obstetricsIndexGroupService.addIndexGroup(obstetricsIndexGroupVO);
   }




    @ApiOperation(value="指标组-复制", notes="指标组管理-复制")
    @PostMapping(value = "/copy")
    public Result<?> copy(@RequestParam(name="indexGroupId",required=true) String indexGroupId) {
        QueryWrapper<ObstetricsIndexGroup> queryWrapper = new QueryWrapper<ObstetricsIndexGroup>();
        queryWrapper.eq("id",indexGroupId);
        ObstetricsIndexGroup obstetricsIndexGroup = obstetricsIndexGroupService.getOne(queryWrapper);
        if (obstetricsIndexGroup != null ){
            ObstetricsIndexGroup ob = new ObstetricsIndexGroup();
            List<ObstetricsIndexGroup> obstetricsIndexGroupList=  obstetricsIndexGroupService.listCopy(obstetricsIndexGroup.getIndexGroupName());
            obstetricsIndexGroupList.forEach(p->{
                int cout = obstetricsIndexGroupService.countCopy(obstetricsIndexGroup.getIndexGroupName());
                String name = obstetricsIndexGroup.getIndexGroupName()+"-"+"副"+cout;
                BeanUtils.copyProperties(obstetricsIndexGroup,ob);
                ob.setId(null);
                ob.setIndexGroupName(name);
                obstetricsIndexGroupService.save(ob);
            });
        }
        return Result.OK("复制成功！");
    }
//
//
//   /**
//    * 编辑
//    * @param obstetricsIndexGroup
//    * @return
//    */
//   @ApiOperation(value="指标组-编辑", notes="指标组-编辑")
//   @RequestMapping(value = "/edit", method = {RequestMethod.PUT, RequestMethod.POST})
//   public Result<?> edit(@RequestBody ObstetricsIndexGroup obstetricsIndexGroup) {
//       QueryWrapper<ObstetricsIndexGroup> queryWrapper = new QueryWrapper<ObstetricsIndexGroup>();
//       queryWrapper.eq("id",obstetricsIndexGroup.getId());
//       ObstetricsIndexGroup obstetricsIndexGroupOne = obstetricsIndexGroupService.getOne(queryWrapper);
////       if (obstetricsIndexGroupOne == null) {
////           return Result.errorMsg("编辑失败，找不到对应的信息");
////       }
////       if(obstetricsIndexGroupOne.getIndexGroupName().equals(obstetricsIndexGroup.getIndexGroupName())){
////           return Result.errorMsg("该名称已经存在");
////       }
////       if(obstetricsIndexGroupOne.getCrowdIds().equals(obstetricsIndexGroup.getCrowdIds())){
////           return Result.errorMsg("该群组已经存在");
////       }
////       if(obstetricsIndexGroup.getCrowdIds() == null){
////           return Result.errorMsg("“适用群组不可为空");
////       }
////       if(obstetricsIndexGroupOne.getRemark().equals(obstetricsIndexGroup.getRemark())){
////           return Result.errorMsg("该备注已经存在");
////       }
//       obstetricsIndexGroup.setUpdateTime(new Date());
//       obstetricsIndexGroupService.updateById(obstetricsIndexGroup);
//       return Result.OK("编辑成功!");
//   }
//
//
//
//   /**
//    * 批量删除
//    *
//    * @param ids
//    * @return
//    */
//   @ApiOperation(value="指标组-批量删除", notes="指标字典-批量删除")
//   @DeleteMapping(value = "/deleteBatch")
//   public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
//       this.obstetricsIndexGroupService.removeByIds(Arrays.asList(ids.split(",")));
//       return Result.OK("批量删除成功！");
//   }




}
