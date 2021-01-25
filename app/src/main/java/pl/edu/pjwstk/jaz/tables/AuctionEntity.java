package pl.edu.pjwstk.jaz.tables;

import pl.edu.pjwstk.jaz.readiness.UserEntity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "auction")
public class AuctionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int price;

    private String title;

    private String description;

    private Long version;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private UserEntity userEntity;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryEntity category;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "auction_id")
    private Set<PhotoEntity> photoSet;

    @OneToMany(mappedBy = "auction",cascade = CascadeType.ALL)
    private Set<AuctionParameterEntity> auctionParameters;

    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public Set<PhotoEntity> getPhotosSet() {
        return photoSet;
    }

    public void setPhotosSet(Set<PhotoEntity> photoSet) {
        this.photoSet = photoSet;
    }

    public Set<AuctionParameterEntity> getAuctionParameters() {
        return auctionParameters;
    }

    public void setAuctionParameters(Set<AuctionParameterEntity> auctionParameters) {
        this.auctionParameters = auctionParameters;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}

