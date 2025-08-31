package TeamMission1;

import TeamMission1.common.ErrorCode;
import TeamMission1.common.StudentText;

import java.io.*;
import java.util.*;

public class StudentOutput {
    // static
    static File pathName = new File(StudentText.PATHNAME_S.getText());
    static HashMap<String, Student> studentInfo = new HashMap<>();
    static List<Student> datas = new ArrayList<>();
    static Set<String> keys; // 임시
    static String[] names; // 정렬된 이름 순서 보관용

    public static void main(String[] args) {
        // student.dat에서 읽어온 다음 화면에 출력
        // 입) student.dat // 출) 콘솔(성적순 목록)

         loadObjectFromFile();

         rearrangeData();

         printInfo();

    }
    // student.dat 역직렬화 로드
    public static void loadObjectFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(pathName))) {
            while (true) {
                Object obj = ois.readObject();
                studentInfo = (HashMap<String, Student>) obj;
            }

        } catch (EOFException e) {
            System.out.println(StudentText.OUTPUT_TITLE_PRINT.getText());

        } catch (FileNotFoundException e) {
            System.out.println(ErrorCode.FILE_NOT_FOUND.getMsg());

        } catch (IOException | ClassNotFoundException e) {
            System.out.println(ErrorCode.FILE_IO_ERROR.getMsg());
            e.printStackTrace();
        }
    }

    // 평균 기준 정렬
    public static void rearrangeData() {
        // keys : 이름 키값들
        // names : 정렬된 이름 순서 보관
        keys = studentInfo.keySet();
        names = new String[keys.size()];
        keys.stream().sorted().forEach(key -> {
            Arrays.fill(names, key);
        });

        // 평균 오름차순으로 datas 학생 목록 재구성
        datas.addAll(studentInfo.values());
        datas = datas.stream().sorted(Comparator.comparing(Student::getAverage))
                .toList();
    }

    // 정렬 결과 출력
    public static void printInfo() {
        for (int i = 0; i < datas.size(); i++) {
            System.out.printf("%d) %s\n", i+1, datas.get(i).getName());
            System.out.println(datas.get(i).toString());
        }
    }
}
