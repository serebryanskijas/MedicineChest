package MedicineChest.medicine.vo;

import MedicineChest.category.Category;

public class SearchVo {

    private String name;

    private Category category;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
