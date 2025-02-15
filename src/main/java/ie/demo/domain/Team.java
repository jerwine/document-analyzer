package ie.demo.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Data
@EqualsAndHashCode(exclude = {"users"})
@ToString(exclude = {"users"})
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "teams", catalog = "DOCUMENTS_SCHEMA")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "team_name", nullable = false, unique = true, length = 256)
    private String teamName;

    @ManyToMany(mappedBy = "teams")
    private Set<User> users;
}