package pl.edu.pjwstk.jaz.tables;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "auction_parameter")
public class AuctionParameterEntity implements Serializable  {

    @EmbeddedId
    private AuctionParameterID id = new AuctionParameterID();

    @ManyToOne(cascade = CascadeType.ALL)
    @MapsId("auction_id")
    @JoinColumn(name = "auction_id")
    private AuctionEntity auction;

    @ManyToOne(cascade = CascadeType.ALL)
    @MapsId("parameter_id")
    @JoinColumn(name = "parameter_id")
    private ParameterEntity parameter;

    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public AuctionParameterID getId() {
        return id;
    }

    public void setId(AuctionParameterID id) {
        this.id = id;
    }

    public AuctionEntity getAuction() {
        return auction;
    }

    public void setAuction(AuctionEntity auction) {
        this.auction = auction;
    }

    public ParameterEntity getParameter() {
        return parameter;
    }

    public void setParameter(ParameterEntity parameter) {
        this.parameter = parameter;
    }

}
