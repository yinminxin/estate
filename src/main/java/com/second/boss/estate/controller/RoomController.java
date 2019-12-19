package com.second.boss.estate.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.second.boss.estate.common.base.BaseController;
import com.second.boss.estate.common.result.ResponseVO;
import com.second.boss.estate.entity.Room;
import com.second.boss.estate.service.RoomService;
import com.second.boss.estate.vo.request.PageVo;
import jdk.nashorn.internal.ir.IfNode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author yinminxin
 * @description 房间Controller
 * @date 2019/12/19 9:49
 */
@RestController
@RequestMapping("/back/room")
public class RoomController extends BaseController {

    @Autowired
    private RoomService roomService;

    //分页获取所有房间列表
    public ResponseVO findRoomListByPage(PageVo pageVo){
        Page<Room> roomPage = roomService.findRoomListByPage(pageVo);
        return getFromData(roomPage);
    }

    //根据室户ID获取房间列表
    @PostMapping("findRoomListByEstateId")
    public ResponseVO findRoomListByEstateId(@RequestBody String str){
        if (StringUtils.isBlank(str)) {
            return getFailure("参数有误!");
        }
        String estateId = null;
        JSONObject jsonObject = JSON.parseObject(str);
        if (StringUtils.isNotBlank(jsonObject.getString("estateId"))) {
            estateId = jsonObject.getString("estateId");
        }else {
            return getFailure("室户ID不能为空!");
        }
        //根据室户ID获取房间列表
        List<Room> rooms = roomService.findRoomListByEstateId(estateId);
        return getFromData(rooms);
    }

    //添加房间
    @PostMapping("saveOrUpdateRoom")
    public ResponseVO saveOrUpdateRoom(Room room){
        if (room == null || StringUtils.isBlank(room.getEstate_id())) {
            return getFailure("参数有误!");
        }
        if (StringUtils.isBlank(room.getEstate_id())) {
            return getFailure("室户信息不能为空!");
        }
        if (StringUtils.isBlank(room.getName())) {
            return getFailure("房间号不能为空!");
        }
        if (StringUtils.isBlank(room.getMoney())) {
            return getFailure("金额不能为空!");
        }
        Room roomNew = roomService.saveOrUpdateRoom(room);
        return getFromData(roomNew);
    }

}
