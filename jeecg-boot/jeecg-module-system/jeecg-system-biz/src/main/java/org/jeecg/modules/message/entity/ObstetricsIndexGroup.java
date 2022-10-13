package org.jeecg.modules.message.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * @Description: 指标字典
 * @Author: jeecg-boot
 * @Date:   2022-10-11
 * @Version: V1.0
 */
@Data
@TableName("obstetrics_index_group")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ObstetricsIndexGroup {
    
	/**ID主键*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "ID主键")
	private String id;

	/**指标组名称*/
	@Excel(name = "指标组名称", width = 15)
    @ApiModelProperty(value = "指标组名称")
	private String indexGroupName;

	/**指标组状态（O启用,1停用）*/
	@Excel(name = "指标组状态（O启用,1停用）", width = 15)
    @ApiModelProperty(value = "指标组状态（O启用,1停用）")
	private Integer indexState;

	/**适用群组,多个逗号隔开*/
	@Excel(name = "适用群组,多个逗号隔开", width = 15)
    @ApiModelProperty(value = "适用群组,多个逗号隔开")
	private String crowdIds;

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
	@TableField(value = "create_time", fill = FieldFill.INSERT)
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
	@TableField(fill = FieldFill.INSERT_UPDATE)
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
