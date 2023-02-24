package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

@Repository

public class StudentRepository {
    private HashMap<String, Student> studentMap;
    private HashMap<String, Teacher> teacherMap;
    private HashMap<String, List<String>> pairMap;
    public StudentRepository(){
        this.studentMap = new HashMap<>();
        this.teacherMap = new HashMap<>();
        this.pairMap = new HashMap<>();
    }
    public void saveStudent(Student student) {
        studentMap.put(student.getName(), student);
    }

    public void saveTeacher(Teacher teacher) {
        teacherMap.put(teacher.getName(), teacher);
    }

    public void saveStudentTeacherPair(String student, String teacher) {
        if(studentMap.containsKey(student) && teacherMap.containsKey(teacher)){
            studentMap.put(student, studentMap.get(student));
            teacherMap.put(teacher, teacherMap.get(teacher));
            List<String> students = new ArrayList<>();
            if(pairMap.containsKey(teacher)) students = pairMap.get(teacher);
            students.add(student);
            pairMap.put(teacher, students);
        }
    }

    public Student findStudent(String name) {
        return studentMap.get(name);
    }

    public Teacher findTeacher(String name) {
        return teacherMap.get(name);
    }

    public List<String> getStudentsByTeacher(String teacher) {
        List<String> studentList = new ArrayList<>();
        if(pairMap.containsKey(teacher)) studentList = pairMap.get(teacher);
        return studentList;
    }

    public List<String> getAllStudents() {
        return new ArrayList<>(studentMap.keySet());

    }

    public void deleteTeacher(String teacher) {
        List<String> students = new ArrayList<>();
        if(pairMap.containsKey(teacher)){
            students = pairMap.get(teacher);
            for(String studentC : students){
                if(studentMap.containsKey(studentC)) studentMap.remove(studentC);
            }
            pairMap.remove(teacher);
        }
        if(teacherMap.containsKey(teacher)) teacherMap.remove(teacher);
    }

    public void deleteAllTeachers() {
        HashSet<String>  studentSet = new HashSet<>();
        for(String teacher : pairMap.keySet()){
            for (String student : pairMap.get(teacher)){
                studentSet.add(student);
            }
        }
        for(String student : studentSet){
            if(studentMap.containsKey(student)) studentMap.remove(student);
        }
    }

}
