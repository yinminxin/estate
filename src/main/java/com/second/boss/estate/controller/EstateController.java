package com.second.boss.estate.controller;

import com.second.boss.estate.common.base.BaseController;
import com.second.boss.estate.common.result.ResponseVO;
import com.second.boss.estate.entity.Estate;
import com.second.boss.estate.service.EstateService;
import com.second.boss.estate.vo.request.PageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author yinminxin
 * @description 室户信息Controller
 * @date 2019/12/18 17:27
 */
@RestController
@RequestMapping("/back/estate")
public class EstateController extends BaseController {

    @Autowired
    private EstateService estateService;

    /**
     * @api {get} /back/estate/findEatateList 查询室户列表
     * @apiName findEatateList
     * @apiDescription 查询室户列表
     * @apiGroup estate
     * @apiUse sendSuccess
     * @apiSuccess {Object} data 数据
     * @apiSuccess {Integer} data.id ID
     * @apiSuccess {String} data.address 详细地址
     * @apiSuccess {String} data.buildingNum 楼栋号
     * @apiSuccess {String} data.estateName 小区名称
     * @apiSuccess {String} data.remainRoom 剩余房间数
     * @apiSuccess {String} data.roomNum 室户号
     * @apiSuccess {Number} data.state 状态 0-正常 1-删除
     * @apiSuccess {String} data.thumbnail 缩略图
     * @apiSuccess {String} data.totalRoom 房间总数
     * @apiSuccessExample {json} Success-Response:
    {
    "code": "200",
    "data": {
    "address": "测试地址5",
    "buildingNum": "1",
    "createTime": 1576665211422,
    "estateName": "1",
    "id": "8a8392ea6f1890be016f1891e2200006",
    "remainRoom": 1,
    "roomNum": "1",
    "state": 0,
    "thumbnail": "1",
    "totalRoom": 1,
    "updateTime": 1576665211422
    },
    "message": "successed",
    "success": true
    }
     */
    @PostMapping("findEatateList")
    public ResponseVO findEatateList(){
        List<Estate> estate = estateService.findEatateList();
        return getFromData(estate);
    }

    /**
     * @api {get} /back/estate/findEatateListByPage 查询室户列表分页
     * @apiName findEatateListByPage
     * @apiDescription 查询室户列表分页
     * @apiGroup estate
     * @apiParam {Integer} pageNum 页码
     * @apiParam {Integer} pageSize 楼栋号
     * @apiParam {String} searchKey 小区名称
     * @apiContentType application/json
     * @apiParamExample {json} Request-Example:
    {
    "pageNum":1,
    "pageSize":4,
    "searchKey":"5"
    }
     * @apiUse sendSuccess
     * @apiSuccess {Object} data 数据
     * @apiSuccess {Integer} data.id ID
     * @apiSuccess {String} data.address 详细地址
     * @apiSuccess {String} data.buildingNum 楼栋号
     * @apiSuccess {String} data.estateName 小区名称
     * @apiSuccess {String} data.remainRoom 剩余房间数
     * @apiSuccess {String} data.roomNum 室户号
     * @apiSuccess {Number} data.state 状态 0-正常 1-删除
     * @apiSuccess {String} data.thumbnail 缩略图
     * @apiSuccess {String} data.totalRoom 房间总数
     * @apiSuccessExample {json} Success-Response:
    {
    "code": "200",
    "data": {
    "content": [
    {
    "address": "测试地址5",
    "buildingNum": "1",
    "createTime": 1576665211000,
    "estateName": "1",
    "id": "8a8392ea6f1890be016f1891e2200006",
    "remainRoom": 1,
    "roomNum": "1",
    "state": 0,
    "thumbnail": "1",
    "totalRoom": 1,
    "updateTime": 1576665211000
    }
    ],
    "empty": false,
    "first": true,
    "last": true,
    "number": 0,
    "numberOfElements": 1,
    "pageable": {
    "offset": "0",
    "pageNumber": 0,
    "pageSize": 4,
    "paged": true,
    "sort": {
    "empty": false,
    "sorted": true,
    "unsorted": false
    },
    "unpaged": false
    },
    "size": 4,
    "sort": {
    "empty": false,
    "sorted": true,
    "unsorted": false
    },
    "totalElements": "1",
    "totalPages": 1
    },
    "message": "successed",
    "success": true
    }
     */
    @PostMapping("findEatateListByPage")
    public ResponseVO findEatateListByPage(@RequestBody PageVo pageVo){
        Page<Estate> estate = estateService.findEatateListByPage(pageVo);
        return getFromData(estate);
    }

    /**
     * @api {get} /back/estate/saveOrUpdateEstate 添加室户信息
     * @apiName saveOrUpdateEstate
     * @apiDescription 添加室户信息
     * @apiGroup estate
     * @apiParam {String} address 详细地址
     * @apiParam {String} buildingNum 楼栋号
     * @apiParam {String} estateName 小区名称
     * @apiParam {Integer} remainRoom 剩余房间数
     * @apiParam {String} roomNum 室户号
     * @apiParam {String} thumbnail 缩略图
     * @apiParam {Integer} totalRoom 房间总数
     * @apiContentType application/json
     * @apiParamExample {json} Request-Example:
    {
    "address": "测试地址5",
    "buildingNum": "1",
    "estateName": "1",
    "remainRoom": 1,
    "roomNum": "1",
    "thumbnail": "1",
    "totalRoom": 1
    }
     * @apiUse sendSuccess
     * @apiSuccess {Object} data 数据
     * @apiSuccess {Integer} data.id ID
     * @apiSuccess {String} data.address 详细地址
     * @apiSuccess {String} data.buildingNum 楼栋号
     * @apiSuccess {String} data.estateName 小区名称
     * @apiSuccess {String} data.remainRoom 剩余房间数
     * @apiSuccess {String} data.roomNum 室户号
     * @apiSuccess {Number} data.state 状态 0-正常 1-删除
     * @apiSuccess {String} data.thumbnail 缩略图
     * @apiSuccess {String} data.totalRoom 房间总数
     * @apiSuccessExample {json} Success-Response:
    {
    "code": "200",
    "data": {
    "address": "测试地址5",
    "buildingNum": "1",
    "createTime": 1576665211422,
    "estateName": "1",
    "id": "8a8392ea6f1890be016f1891e2200006",
    "remainRoom": 1,
    "roomNum": "1",
    "state": 0,
    "thumbnail": "1",
    "totalRoom": 1,
    "updateTime": 1576665211422
    },
    "message": "successed",
    "success": true
    }
     */
    @PostMapping("saveOrUpdateEstate")
    public ResponseVO saveOrUpdateEstate(@RequestBody Estate estate){
        if (estate == null) {
            return getFailure("参数错误!");
        }
        Estate estateNew = estateService.saveOrUpdateEstate(estate);
        return getFromData(estateNew);
    }
}
