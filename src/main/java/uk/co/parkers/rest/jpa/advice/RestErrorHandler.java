package uk.co.parkers.rest.jpa.advice;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import uk.co.parkers.validation.ValidationError;
import uk.co.parkers.validation.ValidationError.FieldErrorMessage;


@ControllerAdvice
public class RestErrorHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		List<FieldErrorMessage> fieldErrors = ex.getBindingResult().getFieldErrors().stream().map(f -> {
			FieldErrorMessage fem = new FieldErrorMessage(f.getField(), f.getRejectedValue());
			fem.messages.add(f.getDefaultMessage());
			return fem;
		}).collect(Collectors.toMap(FieldErrorMessage::getField, e -> e, (a, b) -> {
			a.messages.addAll(b.messages);
			return a;
		})).values().stream().collect(Collectors.toList());

		List<String> globalErrors = new ArrayList<String>();
		for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
			globalErrors.add(error.getObjectName() + ": " + error.getDefaultMessage());
		}

		ValidationError validationError = new ValidationError(HttpStatus.BAD_REQUEST, "Validation Error", fieldErrors,
				globalErrors);
		return handleExceptionInternal(ex, validationError, headers, validationError.getStatus(), request);
	}

}
