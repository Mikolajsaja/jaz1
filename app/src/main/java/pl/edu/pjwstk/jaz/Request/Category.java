package pl.edu.pjwstk.jaz.Request;

import java.util.List;

public class Category {

    private String categoryName;
    private String sectionName;

    public Category(String categoryName, String sectionName) {
        this.categoryName = categoryName;
        this.sectionName = sectionName;
    }

    public Category() {
    }

    public String getCategoryName() {
        return categoryName;
    }

    public String getSectionName() {
        return sectionName;
    }

}
