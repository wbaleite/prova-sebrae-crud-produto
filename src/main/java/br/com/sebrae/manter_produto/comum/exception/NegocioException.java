package br.com.sebrae.manter_produto.comum.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NegocioException extends RuntimeException {

    public NegocioException(String msg) {
        super(msg);
    }
}
