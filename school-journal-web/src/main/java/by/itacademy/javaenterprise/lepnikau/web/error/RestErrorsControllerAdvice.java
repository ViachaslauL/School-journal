package by.itacademy.javaenterprise.lepnikau.web.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.transaction.NotSupportedException;
import java.util.Map;

@RestControllerAdvice
public class RestErrorsControllerAdvice {

    private final static Logger LOG = LoggerFactory.getLogger(RestErrorsControllerAdvice.class);

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public Object errors(MethodArgumentTypeMismatchException e) {
        LOG.error("something went wrong ", e);
        return Map.of("error", "Error message: " + e.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotSupportedException.class)
    public Object errors(NotSupportedException e) {
        LOG.error("no entity found", e);
        return Map.of("error", "Error message " + e.getMessage());
    }

}
