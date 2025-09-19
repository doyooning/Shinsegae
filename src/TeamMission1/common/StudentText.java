package TeamMission1.common;

import lombok.Getter;

@Getter
public enum StudentText {
    FILENAME_S("student.dat"),
    PATHNAME_S("C:/Temp/" + FILENAME_S.getText()),
    FILENAME_O("orderByAvg.dat"),
    PATHNAME_O("./" +  FILENAME_O.getText()),

    /*
    * StudentInput
    * */
    INPUT_TITLE_PRINT("[학생 성적 입력 프로그램]"),
    PRINT_USAGE("""
            - 종료하려면 이름에 ^^ 를 입력하세요.
            - 점수는 0~100 사이의 정수만 허용됩니다.
            """),
    NAME("이름: "),
    KOREAN("국어: "),
    ENGLISH("영어: "),
    MATH("수학: "),
    SCIENCE("과학: "),
    DATA_SAVED("=> 저장됨: %s (총점=%d, 평균=%.1f, 학점=%s)\n"),
    INPUT_EXIT("입력을 종료합니다."),
    SAVE_COMPLETE("[완료] %d명의 정보가 %s 에 저장되었습니다.\n"),

    /*
    * StudentOutput
    * */
    OUTPUT_TITLE_PRINT("[평균 오름차순 성적표]"),
    PRINT_SCORE("   점수: %s\n   총점: %d, 평균: %.1f, 학점: %s\n"),

    /*
     * SortedStudent
     * */
    SORTED_TITLE_PRINT("[정렬 및 저장: 평균 오름차순]"),
    LOAD_STUDENT_AMOUNT("불러온 학생 수: "),
    SORT_RULES("정렬 규칙: 평균 ASC, 평균 동률이면 이름 사전순 ASC"),

    SAVE_PREVIEW_TITLE("저장 대상(미리보기 상위 10명): "),
    SAVE_PREVIEW_PRINT("- %s (평균 %.1f)\n"),

    SAVE_FILE_PATH("결과 파일: " + PATHNAME_O.getText()),
    SAVE_SORT_COMPLETE("[완료] 정렬된 결과를 파일로 저장했습니다.\n");

    private final String text;

    StudentText(String text) {
        this.text = text;
    }

}
