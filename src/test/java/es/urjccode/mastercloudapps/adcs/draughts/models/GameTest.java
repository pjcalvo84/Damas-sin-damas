package es.urjccode.mastercloudapps.adcs.draughts.models;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import es.urjccode.mastercloudapps.adcs.draughts.BaseTest;

public class GameTest extends BaseTest{

    @Mock
    private Board board;

    @Mock
    private Turn turn;
    @InjectMocks
    private Game game;

    @Test()
    public void testGivenGameWhenMoveWithOuterCoordinateThenOutCoordinateError(){

        final Coordinate origin = new Coordinate(4, 7);
        final Coordinate target = new Coordinate(3, 8);
        Error error = null;
        assertThat(error, is(nullValue()));
        error = game.move(origin, target);
        assertThat(Error.OUT_COORDINATE, is(error));
    }

    @Test
    public void testGivenGameWhenMoveEmptySquaerThenEmptySquareError(){
        final Coordinate origin = new Coordinate(4, 3);
        final Coordinate target = new Coordinate(3, 4);
        when(board.isEmpty(origin)).thenReturn(true);
        assertThat(Error.EMPTY_ORIGIN, is(game.move(origin, target)));
    }

    @Test
    public void testGivenGameWhenMoveOppositePieceThenError(){
        final Coordinate origin = new Coordinate(3, 6);
        final Coordinate target = new Coordinate(2, 7);
        when(board.getColor(origin)).thenReturn(Color.BLACK);
        when(turn.isColor(Color.BLACK)).thenReturn(false);
        Error error = null;
        assertThat(error, is(nullValue()));
        error = game.move(origin, target);
        assertThat(Error.OPPOSITE_PIECE, is(error));
    }

    @Test
    public void testGivenGameWhenNotDiagonalMovementThenError(){
        final Coordinate origin = new Coordinate(5, 2);
        final Coordinate target = new Coordinate(4, 2);
        when(board.getColor(origin)).thenReturn(Color.WHITE);
        when(turn.isColor(Color.WHITE)).thenReturn(true);
        assertThat(Error.NOT_DIAGONAL, is(this.game.move(origin, target)));
    }

    @Test
    public void testGivenGameWhenMoveWithNotAdvancedThenError(){
        final Coordinate origin = new Coordinate(3, 4);
        final Coordinate target = new Coordinate(4, 5);
        when(board.getColor(origin)).thenReturn(Color.WHITE);
        when(board.getPiece(origin)).thenReturn(new Piece(Color.WHITE));
        when(turn.isColor(Color.WHITE)).thenReturn(true);
        Error error = null;
        assertThat(error, is(nullValue()));
        error = game.move(origin, target);
        assertThat(Error.NOT_ADVANCED, is(error));
    }

    @Test
    public void testGivenGameWhenMoveBadDistanceThenError(){
        final Coordinate origin = new Coordinate(5, 0);
        final Coordinate target = new Coordinate(2, 3);
        when(board.getColor(origin)).thenReturn(Color.WHITE);
        when(turn.isColor(Color.WHITE)).thenReturn(true);
        when(board.getPiece(origin)).thenReturn(new Piece(Color.WHITE));

        assertThat(Error.BAD_DISTANCE, is(this.game.move(origin, new Coordinate(2, 3))));
    }

    @Test
    public void testGivenGameWhenNotEmptyTargeThenError(){
        final Coordinate origin = new Coordinate(4, 7);
        final Coordinate target = new Coordinate(3, 6);
        when(board.getColor(origin)).thenReturn(Color.WHITE);
        when(board.isEmpty(origin)).thenReturn(false);
        when(board.getPiece(origin)).thenReturn(new Piece(Color.WHITE));
        when(turn.isColor(Color.WHITE)).thenReturn(true);
        Error error = null;
        assertThat(error, is(nullValue()));
        error = game.move(origin, target);
        assertThat(Error.NOT_EMPTY_TARGET, is(error));
    }

    @Test
    public void testGivenGameWhenCorrectMovementThenOk(){
        final Coordinate origin = new Coordinate(5, 0);
        final Coordinate target = new Coordinate(4, 1);
        final Coordinate originSecondMove = new Coordinate(2, 3);
        final Coordinate targetSecondMove = new Coordinate(3, 4);
        when(board.getColor(origin)).thenReturn(Color.WHITE).thenReturn(null).thenReturn(Color.BLACK);
        when(board.getColor(target)).thenReturn(Color.WHITE).thenReturn(Color.BLACK);
        when(board.isEmpty(origin)).thenReturn(false);
        when(board.isEmpty(target)).thenReturn(true);
        when(board.getPiece(origin)).thenReturn(new Piece(Color.WHITE));
        when(turn.isColor(Color.WHITE)).thenReturn(true);

        assertThat(this.game.move(origin, target), is(nullValue()));
        assertThat(this.game.getColor(origin), is(nullValue()));
        assertThat(Color.WHITE, is(this.game.getColor(target)));

        when(board.getColor(originSecondMove)).thenReturn(Color.BLACK).thenReturn(null).thenReturn(Color.WHITE);
        when(board.getColor(targetSecondMove)).thenReturn(Color.BLACK);
        when(board.isEmpty(originSecondMove)).thenReturn(false);
        when(board.isEmpty(targetSecondMove)).thenReturn(true);
        when(board.getPiece(originSecondMove)).thenReturn(new Piece(Color.BLACK));
        when(turn.isColor(Color.BLACK)).thenReturn(true);
        assertThat(this.game.move(originSecondMove, targetSecondMove), is(nullValue()));
        assertThat(this.game.getColor(originSecondMove), is(nullValue()));
        assertThat(Color.BLACK, is(this.game.getColor(targetSecondMove)));
    }

    @Test
    public void testGivenGameWhenMovementThenEatPiece(){
        final Coordinate origin = new Coordinate(3, 0);
        final Coordinate target = new Coordinate(5, 2);
        final Coordinate between = new Coordinate(4, 1);

        when(board.getColor(origin)).thenReturn(Color.BLACK).thenReturn(null);
        when(board.getColor(between)).thenReturn(null);
        when(board.getColor(target)).thenReturn(Color.BLACK);
        when(board.isEmpty(origin)).thenReturn(false);
        when(turn.isColor(Color.BLACK)).thenReturn(true);
        when(board.getPiece(origin)).thenReturn(new Piece(Color.BLACK));
        when(board.isEmpty(target)).thenReturn(true);
        when(board.getPiece(between)).thenReturn(new Piece(Color.WHITE));

        Error error = null;
        assertNull(error);
        error = game.move(origin, target);
        assertThat(error, is(nullValue()));
        assertThat(game.getColor(origin), is(nullValue()));
        assertThat(game.getColor(between), is(nullValue()));
        assertThat(Color.BLACK, is(game.getColor(target)));
    }

    @Test
    public void testGivenGameWhenEatEmptyPieceThenError(){
        final Coordinate origin = new Coordinate(5, 4);
        final Coordinate target = new Coordinate(3, 2);
        final Coordinate between = new Coordinate(4, 3);

        when(board.getColor(origin)).thenReturn(Color.WHITE).thenReturn(null);
        when(turn.isColor(Color.WHITE)).thenReturn(true);
        when(board.getPiece(origin)).thenReturn(new Piece(Color.WHITE));
        when(board.isEmpty(target)).thenReturn(true);

        when(board.getColor(between)).thenReturn(null);
        assertEquals(Error.EATING_EMPTY, this.game.move(new Coordinate(5, 4), new Coordinate(3, 2)));
    }

}