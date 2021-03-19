package ltd.newbee.mall.controller.mall;

import ltd.newbee.mall.controller.vo.GoodsStoreVO;
import ltd.newbee.mall.entity.GoodsCategory;
import ltd.newbee.mall.entity.GoodsStore;
import ltd.newbee.mall.service.NewBeeMallCategoryService;
import ltd.newbee.mall.service.NewBeeMallGoodsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class GoodsStoreController {

    @Resource
    private NewBeeMallGoodsService newBeeMallGoodsService;
    @Resource
    private NewBeeMallCategoryService newBeeMallCategoryService;

    @RequestMapping( "/recommend")
    public String GoodsStore(HttpServletRequest request,int categoryId) {
        List<GoodsCategory>  goodsCategoryList = newBeeMallCategoryService.fetchSecLeveLCateList();


        List<GoodsStore> goodsStore = newBeeMallGoodsService.getGoodsByCategoryId(goodsCategoryList);

        request.setAttribute("goodsCategoryList",goodsCategoryList);
        request.setAttribute("goodsStore",goodsStore);

        return "/mall/recommend";
    }
}
