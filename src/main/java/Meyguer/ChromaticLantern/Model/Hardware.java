package Meyguer.ChromaticLantern.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@MappedSuperclass
@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class Hardware {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true)
    private String modelName;
    @Column(nullable = false)
    private String brand;
    @Column(nullable = false)
    private Boolean isImported;
    @Column(nullable = false)
    private Integer stock;
    @Column(nullable = false)
    private Double weight;
    @Column(nullable = false)
    private Double price;
}
