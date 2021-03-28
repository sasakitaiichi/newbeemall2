/**
 * 严肃声明：
 * 开源版本请务必保留此注释头信息，若删除我方将保留所有法律责任追究！
 * 本系统已申请软件著作权，受国家版权局知识产权以及国家计算机软件著作权保护！
 * 可正常分享和学习源码，不得用于违法犯罪活动，违者必究！
 * Copyright (c) 2019-2020 十三 all rights reserved.
 * 版权所有，侵权必究！
 */
package ltd.newbee.mall.service.impl;

import ltd.newbee.mall.common.ServiceResultEnum;
import ltd.newbee.mall.controller.vo.GoodsStoreVO;
import ltd.newbee.mall.controller.vo.NewBeeMallSearchGoodsVO;
import ltd.newbee.mall.dao.GoodsCategoryMapper;
import ltd.newbee.mall.dao.GoodsStoreMapper;
import ltd.newbee.mall.dao.NewBeeMallGoodsMapper;
import ltd.newbee.mall.entity.*;
import ltd.newbee.mall.service.NewBeeMallGoodsService;
import ltd.newbee.mall.util.BeanUtil;
import ltd.newbee.mall.util.PageQueryUtil;
import ltd.newbee.mall.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class NewBeeMallGoodsServiceImpl implements NewBeeMallGoodsService {

    @Autowired
    private NewBeeMallGoodsMapper goodsMapper;
    @Autowired
    private GoodsCategoryMapper goodsCategoryMapper;
    @Autowired
    private GoodsStoreMapper goodsStoreMapper;

    public NewBeeMallGoodsServiceImpl() throws FileNotFoundException {
    }

    @Override
    public PageResult getNewBeeMallGoodsPage(PageQueryUtil pageUtil) {
        List<NewBeeMallGoods> goodsList = goodsMapper.findNewBeeMallGoodsList(pageUtil);
        int total = goodsMapper.getTotalNewBeeMallGoods(pageUtil);
        PageResult pageResult = new PageResult(goodsList, total, pageUtil.getLimit(), pageUtil.getPage());
        return pageResult;
    }

    @Override
    public String saveNewBeeMallGoods(NewBeeMallGoods goods) {
        if (goodsMapper.insertSelective(goods) > 0) {
            return ServiceResultEnum.SUCCESS.getResult();
        }
        return ServiceResultEnum.DB_ERROR.getResult();
    }

    @Override
    public void batchSaveNewBeeMallGoods(List<NewBeeMallGoods> newBeeMallGoodsList) {
        if (!CollectionUtils.isEmpty(newBeeMallGoodsList)) {
            goodsMapper.batchInsert(newBeeMallGoodsList);
        }
    }

    @Override
    public String updateNewBeeMallGoods(NewBeeMallGoods goods) {
        NewBeeMallGoods temp = goodsMapper.selectByPrimaryKey(goods.getGoodsId());
        if (temp == null) {
            return ServiceResultEnum.DATA_NOT_EXIST.getResult();
        }
        goods.setUpdateTime(new Date());
        if (goodsMapper.updateByPrimaryKeySelective(goods) > 0) {
            return ServiceResultEnum.SUCCESS.getResult();
        }
        return ServiceResultEnum.DB_ERROR.getResult();
    }

    @Override
    public NewBeeMallGoods getNewBeeMallGoodsById(Long id) {
        return goodsMapper.selectByPrimaryKey(id);
    }


    @Override
    public Boolean batchUpdateSellStatus(Long[] ids, int sellStatus) {
        return goodsMapper.batchUpdateSellStatus(ids, sellStatus) > 0;
    }

    @Override
    public PageResult searchNewBeeMallGoods(PageQueryUtil pageUtil) {
        List<NewBeeMallGoods> goodsList = goodsMapper.findNewBeeMallGoodsListBySearch(pageUtil);
        int total = goodsMapper.getTotalNewBeeMallGoodsBySearch(pageUtil);
        List<NewBeeMallSearchGoodsVO> newBeeMallSearchGoodsVOS = new ArrayList<>();
        if (!CollectionUtils.isEmpty(goodsList)) {
            newBeeMallSearchGoodsVOS = BeanUtil.copyList(goodsList, NewBeeMallSearchGoodsVO.class);
            for (NewBeeMallSearchGoodsVO newBeeMallSearchGoodsVO : newBeeMallSearchGoodsVOS) {
                String goodsName = newBeeMallSearchGoodsVO.getGoodsName();
                String goodsIntro = newBeeMallSearchGoodsVO.getGoodsIntro();
                // 字符串过长导致文字超出的问题
                if (goodsName.length() > 28) {
                    goodsName = goodsName.substring(0, 28) + "...";
                    newBeeMallSearchGoodsVO.setGoodsName(goodsName);
                }
                if (goodsIntro.length() > 30) {
                    goodsIntro = goodsIntro.substring(0, 30) + "...";
                    newBeeMallSearchGoodsVO.setGoodsIntro(goodsIntro);
                }
            }
        }
        PageResult pageResult = new PageResult(newBeeMallSearchGoodsVOS, total, pageUtil.getLimit(), pageUtil.getPage());
        return pageResult;
    }

    @Override
    public List<NewBeeMallGoods> getNewBeeMallGoodsByIds(List<Long> ids) {
        List<NewBeeMallGoods> newBeeMallGoods = goodsMapper.selectByPrimaryKeys(ids);
        return newBeeMallGoods;
    }

    /**
     * 获取商品详情
     *
     * @param list@return
     */
    @Override
    public void fileWriter(List<NewBeeMallGoods> list) {
        final String comma = ",";
        String header = "goods_id" + "," + " goods_name" + "," + "goods_intro" + "," + "goods_category_id" + "," + "goods_cover_img" + "," + "goods_carousel" + "," + "goods_detail_content" + "," + "original_price"
                + "," + "selling_price" + "," + "stock_num" + "," + "tag" + "," + "goods_sell_status" + "," + "create_user" + "," + "create_time" + "," + "update_user" + "," + "update_time\r\n";
        try {
            File file = new File("c:\\download\\goods.csv");

            FileWriter filewriter = new FileWriter(file);

            filewriter.write(header);
            list.forEach(goods -> {
                try {
                    String str = goods.getGoodsId() + comma + goods.getGoodsName() + comma + goods.getGoodsIntro() + comma + goods.getGoodsCategoryId() + comma + goods.getGoodsCoverImg() + comma + goods.getGoodsCarousel() + comma + goods.getGoodsDetailContent() + comma + goods.getOriginalPrice()
                            + comma + goods.getSellingPrice() + comma + goods.getStockNum() + comma + goods.getTag() + comma + goods.getGoodsSellStatus() + comma + goods.getCreateUser() + comma + goods.getCreateTime() + comma + goods.getUpdateUser() + comma + goods.getUpdateTime();
                    filewriter.write(str + "\r\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            filewriter.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }


    @Override
    public int batchUpdateGoods(List<NewBeeMallGoods> newBeeMallGoods) {
        int count = goodsMapper.batchUpdateGoods(newBeeMallGoods);
        return count;
    }

    //added 2021/03/08 sasaki for file upload
    @Override
    public List<NewBeeMallGoods> saveNewBeeMallGoodsByUpload(List<NewBeeMallGoods> newBeeMallGoods) {
        if (!CollectionUtils.isEmpty(newBeeMallGoods)) {
            goodsMapper.InsertByUpload(newBeeMallGoods);
        }
        return newBeeMallGoods;
    }

    //added 2021/03/08 sasaki for file upload
    @Override
    public String updateNewBeeMallGoodsByUpload(List<NewBeeMallGoods> newBeeMallGoods) {
        int count = 0;
        if (!newBeeMallGoods.isEmpty()) {
            count = goodsMapper.batchUpdateGoods(newBeeMallGoods);
        }
        if (count > 0) {
            return ServiceResultEnum.SUCCESS.getResult();
        }
        return ServiceResultEnum.DB_ERROR.getResult();
    }

    @Override
    public List<GoodsStoreVO> getGoodsByCategoryId(Long categoryId) {

        List<GoodsStoreVO> goodsStoreVO = new ArrayList<>();

        List<NewBeeMallGoods> newBeeMallGoods = null;

        List<GoodsStore> goodsStore = new ArrayList<>();

        Long parentId = categoryId;

        List<GoodsCategory> goodsCategoryList = goodsCategoryMapper.selectLevelThreeList(parentId);

        for (int n = 0; n < goodsCategoryList.size(); n++) {

            newBeeMallGoods = goodsMapper.findNewBeeMallGoodsByCategoryId((goodsCategoryList.get(n)).getCategoryId());

//            if (!newBeeMallGoods.isEmpty()) {
//
//                Long goodsId = newBeeMallGoods.get(n).getGoodsId();
//
//                goodsIds.add(goodsId);
//            }
//
//            for (int i = 0; i < newBeeMallGoods.size(); i++) {
//
//                List<String> img = goodsMapper.selectImgByGoodsId((newBeeMallGoods.get(i)).getGoodsId());
//
////                System.out.println("aa");
////                GoodsStore temp = new GoodsStore();
////                if (img.size() > 0){//没有图片的不显示
////                    temp.setId((img.get(i)).get);
////                    temp.setImg(imgList);
////                    GoodsStore.add(goodsStrore);
////                    GoodsStoreVO = BeanUtil.copyList(GoodsStore, GoodsStoreVO.class);
////                }
//                for (int g = 0; g < img.size(); g++) {
//                    imgList.add(img.get(g));
//                }
//            }
//        }
//        GoodsStore temp = new GoodsStore();
//        for (int g = 0; g < goodsIds.size(); g++) {
//            temp.setId(goodsIds.get(g));
//            temp.setImg(imgList);
//            goodsStore.add(temp);
//        }
            for (int i = 0; i < newBeeMallGoods.size(); i++) {
                List<String> img = goodsStoreMapper.selectImgByGoodsId(newBeeMallGoods.get(i).getGoodsId());

                System.out.println((newBeeMallGoods.get(i)).getGoodsId());

                GoodsStore temp = new GoodsStore();
                if (img.size() > 0) {
                    temp.setGoodsId((newBeeMallGoods.get(i)).getGoodsId());
                    temp.setGoodsCoverImg(img);
                    goodsStore.add(temp);
                    goodsStoreVO = BeanUtil.copyList(goodsStore, GoodsStoreVO.class);
                }
            }
        }

        return goodsStoreVO;
    }

    @Override
    @Transactional
    public String updateGoods(NewBeeMallGoods newBeeMallGoods) {
        NewBeeMallGoods temp = goodsMapper.selectByPrimaryKey(newBeeMallGoods.getGoodsId());
        if (temp != null) {
            temp.setGoodsName(newBeeMallGoods.getGoodsName());
            temp.setGoodsCoverImg(newBeeMallGoods.getGoodsCoverImg());
            temp.setUpdateTime(new Date());
            if (goodsMapper.updateByPrimaryKeySelective(temp) > 0) {
                return ServiceResultEnum.SUCCESS.getResult();
            }
            return ServiceResultEnum.DB_ERROR.getResult();
        }
        return ServiceResultEnum.DATA_NOT_EXIST.getResult();
    }

}











