package oop.homework1030;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;

public class GraduateStudent extends StudentInfo {
  private String supervisor;

  public String getSupervisor() {
    return supervisor;
  }
  public void setSupervisor(String supervisor) {
    this.supervisor = supervisor;
  }

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

  public static List<GraduateStudent> readFromFile(String path) throws IOException {
    byte[] data = Files.readAllBytes(Paths.get(path));
    JSONArray res = JSON.parseArray(data);
    return res.toJavaList(GraduateStudent.class);
  }

  public String toString() {
    return JSON.toJSONString(this);
  }
}
