package com.dme.DormitoryProject.apiController;

import com.dme.DormitoryProject.Manager.Abstract.IRentalService;
import com.dme.DormitoryProject.dtos.rentalDtos.RentalDTO;
import com.dme.DormitoryProject.entity.Rental;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/rentals/")
public class RentalController {

    private IRentalService rentalService;
    @Autowired
    public RentalController(IRentalService rentalService){
        this.rentalService=rentalService;
    }

    @GetMapping("getAll")
    public List<RentalDTO> getAll(){
        return this.rentalService.getAll();
    }

    @GetMapping("rentalId/{id}")
    public Optional<RentalDTO> getById(@PathVariable Long id){
        return this.rentalService.getById(id);
    }
    @PostMapping("saveRental")
    public Rental saveRental(@RequestBody RentalDTO rentalDTO){
        return this.rentalService.saveRental(rentalDTO);
    }
    @PutMapping("update/{id}")
    public Rental updateRental(@PathVariable Long id,@RequestBody RentalDTO rentalDTO){
        return this.rentalService.updateRental(id,rentalDTO);
    }
    @PutMapping("delete/{id}")
    public Rental deleteRental(@PathVariable Long id){
        return this.rentalService.deleteRental(id);
    }
}
