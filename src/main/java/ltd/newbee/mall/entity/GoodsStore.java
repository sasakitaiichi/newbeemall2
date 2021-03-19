package ltd.newbee.mall.entity;

import java.util.List;

public class GoodsStore {

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

    @Override
    public String toString() {
        return "GoodsStore{" +
                "id=" + id +
                ", img=" + img +
                '}';
    }

}
