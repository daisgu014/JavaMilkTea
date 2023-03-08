package App.Model;

import java.sql.Date;

public class Category {
    private Integer categoryID;
    private String categoryName;
    private Date createAt;
    private Date deleteAt;

    public Category() {

    }

    public Category(Integer categoryID, String categoryName, Date createAt, Date deleteAt) {
        this.categoryID = categoryID;
        this.categoryName = categoryName;
        this.createAt = createAt;
        this.deleteAt = deleteAt;
    }

    public Category(String categoryName, Date createAt) {
        this.categoryName = categoryName;
        this.createAt = createAt;
    }

    public Integer getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(Integer categoryID) {
        this.categoryID = categoryID;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Date getDeleteAt() {
        return deleteAt;
    }

    public void setDeleteAt(Date deleteAt) {
        this.deleteAt = deleteAt;
    }
}