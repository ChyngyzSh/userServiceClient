package kg.megacom.fileservice2client.services.impl;

import kg.megacom.fileservice2client.dao.StatusHistoryRepo;
import kg.megacom.fileservice2client.mappers.StatusHistoryMapper;
import kg.megacom.fileservice2client.models.StatusHistory;
import kg.megacom.fileservice2client.models.enums.UserStatus;
import kg.megacom.fileservice2client.responses.ResponseByStatus;
import kg.megacom.fileservice2client.services.StatusHistoryService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class StatusHistoryServiceImpl implements StatusHistoryService {

    private final StatusHistoryMapper statusHistoryMapper;
    private final StatusHistoryRepo statusHistoryRepo;

    public StatusHistoryServiceImpl(StatusHistoryRepo statusHistoryRepo) {
        this.statusHistoryMapper = StatusHistoryMapper.INSTANCE;
        this.statusHistoryRepo = statusHistoryRepo;
    }

    //5
    @Override
    public ResponseByStatus getAllUsersByStatus(UserStatus status, Date date) {
        List<StatusHistory> statusHistories = null;
        //так как есть 2 параметра, есть 4 варианта событий, в каждом нахожу подходящие под условия списки
        if(date==null && status!=null) {
            statusHistories = getListsByStatus(status);
        }else if(date==null && status==UserStatus.EMPTY) {
            statusHistories = statusHistoryRepo.findAll();
        }else if(date!=null && status==UserStatus.EMPTY) {
            statusHistories = statusHistoryRepo.findAllByDate(date);
        }else if(date!=null && status!=null){
            System.out.println(date);
            statusHistories = getListsByStatusAndDate(status, date);
        }
        ResponseByStatus response = new ResponseByStatus();
        response.setStatusList(statusHistoryMapper.toDtos(statusHistories));
        response.setRequestDate(date);
        return response;
    }

    //поиск и выборка последних записей в базе ТОЛЬКО по статусу
    private List<StatusHistory> getListsByStatus(UserStatus status){
        List<StatusHistory>statusHistories = statusHistoryRepo.findAllByStatus(status);
        for(int i=0; i< statusHistories.size(); i++){
            int userId= Math.toIntExact(statusHistories.get(i).getUser().getId());

            for(int j=1; j< statusHistories.size(); j++){
                if(i!= j && userId == statusHistories.get(j).getUser().getId()){
                    statusHistories.remove(statusHistories.get(j));
                }
            }
        }
        return statusHistories;
    }
    //поиск и выборка последних записей в базе  по статусу и где диапазон времени подходит под наше время
    private List<StatusHistory>getListsByStatusAndDate(UserStatus status, Date date){
        List<StatusHistory>statusHistories = statusHistoryRepo.findAllByStatusDate(status);
        for (int i=0; i<statusHistories.size(); i++){
            StatusHistory item = statusHistories.get(i);
            if(item.getStartDate().after(date)){
                statusHistories.remove(i);
            }else if(item.getStartDate().before(date) && item.getEndDate().before(date)){
                statusHistories.remove(i);
            }
        }
        return statusHistories;
    }
    /*
5
Ответ сервера - список пользователей со статусами и URI картинки, а также уникальный ID (timestamp) запроса.
Примечание: Если в запросе есть параметры, то сервер должен фильтровать по ним свой ответ. Если в запросе есть уникальный ID (timestamp) запроса (полученный ранее), то сервер должен вернуть только пользователей, у которых изменились статусы после (по времени)этого уникального ID (timestamp).

 */
}
