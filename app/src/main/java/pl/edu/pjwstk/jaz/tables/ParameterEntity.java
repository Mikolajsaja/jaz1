package pl.edu.pjwstk.jaz.tables;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "parameter")
public class ParameterEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String key;

    @OneToMany(mappedBy = "parameter",orphanRemoval = true,cascade = CascadeType.ALL)
    private Set<AuctionParameterEntity> auctionParameters;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Set<AuctionParameterEntity> getAuctionParameters() {
        return auctionParameters;
    }

    public void setAuctionParameters(Set<AuctionParameterEntity> auctionParameters) {
        this.auctionParameters = auctionParameters;
    }
}
