package org.jeecg.modules.message.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jeecg.modules.message.entity.BidNotice;
import org.jeecg.modules.message.mapper.BidsNoticeMapper;
import org.jeecg.modules.message.service.IBidsNoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Description: 公告管理
 * @Author: jeecg-boot
 * @Date:   2022-05-06
 * @Version: V1.0
 */
@Service
public class BidNoticssServiceImpl extends ServiceImpl<BidsNoticeMapper, BidNotice> implements IBidsNoticeService {
    @Resource
    private BidsNoticeMapper bidNoticeMapper;
    @Override
    public void updateSortByDel(BidNotice bidNotice) {
        bidNoticeMapper.updateSortByDel(bidNotice);
    }
    @Override
    public synchronized int increVisit(String id) {
        BidNotice bidNotice = bidNoticeMapper.selectById(id);
        bidNotice.setVisit(bidNotice.getVisit()+1);
        bidNoticeMapper.updateById(bidNotice);
        return bidNotice.getVisit();
    }
}

