package es.urjccode.mastercloudapps.adcs.draughts.models;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class PieceTest {

    @Test
    public void testGivenPieceWhenIsAdvancedThenTrue(){
        assertThat(new Piece(Color.WHITE).isAdvanced(new Coordinate(5,0), new Coordinate(4,1)), is(true));
        assertThat(new Piece(Color.BLACK).isAdvanced(new Coordinate(2,1), new Coordinate(3,2)), is(true));
    }

    @Test
    public void testGivenPieceWhenNotIsAdvancedThenFalse(){
        assertThat(new Piece(Color.WHITE).isAdvanced(new Coordinate(5,0), new Coordinate(6,1)), is(false));
        assertThat(new Piece(Color.BLACK).isAdvanced(new Coordinate(2,1), new Coordinate(1,2)), is(false));
    }
}