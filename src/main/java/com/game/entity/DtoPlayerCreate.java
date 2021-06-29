package com.game.entity;

import java.util.Date;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;



public class DtoPlayerCreate {

  @NotBlank(message = "invalid name")
  @Size(max = 12)
  private String name;

  @Size(max = 30)
  @NotBlank
  private String title;


  @NotNull
  private Race race;


  @NotNull
  private Profession profession;

  @Min(946674000000L)
  @Max(32533477200000L)
  private Long birthday;

  private Boolean banned;

  @NotNull
  @Max(10000000)
  @Min(0)
  private Integer experience;

  public DtoPlayerCreate() {
  }

  public DtoPlayerCreate(
      String name, String title, Race race, Profession profession,
      Long birthday, Boolean banned, Integer experience) {
    this.name = name;
    this.title = title;
    this.race = race;
    this.profession = profession;
    this.birthday = birthday;
    this.banned = banned;
    this.experience = experience;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Race getRace() {
    return race;
  }

  public void setRace(Race race) {
    this.race = race;
  }

  public Profession getProfession() {
    return profession;
  }

  public void setProfession(Profession profession) {
    this.profession = profession;
  }

  public Long getBirthday() {
    return birthday;
  }

  public void setBirthday(Long birthday) {
    this.birthday = birthday;
  }

  public Boolean getBanned() {
    return banned;
  }

  public void setBanned(Boolean banned) {
    this.banned = banned;
  }

  public Integer getExperience() {
    return experience;
  }

  public void setExperience(Integer experience) {
    this.experience = experience;
  }

  @Override
  public String toString() {
    return "DtoPlayerCreate{" +
        "name='" + name + '\'' +
        ", title='" + title + '\'' +
        ", race=" + race +
        ", profession=" + profession +
        ", birthday=" + birthday +
        ", banned=" + banned +
        ", experience=" + experience +
        '}';
  }



}
