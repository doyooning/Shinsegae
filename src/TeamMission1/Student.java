package TeamMission1;

import TeamMission1.common.StudentText;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private List<Integer> record;
    private int total;
    private double average;
    private String grade;
    // toString() 구현 필요

    @Override
    public String toString() {

        return String.format(StudentText.PRINT_SCORE.getText(), record, total, average, grade);
    }
}
