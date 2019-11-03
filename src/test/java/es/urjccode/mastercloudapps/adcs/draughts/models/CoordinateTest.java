package es.urjccode.mastercloudapps.adcs.draughts.models;


import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
public class CoordinateTest {

    @Test
    public void testGivenTwoCoordinatesWhenBettweenDiagonalThenOk() {
        assertThat(new Coordinate(1, 1), is(new Coordinate(2, 2).betweenDiagonal(new Coordinate(0, 0))));
        assertThat(new Coordinate(3, 1), is(new Coordinate(2, 2).betweenDiagonal(new Coordinate(4, 0))));
        assertThat(new Coordinate(3, 3), is(new Coordinate(2, 2).betweenDiagonal(new Coordinate(4, 4))));
        assertThat(new Coordinate(1, 3), is(new Coordinate(2, 2).betweenDiagonal(new Coordinate(0, 4))));
    }

    @Test
    public void testGivenDiagonalBetwennTwoCoordinate(){
        assertThat(3, is(new Coordinate(3, 4).diagonalDistance(new Coordinate(0, 7))));
    }

}