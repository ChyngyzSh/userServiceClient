package kg.megacom.fileservice2client.dao;

import kg.megacom.fileservice2client.models.User;
import kg.megacom.fileservice2client.models.enums.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

}
