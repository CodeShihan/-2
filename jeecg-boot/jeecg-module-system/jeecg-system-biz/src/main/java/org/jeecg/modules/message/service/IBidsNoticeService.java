package org.jeecg.modules.message.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.message.entity.BidNotice;

/**
 * @Description: 公告管理
 * @Author: jeecg-boot
 * @Date:   2022-05-06
 * @Version: V1.0
 */
public interface IBidsNoticeService extends IService<BidNotice> {
    void updateSortByDel(BidNotice bidNotice);
    int increVisit(String id);
}
