package TeamMission1;

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
        return "   점수: " + record + "\n"
                + "   총점: " + total + ", 평균: " + average + ", 학점: " + grade + "\n";
    }
}
