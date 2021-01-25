package pl.edu.pjwstk.jaz.tables;

import pl.edu.pjwstk.jaz.Request.Parameter;
import pl.edu.pjwstk.jaz.Request.Photo;

import java.util.Set;

public class ViewAuction {

    private Long auctionId;
    private int price;
    private String auctionTitle;
    private String auctionDescription;
    private String categoryName;
    private String userName;
    private Long version;
    private Set<Photo> photoList;
    private Set<Parameter> parameterList;


    public Long getAuctionId() {
        return auctionId;
    }

    public void setAuctionId(Long auctionId) {
        this.auctionId = auctionId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getAuctionTitle() {
        return auctionTitle;
    }

    public void setAuctionTitle(String auctionTitle) {
        this.auctionTitle = auctionTitle;
    }

    public String getAuctionDescription() {
        return auctionDescription;
    }

    public void setAuctionDescription(String auctionDescription) {
        this.auctionDescription = auctionDescription;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public Set<Photo> getPhotoList() {
        return photoList;
    }

    public void setPhotoList(Set<Photo> photoList) {
        this.photoList = photoList;
    }

    public Set<Parameter> getParameterList() {
        return parameterList;
    }

    public void setParameterList(Set<Parameter> parameterList) {
        this.parameterList = parameterList;
    }
}

