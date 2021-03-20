package ltd.newbee.mall.controller.mall;

import ltd.newbee.mall.controller.vo.GoodsStoreVO;
import ltd.newbee.mall.dao.NewBeeMallGoodsMapper;
import ltd.newbee.mall.entity.GoodsCategory;
import ltd.newbee.mall.entity.GoodsStore;
import ltd.newbee.mall.entity.NewBeeMallGoods;
import ltd.newbee.mall.service.NewBeeMallCategoryService;
import ltd.newbee.mall.service.NewBeeMallGoodsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class GoodsStoreController {

    @Resource
    private NewBeeMallGoodsService newBeeMallGoodsService;
    @Resource
    private NewBeeMallCategoryService newBeeMallCategoryService;
    @Resource
    private NewBeeMallGoodsMapper goodsMapper;

    @GetMapping("/recommend")
    public String GoodsStore(HttpServletRequest request,Long categoryId) {

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

        request.setAttribute("goodsCategoryList", goodsCategoryList);
        request.setAttribute("goodsStore", goodsStore);

        return "/mall/recommend";
    }
}
