package com.test.jorgenieto.adea.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.jorgenieto.adea.entity.Usuario;
import com.test.jorgenieto.adea.service.UserService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("users")
public class UserController {
    Logger log = LoggerFactory.getLogger(UserController.class);
    @Autowired UserService userService;

    @GetMapping("list")
    public ResponseEntity<?> getUsers() {
        HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setContentType(MediaType.APPLICATION_JSON);
		responseHeaders.setContentLanguage(Locale.of("ES"));
        HttpStatus estatus = HttpStatus.INTERNAL_SERVER_ERROR;
        try {
            List<Usuario> users = userService.getAllUsers();
            estatus = HttpStatus.OK;
            log.info("Usuarios listados");
            return new ResponseEntity<>(users, responseHeaders, estatus);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(null, responseHeaders, estatus);
        }
        
    }

    @GetMapping("list/status")
    public ResponseEntity<?> getUsersByStatus(@RequestParam char status) {
        HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setContentType(MediaType.APPLICATION_JSON);
		responseHeaders.setContentLanguage(Locale.of("ES"));
        HttpStatus estatus = HttpStatus.INTERNAL_SERVER_ERROR;
        try {
            List<Usuario> users = userService.getUsersByStatus(status);
            estatus = HttpStatus.OK;
            log.info("Usuarios listados");
            return new ResponseEntity<>(users, responseHeaders, estatus);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(null, responseHeaders, estatus);
        }
        
    }

    @GetMapping("list/filters")
    public ResponseEntity<?> getUsersByFilters(@RequestParam String filterName, @RequestParam String filter1, @RequestParam String filter2) {
        HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setContentType(MediaType.APPLICATION_JSON);
		responseHeaders.setContentLanguage(Locale.of("ES"));
        HttpStatus estatus = HttpStatus.INTERNAL_SERVER_ERROR;
        try {
            List<Usuario> users = new ArrayList<>();
            if(!filterName.isEmpty() && (filter1.isEmpty() || filter2.isEmpty())){
                users = userService.getUsersByName("%".concat(filterName).concat("%"));
            } else if (filterName.isEmpty() && (!filter1.isEmpty() && !filter2.isEmpty())) {
                users = userService.getUsersByUpDate(filter1.concat("T00:00:00"), filter2.concat("T23:59:59"));
            } else if(!filterName.isEmpty() && (!filter1.isEmpty() && !filter2.isEmpty())){
                users = userService.getUsersByFilters("%".concat(filterName).concat("%"), filter1.concat("T00:00:00"), filter2.concat("T23:59:59"));
            } else {
                estatus = HttpStatus.BAD_REQUEST;
                log.info("Filtros no claros");
                return new ResponseEntity<>(null, responseHeaders, estatus);
            }
            estatus = HttpStatus.OK;
            log.info("Usuarios listados");
            return new ResponseEntity<>(users, responseHeaders, estatus);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(null, responseHeaders, estatus);
        }
        
    }

    @PostMapping("create")
    public ResponseEntity<?> createUser(@RequestBody Usuario user) {
        HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setContentType(MediaType.APPLICATION_JSON);
		responseHeaders.setContentLanguage(Locale.of("ES"));
        HttpStatus estatus = HttpStatus.INTERNAL_SERVER_ERROR;
        try {
            System.out.println(user.toString());
            Usuario userCreated = userService.createUser(user);
            if(userCreated != null){
                estatus = HttpStatus.OK;
                log.info("Se crea el usuario");
                return new ResponseEntity<>(userCreated, responseHeaders, estatus);
            } else {
                estatus = HttpStatus.BAD_REQUEST;
                log.info("No se puede crear el usuario");
                return new ResponseEntity<>(null, responseHeaders, estatus);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(null, responseHeaders, estatus);
        }
    }

    @PostMapping("update")
    public ResponseEntity<?> updateUser(@RequestBody Usuario user) {
        HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setContentType(MediaType.APPLICATION_JSON);
		responseHeaders.setContentLanguage(Locale.of("ES"));
        HttpStatus estatus = HttpStatus.INTERNAL_SERVER_ERROR;
        try {
            Usuario userSearch = userService.getUserById(user.getLogin());
            if(userSearch != null){
                Usuario userCreated = userService.updateUser(user);
                if(userCreated != null){
                    estatus = HttpStatus.OK;
                    log.info("Se actualiza el usuario");
                    return new ResponseEntity<>(userCreated, responseHeaders, estatus);
                } else {
                    estatus = HttpStatus.BAD_REQUEST;
                    log.info("No se puede actualizar el usuario");
                    return new ResponseEntity<>(null, responseHeaders, estatus);
                }
            } else {
                estatus = HttpStatus.NOT_FOUND;
                log.info("No se encuentra el usuario");
                return new ResponseEntity<>(null, responseHeaders, estatus);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(null, responseHeaders, estatus);
        }
    }

    @GetMapping("delete")
    public ResponseEntity<?> deleteUser(@RequestParam String request) {
        HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setContentType(MediaType.APPLICATION_JSON);
		responseHeaders.setContentLanguage(Locale.of("ES"));
        HttpStatus estatus = HttpStatus.INTERNAL_SERVER_ERROR;
        try {
            Usuario userSearch = userService.getUserById(request);
            if(userSearch != null){
                userService.deleteUser(request);
                estatus = HttpStatus.OK;
                log.info("Se actualiza el usuario");
                return new ResponseEntity<>(null, responseHeaders, estatus);
            } else {
                estatus = HttpStatus.NOT_FOUND;
                log.info("No se encuentra el usuario");
                return new ResponseEntity<>(null, responseHeaders, estatus);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(null, responseHeaders, estatus);
        }
    }

    @GetMapping("search")
    public ResponseEntity<?> login(@RequestParam String request) {
        HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setContentType(MediaType.APPLICATION_JSON);
		responseHeaders.setContentLanguage(Locale.of("ES"));
        HttpStatus estatus = HttpStatus.INTERNAL_SERVER_ERROR;
        try {
            Usuario user = userService.getUserById(request);
            if(user != null){
                estatus = HttpStatus.OK;
                log.info("Usuario encontrado");
                return new ResponseEntity<>(user, responseHeaders, estatus);
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
