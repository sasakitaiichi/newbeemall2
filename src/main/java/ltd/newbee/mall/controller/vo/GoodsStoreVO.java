package ltd.newbee.mall.controller.vo;

import java.io.Serializable;
import java.util.List;

public class GoodsStoreVO implements Serializable {

    private Long goodsId;

    private List<String> goodsCoverImg;

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public List<String> getGoodsCoverImg() {
        return goodsCoverImg;
    }

    public void setGoodsCoverImg(List<String> goodsCoverImg) {
        this.goodsCoverImg = goodsCoverImg;
    }
}
