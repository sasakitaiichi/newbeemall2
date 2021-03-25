package ltd.newbee.mall.entity;

public class GoodsStoreImg {
    private Long goodsId;

    private String goodsCoverImg;

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsCoverImg() {
        return goodsCoverImg;
    }

    public void setGoodsCoverImg(String goodsCoverImg) {
        this.goodsCoverImg = goodsCoverImg;
    }

    @Override
    public String toString() {
        return "GoodsStoreImg{" +
                "goodsId=" + goodsId +
                ", goodsCoverImg='" + goodsCoverImg + '\'' +
                '}';
    }
}
