package ve.ucla.informationsystems.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
public class ApiError implements Serializable {

    private String apiMessage;

    private String message;

    private String url;

    private String method;

    private LocalDateTime timestamp;

    private List<String> messages;
}
