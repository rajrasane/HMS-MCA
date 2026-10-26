package com.hms.service;
import com.hms.dao.*;
import java.sql.Connection;
public record HospitalService(PatientDao patients, DoctorDao doctors, AppointmentDao appointments) {
    public HospitalService(Connection c){this(new PatientDao(c),new DoctorDao(c),new AppointmentDao(c));}
}
