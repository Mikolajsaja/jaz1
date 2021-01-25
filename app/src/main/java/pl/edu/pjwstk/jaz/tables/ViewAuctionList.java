package pl.edu.pjwstk.jaz.tables;

public class ViewAuctionList {
    private int price;
    private Long auctionId;
    private String category;
    private String title;
    private String miniature;

    public ViewAuctionList(int price, Long auctionId, String category, String title, String miniature) {
        this.price = price;
        this.auctionId = auctionId;
        this.category = category;
        this.title = title;
        this.miniature = miniature;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Long getAuctionId() {
        return auctionId;
    }

    public void setAuctionId(Long auctionId) {
        this.auctionId = auctionId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMiniature() {
        return miniature;
    }

    public void setMiniature(String miniature) {
        this.miniature = miniature;
    }
}
