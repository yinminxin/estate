package com.second.boss.estate.service.impl;

import com.second.boss.estate.entity.Room;
import com.second.boss.estate.repository.RoomRepository;
import com.second.boss.estate.service.RoomService;
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
import java.util.ArrayList;
import java.util.List;

/**
 * @author yinminxin
 * @description 房间业务层
 * @date 2019/12/19 9:51
 */
@Service
@Transactional
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Override
    public List<Room> findRoomListByEstateId(String estateId) {
        return roomRepository.findRoomListByEstateId(estateId);
    }

    @Override
    public Room saveOrUpdateRoom(Room room) {
        return roomRepository.save(room);
    }

    @Override
    public Page<Room> findRoomListByPage(PageVo pageVo) {
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
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize, Sort.by(Sort.Direction.DESC,"updateTime"));
        Specification<Room> specification = (Specification<Room>) (root, query, cb)->{
            List<Predicate> predicates = new ArrayList<>();
            //添加状态正常的条件
            predicates.add(cb.equal(root.get("state").as(short.class), (short)0));
            //封装where
            if(!CollectionUtils.isEmpty(predicates)){
                javax.persistence.criteria.Predicate[] preArr = new javax.persistence.criteria.Predicate[predicates.size()];
                query.where(predicates.toArray(preArr));
            }
            return query.getRestriction();
        };
        return roomRepository.findAll(specification,pageable);
    }
}
