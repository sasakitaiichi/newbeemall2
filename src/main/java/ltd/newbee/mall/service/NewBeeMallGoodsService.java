/**
 * 严肃声明：
 * 开源版本请务必保留此注释头信息，若删除我方将保留所有法律责任追究！
 * 本系统已申请软件著作权，受国家版权局知识产权以及国家计算机软件著作权保护！
 * 可正常分享和学习源码，不得用于违法犯罪活动，违者必究！
 * Copyright (c) 2019-2020 十三 all rights reserved.
 * 版权所有，侵权必究！
 */
package ltd.newbee.mall.service;

import ltd.newbee.mall.entity.NewBeeMallGoods;
import ltd.newbee.mall.util.PageQueryUtil;
import ltd.newbee.mall.util.PageResult;

import java.io.FileNotFoundException;
import java.util.List;

public interface NewBeeMallGoodsService {
    /**
     * 后台分页
     *
     * @param pageUtil
     * @return
     */
    PageResult getNewBeeMallGoodsPage(PageQueryUtil pageUtil);

    /**
     * 添加商品
     *
     * @param goods
     * @return
     */
    String saveNewBeeMallGoods(NewBeeMallGoods goods);

    /**
     * 批量新增商品数据
     *
     * @param newBeeMallGoodsList
     * @return
     */
    void batchSaveNewBeeMallGoods(List<NewBeeMallGoods> newBeeMallGoodsList);

    /**
     * 修改商品信息
     *
     * @param goods
     * @return
     */
    String updateNewBeeMallGoods(NewBeeMallGoods goods);

    /**
     * 获取商品详情
     *
     * @param id
     * @return
     */
    NewBeeMallGoods getNewBeeMallGoodsById(Long id);

    /**
     * 批量修改销售状态(上架下架)
     *
     * @param ids
     * @return
     */
    Boolean batchUpdateSellStatus(Long[] ids, int sellStatus);

    /**
     * 商品搜索
     *
     * @param pageUtil
     * @return
     */
    PageResult searchNewBeeMallGoods(PageQueryUtil pageUtil);

    /**
     * 获取商品详情
     * 2021/03/03
     *
     * @param ids
     * @return
     */
    List<NewBeeMallGoods> getNewBeeMallGoodsByIds(List<Long> ids);

    /**
     * download
     * 2021/03/03
     *
     * @param
     * @return
     */
    void fileWriter(List<NewBeeMallGoods> list);


    /**
     * 更新商品信息
     * 2021/03/04
     *
     * @param ids
     * @return
     */
    int batchUpdateGoods(List<NewBeeMallGoods> newBeeMallGoods);


    /**
     * 新增上传商品
     *
     * @param newBeeMallGoods
     * @return
     */
    List<NewBeeMallGoods> saveNewBeeMallGoodsByUpload(List<NewBeeMallGoods> newBeeMallGoods);

    /**
     * 修改上传商品信息
     *
     * @param newBeeMallGoods
     * @return
     */
    String updateNewBeeMallGoodsByUpload(List<NewBeeMallGoods> newBeeMallGoods);
}
