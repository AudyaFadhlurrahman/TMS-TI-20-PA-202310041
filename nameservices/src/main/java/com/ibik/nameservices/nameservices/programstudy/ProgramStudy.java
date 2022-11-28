package com.ibik.nameservices.nameservices.programstudy;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.ibik.nameservices.nameservices.programs.Programs;

@Entity
@Table(name = "programstudy")

public class ProgramStudy implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 50)
    @NotEmpty(message = "Name is required")
    private String name;

    @Column(length = 255)
    private String description;

    @Column(length = 5)
    @NotEmpty(message = "Code is required")
    private String code;

    // @Column(length = 11)
    // private Integer program_id;

    @ManyToOne
    @JoinColumn(name = "program_id")
    private Programs programs;

    // @Column(length = 11)
    // private Integer faculty_id;

    @OneToMany
    @JoinColumn(name = "faculty_id")
    private Set<ProgramStudy> departments;

    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    private boolean is_active;

    // private Integer program_id;

    public ProgramStudy() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ProgramStudy(int id, String name, String description, String code, boolean is_active) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.code = code;
        this.is_active = is_active;
    }

    public String getCode() {
        return code;
    }

    public Set<ProgramStudy> getDepartments() {
        return departments;
    }

    public void setDepartments(Set<ProgramStudy> departments) {
        this.departments = departments;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Programs getPrograms() {
        return programs;
    }

    public void setPrograms(Programs programs) {
        this.programs = programs;
    }

    public boolean isIs_active() {
        return is_active;
    }

    public void setIs_active(boolean is_active) {
        this.is_active = is_active;
    }

}
