package com.dyma.tennis.service;

import com.dyma.tennis.Player;
import com.dyma.tennis.data.PlayerEntityList;
import com.dyma.tennis.data.PlayerRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DataRetrievalFailureException;

import java.util.List;
import java.util.Optional;

public class PlayerServiceTest {
    @Mock
    private PlayerRepository playerRepository;

    private PlayerService playerService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        playerService = new PlayerService(playerRepository);
    }

    @Test
    public void shouldReturnPlayersRanking() {
        // Given
        Mockito.when(playerRepository.findAll()).thenReturn(PlayerEntityList.ALL);
        // When
        List<Player> allPlayers = playerService.getAllPlayers();
        // Then
        Assertions.assertThat(allPlayers)
                .extracting("lastName")
                .containsExactly("Nadal", "Djokovic", "Federer", "Murray");
    }

    @Test
    public void shouldFailToReturnPlayersRanking_WhenDataAccessExceptionOccurs() {
        // Given
        Mockito.when(playerRepository.findAll()).thenThrow(new DataRetrievalFailureException("Data access error"));
        //When / Then
        Exception exception = org.junit.jupiter.api.Assertions.assertThrows(PlayerDataRetrivalException.class, () -> {
            playerService.getAllPlayers();
        });
        Assertions.assertThat(exception.getMessage()).isEqualTo("Could not be retrieve player data");
    }

    @Test
    public void shouldRetrievePlayer() {
        //Given
        String playerToRetrieve = "Nadal";
        Mockito.when(playerRepository.findOneByLastNameIgnoreCase(playerToRetrieve)).thenReturn(Optional.of(PlayerEntityList.RAFAEL_NADAL));
        //When
        Player retrievedPlayer = playerService.getByLastName(playerToRetrieve);
        //Then
        Assertions.assertThat(retrievedPlayer.lastName()).isEqualTo("Nadal");
        Assertions.assertThat(retrievedPlayer.firstName()).isEqualTo("Rafael");
        Assertions.assertThat(retrievedPlayer.rank().position()).isEqualTo(1);
    }

    @Test
    public void shouldFailToRetrievePlayer_WhenPlayerDoesNotExist() {
        //Given
        String unknownPlayer = "Doe";
        Mockito.when(playerRepository.findOneByLastNameIgnoreCase(unknownPlayer)).thenReturn(Optional.empty());
        //When / Then
        Exception exception = org.junit.jupiter.api.Assertions.assertThrows(PlayerNotFoundException.class, () -> {
            playerService.getByLastName(unknownPlayer);
        });
        Assertions.assertThat(exception.getMessage()).isEqualTo("Player with last name Doe not found");
    }
}
