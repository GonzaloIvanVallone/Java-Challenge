package Meyguer.ChromaticLantern.Model.DTO;

public class KeyboardDTO {
    private String modelName;
    private String brand;
    private Boolean isImported;
    private Integer stock;
    private Double weight;
    private Double price;
    private Integer keyQuantity;
    private Boolean isMechanical;


    public KeyboardDTO() {
    }

    public KeyboardDTO(String modelName, String brand, Boolean isImported, Integer stock, Double weight, Double price, Integer keyQuantity, Boolean isMechanical) {
        this.modelName = modelName;
        this.brand = brand;
        this.isImported = isImported;
        this.stock = stock;
        this.weight = weight;
        this.price = price;
        this.keyQuantity = keyQuantity;
        this.isMechanical = isMechanical;
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

    public Integer getKeyQuantity() {
        return keyQuantity;
    }

    public void setKeyQuantity(Integer keyQuantity) {
        this.keyQuantity = keyQuantity;
    }

    public Boolean getMechanical() {
        return isMechanical;
    }

    public void setMechanical(Boolean mechanical) {
        isMechanical = mechanical;
    }

}
