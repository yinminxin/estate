package com.second.boss.estate.repository;

import com.second.boss.estate.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author yinminxin
 * @description 房间Repository
 * @date 2019/12/19 9:52
 */
@Repository
public interface RoomRepository extends JpaRepository<Room,String>, JpaSpecificationExecutor<Room> {

    @Query(value = "SELECT * FROM Room WHERE state = 0 AND estate_id = :estateId",nativeQuery = true)
    List<Room> findRoomListByEstateId(@Param("estateId") String estateId);
}
