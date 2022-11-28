package com.ibik.nameservices.nameservices.student;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibik.nameservices.nameservices.dto.AuthentificationKey;
import com.ibik.nameservices.nameservices.dto.ResponseData;
import com.ibik.nameservices.nameservices.dto.searchData;

@RestController
@RequestMapping("/api/student")

public class StudentsController {

    @Autowired
    private StudentsServices studentsServices;

    @PostMapping
    public ResponseEntity<ResponseData<Students>> postStudent(@Valid @RequestBody Students students, Errors errors) {

        ResponseData<Students> responseData = new ResponseData<>();
        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessage().add(error.getDefaultMessage());
            }
            responseData.setResult(false);
            responseData.setData(null);

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        responseData.setResult(true);
        List<Students> value = new ArrayList<>();
        value.add(studentsServices.save(students));
        responseData.setData(value);

        return ResponseEntity.ok(responseData);
    }

    @GetMapping
    public ResponseEntity<ResponseData<Students>> fetchStudents() {

        ResponseData<Students> responseData = new ResponseData<>();

        try {
            responseData.setResult(true);
            List<Students> value = (List<Students>) studentsServices.findAll();
            responseData.setData(value);

            return ResponseEntity.ok(responseData);
        } catch (Exception ex) {
            responseData.setResult(false);
            responseData.getMessage().add(ex.getMessage());

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseData);
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseData<Students>> fetchStudentsById(@PathVariable("id") int id) {
        ResponseData<Students> responseData = new ResponseData<>();
        try {
            responseData.setResult(true);
            List<Students> value = new ArrayList<>();
            value.add(studentsServices.findOne(id));
            responseData.setData(value);

            return ResponseEntity.ok(responseData);
        } catch (Exception ex) {
            responseData.setResult(false);
            responseData.getMessage().add(ex.getMessage());

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
    }

    @PutMapping
    public ResponseEntity<ResponseData<Students>> updateStudent(@Valid @RequestBody Students students, Errors errors) {

        ResponseData<Students> responseData = new ResponseData<>();
        if (students.getId() != 0) {
            if (errors.hasErrors()) {
                for (ObjectError error : errors.getAllErrors()) {
                    responseData.getMessage().add(error.getDefaultMessage());
                }
                responseData.setResult(false);
                responseData.setData(null);

                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
            }
            responseData.setResult(true);
            List<Students> value = new ArrayList<>();
            value.add(studentsServices.save(students));
            responseData.setData(value);

            return ResponseEntity.ok(responseData);
        } else {
            responseData.setResult(false);
            responseData.getMessage().add("ID is Required");
            responseData.setData(null);

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseData<Void>> deleteStudentsById(@PathVariable("id") int id) {
        ResponseData<Void> responseData = new ResponseData<>();
        try {
            studentsServices.removeOne(id);
            responseData.setResult(true);
            responseData.getMessage().add("Successfully Remove");

            return ResponseEntity.ok(responseData);
        } catch (Exception ex) {
            responseData.setResult(false);
            responseData.getMessage().add(ex.getMessage());

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
    }

    @PostMapping("/search")
    public ResponseEntity<ResponseData<Students>> getStudentByName(@RequestBody searchData searchData) {
        ResponseData<Students> responseData = new ResponseData<>();

        System.out.println(searchData.getSearchKey());

        try {
            Iterable<Students> values = studentsServices.findByName(searchData.getSearchKey());
            responseData.setResult(true);
            responseData.getMessage();
            responseData.setData(values);
            return ResponseEntity.ok(responseData);
        } catch (Exception ex) {
            responseData.getMessage().add(ex.getMessage());
            responseData.setData(null);
            responseData.setResult(false);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
    }

    @PostMapping("/auth")
    public ResponseEntity<ResponseData<Students>> getStudentAuth(@RequestBody AuthentificationKey authentificationKey) {
        ResponseData<Students> responseData = new ResponseData<>();

        System.out.print(authentificationKey.getEmail());
        System.out.print(authentificationKey.getPassword());

        try {
            Iterable<Students> values = studentsServices.findAuth(authentificationKey.getEmail(),
                    authentificationKey.getPassword());
            responseData.setResult(true);
            responseData.getMessage();
            responseData.setData(values);
            return ResponseEntity.ok(responseData);

        } catch (Exception e) {
            List<String> message = new ArrayList<>();
            message.add(e.getMessage());
            responseData.setMessage(message);
            responseData.setData(null);
            responseData.setResult(false);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
    }
}
