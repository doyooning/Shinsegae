package TeamMission1.common;

public enum StudentText {

    PRINT_USAGE("""
            [학생 성적 입력 프로그램]
            - 종료하려면 이름에 ^^ 를 입력하세요.
            - 점수는 0~100 사이의 정수만 허용됩니다.
            """),
    NAME("이름: "),
    KOREAN("국어: "),
    ENGLISH("영어: "),
    MATH("수학: "),
    SCIENCE("과학: "),
    DATA_SAVED("저장됨: "),
    INPUT_EXIT("입력을 종료합니다.");

    private String msg;

    StudentText(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
