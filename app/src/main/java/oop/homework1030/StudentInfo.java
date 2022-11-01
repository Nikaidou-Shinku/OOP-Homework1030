package oop.homework1030;

import java.util.Date;

enum Gender {
  Male,
  Female,
}

public class StudentInfo {
  private String studentNumber;
  private String studentName;
  private Gender gender;
  private Date birthday;
  private String academy;
  private String major;

  public StudentInfo(
    String initStudentNumber,
    String initStudentName,
    Gender initGender,
    Date initBirthday,
    String initAcademy,
    String initMajor
  ) {
    this.studentNumber = initStudentNumber;
    this.studentName = initStudentName;
    this.gender = initGender;
    this.birthday = initBirthday;
    this.academy = initAcademy;
    this.major = initMajor;
  }
}
