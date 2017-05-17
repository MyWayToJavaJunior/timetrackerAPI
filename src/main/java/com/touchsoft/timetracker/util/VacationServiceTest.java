package com.touchsoft.timetracker.util;

import com.touchsoft.timetracker.api.dao.UserException;
import com.touchsoft.timetracker.api.model.User;
import com.touchsoft.timetracker.api.model.Vacation;
import com.touchsoft.timetracker.api.service.UserService;
import com.touchsoft.timetracker.api.service.VacationService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.Collections;


public class VacationServiceTest {
    static ApplicationContext ctr=new ClassPathXmlApplicationContext("/META-INF/spring/applicationContext.xml");
    static VacationService vacationService=(VacationService)ctr.getBean("vacationService");
    UserService userService=(UserService)ctr.getBean("userService");
    Vacation vacation=new Vacation();

    public void createVacation() {
        Vacation vacation=new Vacation();
        vacation.setUserVacationId((long)3);
        vacation.setStartDate(LocalDate.now());
        vacation.setEndDate(LocalDate.now());
        vacation.setDuration(123);
        vacation.setDayOffType(1);
        vacationService.addVacation(vacation);
    }


    public void getUser() throws UserException {
        User user=userService.getUser("admin");
        System.out.println(user.toString());
    }


    public void updateVacation(){
        vacation.setComment("update");
        vacationService.editVacation(vacation);
    }

    public static void getVacation(){
        for (Vacation vacation:vacationService.getVacations(LocalDate.now())) {
            System.out.println(vacation.toString());
        }
    }

    public void deleteVacation(){
        Vacation vacation=new Vacation();
        vacation.setVacationId((long)28);
        vacationService.removeVacation(vacation);
    }

    public static void isVacation(){
        System.out.println(vacationService.isVacation((long)1));
    }

    public static void main(String[] args) {
       isVacation();
    }
}
