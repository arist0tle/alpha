package com.geektcp.alpha.driver.mybatis.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.geektcp.alpha.driver.mybatis.model.Card;
import com.geektcp.alpha.driver.mybatis.dao.CardDao;
import com.geektcp.alpha.driver.mybatis.service.CardService;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author tanghaiyang
 * @since 2018-08-31
 */
@Service
@Slf4j
public class CardServiceImpl extends ServiceImpl<CardDao, Card> implements CardService {

    @Override
    public boolean addIdCard(Card card) {
        if (queryIdCardByCode(card.getCode()) == null)
            return this.insert(card);
        return true;
    }

    @Override
    public Card queryIdCardByCode(String code) {
        Wrapper<Card> queryWrapper = new EntityWrapper<>();
        queryWrapper.eq(Card.CODE, code);
        List<Card> idCardList = this.selectList(queryWrapper);

        if (idCardList == null || idCardList.isEmpty())
            return null;

        if (idCardList.size() > 1)
            log.error("queryIdCardByCode有多个返回结果，code={}", code);

        return idCardList.get(0);
    }

    @Override
    public List<Card> findPage(String code) {
        Wrapper<Card> queryWrapper = new EntityWrapper<>();
        queryWrapper.eq(Card.CODE, code);
        List<Card> idCardList = this.selectList(queryWrapper);

        if (idCardList == null || idCardList.isEmpty())
            return Lists.newArrayList();

        if (idCardList.size() > 1)
            log.error("queryIdCardByCode有多个返回结果，code={}", code);

        return idCardList;
    }
}
