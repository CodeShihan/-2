package org.jeecg.modules.message.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Description: 指标
 * @Author: jeecg-boot
 * @Date:   2022-10-11
 * @Version: V1.0
 */
@Data
@TableName("obstetrics_index")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ObstetricsIndex {
    
	/**ID主键*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "ID主键")
	private String id;
	/**指标组ID*/
	@Excel(name = "指标组ID", width = 15)
    @ApiModelProperty(value = "指标组ID")
	private String indexGruopId;
	/**指标分类名称*/
	@Excel(name = "指标分类名称", width = 15)
    @ApiModelProperty(value = "指标分类名称")
	private String indexCategory;
	/**指标分类名称code*/
	@Excel(name = "指标分类名称code", width = 15)
    @ApiModelProperty(value = "指标分类名称code")
	private String cateCode;
	/**指标项目名称*/
	@Excel(name = "指标项目名称", width = 15)
    @ApiModelProperty(value = "指标项目名称")
	private String indexName;
	/**参预警值最小值*/
	@Excel(name = "预警值最小值", width = 15)
    @ApiModelProperty(value = "预警值最小值")
	private java.math.BigDecimal alertMin;
	/**预警值最大值*/
	@Excel(name = "考预警值最大值", width = 15)
    @ApiModelProperty(value = "预警值最大值")
	private java.math.BigDecimal alertMax;
	/**指标单位*/
	@Excel(name = "指标单位", width = 15)
    @ApiModelProperty(value = "指标单位")
	private String indexUnit;
	/**指标项目编码*/
	@Excel(name = "指标项目编码", width = 15)
    @ApiModelProperty(value = "指标项目编码")
	private String indexCode;
	/**排序*/
	@Excel(name = "排序", width = 15)
    @ApiModelProperty(value = "排序")
	private Integer sort;
	/**创建人*/
	@Excel(name = "创建人", width = 15)
    @ApiModelProperty(value = "创建人")
	private String createBy;
	/**创建人id*/
	@Excel(name = "创建人id", width = 15)
    @ApiModelProperty(value = "创建人id")
	private String createById;
	/**创建时间*/
	@Excel(name = "创建时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间")
	private Date createTime;
	/**修改人*/
	@Excel(name = "修改人", width = 15)
    @ApiModelProperty(value = "修改人")
	private String updateBy;
	/**修改人id*/
	@Excel(name = "修改人id", width = 15)
    @ApiModelProperty(value = "修改人id")
	private String updateById;
	/**修改时间*/
	@Excel(name = "修改时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "修改时间")
	private Date updateTime;
	/**删除标志：0未删除1已删除*/
	@Excel(name = "删除标志：0未删除1已删除", width = 15)
    @ApiModelProperty(value = "删除标志：0未删除1已删除")
	private Integer delFlag;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
	private String remark;
}
