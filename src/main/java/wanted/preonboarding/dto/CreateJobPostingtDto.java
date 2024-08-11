package wanted.preonboarding.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateJobPostingtDto {

    private int companyId;
    private String country; // 나라
    private String region; // 지역
    private String position; // 채용포지션
    private int compensation; // 보상
    private String description;
    private String skill;

}
