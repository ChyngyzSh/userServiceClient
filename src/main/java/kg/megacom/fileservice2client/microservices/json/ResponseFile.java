package kg.megacom.fileservice2client.microservices.json;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseFile {
    private String fileName;
    private String downloadUri;
    private String fileType;
    private long size;

}
