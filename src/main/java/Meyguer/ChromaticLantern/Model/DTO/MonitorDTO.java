package Meyguer.ChromaticLantern.Model.DTO;

public class MonitorDTO {
    private String modelName;
    private String brand;
    private Boolean isImported;
    private Integer stock;
    private Double weight;
    private Double price;
    private Double inches;
    private String panelType;


    public MonitorDTO() {
    }

    public MonitorDTO(String modelName, String brand, Boolean isImported, Integer stock, Double weight, Double price, Double inches, String panelType) {
        this.modelName = modelName;
        this.brand = brand;
        this.isImported = isImported;
        this.stock = stock;
        this.weight = weight;
        this.price = price;
        this.inches = inches;
        this.panelType = panelType;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Boolean getImported() {
        return isImported;
    }

    public void setImported(Boolean imported) {
        isImported = imported;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getInches() {
        return inches;
    }

    public void setInches(Double inches) {
        this.inches = inches;
    }

    public String getPanelType() {
        return panelType;
    }

    public void setPanelType(String panelType) {
        this.panelType = panelType;
    }

}

