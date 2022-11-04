package oop.homework1030;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

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

  private static DateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日");
  public static GraduateStudent readOne(Scanner scan) throws ParseException {
    String[] args = scan.nextLine().split("_");

    if (args.length != 7) {
      throw new IllegalArgumentException("Invalid input");
    }

    Date birthday = formatter.parse(args[3]);

    return new GraduateStudent(
      args[0],
      args[1],
      args[2].equals("男") ? Gender.Male : Gender.Female,
      birthday,
      args[4],
      args[5],
      args[6]
    );
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
