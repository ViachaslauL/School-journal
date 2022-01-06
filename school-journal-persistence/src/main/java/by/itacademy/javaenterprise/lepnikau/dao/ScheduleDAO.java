package by.itacademy.javaenterprise.lepnikau.dao;

import by.itacademy.javaenterprise.lepnikau.entity.Schedule;

import java.util.List;

public interface ScheduleDAO extends DAO<Schedule>{
    List<Schedule> getAll();
}
