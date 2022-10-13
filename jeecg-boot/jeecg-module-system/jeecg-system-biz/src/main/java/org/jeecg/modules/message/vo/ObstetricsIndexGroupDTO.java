package org.jeecg.modules.message.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 功能描述
 *
 * @author: scott
 * @date: 2022年10月11日 11:54
 */
@Data
public class ObstetricsIndexGroupDTO {

    /**ID主键*/

    @ApiModelProperty(value = "ID主键")
    private String id;
    /**指标组名称*/

    @ApiModelProperty(value = "指标组名称")
    private String indexGroupName;

    @ApiModelProperty(value = "指标组名称")
    private String remark;

}
