package ve.ucla.informationsystems.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ve.ucla.informationsystems.dto.ApiError;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handlerGenericException(Exception exception, HttpServletRequest request){

        ApiError apiError = new ApiError();
        apiError.setApiMessage(exception.getLocalizedMessage());
        apiError.setUrl(request.getRequestURL().toString());
        apiError.setMethod(request.getMethod());
        apiError.setMessage("Error interno en el servidor");

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiError);

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handlerMethodArgumentNotValidException(MethodArgumentNotValidException exception,
                                                                    HttpServletRequest request){

        ApiError apiError = new ApiError();
        apiError.setApiMessage(exception.getLocalizedMessage());
        apiError.setUrl(request.getRequestURL().toString());
        apiError.setMethod(request.getMethod());
        apiError.setMessage("Error en la petición enviada");
        apiError.setTimestamp(LocalDateTime.now());
        List<String> errors = exception.getAllErrors().stream().map(each -> each.getDefaultMessage()).collect(Collectors.toList());
        apiError.setMessages(errors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);

    }
}
