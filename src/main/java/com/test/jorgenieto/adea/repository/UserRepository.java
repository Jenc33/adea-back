package com.test.jorgenieto.adea.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.test.jorgenieto.adea.entity.Usuario;

public interface UserRepository extends JpaRepository<Usuario, String>{
    String userByStatus = "SELECT * FROM usuario WHERE status = :filter";
    @Query(value = userByStatus, nativeQuery = true)
    List<Usuario> getUsuarioByStatus(@Param("filter") char status);

    String usersByName = "SELECT * FROM usuario WHERE nombre LIKE :filterName";
    @Query(value = usersByName, nativeQuery = true)
    List<Usuario> getUsuarioByName(@Param("filterName") String filterName);

    String usersByUpDate = "SELECT * FROM usuario WHERE fechaalta BETWEEN :filter1 AND :filter2";
    @Query(value = usersByUpDate, nativeQuery = true)
    List<Usuario> getUsuarioByUpDate(@Param("filter1") String filter1, @Param("filter2") String filter2);

    String usersByFilters = "SELECT * FROM usuario WHERE nombre LIKE :filterName AND fechaalta BETWEEN :filter1 AND :filter2";
    @Query(value = usersByFilters, nativeQuery = true)
    List<Usuario> getUsuarioByFilters(@Param("filterName") String filterName, @Param("filter1") String filter1, @Param("filter2") String filter2);
}
