package TeamMission1;

import TeamMission1.common.ErrorCode;
import TeamMission1.common.StudentText;
import TeamMission1.common.ValidCheck;
import TeamMission1.exception.StudentException;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentInput {
    // static
    static String fileName = StudentText.FILENAME_S.getText();
    static File pathName = new File(StudentText.PATHNAME_S.getText());
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static ObjectInputStream ois = null;
    static ObjectOutputStream oos = null;
    static HashMap<String, Student> studentInfo = new HashMap<>();
    // studentInfo = 이름:학생

    public static void main(String[] args) throws IOException {

        loadCheck();

        printUsage();

        checkKeyAndInputData();

        saveData();

    }
    public static void loadCheck() throws IOException {
        // 파일이 있나요?
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(pathName))){
            while (true) {
                Object obj = ois.readObject();
                if (obj == null) {
                    break;
                }
                System.out.println(obj);
            }

        } catch (FileNotFoundException e) { // 파일 없으면 만들기
            System.out.println(ErrorCode.FILE_NOT_FOUND.getMsg());
            oos = new ObjectOutputStream(new FileOutputStream(pathName));
            oos.writeObject(null); // null 넣고 파일 만들어야지
            oos.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static void printUsage() {
        System.out.println(StudentText.PRINT_USAGE.getText());
    }

    public static void checkKeyAndInputData() throws IOException {
        List<String> inputScores; // 최초 입력받은 성적 저장 리스트
        List<Integer> scores; // 최초 입력받은 성적 저장 리스트

        while (true) {
            inputScores = new ArrayList<>();
            scores = new ArrayList<>();

            System.out.print(StudentText.NAME.getText());
            String name = br.readLine();
            if (name.equals("^^")) {
                break;
            }

            System.out.print(StudentText.KOREAN.getText());
            String korean = br.readLine();
            inputScores.add(korean);

            System.out.print(StudentText.ENGLISH.getText());
            String english = br.readLine();
            inputScores.add(english);

            System.out.print(StudentText.MATH.getText());
            String math = br.readLine();
            inputScores.add(math);

            System.out.print(StudentText.SCIENCE.getText());
            String science = br.readLine();
            inputScores.add(science);

            // 입력값 유효성 검사
            try {
                ValidCheck.isNameValid(studentInfo, name);
                for (String score : inputScores) {
                    ValidCheck.isScoreValid(score);
                    scores.add(Integer.parseInt(score));
                }
            } catch (StudentException e) {
                checkKeyAndInputData();
            } catch (Exception e) {
                e.printStackTrace();
            }

            // 입력받은 정보 맵핑
            int scoreSum = scores.stream().mapToInt(Integer::intValue).sum();
            double scoreAvg = (double) scoreSum / scores.size();
            Student student = new Student(name, scores, scoreSum, scoreAvg, getGrade(scoreAvg));
            studentInfo.put(name, student);
            System.out.printf(StudentText.DATA_SAVED.getText(), name, scoreSum, scoreAvg, student.getGrade());
        }
        System.out.println(StudentText.INPUT_EXIT.getText());
        System.out.printf(StudentText.SAVE_COMPLETE.getText(), studentInfo.size(), fileName);
    }

    public static void saveData() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(pathName))){
            for (Map.Entry<String, Student> map : studentInfo.entrySet()) {
                oos.writeObject(map);
            }

        } catch (IOException e) {
            System.out.println(ErrorCode.FILE_IO_ERROR.getMsg());
            e.printStackTrace();
        }
    }

    // 학점을 구해주는 getGrade
    public static String getGrade(double scoreAvg) {
        if (scoreAvg >= 90.0) {
            return "A";
        } else if (scoreAvg >= 80.0) {
            return "B";
        } else if (scoreAvg >= 70.0) {
            return "C";
        } else if (scoreAvg >= 60.0) {
            return "D";
        } else {
            return "F";
        }
    }
}
