package com.atendimentos.api.apiatendimento.domain.exception;

public class NegocioException extends RuntimeException{
    
        public NegocioException(String mensagem){
            super(mensagem);
        }
}
