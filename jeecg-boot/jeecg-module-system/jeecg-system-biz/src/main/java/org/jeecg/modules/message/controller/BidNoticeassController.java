package org.jeecg.modules.message.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.common.system.base.controller.JeecgController;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.message.entity.BidNotice;
import org.jeecg.modules.message.enums.PushPlanStatusEnum;
import org.jeecg.modules.message.service.IBidsNoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @Description: 公告管理
 * @Author: jeecg-boot
 * @Date:   2022-05-06
 * @Version: V1.0
 */
@Api(tags="公告管理")
@RestController
@RequestMapping("/bidding/bidNotice")
@Slf4j
public class BidNoticeassController extends JeecgController<BidNotice, IBidsNoticeService> {
    @Autowired
    private IBidsNoticeService bidNoticeService;

    /**
     * 分页列表查询
     *
     * @param bidNotice
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
//    @AutoLog(value = "公告管理-分页列表查询")
    @ApiOperation(value="公告管理-分页列表查询", notes="公告管理-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(BidNotice bidNotice,
                                   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                                   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                                   HttpServletRequest req) {
        if(oConvertUtils.isNotEmpty(bidNotice.getTitle())){
            bidNotice.setTitle("*"+bidNotice.getTitle()+"*");
        }
        QueryWrapper<BidNotice> queryWrapper = QueryGenerator.initQueryWrapper(bidNotice, req.getParameterMap());
        Page<BidNotice> page = new Page<BidNotice>(pageNo, pageSize);
        queryWrapper.lambda()
                .orderByAsc(BidNotice::getIsTop).orderByDesc(BidNotice::getSort);
        IPage<BidNotice> pageList = bidNoticeService.page(page, queryWrapper);
        return Result.OK(pageList);
    }

    /**
     *   添加
     *
     * @param bidNotice
     * @return
     */
    @AutoLog(value = "公告管理-添加")
    @ApiOperation(value="公告管理-添加", notes="公告管理-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody BidNotice bidNotice) {
        int aCount = (int) bidNoticeService.count(new LambdaQueryWrapper<BidNotice>().eq(BidNotice::getIsTop,"20"));
        bidNotice.setSort(aCount+1);
        bidNotice.setIsTop("20");
        bidNotice.setStatus(20);
        bidNotice.setVisit(0);
        bidNoticeService.save(bidNotice);
        return Result.OK("添加成功！");
    }

    @ApiOperation(value="公告管理-批量添加")
    @PostMapping(value = "/saveBatch")
    public Result<?> saveBatch(@RequestBody A a) {
        bidNoticeService.saveBatch(a.obstetricsIndexList);
        return Result.OK("批量添加成功！");
    }


    /**
     * 递增指定公告的访问数
     *
     * @param id
     * @return
     */
    @ApiOperation(value="递增指定公告的访问数", notes="递增")
    @GetMapping("/increVisit")
    public Result<?> increVisit(@RequestParam(name="id",required=true) String id) {
        int visit = bidNoticeService.increVisit(id);
        return Result.ok(visit);
    }

    /**
     *  发布
     *
     * @param id
     * @return
     */
    @AutoLog(value = "通知公告-发布")
    @ApiOperation(value="通知公告-发布", notes="通知公告-发布")
    @GetMapping(value = "/release")
    public Result<?> release(@RequestParam(name="id",required=true) String id) {
        BidNotice bidNotice = bidNoticeService.getById(id);
        bidNotice.setStatus(10);
        bidNotice.setReleaseDate(new Date());
        bidNoticeService.updateById(bidNotice);
        return Result.OK("发布成功!");
    }

    /**
     *  下架
     *
     * @param id
     * @return
     */
    @AutoLog(value = "通知公告-下架")
    @ApiOperation(value="通知公告-下架", notes="通知公告-下架")
    @GetMapping(value = "/notRelease")
    public Result<?> notRelease(@RequestParam(name="id",required=true) String id) {
        BidNotice bidNotice = bidNoticeService.getById(id);
        bidNotice.setStatus(20);
        bidNoticeService.updateById(bidNotice);
        return Result.OK("下架成功!");
    }

    /**
     *  置顶
     *
     * @param id
     * @return
     */
    @AutoLog(value = "通知公告-置顶")
    @ApiOperation(value="通知公告-置顶", notes="通知公告-置顶")
    @GetMapping(value = "/top")
    public Result<?> top(@RequestParam(name="id",required=true) String id) {
        int aCount = (int) bidNoticeService.count(new LambdaQueryWrapper<BidNotice>().eq(BidNotice::getIsTop,"10"));
        BidNotice bidNotice = bidNoticeService.getById(id);
        bidNoticeService.updateSortByDel(bidNotice);
        bidNotice.setIsTop("10");
        bidNotice.setSort(aCount+1);
        bidNoticeService.updateById(bidNotice);
        return Result.OK("置顶成功!");
    }

    /**
     *  取消置顶
     *
     * @param id
     * @return
     */
    @AutoLog(value = "通知公告-取消置顶")
    @ApiOperation(value="通知公告-取消置顶", notes="通知公告-取消置顶")
    @GetMapping(value = "/notTop")

    public Result<?> notTop(@RequestParam(name="id",required=true) String id) {
        int aCount = (int) bidNoticeService.count(new LambdaQueryWrapper<BidNotice>().eq(BidNotice::getIsTop,"20"));
        BidNotice bidNotice = bidNoticeService.getById(id);
        bidNoticeService.updateSortByDel(bidNotice);
        bidNotice.setIsTop("20");
        bidNotice.setSort(aCount+1);
        bidNoticeService.updateById(bidNotice);
        return Result.OK("取消置顶成功!");
    }

    /**
     *  上移
     *
     * @param id
     * @return
     */
    @AutoLog(value = "通知公告-上移")
    @ApiOperation(value="通知公告-上移", notes="通知公告-上移")
    @GetMapping(value = "/up")
    public Result<?> up(@RequestParam(name="id",required=true) String id) {
        BidNotice bidNotice = bidNoticeService.getById(id);
        int aSort = bidNotice.getSort();
        if(aSort != 1) {
            BidNotice aBidNotice = bidNoticeService.getOne(new QueryWrapper<BidNotice>().eq("sort", aSort - 1).eq("is_top",bidNotice.getIsTop()));
            bidNotice.setSort(aSort - 1);
            aBidNotice.setSort(aSort);
            bidNoticeService.updateById(bidNotice);
            bidNoticeService.updateById(aBidNotice);

        }
        return Result.OK("上移成功!");
    }

    /**
     *  下移
     *
     * @param id
     * @return
     */
    @AutoLog(value = "通知公告-下移")
    @ApiOperation(value="通知公告-下移", notes="通知公告-下移")
    @GetMapping(value = "/down")
    public Result<?> down(@RequestParam(name="id",required=true) String id) {
        BidNotice bidNotice = bidNoticeService.getById(id);
        int aSort = bidNotice.getSort();
        int aCount= (int) bidNoticeService.count();
        if(aSort != aCount) {
            BidNotice aBidNotice= bidNoticeService.getOne(new QueryWrapper<BidNotice>().eq("sort", aSort + 1).eq("is_top",bidNotice.getIsTop()));
            bidNotice.setSort(aSort + 1);
            aBidNotice.setSort(aSort);
            bidNoticeService.updateById(bidNotice);
            bidNoticeService.updateById(aBidNotice);
        }
        return Result.OK("下移成功!");
    }

    /**
     *  编辑
     *
     * @param bidNotice
     * @return
     */
    @AutoLog(value = "公告管理-编辑")
    @ApiOperation(value="公告管理-编辑", notes="公告管理-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody BidNotice bidNotice) {
        bidNoticeService.updateById(bidNotice);
        return Result.OK("编辑成功!");
    }

    /**
     *   通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "公告管理-通过id删除")
    @ApiOperation(value="公告管理-通过id删除", notes="公告管理-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name="id",required=true) String id) {

        BidNotice bidNotice = bidNoticeService.getById(id);
        bidNoticeService.updateSortByDel(bidNotice);
        bidNoticeService.removeById(id);
        return Result.OK("删除成功!");
    }


    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "公告管理-通过id查询")
    @ApiOperation(value="公告管理-通过id查询", notes="公告管理-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
        BidNotice bidNotice = bidNoticeService.getById(id);
        if(bidNotice==null) {
            return Result.error("未找到对应数据");
        }
        return Result.OK(bidNotice);
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @ApiOperation(value="公告管理-批量删除", notes="指标字典-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
        List<String> a = Arrays.asList(ids.split(","));
        this.bidNoticeService.removeByIds(a);
        return Result.OK("批量删除成功！");
    }


    /**
     * 修改通知状态
     * @param
     *@return
     */
//    @RequiresPermissions("groupPlan:putPlan")
    @RequestMapping(value = "/putPlan", method = RequestMethod.POST)
    @ApiOperation(value = "修改通知状态")
    public Result<?> putPlan(@RequestParam (name = "id") String id) {
        BidNotice obstetricsGroupPushPlan= bidNoticeService.getById(id);
        if (obstetricsGroupPushPlan == null) {
            return Result.error("修改失败，找不到对应的信息");
        }
        obstetricsGroupPushPlan.setStatus(PushPlanStatusEnum.TERMINATED.getCode());
        bidNoticeService.updateById(obstetricsGroupPushPlan);
        return Result.OK();
    }


}