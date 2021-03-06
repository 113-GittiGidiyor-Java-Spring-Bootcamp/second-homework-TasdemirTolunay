package dev.patika.schoolmanagement.service;

import dev.patika.schoolmanagement.model.*;
import dev.patika.schoolmanagement.repository.AddressRepository;
import dev.patika.schoolmanagement.repository.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//This class contains methods of transaction on the instructor.
@Service
public class InstructorService {

    InstructorRepository instructorRepository;

    AddressRepository addressRepository;

    @Autowired
    public InstructorService(InstructorRepository instructorRepository, AddressRepository addressRepository) {

        this.instructorRepository = instructorRepository;
        this.addressRepository = addressRepository;

    }

    public List<Instructor> instructorList(){

        return instructorRepository.findAll();

    }

    public Instructor findInstructorById(int id){

        return (Instructor) instructorRepository.findById(id);

    }

    public Instructor savePermanentInstructor(PermanentInstructor instructor){

        return (Instructor) instructorRepository.save(instructor);

    }

    public Instructor saveVisitingInstructor(VisitingResearcher instructor){

        return (Instructor) instructorRepository.save(instructor);

    }

    public void updateInstructor(Instructor instructor, int id){

        instructorRepository.update(instructor, id);

    }

    public void deleteInstructor(Instructor instructor){

        instructorRepository.delete(instructor);

    }

    public void deleteInstructorById(int id){

        instructorRepository.deleteById(id);

    }

    public List<Course> coursesOfInstructor(int id){

        return findInstructorById(id).getCourseList();

    }

    public Address addressOfInstructor(int id){

        return findInstructorById(id).getAddress();

    }

    @Transactional
    public void setAddressOfInstructor(int instructorId, int addressId){

        Address findAddress = (Address) addressRepository.findById(addressId);
        Instructor findInstructor = findInstructorById(instructorId);
        findInstructor.setAddress(findAddress);
        instructorRepository.update(findInstructor, instructorId);

    }

}
