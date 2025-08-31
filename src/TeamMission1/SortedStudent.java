package TeamMission1;

import TeamMission1.common.ErrorCode;
import TeamMission1.common.StudentText;

import java.io.*;
import java.util.Comparator;
import java.util.HashMap;
import java.util.TreeSet;

public class SortedStudent {
    // static
    static File pathName = new File(StudentText.PATHNAME_O.getText());
    static HashMap<String, Student> studentInfo = new HashMap<>();
    static TreeSet<Student> treeSet;

    public static void main(String[] args) {
        // student.dat에서 Student 객체 읽음, 평균 기준 오름차순 정렬
        // 결과를 orderByAvg.dat에 저장
        // 정렬시 TreeSet, Comparator 이용

        // TreeSet<Student>, Comparator<Student>
        // 평균 같으면 이름 등 보조키로 정렬, 정렬된 컬렉션을 파일로 직렬화 저장

         loadObjectFromFile();

         createTreeSet();

         printResult();

         outputObject();

    }

    public static void loadObjectFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(pathName))) {
            while (true) {
                Object obj = ois.readObject();
                studentInfo = (HashMap<String, Student>) obj;
            }

        } catch (EOFException e) {
            System.out.println(StudentText.SORTED_TITLE_PRINT.getText());
            System.out.println(StudentText.LOAD_STUDENT_AMOUNT.getText() + studentInfo.size());

        } catch (FileNotFoundException e) {
            System.out.println(ErrorCode.FILE_NOT_FOUND.getMsg());

        } catch (IOException | ClassNotFoundException e) {
            System.out.println(ErrorCode.FILE_IO_ERROR.getMsg());
            e.printStackTrace();
        }
    }

    // 트리셋 구성 및 정렬
    public static void createTreeSet() {
        treeSet = new TreeSet<>(Comparator.comparing(Student::getAverage)
        .thenComparing(Student::getName));
        treeSet.addAll(studentInfo.values());
        System.out.println(StudentText.SORT_RULES.getText());
    }


    // 정렬 결과 출력
    public static void printResult() {
        System.out.println(StudentText.SAVE_PREVIEW_TITLE.getText());
        int sCount = 0;
        for (Student s : treeSet) { // 오름차순 시작부터 최대 10명까지 출력
            if (sCount == 10) {
                break;
            }
            System.out.printf(StudentText.SAVE_PREVIEW_PRINT.getText(), s.getName(), s.getAverage());
            sCount++;
        }
    }

    // 저장하기
    public static void outputObject() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(pathName))) {
            oos.writeObject(treeSet);

        } catch (IOException e) {
            System.out.println(ErrorCode.FILE_IO_ERROR.getMsg());
            e.printStackTrace();
        }
        System.out.println(StudentText.SAVE_SORT_COMPLETE.getText());
    }
}
