package TeamMission1.common;

public enum StudentText {
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
    OUTPUT_TITLE_PRINT("[평균 오름차순 성적표]");



    /*
     * SortedStudent
     * */



    private String msg;

    StudentText(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
