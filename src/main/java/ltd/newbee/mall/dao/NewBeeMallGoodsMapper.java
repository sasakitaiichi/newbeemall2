/**
 * 严肃声明：
 * 开源版本请务必保留此注释头信息，若删除我方将保留所有法律责任追究！
 * 本系统已申请软件著作权，受国家版权局知识产权以及国家计算机软件著作权保护！
 * 可正常分享和学习源码，不得用于违法犯罪活动，违者必究！
 * Copyright (c) 2019-2020 十三 all rights reserved.
 * 版权所有，侵权必究！
 */
package ltd.newbee.mall.dao;

import ltd.newbee.mall.entity.NewBeeMallGoods;
import ltd.newbee.mall.entity.StockNumDTO;
import ltd.newbee.mall.util.PageQueryUtil;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NewBeeMallGoodsMapper {
    int deleteByPrimaryKey(Long goodsId);

    int insert(NewBeeMallGoods record);

    int insertSelective(NewBeeMallGoods record);

    NewBeeMallGoods selectByPrimaryKey(Long goodsId);

    int updateByPrimaryKeySelective(NewBeeMallGoods record);

    int updateByPrimaryKeyWithBLOBs(NewBeeMallGoods record);

    int updateByPrimaryKey(NewBeeMallGoods record);

    List<NewBeeMallGoods> findNewBeeMallGoodsList(PageQueryUtil pageUtil);

    int getTotalNewBeeMallGoods(PageQueryUtil pageUtil);

    List<NewBeeMallGoods> selectByPrimaryKeys(List<Long> goodsIds);

    List<NewBeeMallGoods> findNewBeeMallGoodsListBySearch(PageQueryUtil pageUtil);

    int getTotalNewBeeMallGoodsBySearch(PageQueryUtil pageUtil);

    int batchInsert(@Param("newBeeMallGoodsList") List<NewBeeMallGoods> newBeeMallGoodsList);

    int updateStockNum(@Param("stockNumDTOS") List<StockNumDTO> stockNumDTOS);

    int batchUpdateSellStatus(@Param("orderIds") Long[] orderIds, @Param("sellStatus") int sellStatus);

    //added 2021/02/22 order by price
    List<NewBeeMallGoods> selectBySellingPrice(List<Long> list, String order, String orderBy);

    //added 2021/02/28 sasaki for ランキング
    List<NewBeeMallGoods> selectByHotGoods(List<Long> goodsIds);

    //added 2021/03/04 sasaki for ランキング
    int batchUpdateGoods(List<NewBeeMallGoods> list);

    //added 2021/03/03 sasaki for file upload
    int InsertByUpload(@Param("newBeeMallGoodsList") List<NewBeeMallGoods> newBeeMallGoodsList);

    //added by sasaki 2021/03/19 for goodsStore
    List<NewBeeMallGoods> findNewBeeMallGoodsByCategoryId(Long categoryId);

    //added by sasaki 2021/03/19 for goodsStore
    List<String> selectImgByGoodsId(Long goodsId);

}