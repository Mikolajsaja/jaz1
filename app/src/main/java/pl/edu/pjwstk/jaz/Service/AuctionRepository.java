package pl.edu.pjwstk.jaz.Service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.pjwstk.jaz.BadRequestException;
import pl.edu.pjwstk.jaz.LoginController;
import pl.edu.pjwstk.jaz.Request.Auction;
import pl.edu.pjwstk.jaz.Request.Parameter;
import pl.edu.pjwstk.jaz.Request.Photo;
import pl.edu.pjwstk.jaz.UnauthorizedException;
import pl.edu.pjwstk.jaz.readiness.UserEntity;
import pl.edu.pjwstk.jaz.tables.*;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.*;


@Transactional
@Service
public class AuctionRepository {

    private final LoginController loginController;
    private final EntityManager entityManager;


    public AuctionRepository(LoginController loginController, EntityManager entityManager) {
        this.loginController = loginController;
        this.entityManager = entityManager;
    }

    public void createAuction(Auction auction) {
        var auctionEntity = new AuctionEntity();

        auctionEntity.setTitle(auction.getAuctionTitle());
        auctionEntity.setDescription(auction.getAuctionDescription());
        auctionEntity.setPrice(auction.getPrice());
        auctionEntity.setVersion(1L);
        auctionEntity.setUserEntity(findUserByUsername(loginController.getUsername()));
        auctionEntity.setCategory(findCategoryByName(auction.getCategoryName()));
        auctionEntity.setAuctionParameters(setAuctionParameters(auction.getParameterList(), auctionEntity));
        auctionEntity.setPhotosSet(setAuctionPhotos(auction.getPhotoList(), auctionEntity));
        entityManager.persist(auctionEntity);

    }

    public void editAuction(Auction auctionRequest, Long auction_id) {

        var auction = findAuctionById(auction_id);

        if (findUserByUsername(loginController.getUsername()).equals(auction.getUserEntity())) {

            if (auctionRequest.getAuctionTitle() != null) {
                auction.setTitle(auctionRequest.getAuctionTitle());
            }
            if (auctionRequest.getAuctionDescription() != null) {
                auction.setDescription(auctionRequest.getAuctionDescription());
            }
            if (auctionRequest.getPrice() != 0) {
                auction.setPrice(auctionRequest.getPrice());
            }
            if (auctionRequest.getCategoryName() != null) {
                try {
                    if (findCategoryByName(auctionRequest.getCategoryName()) != null) {
                        auction.setCategory(findCategoryByName(auctionRequest.getCategoryName()));
                    }
                } catch (NoResultException exception) {
                    throw new BadRequestException();
                }
            }
            if (auctionRequest.getPhotoList() != null) {
                for (PhotoEntity p : auction.getPhotosSet()){
                    deletePhoto(p);
                }
                for (PhotoEntity p : setAuctionPhotos(auctionRequest.getPhotoList(), auction)){
                    entityManager.persist(p);
                }
            }
            if (auctionRequest.getParameterList() != null) {
                for(ParameterEntity auctionParameter : getAllParametersForAuction(auction_id)){
                    deleteParameter(auctionParameter);
                }
                auction.setAuctionParameters(setAuctionParameters(auctionRequest.getParameterList(), auction));
            }
            if (auctionRequest.getVersion().equals(auction.getVersion())) {
                auction.setVersion(auction.getVersion() + 1);

                entityManager.merge(auction);
            } else {
                throw new BadRequestException();
            }

        } else {

            throw new UnauthorizedException();
        }
    }

    public Set<AuctionParameterEntity> setAuctionParameters(List<Parameter> parameters, AuctionEntity auction) {

        Set<AuctionParameterEntity> auctionParameters = new HashSet<>();

        for (Parameter parameterRequest : parameters) {
            var parameter = new ParameterEntity();
            parameter.setKey(parameterRequest.getParameterKey());

            var auctionParameter = new AuctionParameterEntity();
            auctionParameter.setAuction(auction);
            auctionParameter.setParameter(parameter);
            auctionParameter.setValue(parameterRequest.getParameterValue());
            auctionParameters.add(auctionParameter);
        }
        return auctionParameters;
    }

    public Set<PhotoEntity> setAuctionPhotos(List<Photo> auctionPhotos, AuctionEntity auction) {

        Set<PhotoEntity> auctionPhotosSet = new HashSet<>();

        for (Photo photo : auctionPhotos) {
            var auctionPhoto = new PhotoEntity();
            auctionPhoto.setName(photo.getPhotoName());
            auctionPhoto.setPosition(photo.getPhotoPosition());
            auctionPhoto.setAuction(auction);
            auctionPhotosSet.add(auctionPhoto);
        }
        return auctionPhotosSet;

    }

    public CategoryEntity findCategoryByName(String name) {
        return entityManager.createQuery("SELECT category FROM CategoryEntity category WHERE category.name =: name", CategoryEntity.class)
                .setParameter("name", name)
                .getSingleResult();
    }

    public UserEntity findUserByUsername(String username) {
        return entityManager.createQuery("SELECT user FROM UserEntity user WHERE user.username =: username", UserEntity.class)
                .setParameter("username", username) //username!!!
                .getSingleResult();
    }

    public AuctionEntity findAuctionById(Long auction_id) {
        return entityManager.createQuery("SELECT auction FROM AuctionEntity auction WHERE auction.id =: auction_id", AuctionEntity.class)
                .setParameter("auction_id", auction_id)
                .getSingleResult();
    }
    public void deletePhoto(PhotoEntity photo){
        entityManager.createQuery("DELETE FROM PhotoEntity photo where photo=:photo")
                .setParameter("photo",photo)
                .executeUpdate();
    }
    public void deleteParameter(ParameterEntity parameter){
        entityManager.createQuery("DELETE FROM AuctionParameterEntity auctionparameter  where auctionparameter.parameter =:parameter")
                .setParameter("parameter",parameter)
                .executeUpdate();
        entityManager.createQuery("DELETE FROM ParameterEntity parameter where parameter=:parameter")
                .setParameter("parameter",parameter)
                .executeUpdate();
    }
    public List<ParameterEntity> getAllParametersForAuction(Long auction){
        return entityManager.createQuery("SELECT auctionparameter.parameter FROM AuctionParameterEntity auctionparameter WHERE " +
                "auctionparameter.auction.id =: auction",ParameterEntity.class)
                .setParameter("auction",auction)
                .getResultList();
    }

}
