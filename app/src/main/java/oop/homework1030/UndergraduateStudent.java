package oop.homework1030;

import java.util.Date;

public class UndergraduateStudent extends StudentInfo {
  private String tutor;

  public UndergraduateStudent(
    String initStudentNumber,
    String initStudentName,
    Gender initGender,
    Date initBirthday,
    String initAcademy,
    String initMajor,
    String initTutor
  ) {
    super(
      initStudentNumber,
      initStudentName,
      initGender,
      initBirthday,
      initAcademy,
      initMajor
    );

    this.tutor = initTutor;
  }
}
