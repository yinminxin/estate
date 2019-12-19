package com.second.boss.estate.service;

import com.second.boss.estate.entity.Estate;
import com.second.boss.estate.vo.request.PageVo;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author yinminxin
 * @description 室户信息Service
 * @date 2019/12/18 17:28
 */
public interface EstateService {

    /**
     * 查询所有室户信息
     * @return
     */
    List<Estate> findEatateList();

    /**
     * 分页查询所有室户信息
     * @return
     */
    Page<Estate> findEatateListByPage(PageVo pageVo);

    /**
     * 添加或更新室户信息
     * @param estate
     * @return
     */
    Estate saveOrUpdateEstate(Estate estate);
}
