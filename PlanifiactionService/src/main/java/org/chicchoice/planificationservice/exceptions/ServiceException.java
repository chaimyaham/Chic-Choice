package org.chicchoice.planificationservice.exceptions;

public class ServiceException extends RuntimeException {
    private String service;

    public ServiceException(String service, String message, Throwable cause) {
        super(String.format("%s service %s", service, message), cause);
        this.service = service;
    }

    public String getService() {
        return service;
    }
}