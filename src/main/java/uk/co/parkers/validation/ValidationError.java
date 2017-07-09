package uk.co.parkers.validation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;

public class ValidationError {

	private HttpStatus status;
	private String message;
	private List<FieldErrorMessage> errors;
	private List<String> globalErrors;

	public ValidationError(HttpStatus status, String message) {
		super();
		this.status = status;
		this.message = message;
	}

	public ValidationError(HttpStatus status, String message, List<FieldErrorMessage> errors) {
		super();
		this.status = status;
		this.message = message;
		this.errors = errors;
	}

	public ValidationError(HttpStatus status, String message, List<FieldErrorMessage> errors,
			List<String> globalErrors) {
		super();
		this.status = status;
		this.message = message;
		this.errors = errors;
		this.globalErrors = globalErrors;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<FieldErrorMessage> getErrors() {
		return errors;
	}

	public void setErrors(List<FieldErrorMessage> errors) {
		this.errors = errors;
	}

	public List<String> getGlobalErrors() {
		return globalErrors;
	}

	public void setGlobalErrors(List<String> globalErrors) {
		this.globalErrors = globalErrors;
	}

	public static class FieldErrorMessage {
		public String field;
		public Object rejectedValue;
		public List<String> messages = new ArrayList<>();

		public FieldErrorMessage(String field, Object rejectedValue) {
			super();
			this.field = field;
			this.rejectedValue = rejectedValue;
		}

		public FieldErrorMessage(String field, Object rejectedValue, List<String> messages) {
			super();
			this.field = field;
			this.rejectedValue = rejectedValue;
			this.messages = messages;
		}

		public String getField() {
			return field;
		}

		public void setField(String field) {
			this.field = field;
		}

		public Object getRejectedValue() {
			return rejectedValue;
		}

		public void setRejectedValue(Object rejectedValue) {
			this.rejectedValue = rejectedValue;
		}

		public List<String> getMessages() {
			return messages;
		}

		public void setMessages(List<String> messages) {
			this.messages = messages;
		}

	}
}
