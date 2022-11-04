package oop.homework1030;

import java.util.Date;

import com.alibaba.fastjson2.annotation.JSONField;

enum Gender {
  Male,
  Female,
}

public class StudentInfo {
  private String studentNumber;
  private String studentName;
  private Gender gender;
  @JSONField(format = "yyyy年MM月dd日")
  private Date birthday;
  private String academy;
  private String major;

  public String getStudentNumber() {
    return studentNumber;
  }
  public void setStudentNumber(String studentNumber) {
    this.studentNumber = studentNumber;
  }

  public String getStudentName() {
    return studentName;
  }
  public void setStudentName(String studentName) {
    this.studentName = studentName;
  }

  public String getGender() {
    return gender == Gender.Male ? "男" : "女";
  }
  public void setGender(String gender) {
    this.gender = gender.equals("男") ? Gender.Male : Gender.Female;
  }

  public Date getBirthday() {
    return birthday;
  }
  public void setBirthday(Date birthday) {
    this.birthday = birthday;
  }

  public String getAcademy() {
    return academy;
  }
  public void setAcademy(String academy) {
    this.academy = academy;
  }

  public String getMajor() {
    return major;
  }
  public void setMajor(String major) {
    this.major = major;
  }

  public StudentInfo() { }

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
