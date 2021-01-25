package pl.edu.pjwstk.jaz.Service;

import org.springframework.stereotype.Repository;
import pl.edu.pjwstk.jaz.BadRequestException;
import pl.edu.pjwstk.jaz.Request.Category;
import pl.edu.pjwstk.jaz.readiness.UserEntity;
import pl.edu.pjwstk.jaz.tables.AuctionEntity;
import pl.edu.pjwstk.jaz.tables.CategoryEntity;
import pl.edu.pjwstk.jaz.tables.SectionEntity;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public class SectionRepository {
    private final EntityManager em;

    public SectionRepository(EntityManager em) {
        this.em = em;
    }

    public void createSection(String sectionName) {
        var section = new SectionEntity();
        section.setName(sectionName);
        em.persist(section);

    }

    public void updateSection(String sectionName,String newName) {
        try{
            var section = findSectionByName(sectionName);
            section.setName(newName);
            em.merge(section);
        }
        catch (NoResultException exception) {
            throw new BadRequestException();
        }
    }

    public void createCategory(Category category){

        var categoryEntity  = new CategoryEntity();
        categoryEntity.setName(category.getCategoryName());
        categoryEntity.setSection(findSectionByName(category.getSectionName()));
        em.persist(categoryEntity);
    }

    public void updateCategory(Category category,String categoryName) {
        try{
            var categoryEntity = findCategoryByName(categoryName);
            categoryEntity.setName(category.getCategoryName());
            categoryEntity.setSection(findSectionByName(category.getSectionName()));
            em.merge(categoryEntity);
        }catch (NoResultException exception) {
            throw new BadRequestException();
        }
    }

    public CategoryEntity findCategoryByName(String categoryName) {
        return em.createQuery("SELECT category FROM CategoryEntity category WHERE category.name =: categoryName", CategoryEntity.class)
                .setParameter("categoryName", categoryName)
                .getSingleResult();
    }

    public SectionEntity findSectionByName(String sectionName) {
        return em.createQuery("SELECT section FROM SectionEntity section WHERE section.name =: sectionName", SectionEntity.class)
                .setParameter("sectionName", sectionName)
                .getSingleResult();
    }

    public List<AuctionEntity> getAuctionsByCreator(UserEntity userEntity){
        return em.createQuery("select auction FROM AuctionEntity auction WHERE auction.userEntity =:userEntity", AuctionEntity.class)
                .setParameter("userEntity",userEntity)
                .getResultList();
    }

}
