package TeamMission1.exception;

import TeamMission1.common.ErrorCode;

public class StudentException extends RuntimeException {
    private final ErrorCode error;

    public StudentException(ErrorCode error) {
        super(error.getMsg());
        this.error = error;
    }
}
