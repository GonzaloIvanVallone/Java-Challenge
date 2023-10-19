package Meyguer.ChromaticLantern.Model;

import jakarta.persistence.*;
import lombok.*;
@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Mouse extends Hardware{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private Double dpi;
    @Column(nullable = false)
    private Integer buttonQuantity;
}
