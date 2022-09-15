package kg.megacom.fileservice2client.services;

import kg.megacom.fileservice2client.models.enums.UserStatus;
import kg.megacom.fileservice2client.responses.ResponseByStatus;

import java.util.Date;

public interface StatusHistoryService {
    ResponseByStatus getAllUsersByStatus(UserStatus status, Date date);
}
