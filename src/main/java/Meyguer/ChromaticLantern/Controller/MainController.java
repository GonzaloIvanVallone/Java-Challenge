package Meyguer.ChromaticLantern.Controller;

import Meyguer.ChromaticLantern.Exceptions.*;
import Meyguer.ChromaticLantern.Model.HardwareEntity;
import Meyguer.ChromaticLantern.Model.Keyboard;
import Meyguer.ChromaticLantern.Model.Monitor;
import Meyguer.ChromaticLantern.Model.Mouse;
import Meyguer.ChromaticLantern.Service.HardwareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("meyguer")
public class MainController {
    @Autowired
    HardwareService hardwareService;
    @GetMapping("/details/{modelName}")
    public ResponseEntity<Object> getById(@PathVariable("modelName") String modelName, @RequestParam(value="flag", required = false, defaultValue = "false") Boolean flag){
        try {
            Optional<HardwareEntity> he = hardwareService.getIndex(modelName);
            if(he.isPresent()) {
                switch (he.get().getType()) {
                    case "monitor" -> {
                        return new ResponseEntity<>(hardwareService.monitorDetails(he, flag), HttpStatus.OK);
                    }
                    case "mouse" -> {
                        return new ResponseEntity<>(hardwareService.mouseDetails(he, flag), HttpStatus.OK);
                    }
                    case "keyboard" -> {
                        return new ResponseEntity<>(hardwareService.keyboardDetails(he, flag), HttpStatus.OK);
                    }
                    default -> throw new ModelNotFoundException();
                }
            }
            throw new ModelNotFoundException();
        }catch(ModelNotFoundException e){
            return new ResponseEntity<>("Model not found", HttpStatus.BAD_REQUEST);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @GetMapping("/model/keyboard")
    public ResponseEntity<Object> getModelKeyboard(){
        return new ResponseEntity<>(hardwareService.getAllKeyboardModels(), HttpStatus.OK);
    }
    @GetMapping("/model/mouse")
    public ResponseEntity<Object> getModelMouse() {
        return new ResponseEntity<>(hardwareService.getAllMouseModels(), HttpStatus.OK);
    }
    @GetMapping("/model/monitor")
    public ResponseEntity<Object> getModelMonitor() {
        return new ResponseEntity<>(hardwareService.getAllMonitorModels(), HttpStatus.OK);
    }
    @PostMapping("/save/monitor")
    public ResponseEntity<Object> createMonitor(@RequestBody Monitor m) {
        try {
            hardwareService.createMonitor(m);
            return new ResponseEntity<>("Product created", HttpStatus.OK);
        }catch (DuplicatesNotAllowedException e){
            return new ResponseEntity<>("A product with given Model Name already exist", HttpStatus.BAD_REQUEST);
        }catch (DataValueException e){
            return new ResponseEntity<>("Some field/s might be null, please check", HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/save/keyboard")
    public ResponseEntity<Object> createKeyboard(@RequestBody Keyboard k){
        try{
            hardwareService.createKeyboard(k);
            return new ResponseEntity<>("Product created", HttpStatus.OK);
        }catch(DuplicatesNotAllowedException e){
            return new ResponseEntity<>("A product with given Model Name already exist", HttpStatus.BAD_REQUEST);
        }catch (DataValueException e){
            return new ResponseEntity<>("Some field/s might be null, please check", HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/save/mouse")
    public ResponseEntity<Object> createMouse(@Validated @RequestBody Mouse ms){
        try{
            hardwareService.createMouse(ms);
            return new ResponseEntity<>("Product created", HttpStatus.OK);
        }catch(DuplicatesNotAllowedException e){
            return new ResponseEntity<>("A product with given Model Name already exist", HttpStatus.BAD_REQUEST);
        }catch (DataValueException e){
            return new ResponseEntity<>("Some field/s might be null, please check", HttpStatus.BAD_REQUEST);
        }
    }


    @PutMapping("/sell/{modelName}")
    public ResponseEntity<Object> sellProduct(@PathVariable("modelName") String modelName){
        try{
            return new ResponseEntity<>("Product sold, stock recalculated: " + hardwareService.sellProduct(modelName) , HttpStatus.OK);
        }catch(OutOfStockException e){
            return new ResponseEntity<>("Out of stock", HttpStatus.BAD_REQUEST);
        }catch(ModelNotFoundException e){
            return new ResponseEntity<>("Model Not found", HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/{modelName}")
    public ResponseEntity<Object> deleteProduct(@PathVariable("modelName") String modelName) {
        try{
            return new ResponseEntity<>("Product deleted: "+  hardwareService.deleteProduct(modelName), HttpStatus.OK);
        }catch(ModelNotFoundException e) {
            return new ResponseEntity<>("Model Not found", HttpStatus.BAD_REQUEST);
        }catch(DataValueException e){
            return new ResponseEntity<>("Invalid value/es, try again", HttpStatus.BAD_REQUEST);
        }
    }
}
