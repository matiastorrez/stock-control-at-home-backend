package com.stockcontrolathome.authentication.exception;

import com.stockcontrolathome.authentication.exception.dto.DetailsError;
import io.jsonwebtoken.JwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Date;
import java.util.HashMap;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	private static final String ERROR  = "error";

	private ResponseEntity<HashMap<String, DetailsError>> buildErrorResponse(Exception exception, WebRequest webRequest, HttpStatus status, String defaultMessage) {
		logger.info("Mensaje de la excepcion: " + exception.getMessage());

		HashMap<String, DetailsError> dataError = new HashMap<>();

		DetailsError detailsError = new DetailsError(new Date(), defaultMessage,
				webRequest.getDescription(false), status.value());

		dataError.put(ERROR, detailsError);

		return new ResponseEntity<>(dataError, status);
	}


	@ExceptionHandler(ResendConfirmRegistrationTokenException.class)
	public ResponseEntity<HashMap<String, DetailsError>> resendConfirmRegistrationTokenExceptionHandler(ResendConfirmRegistrationTokenException exception, WebRequest webRequest) {
		logger.info("estoy en ResendConfirmRegistrationTokenException");
		return buildErrorResponse(exception, webRequest, HttpStatus.NOT_FOUND, exception.getMessage());
	}

	@ExceptionHandler(NonUserWithThisEmailException.class)
	public ResponseEntity<HashMap<String, DetailsError>> nonUserWithThisEmailExceptionHandler(NonUserWithThisEmailException exception, WebRequest webRequest) {
		logger.info("estoy en NonUserWithThisEmailException");
		return buildErrorResponse(exception, webRequest, HttpStatus.NOT_FOUND, exception.getMessage());
	}

	@ExceptionHandler(ConfirmRegistrationTokenExpiratedException.class)
	public ResponseEntity<HashMap<String, DetailsError>> confirmRegistrationTokenExpiratedExceptionHandler(ConfirmRegistrationTokenExpiratedException exception, WebRequest webRequest) {
		logger.info("estoy en ConfirmRegistrationTokenExpiratedException");
		return buildErrorResponse(exception, webRequest, HttpStatus.GONE, exception.getMessage());
	}


	@ExceptionHandler(PasswordRecoveryTokenNotFoundException.class)
	public ResponseEntity<HashMap<String, DetailsError>> passwordRecoveryTokenNotFoundExceptionHandler(PasswordRecoveryTokenNotFoundException exception, WebRequest webRequest) {
		logger.info("estoy en PasswordRecoveryTokenNotFoundException");
		return buildErrorResponse(exception, webRequest, HttpStatus.NOT_FOUND, exception.getMessage());
	}

	@ExceptionHandler(ConfirmRegistrationTokenNotFoundException.class)
	public ResponseEntity<HashMap<String, DetailsError>> confirmRegistrationTokenNotFoundExceptionHandler(ConfirmRegistrationTokenNotFoundException exception, WebRequest webRequest) {
		logger.info("estoy en ConfirmRegistrationTokenNotFoundException");
		return buildErrorResponse(exception, webRequest, HttpStatus.NOT_FOUND, exception.getMessage());
	}

	@ExceptionHandler(NotificationFailureException.class)
	public ResponseEntity<HashMap<String, DetailsError>> notificationFailureExceptionHandler(NotificationFailureException exception, WebRequest webRequest) {
		logger.info("estoy en NotificationFailureException");
		return buildErrorResponse(exception, webRequest, HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
	}


	@ExceptionHandler(NonExistentRoleException.class)
	public ResponseEntity<HashMap<String, DetailsError>> nonExistentRoleExceptionHandler(NonExistentRoleException exception, WebRequest webRequest) {
		logger.info("estoy en NonExistentRoleException");
		return buildErrorResponse(exception, webRequest, HttpStatus.NOT_FOUND, exception.getMessage());
	}

	@ExceptionHandler(ExistingEmailException.class)
	public ResponseEntity<HashMap<String, DetailsError>> existingEmailExceptionHandler(ExistingEmailException exception, WebRequest webRequest) {
		logger.info("estoy en ExistingEmailException");
		return buildErrorResponse(exception, webRequest, HttpStatus.CONFLICT, exception.getMessage());
	}

	@ExceptionHandler(NeedToConfirmException.class)
	public ResponseEntity<HashMap<String, DetailsError>> needToConfirmExceptionHandler(NeedToConfirmException exception, WebRequest webRequest) {
		logger.info("estoy en NeedToConfirmException");
		return buildErrorResponse(exception, webRequest, HttpStatus.CONFLICT, exception.getMessage());
	}

	@ExceptionHandler(NeedToConfirmBeforeLoginException.class)
	public ResponseEntity<HashMap<String, DetailsError>> needToConfirmBeforeLoginExceptionHandler(NeedToConfirmBeforeLoginException exception, WebRequest webRequest) {
		logger.info("estoy en NeedToConfirmBeforeLoginException");
		return buildErrorResponse(exception, webRequest, HttpStatus.FORBIDDEN, exception.getMessage());
	}



	@ExceptionHandler(JwtException.class)
	public ResponseEntity<HashMap<String, DetailsError>> jwtExceptionHandler(JwtException exception, WebRequest webRequest) {
		logger.info("estoy en JwtException");
		return buildErrorResponse(exception, webRequest, HttpStatus.UNAUTHORIZED, exception.getMessage());
	}



	//se lanza cuando un usuario autenticado intenta realizar una operación para la cual no tiene los permisos adecuados
	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<HashMap<String, DetailsError>> accessDeniedExceptionHandler(AccessDeniedException exception, WebRequest webRequest) {
		logger.info("estoy en AccessDeniedException");
		return buildErrorResponse(exception, webRequest, HttpStatus.FORBIDDEN, "Acceso denegado");
	}

	//se lanza en situaciones donde hay una discrepancia entre el tipo esperado de un argumento de método y el valor proporcionado en la solicitud.
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<HashMap<String, DetailsError>> methodArgumentTypeMismatchExceptionHandler(MethodArgumentTypeMismatchException  exception, WebRequest webRequest) {

		logger.info("estoy en MethodArgumentTypeMismatchException");
		return buildErrorResponse(exception, webRequest, HttpStatus.NOT_FOUND, "Recurso no encontrado");

	}

	//se lanza en entornos de persistencia de datos como Spring Data JPA cuando ocurre una violación de integridad de datos. Esta excepción puede tener varias causas, pero comúnmente se lanza cuando se intenta realizar una operación que viola la integridad de los datos en la base de datos.
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<HashMap<String, DetailsError>> dataIntegrityViolationException(DataIntegrityViolationException  exception, WebRequest webRequest) {

		logger.info("estoy en DataIntegrityViolationException");
		return buildErrorResponse(exception, webRequest, HttpStatus.BAD_REQUEST, "No se pudo realizar la accion");
	}


	//se lanza cuando el controlador no puede manejar la solicitud HTTP porque el método de la solicitud no coincide con los métodos permitidos para ese controlador o endpoint específico.
	//Por ejemplo, si tienes un controlador que solo permite solicitudes GET pero recibes una solicitud POST para ese endpoint, se lanzará esta excepción.
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<HashMap<String, DetailsError>> httpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException  exception, WebRequest webRequest) {

		logger.info("estoy en HttpRequestMethodNotSupportedException");
		return buildErrorResponse(exception, webRequest, HttpStatus.NOT_FOUND, "Recurso no encontrado");

	}

	//Si el objeto que intentas deserializar tiene campos obligatorios y el cuerpo del mensaje no proporciona esos campos.
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<HashMap<String, DetailsError>> httpMessageNotReadableException(HttpMessageNotReadableException  exception, WebRequest webRequest) {

		logger.info("estoy en HttpMessageNotReadableException");
		return buildErrorResponse(exception, webRequest, HttpStatus.BAD_REQUEST, "No se puede guardar este dato");

	}


	//tiene que ver con las validaciones
	@ExceptionHandler(MethodArgumentNotValidException.class)
	protected ResponseEntity<HashMap<String, DetailsError>> methodArgumentNotValidException(MethodArgumentNotValidException exception, WebRequest webRequest) {
		logger.info("estoy en MethodArgumentNotValidException");

		String errors = exception.getBindingResult().getFieldErrors()
				.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(". "));

		return buildErrorResponse(exception, webRequest, HttpStatus.BAD_REQUEST, errors);
	}


	@ExceptionHandler(Exception.class)
	public ResponseEntity<HashMap<String, DetailsError>> exceptionHandler(Exception exception, WebRequest webRequest) {

		logger.info("estoy en Exception");
		return buildErrorResponse(exception, webRequest, HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());

	}
}
