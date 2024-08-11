package wanted.preonboarding.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateJobPostingtDto {
    private int companyId;
    private String position; // 채용포지션
    private int compensation; // 보상금
    private String description;
    private String skill;
}
