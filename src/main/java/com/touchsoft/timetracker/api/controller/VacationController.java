package com.touchsoft.timetracker.api.controller;

import com.touchsoft.timetracker.api.model.Vacation;
import com.touchsoft.timetracker.api.service.VacationService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping(value = "/vacations")
public class VacationController {

    @Resource
    private VacationService vacationService;

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET)
    public List<Vacation> getVacations() {
        return vacationService.getVacations(LocalDate.now());
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/{date}", method = RequestMethod.GET)
    public List<Vacation> getVacations(@DateTimeFormat(pattern = "yyyy-MM-dd") @PathVariable(value = "date") LocalDate date) {
        return vacationService.getVacations(date);
    }

    @RequestMapping(value = "/{vacationId}", method = RequestMethod.DELETE)
    public ResponseEntity removeVacation(@PathVariable(value = "vacationId") long vacationId) {
        if (vacationService.isVacation(vacationId)) {
            Vacation vacation = new Vacation();
            vacation.setVacationId(vacationId);
            vacationService.removeVacation(vacation);
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/{vacationId}", method = RequestMethod.PUT)
    public ResponseEntity editVacation(@PathVariable(value = "vacationId") long vacationId, @RequestBody Vacation vacation) {
        if (vacationService.isVacation(vacationId)) {
            vacation.setVacationId(vacationId);
            vacationService.editVacation(vacation);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST)
    public void addVacation(@RequestBody Vacation vacation) {
        vacationService.addVacation(vacation);
    }
}
