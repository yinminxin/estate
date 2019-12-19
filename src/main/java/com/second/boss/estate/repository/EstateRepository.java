package com.second.boss.estate.repository;

import com.second.boss.estate.entity.Estate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author yinminxin
 * @description 室户信息Repository
 * @date 2019/12/18 17:29
 */
@Repository
public interface EstateRepository extends JpaRepository<Estate,String>, JpaSpecificationExecutor<Estate> {
}
