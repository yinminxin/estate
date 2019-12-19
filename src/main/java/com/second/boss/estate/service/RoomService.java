package com.second.boss.estate.service;

import com.second.boss.estate.entity.Room;
import com.second.boss.estate.vo.request.PageVo;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author yinminxin
 * @description 房间Service
 * @date 2019/12/19 9:51
 */
public interface RoomService {

    //根据室户ID获取房间列表
    List<Room> findRoomListByEstateId(String eatateId);

    //保存或更新房间
    Room saveOrUpdateRoom(Room room);

    //分页获取所有房间列表
    Page<Room> findRoomListByPage(PageVo pageVo);
}
