package Meyguer.ChromaticLantern.Service;

import Meyguer.ChromaticLantern.Exceptions.*;
import Meyguer.ChromaticLantern.Model.*;
import Meyguer.ChromaticLantern.Model.DTO.HardwareDTO;
import Meyguer.ChromaticLantern.Repository.*;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;


@Service
public class HardwareService {
    @Autowired
    HardwareEntityRepository hardwareEntityRepository;
    @Autowired
    HardwareRepository hardwareRepository;
    @Autowired
    ConsumerService consumerService;
    public HardwareDTO getDetails(String modelName, Boolean flag) throws ModelNotFoundException, IOException {
        Optional<HardwareEntity> h = hardwareEntityRepository.findType(modelName);
        if(h.isPresent()){
            switch(h.get().getType()){
                case "monitor":{
                    Optional<Monitor> ha = hardwareRepository.findMonitorData(h.get().getProdId());
                    if(ha.isPresent()){
                        HardwareDTO hDTO = new HardwareDTO();
                        hDTO.setId(ha.get().getId());
                        hDTO.setModelName(ha.get().getModelName());
                        hDTO.setBrand(ha.get().getBrand());
                        hDTO.setImported(ha.get().getIsImported());
                        hDTO.setStock(ha.get().getStock());
                        hDTO.setWeight(ha.get().getWeight());
                        if(ha.get().getIsImported() && flag){
                            hDTO.setPrice(ha.get().getPrice()*consumerService.requested());
                        }else{
                            hDTO.setPrice(ha.get().getPrice());
                        }
                        hDTO.setInches(ha.get().getInches());
                        hDTO.setPanelType(ha.get().getPanelType());
                        return hDTO;
                    }
                }
                case "mouse":{
                    Optional<Mouse> ha = hardwareRepository.findMouseData(h.get().getProdId());
                    if(ha.isPresent()){
                        HardwareDTO hDTO = new HardwareDTO();
                        hDTO.setId(ha.get().getId());
                        hDTO.setModelName(ha.get().getModelName());
                        hDTO.setBrand(ha.get().getBrand());
                        hDTO.setImported(ha.get().getIsImported());
                        hDTO.setStock(ha.get().getStock());
                        hDTO.setWeight(ha.get().getWeight());
                        if(ha.get().getIsImported() && flag){
                            hDTO.setPrice(ha.get().getPrice()*consumerService.requested());
                        }else{
                            hDTO.setPrice(ha.get().getPrice());
                        }
                        hDTO.setDpi(ha.get().getDpi());
                        hDTO.setButtonQuantity(ha.get().getButtonQuantity());
                        return hDTO;
                    }
                }
                case "keyboard":{
                    Optional<Keyboard> ha = hardwareRepository.findKeyboardData(h.get().getProdId());
                    if(ha.isPresent()){
                        HardwareDTO hDTO = new HardwareDTO();
                        hDTO.setId(ha.get().getId());
                        hDTO.setModelName(ha.get().getModelName());
                        hDTO.setBrand(ha.get().getBrand());
                        hDTO.setImported(ha.get().getIsImported());
                        hDTO.setStock(ha.get().getStock());
                        hDTO.setWeight(ha.get().getWeight());
                        if(ha.get().getIsImported() && flag){
                            hDTO.setPrice(ha.get().getPrice()*consumerService.requested());
                        }else{
                            hDTO.setPrice(ha.get().getPrice());
                        }
                        hDTO.setKeyQuantity(ha.get().getKeyQuantity());
                        hDTO.setMechanical(ha.get().getIsMechanical());
                        return hDTO;
                    }
                }
                default:
                    return new HardwareDTO();
            }
        }
        throw new ModelNotFoundException();
    }

    public List<String> getAllKeyboardModels() {
        return hardwareRepository.findAllKeyboardModels();
    }
    public List<String> getAllMonitorModels(){
        return hardwareRepository.findAllMonitorModels();
    }
    public List<String> getAllMouseModels(){
        return hardwareRepository.findAllMouseModels();
    }

    public void createMonitor(@NotNull Monitor m) throws DuplicatesNotAllowedException, DataValueException {
        if(checkNullFields(m)){
            throw new DataValueException();
        }
        if(getAllMonitorModels().contains(m.getModelName())) {
            throw new DuplicatesNotAllowedException();
        }
        hardwareRepository.save(m);
        HardwareEntity hardwareEntity = new HardwareEntity();
        hardwareEntity.setType(m.getClass().getSimpleName().toLowerCase());
        hardwareEntity.setModelName(m.getModelName());
        hardwareEntity.setProdId(m.getId());
        hardwareEntityRepository.save(hardwareEntity);
    }
    public void createKeyboard(@NotNull Keyboard k) throws DuplicatesNotAllowedException, DataValueException {
        if(checkNullFields(k)){
            throw new DataValueException();
        }
        if (getAllKeyboardModels().contains(k.getModelName())) {
            throw new DuplicatesNotAllowedException();
        }
        HardwareEntity hardwareEntity = new HardwareEntity();
        hardwareEntity.setType(k.getClass().getSimpleName().toLowerCase());
        hardwareEntity.setModelName(k.getModelName());
        hardwareRepository.save(k);
        hardwareEntity.setProdId(k.getId());
        hardwareEntityRepository.save(hardwareEntity);
    }
    public void createMouse(@NotNull Mouse ms) throws DuplicatesNotAllowedException, DataValueException {
        if(checkNullFields(ms)){
            throw new DataValueException();
        }
        if (getAllMouseModels().contains(ms.getModelName())) {
            throw new DuplicatesNotAllowedException();
        }
        HardwareEntity hardwareEntity = new HardwareEntity();
        hardwareEntity.setType(ms.getClass().getSimpleName().toLowerCase());
        hardwareEntity.setModelName(ms.getModelName());
        hardwareRepository.save(ms);
        hardwareEntity.setProdId(ms.getId());
        hardwareEntityRepository.save(hardwareEntity);
    }
    public int sellProduct(@NotNull String modelName) throws OutOfStockException, ModelNotFoundException {
        Optional<HardwareEntity> h = hardwareEntityRepository.findType(modelName);
        if(h.isPresent()){
            switch (h.get().getType()) {
                case "monitor" -> {
                    return reduceMonitorStock(h.get().getProdId());
                }
                case "mouse" -> {
                    return reduceMouseStock(h.get().getProdId());
                }
                case "keyboard" -> {
                    return reduceKeyboardStock(h.get().getProdId());
                }
                default -> throw new ModelNotFoundException();
            }
        }
        throw new ModelNotFoundException();
    }
    public int reduceMonitorStock(Long h) throws OutOfStockException {
        Optional<Monitor> ha = hardwareRepository.findMonitorData(h);
        if(ha.isPresent() && ha.get().getStock() > 0) {
            Monitor updated = ha.get();
            updated.setStock(updated.getStock()-1);
            hardwareRepository.save(updated);
            return updated.getStock();
        } else {
            throw new OutOfStockException();
        }
    }
    public int reduceMouseStock(Long h) throws OutOfStockException {
        Optional<Mouse> ha = hardwareRepository.findMouseData(h);
        if(ha.isPresent() && ha.get().getStock() > 0) {
            Mouse updated = ha.get();
            updated.setStock(updated.getStock()-1);
            hardwareRepository.save(updated);
            return updated.getStock();
        } else {
            throw new OutOfStockException();
        }
    }
    public int reduceKeyboardStock(Long h) throws OutOfStockException {
        Optional<Keyboard> ha = hardwareRepository.findKeyboardData(h);
        if(ha.isPresent() && ha.get().getStock() > 0) {
            Keyboard updated = ha.get();
            updated.setStock(updated.getStock()-1);
            hardwareRepository.save(updated);
            return updated.getStock();
        } else {
            throw new OutOfStockException();
        }
    }
    public Hardware deleteProduct(String modelName) throws DataValueException, ModelNotFoundException {
        Optional<HardwareEntity> h = hardwareEntityRepository.findType(modelName);
        if(h.isPresent()) {
            switch (h.get().getType()) {
                case "monitor" -> {
                    Optional<Monitor> ha = hardwareRepository.findMonitorData(h.get().getProdId());
                    if (ha.isPresent()) {
                        Monitor m = ha.orElse(null);
                        hardwareRepository.delete(m);
                        return m;
                    }
                }
                case "mouse" -> {
                    Optional<Mouse> ha = hardwareRepository.findMouseData(h.get().getProdId());
                    if (ha.isPresent()) {
                        Mouse ms = ha.orElse(null);
                        hardwareRepository.delete(ms);
                        return ms;
                    }
                }
                case "keyboard" -> {
                    Optional<Keyboard> ha = hardwareRepository.findKeyboardData(h.get().getProdId());
                    if (ha.isPresent()) {
                        Keyboard k = ha.orElse(null);
                        hardwareRepository.delete(k);
                        return k;
                    }
                }
                default -> throw new ModelNotFoundException();
            }
        }
        throw new ModelNotFoundException();
    }
    public boolean checkNullFields(Object o){
        Field[] fields = o.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try{
                if(field.get(o)== null){
                    return true;
                }
            }catch (IllegalAccessException e){
                System.out.println("checking");
            }
        }
        return false;
    }
}
