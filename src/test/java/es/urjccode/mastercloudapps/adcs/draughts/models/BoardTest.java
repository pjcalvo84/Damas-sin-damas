package es.urjccode.mastercloudapps.adcs.draughts.models;


import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class BoardTest {

    private Board board;

    public BoardTest() {
        this.board = new Board();
    }

    @Test
    public void testGivenNewBoardThenGoodLocations() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < Board.DIMENSION; j++) {
                Coordinate coordinate = new Coordinate(i,j);
                Color color = board.getColor(coordinate);
                if (coordinate.isBlack()){
                    assertThat(Color.BLACK,is(color));
                } else {
                    assertThat(color, is(nullValue()));
                }
            }
        }
        for (int i = 5; i < Board.DIMENSION; i++) {
            for (int j = 0; j < Board.DIMENSION; j++) {
                Coordinate coordinate = new Coordinate(i,j);
                Color color = board.getColor(coordinate);
                if (coordinate.isBlack()){
                    assertThat(Color.WHITE,is(color));
                } else {
                    assertThat(color, is(nullValue()));
                }
            }
        }
    }
}