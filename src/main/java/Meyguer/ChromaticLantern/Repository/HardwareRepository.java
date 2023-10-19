package Meyguer.ChromaticLantern.Repository;

import Meyguer.ChromaticLantern.Model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface HardwareRepository extends JpaRepository<Hardware, Long> {
    @Query(value = "SELECT * FROM Keyboard WHERE id = :prodId", nativeQuery = true)
    Optional<Keyboard> findKeyboardData(@Param("prodId") Long prodId);
    @Query(value = "SELECT * FROM Mouse WHERE id = :prodId", nativeQuery = true)
    Optional<Mouse> findMouseData(@Param("prodId") Long prodId);
    @Query(value = "SELECT * FROM Monitor WHERE id = :prodId", nativeQuery = true)
    Optional<Monitor> findMonitorData(@Param("prodId") Long prodId);
    @Query(value = "SELECT MODEL_NAME FROM Keyboard", nativeQuery = true)
    List<String> findAllKeyboardModels();
    @Query(value = "SELECT MODEL_NAME FROM Monitor", nativeQuery = true)
    List<String> findAllMonitorModels();
    @Query(value = "SELECT MODEL_NAME FROM Mouse", nativeQuery = true)
    List<String> findAllMouseModels();
}
