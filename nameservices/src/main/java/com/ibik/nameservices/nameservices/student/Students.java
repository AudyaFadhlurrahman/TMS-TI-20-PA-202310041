package com.ibik.nameservices.nameservices.student;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.ibik.nameservices.nameservices.courses.Courses;
import com.ibik.nameservices.nameservices.programstudy.ProgramStudy;
import com.ibik.nameservices.nameservices.programs.Programs;

@Entity
@Table(name = "student")
public class Students implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 15)
    @NotEmpty(message = "NPM is required")
    private String npm;

    @Column(length = 10)
    @NotEmpty(message = "Firstname is required")
    private String firstname;

    @Column(length = 10)
    private String middlename;

    @Column(length = 10)
    @NotEmpty(message = "Lastname is required")
    private String lastname;

    @Column(length = 50)
    @NotEmpty(message = "Email is required")
    private String email;

    @Column(columnDefinition = "DATE")
    @NotEmpty(message = "Birthdate is required")
    private String birth_date;

    @ManyToOne
    @JoinColumn(name = "program_id")
    private Programs programs;

    @ManyToOne
    @JoinColumn(name = "departement_id")
    private ProgramStudy programStudy;

    @ManyToMany
    @JoinTable(name = "student_rel_course", joinColumns = @JoinColumn(name = "student_id"), inverseJoinColumns = @JoinColumn(name = "course_id"))
    private Set<Courses> courses;

    public Students() {
    }

    public Students(int id, String npm, String firstname, String middlename, String lastname, String email,
            String birth_date, int program_id) {
        this.id = id;
        this.npm = npm;
        this.firstname = firstname;
        this.middlename = middlename;
        this.lastname = lastname;
        this.email = email;
        this.birth_date = birth_date;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNpm() {
        return npm;
    }

    public void setNpm(String npm) {
        this.npm = npm;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Set<Courses> getCourses() {
        return courses;
    }

    public void setCourses(Set<Courses> courses) {
        this.courses = courses;
    }

    public Programs getPrograms() {
        return programs;
    }

    public void setPrograms(Programs programs) {
        this.programs = programs;
    }

    public ProgramStudy getProgramStudy() {
        return programStudy;
    }

    public void setProgramStudy(ProgramStudy programStudy) {
        this.programStudy = programStudy;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(String birth_date) {
        this.birth_date = birth_date;
    }

    public Object findByEmail(String email2) {
        return null;
    }

}
