package com.hms.model;
import java.math.BigDecimal;
public record Doctor(int id, String name, String specialization, String department, String licenseNumber,
                     String phone, String email, String room, BigDecimal consultationFee, boolean available) {}
