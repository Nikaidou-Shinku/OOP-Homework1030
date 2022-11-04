package oop.homework1030;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

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

  private static DateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日");
  private static UndergraduateStudent parseStudent(String input) throws ParseException {
    String[] args = input.split("_");

    if (args.length != 7) {
      throw new IllegalArgumentException("Invalid input");
    }

    Date birthday = formatter.parse(args[3]);

    return new UndergraduateStudent(
      args[0],
      args[1],
      args[2].equals("男") ? Gender.Male : Gender.Female,
      birthday,
      args[4],
      args[5],
      args[6]
    );
  }

  public static UndergraduateStudent readOne(Scanner scan) throws ParseException {
    return parseStudent(scan.nextLine());
  }

  public static ArrayList<UndergraduateStudent> readFromFile(String path) throws IOException {
    List<String> data = Files.readAllLines(Paths.get(path));
    ArrayList<UndergraduateStudent> res = new ArrayList<UndergraduateStudent>();

    data.forEach(line -> {
      try {
        res.add(parseStudent(line));
      } catch (Exception e) {
        System.err.println("[Error] Invalid undergraduate student info!");
      }
    });

    return res;
  }

  public String toString() {
    return new StringBuilder()
      .append(getStudentNumber())             .append('_')
      .append(getStudentName())               .append('_')
      .append(getGender())                    .append('_')
      .append(formatter.format(getBirthday())).append('_')
      .append(getAcademy())                   .append('_')
      .append(getMajor())                     .append('_')
      .append(tutor)
      .toString();
  }
}
