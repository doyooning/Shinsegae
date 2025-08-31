package TeamMission1.common;

import lombok.Getter;

@Getter
public enum ErrorCode {

    FILE_NOT_FOUND("파일이 존재하지 않습니다. 새 파일을 생성합니다."),

    ALREADY_EXISTS_NAME("[오류] 이미 존재하는 이름입니다. 다른 이름을 입력하세요."),
    FILE_IO_ERROR("[오류] 파일 입출력 과정에서 오류가 발생하였습니다."),

    INVALID_SCORE_NUMBER("[오류] 숫자(정수)만 입력하여 주십시오."),
    INVALID_SCORE_RANGE("[오류] 0부터 100 사이의 정수를 입력하여 주십시오.");

    private final String msg;

    ErrorCode(String msg) {
        this.msg = msg;
    }

}
