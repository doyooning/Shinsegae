package TeamMission1.common;

import TeamMission1.Student;
import TeamMission1.exception.StudentException;

import java.util.HashMap;

public class ValidCheck {
    // 여기서 메서드에 throw new 에러
    private static final String CHECK_NUMBER = "^[0-9]*$";

    public static void isScoreValid(String number) {
        // 점수 입력값 검사
        if (!number.matches(CHECK_NUMBER)) {
            throw new StudentException(ErrorCode.INVALID_SCORE_NUMBER);
        } else if ((Integer.parseInt(number) < 0) || (Integer.parseInt(number) > 100)) {
            throw new StudentException(ErrorCode.INVALID_SCORE_RANGE);
        }
    }

    public static void isNameValid(HashMap<String, Student> map, String name) {
        // 이름 중복 검사
        if (map.containsKey(name)) {
            throw new StudentException(ErrorCode.ALREADY_EXISTS);
        }
    }


}
