package ltd.newbee.mall.controller.vo;

import java.io.Serializable;
import java.util.List;

public class GoodsStoreVO implements Serializable {

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<String> getImg() {
        return img;
    }

    public void setImg(List<String> img) {
        this.img = img;
    }

    private List<String> img;
}
