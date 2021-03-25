package ltd.newbee.mall.controller.mall;

import ltd.newbee.mall.controller.vo.GoodsStoreVO;
import ltd.newbee.mall.dao.NewBeeMallGoodsMapper;
import ltd.newbee.mall.entity.*;
import ltd.newbee.mall.service.NewBeeMallCategoryService;
import ltd.newbee.mall.service.NewBeeMallGoodsService;
import ltd.newbee.mall.util.Result;
import ltd.newbee.mall.util.ResultGenerator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class GoodsStoreController {

    @Resource
    private NewBeeMallGoodsService newBeeMallGoodsService;
    @Resource
    private NewBeeMallCategoryService newBeeMallCategoryService;

//    @GetMapping({"/recommend"})
//    public String recommend() {
//        return "mall/recommend";
//    }

    @GetMapping({ "/recommend","/recommend?{categoryId}", "/recommend.html"})
    public String GoodsStore(HttpServletRequest request, Long categoryId) {

        List<GoodsCategory> goodsCategoryList = newBeeMallCategoryService.fetchSecLeveLCateList();

//        for (int i = 0; i < categoryIdList.size(); i++) {
//            categoryId = categoryIdList.get(i);

//            Long categoryId = 19l;
//            for (int n = 0; n < categoryIdList.size(); n++) {

        if(categoryId == null ){
            categoryId = 19l ;
        }
        List<GoodsStoreVO> goodsImg = newBeeMallGoodsService.getGoodsByCategoryId(categoryId);

        request.setAttribute("goodsCategoryList",goodsCategoryList);
        request.setAttribute("goodsImg",goodsImg);
        request.setAttribute("categoryId",categoryId);
//        Map result = new HashMap();
//        result.put("goodsCategoryList",goodsCategoryList);
//        result.put("goodsStore",goodsStore);
        return "mall/recommend";
    }

    @PostMapping({"/recommendAjax"})
    @ResponseBody
    public Result everyGoodsStoreControllerCall(@RequestBody GoodsIdCatId goodIdCatId, HttpServletRequest request ) {

        System.out.println(goodIdCatId);

        List<GoodsStoreVO> goodsImg = newBeeMallGoodsService.getGoodsByCategoryId(goodIdCatId.getCategoryId());//categoryId別の商品全部

        List<GoodsStoreVO> newGoodsImg = goodsImg.stream().filter(item -> !(goodIdCatId.getGoodsArray()).contains(item.getGoodsId())).collect(Collectors.toList());//categoryId別list残っている商品

        List<Long> goodsIds = newGoodsImg.stream().map(GoodsStoreVO::getGoodsId).collect(Collectors.toList());//categoryId別list残っている商品のid

        List<GoodsIdCatId> list = new ArrayList<>();
        for (int i = 0;i<2;i++) {
            GoodsIdCatId goodsIdCatId = new GoodsIdCatId();
            goodsIdCatId.setCategoryId(goodIdCatId.getCategoryId());
            goodsIdCatId.setGoodsArray(goodsIds);
            list.add(goodsIdCatId);
        }
        return ResultGenerator.genSuccessResult(list);
    };

}
