package com.jvt.devthread.healthcare.Activities.Model;

public class BookingAppointmentModel {
    String patientName, patientMobile, patientEmail, patientAddress, bookingTime, bookingDate, doctorName, doctorId, clinicAddress, bookingId,
    paymentMode,paymentStatus;
    Long paidAmount;

    public BookingAppointmentModel() {
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getPatientMobile() {
        return patientMobile;
    }

    public void setPatientMobile(String patientMobile) {
        this.patientMobile = patientMobile;
    }

    public String getPatientEmail() {
        return patientEmail;
    }

    public void setPatientEmail(String patientEmail) {
        this.patientEmail = patientEmail;
    }

    public String getPatientAddress() {
        return patientAddress;
    }

    public void setPatientAddress(String patientAddress) {
        this.patientAddress = patientAddress;
    }

    public String getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(String bookingTime) {
        this.bookingTime = bookingTime;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getClinicAddress() {
        return clinicAddress;
    }

    public void setClinicAddress(String clinicAddress) {
        this.clinicAddress = clinicAddress;
    }

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public Long getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(Long paidAmount) {
        this.paidAmount = paidAmount;
    }

    public BookingAppointmentModel(String patientName, String patientMobile, String patientEmail, String patientAddress, String bookingTime, String bookingDate, String doctorName, String doctorId, String clinicAddress, String bookingId, String paymentMode, String paymentStatus, Long paidAmount) {
        this.patientName = patientName;
        this.patientMobile = patientMobile;
        this.patientEmail = patientEmail;
        this.patientAddress = patientAddress;
        this.bookingTime = bookingTime;
        this.bookingDate = bookingDate;
        this.doctorName = doctorName;
        this.doctorId = doctorId;
        this.clinicAddress = clinicAddress;
        this.bookingId = bookingId;
        this.paymentMode = paymentMode;
        this.paymentStatus = paymentStatus;
        this.paidAmount = paidAmount;
    }
}
