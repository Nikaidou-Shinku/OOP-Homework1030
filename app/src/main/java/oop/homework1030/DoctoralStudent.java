package oop.homework1030;

import java.util.Date;

public class DoctoralStudent extends StudentInfo {
  private String supervisor;
  private String researchFields;

  public DoctoralStudent(
    String initStudentNumber,
    String initStudentName,
    Gender initGender,
    Date initBirthday,
    String initAcademy,
    String initMajor,
    String initSupervisor,
    String initResearchFields
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
    this.researchFields = initResearchFields;
  }
}
