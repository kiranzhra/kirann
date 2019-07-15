package fyp.app.aluminportal.model;

public class PostModel {
    public PostModel(String productkey, String imgurl) {
        this.productkey = productkey;
        this.imgurl = imgurl;
    }

    public String getProductkey() {
        return productkey;
    }

    public void setProductkey(String productkey) {
        this.productkey = productkey;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public PostModel() {
    }

    String productkey,imgurl;
}
