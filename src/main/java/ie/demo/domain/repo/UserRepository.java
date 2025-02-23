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
         " u " +
         "FROM " +
         " User u " +
         "WHERE " +
         " u.id NOT IN (SELECT DISTINCT d.user.id FROM Document d)" )
    List<User> findUsersWithoutDocumentUpdates();

    @Query(
        "SELECT" +
        " DISTINCT  u " +
        "FROM " +
        " User u " +
        "WHERE u.id NOT IN ( " +
        " SELECT DISTINCT d.user.id FROM Document d " +
        " WHERE d.uploadedAt > :startDate )")
    List<User> findUsersWithoutDocumentUpdatesOnOrAfter(@Param("startDate") LocalDateTime startDate);

    @Query(
        "SELECT" +
        " DISTINCT  u " +
        "FROM " +
        " User u " +
        "WHERE u.id NOT IN ( " +
        " SELECT DISTINCT d.user.id FROM Document d " +
        " WHERE d.uploadedAt < :endDate )")
    List<User> findUsersWithoutDocumentUpdatesOnOrBefore(@Param("endDate") LocalDateTime endDate);

    @Query( value =
        "SELECT" +
        " DISTINCT  u " +
        "FROM " +
        " User u " +
        "WHERE u.id NOT IN ( " +
        " SELECT DISTINCT d.user.id FROM Document d " +
        " WHERE d.uploadedAt > :startDate AND d.uploadedAt < :endDate )")
    List<User> findUsersWithoutDocumentUpdatesBetween(@Param("startDate") LocalDateTime startDate,
                                                      @Param("endDate") LocalDateTime endDate);
}
