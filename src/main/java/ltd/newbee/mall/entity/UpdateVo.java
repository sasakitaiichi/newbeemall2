package ltd.newbee.mall.entity;

public class UpdateVo {

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isIdFlag() {
        return idFlag;
    }

    public void setIdFlag(boolean idFlag) {
        this.idFlag = idFlag;
    }

    private Long id;

    private boolean idFlag;
}
