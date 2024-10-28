package com.test.jorgenieto.adea.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Usuario {

   @Id
   private String login;
   @Column(nullable = false)
   private String password;
   @Column(nullable = false)
   private String nombre;
   @Column(nullable = false)
   private float cliente;
   private String email;
   @Column(nullable = false)
   private Date fechaalta;
   private Date fechabaja;
   @Column(nullable = false)
   private char status = 'A';
   @Column(nullable = false)
   private float intentos = 0;
   private Date fecharevocado;
   private Date fechaVigencia;
   private int noAcceso;
   private String apellidoPaterno;
   private String apellidoMaterno;
   private int area;
   @Column(nullable = false)
   private Date fechamodificacion;
   
   public String getLogin() {
      return login;
   }
   public void setLogin(String login) {
      this.login = login;
   }
   public String getPassword() {
      return password;
   }
   public void setPassword(String password) {
      this.password = password;
   }
   public String getNombre() {
      return nombre;
   }
   public void setNombre(String nombre) {
      this.nombre = nombre;
   }
   public float getCliente() {
      return cliente;
   }
   public void setCliente(float cliente) {
      this.cliente = cliente;
   }
   public String getEmail() {
      return email;
   }
   public void setEmail(String email) {
      this.email = email;
   }

   public char getStatus() {
      return status;
   }
   public void setStatus(char status) {
      this.status = status;
   }
   public float getIntentos() {
      return intentos;
   }
   public void setIntentos(float intentos) {
      this.intentos = intentos;
   }
   public Date getFecharevocado() {
      return fecharevocado;
   }
   public void setFecharevocado(Date fecharevocado) {
         this.fecharevocado = fecharevocado;
   }
   public Date getFechaVigencia() {
      return fechaVigencia;
   }
   public void setFechaVigencia(Date fechaVigencia) {
      this.fechaVigencia = fechaVigencia;
   }
   public int getNoAcceso() {
      return noAcceso;
   }
   public void setNoAcceso(int noAcceso) {
      this.noAcceso = noAcceso;
   }
   public String getApellidoPaterno() {
      return apellidoPaterno;
   }
   public void setApellidoPaterno(String apellidoPaterno) {
      this.apellidoPaterno = apellidoPaterno;
   }
   public String getApellidoMaterno() {
      return apellidoMaterno;
   }
   public void setApellidoMaterno(String apellidoMaterno) {
      this.apellidoMaterno = apellidoMaterno;
   }
   public int getArea() {
      return area;
   }
   public void setArea(int area) {
      this.area = area;
   }
   public Date getFechamodificacion() {
      return fechamodificacion;
   }
   public void setFechamodificacion(Date fechaModificacion) {
      this.fechamodificacion = fechaModificacion;
   }

   public Date getFechaalta() {
      return fechaalta;
   }

   public void setFechaalta(Date fechaalta) {
      this.fechaalta = fechaalta;
   }

    public Date getFechabaja() {
        return fechabaja;
    }

    public void setFechabaja(Date fechabaja) {
        this.fechabaja = fechabaja;
    }

    @Override
   public String toString() {
      return "User [login=" + login + ", password=" + password + ", nombre=" + nombre + ", cliente=" + cliente
            + ", email=" + email + ", fechaAlta=" + fechaalta + ", fechaBaja=" + fechabaja + ", status=" + status
            + ", intentos=" + intentos + ", fechaRevocado=" + fecharevocado + ", fechaVigencia=" + fechaVigencia
            + ", noAcceso=" + noAcceso + ", apellidoPaterno=" + apellidoPaterno + ", apellidoMaterno=" + apellidoMaterno
            + ", area=" + area + ", fechaModificacion=" + fechamodificacion + "]";
   }

}