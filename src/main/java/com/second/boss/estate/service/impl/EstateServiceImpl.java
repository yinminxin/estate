package com.second.boss.estate.service.impl;

import com.second.boss.estate.entity.Estate;
import com.second.boss.estate.repository.EstateRepository;
import com.second.boss.estate.service.EstateService;
import com.second.boss.estate.vo.request.PageVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import javax.persistence.criteria.Predicate;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yinminxin
 * @description 室户信息业务层
 * @date 2019/12/18 17:28
 */
@Service
@Transactional
public class EstateServiceImpl implements EstateService {

    @Autowired
    private EstateRepository estateRepository;

    @Override
    public List<Estate> findEatateList() {
        return estateRepository.findAll(Sort.by("updateTime").descending());
    }

    @Override
    public Page<Estate> findEatateListByPage(PageVo pageVo) {
        //默认分页参数
        int pageNum = 1;
        int pageSize = 20;
        //有分页参数给分页参数赋值
        if(pageVo.getPageNum() != null){
            pageNum=pageVo.getPageNum();
        }
        if(pageVo.getPageSize() != null){
            pageSize=pageVo.getPageSize();
        }
        //根据更新时间默认倒叙
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize,Sort.by(Sort.Direction.DESC,"updateTime"));
//        return estateRepository.findAll(pageable);

        // TODO Specification
        Specification<Estate> specification = (Specification<Estate>) (root, query, cb) -> {
            //查询列表
            List<Predicate> predicates = new ArrayList<>();
            //添加状态正常的条件
            predicates.add(cb.equal(root.get("state").as(short.class), (short)0));
            if (!org.springframework.util.StringUtils.isEmpty(pageVo.getSearchKey())) {
                Predicate p1 = cb.like(root.get("estateName").as(String.class), "%" + pageVo.getSearchKey().trim() + "%");
                Predicate p2 = cb.like(root.get("address").as(String.class), "%" + pageVo.getSearchKey().trim() + "%");
                predicates.add(cb.or(p1,p2));
            }
            //封装where
            if(!CollectionUtils.isEmpty(predicates)){
                Predicate[] preArr = new Predicate[predicates.size()];
                query.where(predicates.toArray(preArr));
            }
            return query.getRestriction();
        };
        //查询
        Page<Estate> all = estateRepository.findAll(specification, pageable);
        return all;
    }

    @Override
    public Estate saveOrUpdateEstate(Estate estate) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        if (StringUtils.isBlank(estate.getId())) {
            estate.setCreateTime(timestamp);
        }
        estate.setUpdateTime(timestamp);
        return estateRepository.save(estate);
    }
}
