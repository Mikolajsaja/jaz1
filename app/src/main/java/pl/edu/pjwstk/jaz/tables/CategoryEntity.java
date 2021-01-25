package pl.edu.pjwstk.jaz.tables;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "category")
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "section_id")
    private SectionEntity section;

    public SectionEntity getSection() {
        return section;
    }

    public void setSection(SectionEntity section) {
        this.section = section;
    }

    @OneToMany
    @JoinColumn(name = "category_id")
    private Set<AuctionEntity> auctions;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<AuctionEntity> getAuctions() {
        return auctions;
    }

    public void setAuctions(Set<AuctionEntity> auctions) {
        this.auctions = auctions;
    }
}
