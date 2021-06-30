package com.game.entity;


public class DtoPlayerCreateOrUpdate {

  private String name;
  private String title;
  private Race race;
  private Profession profession;
  private Long birthday;
  private Boolean banned;
  private Integer experience;

  public DtoPlayerCreateOrUpdate() {
  }

  public DtoPlayerCreateOrUpdate(
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
