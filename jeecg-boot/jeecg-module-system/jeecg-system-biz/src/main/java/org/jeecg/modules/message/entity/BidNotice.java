package org.jeecg.modules.message.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.jeecg.common.aspect.annotation.Dict;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * @Description: 公告管理
 * @Author: jeecg-boot
 * @Date:   2022-05-06
 * @Version: V1.0
 */
@Data
@TableName("bid_notice")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="bid_notice对象", description="公告管理")
public class BidNotice implements Serializable {
    private static final long serialVersionUID = 1L;

    /**主键*/
    @TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
    private java.lang.String id;
    /**创建人*/
    @ApiModelProperty(value = "创建人")
    private java.lang.String createBy;
    /**创建日期*/
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建日期")
    private java.util.Date createTime;
    /**更新人*/
    @ApiModelProperty(value = "更新人")
    private java.lang.String updateBy;
    /**更新日期*/
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "更新日期")
    private java.util.Date updateTime;

    /**标题*/
    @Excel(name = "标题", width = 15)
    @ApiModelProperty(value = "标题")
    private java.lang.String title;

    /**所属部门*/
    @ApiModelProperty(value = "所属部门")
    private java.lang.String sysOrgCode;

    /**内容*/
    @Excel(name = "内容", width = 15)
    @ApiModelProperty(value = "内容")
    private java.lang.String content;
    /**是否置顶*/
    @Excel(name = "是否置顶", width = 15, dicCode = "notice_top")
    @Dict(dicCode = "notice_top")
    @ApiModelProperty(value = "是否置顶")
    private java.lang.String isTop;
    /**排序*/
    @Excel(name = "排序", width = 15)
    @ApiModelProperty(value = "排序")
    private java.lang.Integer sort;
    /**状态*/
    @Excel(name = "状态", width = 15, dicCode = "notice_status")
    @Dict(dicCode = "notice_status")
    @ApiModelProperty(value = "状态")
    private Integer status;
    /**发布时间*/
    @Excel(name = "发布时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "发布时间")
    private java.util.Date releaseDate;
    /**附件*/
    @Excel(name = "附件", width = 15)
    @ApiModelProperty(value = "附件")
    private java.lang.String annex;
    /**访问量*/
    @Excel(name = "访问量", width = 15)
    @ApiModelProperty(value = "访问量")
    private java.lang.Integer visit;
}
