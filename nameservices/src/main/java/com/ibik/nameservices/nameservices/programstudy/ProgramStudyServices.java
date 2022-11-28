package com.ibik.nameservices.nameservices.programstudy;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional

public class ProgramStudyServices {

    @Autowired
    private ProgramStudyRepo programstudyRepo;

    public ProgramStudy save(ProgramStudy programstudy) {
        return programstudyRepo.save(programstudy);
    }

    public ProgramStudy findOne(int id) {
        return programstudyRepo.findById(id).get();
    }

    public Iterable<ProgramStudy> findAll() {
        return programstudyRepo.findAll();
    }

    public void removeOne(int id) {
        programstudyRepo.deleteById(id);
        ;
    }

}
