package com.lsn.lib.ui.widget.banner.widget.banner;

/**
 * 图片轮播条目
 *
 * @author xuexiang
 * @since 2018/11/25 下午7:01
 */
public class BannerItem {
    public String imgUrl;
    public String title;
    private String clientUrl;
    private String titleColor;


    public String getTitleColor() {
        return titleColor;
    }

    public void setTitleColor(String titleColor) {
        this.titleColor = titleColor;
    }

    public String getClientUrl() {
        return clientUrl;
    }

    public void setClientUrl(String clientUrl) {
        this.clientUrl = clientUrl;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public BannerItem setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public BannerItem setTitle(String title) {
        this.title = title;
        return this;
    }


}
