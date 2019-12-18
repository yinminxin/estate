package com.second.boss.estate.entity;

import com.second.boss.estate.common.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author yinminxin
 * @description 房间用户记录表
 * @date 2019/12/11 18:33
 */
@Entity
@Table(name = "record_room_user")
@org.hibernate.annotations.Table(appliesTo = "record_room_user", comment = "房间用户记录表")
public class RecordRoomUser extends BaseEntity {

    @Column(name = "user_id", columnDefinition = "CHAR(32) NOT NULL COMMENT '用户信息ID'")
    private String userId;
    @Column(name = "room_id", columnDefinition = "CHAR(32) NOT NULL COMMENT '房间信息ID'")
    private String roomId;
    @Column(name = "start_date", columnDefinition = "date NOT NULL COMMENT '开始时间'")
    private Date startDate;
    @Column(name = "end_date", columnDefinition = "date NOT NULL COMMENT '结束时间'")
    private Date endDate;
    @Column(name = "money", columnDefinition = "CHAR(32) NOT NULL COMMENT '金额'")
    private String money;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }
}
