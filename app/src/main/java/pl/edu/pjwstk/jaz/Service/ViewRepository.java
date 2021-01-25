package pl.edu.pjwstk.jaz.Service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.pjwstk.jaz.BadRequestException;
import pl.edu.pjwstk.jaz.Request.Parameter;
import pl.edu.pjwstk.jaz.Request.Photo;
import pl.edu.pjwstk.jaz.tables.*;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class ViewRepository {

    private final EntityManager entityManager;

    public ViewRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public ViewAuction getAuction(Long auctionId) {

        ViewAuction viewAuction = new ViewAuction();

        try {
            var auction = findAuctionById(auctionId);

            viewAuction.setAuctionId(auctionId);
            viewAuction.setPrice(auction.getPrice());
            viewAuction.setAuctionTitle(auction.getTitle());
            viewAuction.setAuctionDescription(auction.getDescription());
            viewAuction.setCategoryName(auction.getCategory().getName());
            viewAuction.setUserName(auction.getUserEntity().getUsername());

            Set<Photo> photoRequests = new HashSet<>();
            for (PhotoEntity photo : auction.getPhotosSet()) {
                var auctionPhoto = new Photo(photo.getName(), photo.getPosition());
                photoRequests.add(auctionPhoto);
            }
            viewAuction.setPhotoList(photoRequests);

            Set<Parameter> auctionParameters = new HashSet<>();
            for (AuctionParameterEntity auctionParameter : auction.getAuctionParameters()) {
                var parameter = new Parameter(auctionParameter.getParameter().getKey(), auctionParameter.getValue());
                auctionParameters.add(parameter);
            }
            viewAuction.setParameterList(auctionParameters);

            viewAuction.setVersion(auction.getVersion());
        } catch (NoResultException exception) {
            throw new BadRequestException();
        }

        return viewAuction;
    }
    public List<ViewAuctionList> getAuctionList(){
        List<AuctionEntity> auctions = getAllAuctions();
        List<ViewAuctionList> auctionViews = new ArrayList<>();
        for (AuctionEntity auction : auctions){
            var auctionView = new ViewAuctionList(auction.getPrice(),auction.getId(),auction.getCategory().getName(),auction.getTitle()
                    ,getMiniature(auction.getId(),1));
            auctionViews.add(auctionView);

        }

        return auctionViews;
    }


    public AuctionEntity findAuctionById(Long auction_id) {
        return entityManager.createQuery("SELECT auction FROM AuctionEntity auction WHERE auction.id =: auction_id", AuctionEntity.class)
                .setParameter("auction_id", auction_id)
                .getSingleResult();
    }
    public String getMiniature(Long auction_id,int positionPhoto){
        return (String) entityManager.createQuery("SELECT photo.name FROM PhotoEntity photo WHERE photo.auction.id =: auction_id AND photo.position =: positionPhoto")
                .setParameter("positionPhoto",positionPhoto)
                .setParameter("auction_id", auction_id)
                .getSingleResult();
    }
    public List<AuctionEntity> getAllAuctions(){
        return entityManager.createQuery("select auction FROM AuctionEntity auction",AuctionEntity.class)
                .getResultList();
    }
}
