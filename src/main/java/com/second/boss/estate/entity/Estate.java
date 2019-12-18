package com.second.boss.estate.entity;

import com.second.boss.estate.common.base.BaseEntity;
import javax.persistence.*;

/**
 * @author yinminxin
 * @description 室户信息表
 * @date 2019/12/11 17:01
 */
@Entity
@Table(name = "estate")
@org.hibernate.annotations.Table(appliesTo = "estate", comment = "室户信息表")
public class Estate extends BaseEntity {

    @Column(name = "estate_name", columnDefinition = "VARCHAR(20) NOT NULL COMMENT '小区名称'")
    private String estateName;
    @Column(name = "building_num", columnDefinition = "CHAR(5) NOT NULL COMMENT '楼栋号'")
    private String buildingNum;
    @Column(name = "room_num", columnDefinition = "CHAR(5) NOT NULL COMMENT '室户号'")
    private String roomNum;
    @Column(name = "address", columnDefinition = "CHAR(255) NOT NULL COMMENT '详细地址'")
    private String address;
    @Column(name = "total_room", columnDefinition = "int(1) NOT NULL COMMENT '房间总数'")
    private Integer totalRoom;
    @Column(name = "remain_room", columnDefinition = "int(1) NOT NULL COMMENT '剩余房间数'")
    private Integer remainRoom;

    public String getEstateName() {
        return estateName;
    }

    public void setEstateName(String estateName) {
        this.estateName = estateName;
    }

    public String getBuildingNum() {
        return buildingNum;
    }

    public void setBuildingNum(String buildingNum) {
        this.buildingNum = buildingNum;
    }

    public String getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(String roomNum) {
        this.roomNum = roomNum;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getTotalRoom() {
        return totalRoom;
    }

    public void setTotalRoom(Integer totalRoom) {
        this.totalRoom = totalRoom;
    }

    public Integer getRemainRoom() {
        return remainRoom;
    }

    public void setRemainRoom(Integer remainRoom) {
        this.remainRoom = remainRoom;
    }
}
