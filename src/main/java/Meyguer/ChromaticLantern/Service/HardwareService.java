package Meyguer.ChromaticLantern.Service;

import Meyguer.ChromaticLantern.Exceptions.*;
import Meyguer.ChromaticLantern.Model.*;
import Meyguer.ChromaticLantern.Model.DTO.KeyboardDTO;
import Meyguer.ChromaticLantern.Model.DTO.MonitorDTO;
import Meyguer.ChromaticLantern.Model.DTO.MouseDTO;
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
    public Optional<HardwareEntity> getIndex(String modelName){
        return hardwareEntityRepository.findType(modelName);
    }
    public KeyboardDTO keyboardDetails(@NotNull Optional<HardwareEntity> he, Boolean flag) throws ModelNotFoundException, IOException {
        if(he.isPresent()){
            Optional<Keyboard> ha = hardwareRepository.findKeyboardData(he.get().getProdId());
            if(ha.isPresent()){
                KeyboardDTO kDTO = new KeyboardDTO();
                kDTO.setModelName(ha.get().getModelName());
                kDTO.setBrand(ha.get().getBrand());
                kDTO.setImported(ha.get().getIsImported());
                kDTO.setStock(ha.get().getStock());
                kDTO.setWeight(ha.get().getWeight());
                if(ha.get().getIsImported() && flag){
                    kDTO.setPrice(ha.get().getPrice()*consumerService.requested());
                }else{
                    kDTO.setPrice(ha.get().getPrice());
                }
                kDTO.setKeyQuantity(ha.get().getKeyQuantity());
                kDTO.setMechanical(ha.get().getIsMechanical());
                return kDTO;
            }
        }
        throw new ModelNotFoundException();
    }
    public MouseDTO mouseDetails(@NotNull Optional<HardwareEntity> he, Boolean flag) throws ModelNotFoundException, IOException {
        if(he.isPresent()){
            Optional<Mouse> ha = hardwareRepository.findMouseData(he.get().getProdId());
            if(ha.isPresent()){
                MouseDTO msDTO = new MouseDTO();
                msDTO.setModelName(ha.get().getModelName());
                msDTO.setBrand(ha.get().getBrand());
                msDTO.setImported(ha.get().getIsImported());
                msDTO.setStock(ha.get().getStock());
                msDTO.setWeight(ha.get().getWeight());
                if(ha.get().getIsImported() && flag){
                    msDTO.setPrice(ha.get().getPrice()*consumerService.requested());
                }else{
                    msDTO.setPrice(ha.get().getPrice());
                }
                msDTO.setDpi(ha.get().getDpi());
                msDTO.setButtonQuantity(ha.get().getButtonQuantity());
                return msDTO;
            }
        }
        throw new ModelNotFoundException();
    }
    public MonitorDTO monitorDetails(@NotNull Optional<HardwareEntity> he, Boolean flag) throws ModelNotFoundException, IOException {
        if(he.isPresent()){
            Optional<Monitor> ha = hardwareRepository.findMonitorData(he.get().getProdId());
            if(ha.isPresent()){
                MonitorDTO mDTO = new MonitorDTO();
                mDTO.setModelName(ha.get().getModelName());
                mDTO.setBrand(ha.get().getBrand());
                mDTO.setImported(ha.get().getIsImported());
                mDTO.setStock(ha.get().getStock());
                mDTO.setWeight(ha.get().getWeight());
                if(ha.get().getIsImported() && flag){
                    mDTO.setPrice(ha.get().getPrice()*consumerService.requested());
                }else{
                    mDTO.setPrice(ha.get().getPrice());
                }
                mDTO.setInches(ha.get().getInches());
                mDTO.setPanelType(ha.get().getPanelType());
                return mDTO;
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
