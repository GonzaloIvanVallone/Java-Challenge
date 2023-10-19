package Meyguer.ChromaticLantern.Model.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;


public class HardwareDTO {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private long id;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String modelName;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String brand;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Boolean isImported;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer stock;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Double weight;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Double price;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer keyQuantity;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Boolean mechanical;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Double inches;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String panelType;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Double dpi;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer buttonQuantity;

    public HardwareDTO() {
    }

    public HardwareDTO(long id, String modelName, String brand, Boolean isImported, Integer stock, Double weight, Double price, Integer keyQuantity, Boolean isMechanical, Double inches, String panelType, Double dpi, Integer buttonQuantity) {
        this.id = id;
        this.modelName = modelName;
        this.brand = brand;
        this.isImported = isImported;
        this.stock = stock;
        this.weight = weight;
        this.price = price;
        this.keyQuantity = keyQuantity;
        this.mechanical = isMechanical;
        this.inches = inches;
        this.panelType = panelType;
        this.dpi = dpi;
        this.buttonQuantity = buttonQuantity;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
        return mechanical;
    }

    public void setMechanical(Boolean isMechanical) {
        this.mechanical = isMechanical;
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

    public Double getDpi() {
        return dpi;
    }

    public void setDpi(Double dpi) {
        this.dpi = dpi;
    }

    public Integer getButtonQuantity() {
        return buttonQuantity;
    }

    public void setButtonQuantity(Integer buttonQuantity) {
        this.buttonQuantity = buttonQuantity;
    }
}
