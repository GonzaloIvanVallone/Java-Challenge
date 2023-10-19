package Meyguer.ChromaticLantern.Repository;

import Meyguer.ChromaticLantern.Model.HardwareEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface HardwareEntityRepository extends JpaRepository<HardwareEntity, Long> {
    @Query(value = "SELECT * FROM HARDWARE_ENTITY WHERE MODEL_NAME = :modelName", nativeQuery = true)
    Optional<HardwareEntity> findType(@Param("modelName") String modelName);
}
