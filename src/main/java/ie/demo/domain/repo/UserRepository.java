package ie.demo.domain.repo;

import ie.demo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query(
        "SELECT" +
        " DISTINCT  u " +
        "FROM " +
        " User u JOIN u.documents d " +
        "WHERE " +
        " d.uploadedAt >= :startDate" )
    List<User> findUsersDocumentUpdateAfter(@Param("startDate") LocalDateTime startDate);

    @Query(
        "SELECT" +
        " DISTINCT  u " +
        "FROM " +
        " User u JOIN u.documents d " +
        "WHERE " +
        " d.uploadedAt <= :endDate" )
    List<User> findUsersWithDocumentUpdateBefore(@Param("endDate") LocalDateTime endDate);

    @Query(
        "SELECT" +
        " DISTINCT  u " +
        "FROM " +
        " User u JOIN u.documents d " +
        "WHERE " +
        " d.uploadedAt BETWEEN :startDate AND :endDate" )
    List<User> findUsersWithDocumentUpdateBetween(@Param("startDate") LocalDateTime startDate,
                                                  @Param("endDate") LocalDateTime endDate);
}
