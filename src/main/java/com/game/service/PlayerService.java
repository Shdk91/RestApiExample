package com.game.service;

import com.game.entity.DtoPlayerCreateOrUpdate;
import com.game.entity.DtoPlayerRequestParam;
import com.game.entity.Player;
import com.game.exception_handling.NoSuchPlayerException;
import com.game.repository.PlayerDAO;
import com.game.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class PlayerService {

  @Autowired
  private PlayerRepository playerRepository;
  @Autowired
  private PlayerDAO playerDAO;


  public List<Player> getPlayers(DtoPlayerRequestParam param) {

    int pageSize = (param.getPageSize() == null) ? 3 : param.getPageSize();
    int pageNumber = (param.getPageNumber() == null) ? 0 : param.getPageNumber();

    List<Player> players = playerDAO.getPlayers(param);

    int indexOfBeginPage = pageNumber * pageSize;
    int indexOfEndPage = Math.min((pageNumber + 1) * pageSize, players.size());

    return players.subList(indexOfBeginPage, indexOfEndPage);
  }

  public Player getPlayer(Long id) {
      if (id < 1) {
          throw new RuntimeException();
      }

    Optional<Player> optionalPlayer = playerRepository.findById(id);

    return optionalPlayer.orElseThrow(() -> new NoSuchPlayerException
        ("Нет игрока с ID=" + id + " в базе данных!"));
  }

  public Integer getPlayersCount(DtoPlayerRequestParam param) {
    return playerDAO.getPlayers(param).size();
  }

  public void deletePlayer(Long id) {
    if (id > 0 && !playerRepository.existsById(id)) {
      throw new NoSuchPlayerException("Нет игрока с ID=" + id + " в базе данных!");
    }
    playerRepository.deleteById(id);
  }

  public void createOrUpdatePlayer(Player player) {
    Integer exp = player.getExperience();
    Integer level = (int) (Math.sqrt(2500 + 200 * exp) - 50) / 100;
    Integer untilNextLevel = 50 * (level + 1) * (level + 2) - exp;

    player.setLevel(level);
    player.setUntilNextLevel(untilNextLevel);

    playerRepository.saveAndFlush(player);
  }
}

