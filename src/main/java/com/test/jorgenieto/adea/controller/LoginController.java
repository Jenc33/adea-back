package com.test.jorgenieto.adea.controller;

import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.jorgenieto.adea.entity.Usuario;
import com.test.jorgenieto.adea.model.UsuarioRequest;
import com.test.jorgenieto.adea.service.UserService;

@RestController
@RequestMapping("service")
public class LoginController {
    Logger log = LoggerFactory.getLogger(UserController.class);
    @Autowired UserService userService;

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody UsuarioRequest request) {
        HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setContentType(MediaType.APPLICATION_JSON);
		responseHeaders.setContentLanguage(Locale.of("ES"));
        HttpStatus estatus = HttpStatus.INTERNAL_SERVER_ERROR;
        try {
            Usuario user = userService.getUserById(request.getLogin());
            if(user != null){
                if( user.getPassword().equals(request.getPassword())){
                    Date date = new Date();
                    if(user.getFechaVigencia().getTime() > date.getTime()){
                        estatus = HttpStatus.OK;
                        log.info("Usuario loggin");
                        return new ResponseEntity<>(user, responseHeaders, estatus);
                    } else {
                        estatus = HttpStatus.FORBIDDEN;
                        log.info("Usuario no vigente");
                        return new ResponseEntity<>(null, responseHeaders, estatus);
                    }
                } else {
                    estatus = HttpStatus.BAD_REQUEST;
                    log.info("La Contraseña no coincide");
                    return new ResponseEntity<>(null, responseHeaders, estatus);
                }
            } else {
                estatus = HttpStatus.NOT_FOUND;
                log.info("Usuario No Encontrado");
                return new ResponseEntity<>(null, responseHeaders, estatus);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(null, responseHeaders, estatus);
        }
    }    
}
