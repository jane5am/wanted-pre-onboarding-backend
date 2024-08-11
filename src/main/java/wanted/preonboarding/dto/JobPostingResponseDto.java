package wanted.preonboarding.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobPostingResponseDto {
    private int id;
    private int companyId;
    private String country;
    private String region;
    private String position;
    private int compensation;
    private String skill;
}
