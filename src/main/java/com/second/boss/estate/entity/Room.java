package com.second.boss.estate.entity;

import com.second.boss.estate.common.base.BaseEntity;
import javax.persistence.*;

/**
 * @author yinminxin
 * @description 房间表
 * @date 2019/12/11 17:39
 */
@Entity
@Table(name = "room")
@org.hibernate.annotations.Table(appliesTo = "room", comment = "房间表")
public class Room extends BaseEntity {

    @Column(name = "name", columnDefinition = "VARCHAR(20) NOT NULL COMMENT '房间号'")
    private String name;
    @Column(name = "money", columnDefinition = "VARCHAR(20) NOT NULL COMMENT '租金'")
    private String money;
    @Column(name = "estate_id", columnDefinition = "CHAR(32) NOT NULL COMMENT '室户信息ID'")
    private String estate_id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getEstate_id() {
        return estate_id;
    }

    public void setEstate_id(String estate_id) {
        this.estate_id = estate_id;
    }
}
