package ltd.newbee.mall.controller.mall;

import ltd.newbee.mall.controller.vo.GoodsStoreVO;
import ltd.newbee.mall.dao.NewBeeMallGoodsMapper;
import ltd.newbee.mall.entity.GoodsCategory;
import ltd.newbee.mall.entity.GoodsStore;
import ltd.newbee.mall.entity.NewBeeMallGoods;
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
    @Resource
    private NewBeeMallGoodsMapper goodsMapper;

    @GetMapping({"/recommend"})
    public String recommend() {
        return "mall/recommend";
    }

    @RequestMapping(value = "/recommend", method = RequestMethod.POST)
    @ResponseBody
    public Result GoodsStore(HttpServletRequest request, Long categoryId) {

        List<Long> categoryIdList;

        List<GoodsStore> goodsStore = null;

        List<GoodsCategory> goodsCategoryList = newBeeMallCategoryService.fetchSecLeveLCateList();

        categoryIdList = goodsCategoryList.stream().map(GoodsCategory::getCategoryId).collect(Collectors.toList());

        for (int i = 0; i < categoryIdList.size(); i++) {
            categoryId = categoryIdList.get(i);
        }

        for (int n = 0;n<categoryIdList.size()-1;n++) {

            if (categoryId == null) {
                 goodsStore = newBeeMallGoodsService.getGoodsByCategoryId(goodsCategoryList.get(0));
            } else {
                 goodsStore = newBeeMallGoodsService.getGoodsByCategoryId(goodsCategoryList.get(n+1));
            }
        }
        Map result = new HashMap();
        result.put("goodsCategoryList",goodsCategoryList);
        result.put("goodsStore",goodsStore);

        return ResultGenerator.genSuccessResult(result);
    }
}
