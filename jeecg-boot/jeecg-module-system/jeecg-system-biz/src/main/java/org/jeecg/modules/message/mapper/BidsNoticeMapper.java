package org.jeecg.modules.message.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.jeecg.modules.message.entity.BidNotice;

/**
 * @Description: 公告管理
 * @Author: jeecg-boot
 * @Date:   2022-05-06
 * @Version: V1.0
 */

public interface BidsNoticeMapper extends BaseMapper<BidNotice> {
    void updateSortByDel(BidNotice bidNotice);
}
