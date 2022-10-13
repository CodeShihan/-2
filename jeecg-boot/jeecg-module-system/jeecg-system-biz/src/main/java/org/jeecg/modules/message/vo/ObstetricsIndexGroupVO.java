package org.jeecg.modules.message.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.jeecg.modules.message.entity.ObstetricsIndex;


import java.util.List;

/**
 * 功能描述
 *
 * @author: shihan
 * @date: 2022年10月11日 11:44
 */
@Data
@ApiModel(description = "指标组查询请求类")
public class ObstetricsIndexGroupVO {

//    @NotBlank(message = "标题不能为空")
    @ApiModelProperty(value = "指标组名称")
    private String indexGroupName;

//    @NotBlank(message = "状态不能为空")
    @ApiModelProperty(value = "指标组状态")
    private Integer indexState;

//    @NotBlank(message = "备注不能为空")
//    @Length(max = 500, message = "发文号字符串长度不能超过500")
    @ApiModelProperty(value = "指标组状态")
    private String remark;

    @ApiModelProperty(value = "指标群组id")
    private List<String> crowdIdList;

    @ApiModelProperty(value = "指标群组")
    private List<ObstetricsIndex> indexList;

}
