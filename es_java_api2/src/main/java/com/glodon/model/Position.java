package com.glodon.model;

import java.util.List;

public class Position {
    private String id;
    private String job_title;
    private String job_salary;
    private List<String> work_place;
    private String experience;
    private String education;
    private String company;
    private String company_type;
    private String company_state;
    private String company_staff;
    private String publis_name;
    private String publis_job;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJob_title() {
        return job_title;
    }

    public void setJob_title(String job_title) {
        this.job_title = job_title;
    }

    public String getJob_salary() {
        return job_salary;
    }

    public void setJob_salary(String job_salary) {
        this.job_salary = job_salary;
    }

    public List<String> getWork_place() {
        return work_place;
    }

    public void setWork_place(List<String> work_place) {
        this.work_place = work_place;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCompany_type() {
        return company_type;
    }

    public void setCompany_type(String company_type) {
        this.company_type = company_type;
    }

    public String getCompany_state() {
        return company_state;
    }

    public void setCompany_state(String company_state) {
        this.company_state = company_state;
    }

    public String getCompany_staff() {
        return company_staff;
    }

    public void setCompany_staff(String company_staff) {
        this.company_staff = company_staff;
    }

    public String getPublis_name() {
        return publis_name;
    }

    public void setPublis_name(String publis_name) {
        this.publis_name = publis_name;
    }

    public String getPublis_job() {
        return publis_job;
    }

    public void setPublis_job(String publis_job) {
        this.publis_job = publis_job;
    }

    @Override
    public String toString() {
        return "Position{" +
                "id='" + id + '\'' +
                ", job_title='" + job_title + '\'' +
                ", job_salary='" + job_salary + '\'' +
                ", work_place=" + work_place +
                ", experience='" + experience + '\'' +
                ", education='" + education + '\'' +
                ", company='" + company + '\'' +
                ", company_type='" + company_type + '\'' +
                ", company_state='" + company_state + '\'' +
                ", company_staff='" + company_staff + '\'' +
                ", publis_name='" + publis_name + '\'' +
                ", publis_job='" + publis_job + '\'' +
                '}';
    }
}
