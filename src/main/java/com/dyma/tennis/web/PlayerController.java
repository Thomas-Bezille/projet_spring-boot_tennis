package com.dyma.tennis.web;

import com.dyma.tennis.Player;
import com.dyma.tennis.PlayerList;
import com.dyma.tennis.service.PlayerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@Tag(name = "Tennis players API")
@RestController
@RequestMapping("/players")
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @Operation(summary = "Finds players", description = "Find players")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Players list",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Player.class)))})
    })
    @GetMapping
    public List<Player> list() {
        return playerService.getAllPlayers();
    }


    @Operation(summary = "Finds a player", description = "Finds a player")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Players",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Player.class))})
    })
    @GetMapping("/{lastName}")
    public Player getByLastName(@PathVariable("lastName") String lastName) {
        return PlayerList.ALL.stream()
                .filter(player -> player.lastName().equals(lastName))
                .findFirst()
                .orElseThrow();
    }


    @Operation(summary = "Create a player", description = "Create a player")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Created Players",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Player.class))})
    })
    @PostMapping
    public Player createPlayer(@RequestBody @Valid Player player) {
        return player;
    }


    @Operation(summary = "Updates a player", description = "Updates a player")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Updated Players",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Player.class))})
    })
    @PutMapping
    public Player updatePlayer(@RequestBody @Valid Player player) {
        return player;
    }


    @Operation(summary = "Deletes a player", description = "Deletes a player")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Player has been deleted")
    })
    @DeleteMapping("/{lastname}")
    public void deletePlayerByLastName(@PathVariable("lastname") String lastName) {

    }
}
