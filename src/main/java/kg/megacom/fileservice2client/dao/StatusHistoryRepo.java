package kg.megacom.fileservice2client.dao;

import kg.megacom.fileservice2client.models.StatusHistory;
import kg.megacom.fileservice2client.models.enums.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface StatusHistoryRepo extends JpaRepository<StatusHistory, Long> {

//поиск по юзер_айди, для закрытия энд_дейт последней записи
    @Query("Select u from StatusHistory u where u.user.id = ?1 and u.endDate is null ")
    StatusHistory findByUserIdPrevStatus(Long id);
//выборка всех записей по статусу в обратном порядке, как внизу записи более новые
    @Query("Select u from StatusHistory u where u.currentStatus = ?1 order by u.id desc ")
    List<StatusHistory> findAllByStatus(UserStatus status);
//поиск всех записей по статусу без учета времени
    @Query("select u from StatusHistory u where u.currentStatus = ?1")
    List<StatusHistory> findAllByStatusDate(UserStatus status);
//поиск всех записей по дате без учета статуса
    @Query("select u from StatusHistory u where ?1 between u.startDate and u.endDate")
    List<StatusHistory> findAllByDate(Date date);
}