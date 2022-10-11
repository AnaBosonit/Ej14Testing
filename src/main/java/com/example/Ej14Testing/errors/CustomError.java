package com.example.Ej14Testing.errors;

import lombok.Data;

import java.util.Date;
@Data
public class CustomError {

    Date timestamp;
    String httpCode;
    String mensaje;

    public CustomError(Date timestamp, String httpCode, String mensaje){
        super();

        this.timestamp = timestamp;
        this.httpCode = httpCode;
        this.mensaje = mensaje;
    }


    public String getHttpCode() {
        return httpCode;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getMensaje() {
        return mensaje;
    }

}
