package ltd.newbee.mall.entity;

import java.util.List;

public class GoodsIdCatId {
    private Long categoryId;

    private List<Long> goodsArray;

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public List<Long> getGoodsArray() {
        return goodsArray;
    }

    public void setGoodsArray(List<Long> goodsArray) {
        this.goodsArray = goodsArray;
    }

    @Override
    public String toString() {
        return "GoodsIdCatId{" +
                "categoryId=" + categoryId +
                ", goodsArray=" + goodsArray +
                '}';
    }
}
