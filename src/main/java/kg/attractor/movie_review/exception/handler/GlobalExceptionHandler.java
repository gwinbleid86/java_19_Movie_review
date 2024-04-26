package kg.attractor.movie_review.exception.handler;

import jakarta.servlet.http.HttpServletRequest;
import kg.attractor.movie_review.exception.ErrorResponseBody;
import kg.attractor.movie_review.exception.MovieNotFound;
import kg.attractor.movie_review.exception.MovieNotFoundException;
import kg.attractor.movie_review.exception.SortedCriteriaException;
import kg.attractor.movie_review.service.ErrorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private final ErrorService errorService;

    @ExceptionHandler(MovieNotFoundException.class)
    @ResponseBody
    public ResponseEntity<ErrorResponseBody> noSuchElement(MovieNotFoundException exception) {
        return new ResponseEntity<>(errorService.makeResponse(exception), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SortedCriteriaException.class)
    @ResponseBody
    public ResponseEntity<ErrorResponseBody> noSuchElement(SortedCriteriaException exception) {
        return new ResponseEntity<>(errorService.makeResponse(exception), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseEntity<ErrorResponseBody> validationHandler(MethodArgumentNotValidException exception) {
        return new ResponseEntity<>(errorService.makeResponse(exception.getBindingResult()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MovieNotFound.class)
    public String notFoundHandler(Model model, HttpServletRequest request) {
        model.addAttribute("status", HttpStatus.NOT_FOUND.value());
        model.addAttribute("reason", HttpStatus.NOT_FOUND.getReasonPhrase());
        model.addAttribute("details", request);
        return "errors/error";
    }
}
