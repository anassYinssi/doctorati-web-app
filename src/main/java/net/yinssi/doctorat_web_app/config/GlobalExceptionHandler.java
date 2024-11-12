package net.yinssi.doctorat_web_app.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.SQLIntegrityConstraintViolationException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<String> handleSQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException ex, RedirectAttributes redirectAttributes) {
        // Log the exception if needed
        // logger.error("Integrity constraint violation: ", ex);

        // Return an informative message
        String message = "Unable to delete this record because it is referenced by other records.";
        redirectAttributes.addFlashAttribute("errorMessage", message);

        return new ResponseEntity<>(message, HttpStatus.CONFLICT); // You can change the status code as needed
    }
}

