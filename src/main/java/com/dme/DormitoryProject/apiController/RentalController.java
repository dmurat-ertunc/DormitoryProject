package com.dme.DormitoryProject.apiController;

import com.dme.DormitoryProject.Manager.Abstract.IRentalService;
import com.dme.DormitoryProject.dtos.rentalDtos.RentalDTO;
import com.dme.DormitoryProject.entity.Rental;
import com.dme.DormitoryProject.response.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
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
    public Result getAll(){
        return this.rentalService.getAll();
    }

    @GetMapping("rentalId/{id}")
    public Result getById(@PathVariable Long id){
        return this.rentalService.getById(id);
    }
    @GetMapping("afterStartTime")
    public Result afterStartTime(@RequestParam LocalTime startTime){
        return this.rentalService.afterRental(startTime);
    }
    @PostMapping("saveRental")
    public Result saveRental(@RequestBody RentalDTO rentalDTO){
        return this.rentalService.saveRental(rentalDTO);
    }
    @PutMapping("update/{id}")
    public Result updateRental(@PathVariable Long id,@RequestBody RentalDTO rentalDTO){
        return this.rentalService.updateRental(id,rentalDTO);
    }
    @PutMapping("delete/{id}")
    public Result deleteRental(@PathVariable Long id){
        return this.rentalService.deleteRental(id);
    }
}
