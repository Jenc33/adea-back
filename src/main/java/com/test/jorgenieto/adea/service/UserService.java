package com.test.jorgenieto.adea.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.jorgenieto.adea.entity.Usuario;
import com.test.jorgenieto.adea.repository.UserRepository;

@Service
public class UserService {
    
    @Autowired UserRepository userRepository;

    public Usuario createUser(Usuario user) {
        return userRepository.save(user);
    }

    public List<Usuario> getAllUsers() {
        return userRepository.findAll();
    }

    public Usuario getUserById(String login) {
        return userRepository.findById(login).orElse(null);
    }

    public Usuario updateUser(Usuario updatedUser) {
        return userRepository.save(updatedUser);
    }

    public void deleteUser(String login) {
        userRepository.deleteById(login);
    }

    public List<Usuario> getUsersByStatus(char status) {
        return userRepository.getUsuarioByStatus(status);
    }

    public List<Usuario> getUsersByName(String filterName) {
        return userRepository.getUsuarioByName(filterName);
    }

    public List<Usuario> getUsersByUpDate(String filter1, String filter2) {
        return userRepository.getUsuarioByUpDate(filter1, filter2);
    }

    public List<Usuario> getUsersByFilters(String filterName, String filter1, String filter2) {
        return userRepository.getUsuarioByFilters(filterName, filter1, filter2);
    }

}
