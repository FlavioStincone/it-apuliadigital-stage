package it.exprovia.userManager.model.entity;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

//Non Utilizzo @Data per evitare problemi con l'ereditarietà
@MappedSuperclass
@Getter
@Setter
public abstract class User
{

  private String name;
  private String email;
  private String role;
  private String pwd;

  public User()
  {

  }

  public User(String name, String email, String pwd, String role)
  {
    this.name = name;
    this.email = email;
    this.pwd = pwd;
  }

}