package wanted.preonboarding.recruit.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class JobPosting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private int id;

    @Column(nullable = false)
    private int companyId;

    @Column(nullable = false)
    private String position; // 채용 포지션

    @Column(nullable = false)
    private int compensation; // 채용 보상금

    @Column(nullable = false)
    private String description; // 채용 내용

    @Column(nullable = false)
    private String skill; // 사용 기술
}
