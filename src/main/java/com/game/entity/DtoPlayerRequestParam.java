package com.game.entity;

import com.game.controller.PlayerOrder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class DtoPlayerRequestParam {

  private String name;
  private String title;
  private Race race;
  private Profession profession;
  private Long after;
  private Long before;
  private Boolean banned;
  private Integer minExperience;
  private Integer maxExperience;
  private Integer minLevel;
  private Integer maxLevel;
  private PlayerOrder order;
  private Integer pageNumber;
  private Integer pageSize;

  public DtoPlayerRequestParam() {
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

  public Long getAfter() {
    return after;
  }

  public void setAfter(Long after) {
    this.after = after;
  }

  public Long getBefore() {
    return before;
  }

  public void setBefore(Long before) {
    this.before = before;
  }

  public Boolean getBanned() {
    return banned;
  }

  public void setBanned(Boolean banned) {
    this.banned = banned;
  }

  public Integer getMinExperience() {
    return minExperience;
  }

  public void setMinExperience(Integer minExperience) {
    this.minExperience = minExperience;
  }

  public Integer getMaxExperience() {
    return maxExperience;
  }

  public void setMaxExperience(Integer maxExperience) {
    this.maxExperience = maxExperience;
  }

  public Integer getMinLevel() {
    return minLevel;
  }

  public void setMinLevel(Integer minLevel) {
    this.minLevel = minLevel;
  }

  public Integer getMaxLevel() {
    return maxLevel;
  }

  public void setMaxLevel(Integer maxLevel) {
    this.maxLevel = maxLevel;
  }

  public PlayerOrder getOrder() {
    return order;
  }

  public void setOrder(PlayerOrder order) {
    this.order = order;
  }

  public Integer getPageNumber() {
    return pageNumber;
  }

  public void setPageNumber(Integer pageNumber) {
    this.pageNumber = pageNumber;
  }

  public Integer getPageSize() {
    return pageSize;
  }

  public void setPageSize(Integer pageSize) {
    this.pageSize = pageSize;
  }

  @Override
  public String toString() {
    return "DTOPlayerRequestParam{" +
        "name='" + name + '\'' +
        ", title='" + title + '\'' +
        ", race=" + race +
        ", profession=" + profession +
        ", after=" + after +
        ", before=" + before +
        ", banned=" + banned +
        ", minExperience=" + minExperience +
        ", maxExperience=" + maxExperience +
        ", minLevel=" + minLevel +
        ", maxLevel=" + maxLevel +
        ", order=" + order +
        ", pageNumber=" + pageNumber +
        ", pageSize=" + pageSize +
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
    DtoPlayerRequestParam that = (DtoPlayerRequestParam) o;
    return Objects.equals(name, that.name) && Objects.equals(title, that.title)
        && race == that.race
        && profession == that.profession
        && Objects.equals(after, that.after)
        && Objects.equals(before, that.before)
        && Objects.equals(banned, that.banned)
        && Objects.equals(minExperience, that.minExperience)
        && Objects.equals(maxExperience, that.maxExperience)
        && Objects.equals(minLevel, that.minLevel)
        && Objects.equals(maxLevel, that.maxLevel)
        && order == that.order
        && Objects.equals(pageNumber, that.pageNumber)
        && Objects.equals(pageSize, that.pageSize);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, title, race, profession,
        after, before, banned, minExperience, maxExperience,
        minLevel, maxLevel, order, pageNumber, pageSize);
  }

  public String getSQLQuery() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("SELECT * FROM player")
        .append(getSQLSubQueryForDate())
        .append(getSQLSubQueryForExperience())
        .append(getSQLSubQueryForLevel());

    if (name != null) {
      stringBuilder.append(" AND name LIKE '%").append(name).append("%'");
    }

    if (title != null) {
      stringBuilder.append(" AND title LIKE '%").append(title).append("%'");
    }

    if (race != null) {
      stringBuilder.append(" AND race = '").append(race.toString()).append("'");
    }

    if (profession != null) {
      stringBuilder.append(" AND profession = '").append(profession.toString()).append("'");
    }

    if (banned != null) {
      String bannedToBit = "0";
      if (banned) {
        bannedToBit = "1";
      }
      stringBuilder.append(" AND banned = '").append(bannedToBit).append("'");
    }

    if (order != null) {
      stringBuilder.append(" order by ").append(order.getFieldName());
    }

    //stringBuilder.append(" limit " + (pageSize * pageNumber) + "," + pageSize);
    stringBuilder.append(";");

    return stringBuilder.toString();
  }

  private String getSQLSubQueryForLevel() {
    if (minLevel == null) {
      minLevel = 0;
    }
    if (maxLevel == null) {
      maxLevel = 100000;
    }
    return String
        .format(" AND level between '%d' and '%d'", minLevel, maxLevel);
  }

  private String getSQLSubQueryForExperience() {
    if (minExperience == null) {
      minExperience = 0;
    }
    if (maxExperience == null) {
      maxExperience = Integer.MAX_VALUE;
    }
    return String
        .format(" AND experience between '%d' and '%d'", minExperience, maxExperience);
  }

  private String getSQLSubQueryForDate() {
    Date before = null;
    Date after = null;
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    try {
      if (this.before == null) {
        before = dateFormat.parse("3000-01-01");
      } else {
        before = new Date(this.before);
      }

      if (this.after == null) {
        after = dateFormat.parse("2000-01-01");
      } else {
        after = new Date(this.after);
      }

    } catch (ParseException e) {
      System.out.println("Неправильный парсинг даты в getSQLSubQueryForDate()");
    }

    return String
        .format(" WHERE birthday >= '%s' AND birthday <= '%s'",
            dateFormat.format(after), dateFormat.format(before));
  }
}
