package daycare.controller.error;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.NoSuchElementException;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException.MethodNotAllowed;
import org.springframework.web.client.HttpServerErrorException.InternalServerError;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
@Slf4j
public class GlobalControllerErrorHandler {
  private enum LogStatus {
   STACK_TRACE, MESSAGE_ONLY 
  }

  @Data
  private class ExceptionMessage {
    private String message; 
    private String statusReason;
    private int statusCode;
    private String timestamp;
    private String uri;
    
    } // exceptionMessage
  
  @ExceptionHandler(NoSuchElementException.class)
  @ResponseStatus(code = HttpStatus.NOT_FOUND)
  public ExceptionMessage handleNoSuchElementException(
      NoSuchElementException ex, WebRequest webRequest) {
    return buildExceptionMessage(ex, HttpStatus.NOT_FOUND, webRequest, 
        LogStatus.MESSAGE_ONLY);
  }
    
    @ExceptionHandler(UnsupportedOperationException.class)
    @ResponseStatus(code = HttpStatus.METHOD_NOT_ALLOWED)
    public ExceptionMessage handleUnsupportedOperationException(
        UnsupportedOperationException ex, WebRequest webRequest) {
      return buildExceptionMessage(ex, HttpStatus.METHOD_NOT_ALLOWED, webRequest, 
          LogStatus.MESSAGE_ONLY);
    } // unsupported operation exception
    
    
  
  
  @ExceptionHandler(Exception.class)
  @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
  public ExceptionMessage handlException(Exception ex, WebRequest webRequest) {
    return buildExceptionMessage(ex, HttpStatus.INTERNAL_SERVER_ERROR, webRequest, LogStatus.STACK_TRACE);
  }

  private ExceptionMessage buildExceptionMessage(Exception ex, HttpStatus status,
      WebRequest webRequest, LogStatus LogStatus) {
    String message = ex.toString();
    String statusReason = status.getReasonPhrase();
    int statusCode = status.value();
    String uri = null;
    String timestamp = 
        ZonedDateTime.now().format(DateTimeFormatter.RFC_1123_DATE_TIME);
    
    if(webRequest instanceof ServletWebRequest swr) {
      uri = swr.getRequest().getRequestURI();
    }
    if(LogStatus == LogStatus.MESSAGE_ONLY) {
      log.error("Exception: {}", ex.toString());
    }
    else {
      log.error("Exception: " , ex);
    }
    ExceptionMessage excMsg = new ExceptionMessage(); 
    
    excMsg.setMessage(message);
    excMsg.setStatusCode(statusCode);
    excMsg.setStatusReason(statusReason);
    excMsg.setTimestamp(timestamp);
    excMsg.setUri(uri);
    
    return excMsg;
  } 
  
} // main 