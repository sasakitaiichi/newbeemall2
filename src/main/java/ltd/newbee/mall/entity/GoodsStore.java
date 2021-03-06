package ltd.newbee.mall.entity;

import java.util.List;

public class GoodsStore {

    private Long goodsId;

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

    private List<String> goodsCoverImg;

    @Override
    public String toString() {
        return "GoodsStore{" +
                "goodsId=" + goodsId +
                ", goodsCoverImg=" + goodsCoverImg +
                '}';
    }

}
