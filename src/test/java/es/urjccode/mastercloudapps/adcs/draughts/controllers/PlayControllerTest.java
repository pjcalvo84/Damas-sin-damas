package es.urjccode.mastercloudapps.adcs.draughts.controllers;


import es.urjccode.mastercloudapps.adcs.draughts.BaseTest;
import es.urjccode.mastercloudapps.adcs.draughts.models.Game;
import es.urjccode.mastercloudapps.adcs.draughts.models.Coordinate;
import es.urjccode.mastercloudapps.adcs.draughts.models.Piece;
import es.urjccode.mastercloudapps.adcs.draughts.models.Color;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;

public class PlayControllerTest extends BaseTest {

    @Mock
    Game game;

    @InjectMocks
    PlayController playController;

    @Test
    public void givenPlayControllerWhenMovementRequiereCorrectThenNotError() {
        Coordinate origin = new Coordinate(5, 2);
        Coordinate target = new Coordinate(4, 3);
        when(game.getPiece(target)).thenReturn(new Piece(Color.WHITE));

        assertThat(playController.move(origin, target), is(nullValue()));
        assertThat(playController.getPiece(origin), is(nullValue()));

        Piece pieceTarget = playController.getPiece(target);
        assertThat(pieceTarget, is(nullValue()));
        assertThat(pieceTarget.getColor(), is(Color.WHITE));
    }



}