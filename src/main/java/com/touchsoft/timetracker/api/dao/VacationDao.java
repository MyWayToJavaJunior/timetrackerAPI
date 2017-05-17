package com.touchsoft.timetracker.api.dao;


import com.touchsoft.timetracker.api.model.Vacation;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;


public interface VacationDao {

    void insertVacation(Vacation vacation);

    List<Vacation> getVacations(LocalDate date);

    void editVacation(Vacation vacation);

    void deleteVacation(Vacation vacation);

    boolean isVacation(long vacationId);
}
