package oop.homework1030;

import java.util.Date;

public class GraduateStudent extends StudentInfo {
  private String supervisor;

  public GraduateStudent(
    String initStudentNumber,
    String initStudentName,
    Gender initGender,
    Date initBirthday,
    String initAcademy,
    String initMajor,
    String initSupervisor
  ) {
    super(
      initStudentNumber,
      initStudentName,
      initGender,
      initBirthday,
      initAcademy,
      initMajor
    );

    this.supervisor = initSupervisor;
  }
}
