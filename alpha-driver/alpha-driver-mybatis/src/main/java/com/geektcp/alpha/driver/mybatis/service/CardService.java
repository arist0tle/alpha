package com.geektcp.alpha.driver.mybatis.service;

import com.geektcp.alpha.driver.mybatis.model.Card;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author tanghaiyang
 * @since 2018-08-31
 */
public interface CardService {

    boolean addIdCard(Card card);

    Card queryIdCardByCode(String code);

    List<Card> findPage(String code);
}
