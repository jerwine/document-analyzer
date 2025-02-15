package ie.demo.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_teams", catalog = "DOCUMENTS_SCHEMA")
public class UserTeam {

    @EmbeddedId
    private UserTeamId id;

    @Column(name = "assigned_at", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime assignedAt = LocalDateTime.now();

    @Embeddable
    static class UserTeamId {
        @Column(name = "user_id")
        private Long userId;

        @Column(name = "team_id")
        private Long teamId;
    }
}