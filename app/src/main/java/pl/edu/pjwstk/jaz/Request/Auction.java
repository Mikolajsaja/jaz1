package pl.edu.pjwstk.jaz.Request;

import com.sun.istack.NotNull;
import org.springframework.lang.NonNull;

import java.util.List;

public class Auction {

    private int price;
    private String auctionTitle;
    private String auctionDescription;
    private String categoryName;
    private String userName;
    private Long version;
    private List<Photo> photoList;
    private List<Parameter> parameterList;

    public Auction() {
    }

    public Auction(int price, String auctionTitle, String auctionDescription, String categoryName, String userName, Long version, List<Photo> photoList, List<Parameter> parameterList) {
        this.price = price;
        this.auctionTitle = auctionTitle;
        this.auctionDescription = auctionDescription;
        this.categoryName = categoryName;
        this.userName = userName;
        this.version = version;
        this.photoList = photoList;
        this.parameterList = parameterList;
    }
    public Auction(int price, String auctionTitle, String auctionDescription, String categoryName, List<Photo> photoRequestList, List<Parameter> parameterRequestList) {
        this.price = price;
        this.auctionTitle = auctionTitle;
        this.auctionDescription = auctionDescription;
        this.categoryName = categoryName;
        this.photoList = photoRequestList;
        this.parameterList = parameterRequestList;
    }

    public int getPrice() {
        return price;
    }

    public String getAuctionTitle() {
        return auctionTitle;
    }

    public String getAuctionDescription() {
        return auctionDescription;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public String getUserName() {
        return userName;
    }

    public Long getVersion() {
        return version;
    }

    public List<Photo> getPhotoList() {
        return photoList;
    }

    public List<Parameter> getParameterList() {
        return parameterList;
    }
}
