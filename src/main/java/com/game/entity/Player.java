package com.game.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Table(name = "player")
@Entity
public class Player {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "name")
  private String name;

  @Column(name = "title")
  private String title;

  @Column(name = "race")
  @Enumerated(EnumType.STRING)
  private Race race;

  @Column(name = "profession")
  @Enumerated(EnumType.STRING)
  private Profession profession;

  @Column(name = "experience")
  private Integer experience;

  @Column(name = "level")
  private Integer level;

  @Column(name = "untilNextLevel")
  private Integer untilNextLevel;

  @Column(name = "birthday")
  private Date birthday;

  @Column(name = "banned")
  private Boolean banned;

  public Player() {

  }

  public Player(String name, String title, Race race, Profession profession, Integer experience,
      Integer level, Integer untilNextLevel, Date birthday, Boolean banned) {
    this.name = name;
    this.title = title;
    this.race = race;
    this.profession = profession;
    this.experience = experience;
    this.level = level;
    this.untilNextLevel = untilNextLevel;
    this.birthday = birthday;
    this.banned = banned;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    if (name == null || name.length() > 12 || name.startsWith(" ")){
      throw new RuntimeException("Неправильное имя");
    }
    this.name = name;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    if (title == null || title.length() > 30 || name.startsWith(" ")){
      throw new RuntimeException("Неправильный титул");
    }
    this.title = title;
  }

  public Race getRace() {
    return race;
  }

  public void setRace(Race race) {
    if (race == null){
      throw new RuntimeException("Укажите рассу");
    }
    this.race = race;
  }

  public Profession getProfession() {
    return profession;
  }

  public void setProfession(Profession profession) {
    if (profession == null){
      throw new RuntimeException("Укажите профессию");
    }
    this.profession = profession;
  }

  public Integer getExperience() {
    return experience;
  }

  public void setExperience(Integer experience) {
    if (experience > 10000000 || experience < 0){
      throw new RuntimeException("Недопустимые показатели для опыта");
    }
    this.experience = experience;
  }

  public Integer getLevel() {
    return level;
  }

  public void setLevel(Integer level) {
    this.level = level;
  }

  public Integer getUntilNextLevel() {
    return untilNextLevel;
  }

  public void setUntilNextLevel(Integer untilNextLevel) {
    this.untilNextLevel = untilNextLevel;
  }

  public Date getBirthday() {
    return birthday;
  }

  public void setBirthday(Date birthday) {
    if (birthday.getTime() > 32533477200000L || birthday.getTime() < 946674000000L){
      throw new RuntimeException("Неправильная дата");
    }
    this.birthday = birthday;
  }

  public Boolean getBanned() {
    return banned;
  }

  public void setBanned(Boolean banned) {
    this.banned = banned;
  }

  @Override
  public String toString() {
    return "Player{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", title='" + title + '\'' +
        ", race=" + race +
        ", profession=" + profession +
        ", experience=" + experience +
        ", level=" + level +
        ", untilNextLevel=" + untilNextLevel +
        ", birthday=" + birthday +
        ", banned=" + banned +
        '}';
  }

  @Override
  public boolean equals(Object o) {
      if (this == o) {
          return true;
      }
      if (o == null || getClass() != o.getClass()) {
          return false;
      }
    Player player = (Player) o;
    return Objects.equals(id, player.id) && Objects.equals(name, player.name)
        && Objects.equals(title, player.title)
        && race == player.race
        && profession == player.profession
        && Objects.equals(experience, player.experience)
        && Objects.equals(level, player.level)
        && Objects.equals(untilNextLevel, player.untilNextLevel)
        && Objects.equals(birthday, player.birthday)
        && Objects.equals(banned, player.banned);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, title, race,
        profession, experience, level, untilNextLevel, birthday, banned);
  }
}
