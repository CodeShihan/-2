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
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Description: 指标字典
 * @Author: jeecg-boot
 * @Date:   2022-10-11
 * @Version: V1.0
 */
@Data
@TableName("obstetrics_index_gruop_real")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="obstetrics_index_gruop_real对象", description="指标字典")
public class ObstetricsIndexGroupReal {
    
	/**ID主键*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "ID主键")
	private String id;
	/**指标组id*/
	@Excel(name = "指标组id", width = 15)
    @ApiModelProperty(value = "指标组id")
	private String indexGroupId;
	/**适用群组Id*/
	@Excel(name = "指标Id", width = 15)
    @ApiModelProperty(value = "指标Id")
	private String indexId;
	/**适用患者群组Id*/
	@Excel(name = "适用患者群组Id", width = 15)
    @ApiModelProperty(value = "适用患者群组Id")
	private String crowdId;
	/**创建时间*/
	@Excel(name = "创建时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间")
	private Date createTime;
	/**修改时间*/
	@Excel(name = "修改时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "修改时间")
	private Date updateTime;
	/**0有效1无效*/
	@Excel(name = "0有效1无效", width = 15)
    @ApiModelProperty(value = "0有效1无效")
	private Integer delFlag;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
	private String remark;
}
