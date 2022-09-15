package kg.megacom.fileservice2client.controllers;

import kg.megacom.fileservice2client.models.enums.UserStatus;
import kg.megacom.fileservice2client.responses.ResponseByStatus;
import kg.megacom.fileservice2client.services.StatusHistoryService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
@RestController
@RequestMapping(value = "/api/v1/status")
public class StatusHistoryController {

    private final StatusHistoryService statusHistoryService;

    public StatusHistoryController(StatusHistoryService statusHistoryService) {
        this.statusHistoryService = statusHistoryService;
    }
//5-пункт
    @GetMapping("/find/all")
    public ResponseByStatus getAllByStatus(@RequestParam UserStatus status, @RequestParam(required = false)
        @DateTimeFormat(pattern="yyyy-MM-dd") Date date){

        return statusHistoryService.getAllUsersByStatus(status, date);
    }
}
