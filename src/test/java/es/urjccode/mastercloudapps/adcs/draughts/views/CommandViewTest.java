package es.urjccode.mastercloudapps.adcs.draughts.views;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import es.urjccode.mastercloudapps.adcs.draughts.BaseTest;
import es.urjccode.mastercloudapps.adcs.draughts.controllers.PlayController;
import es.urjccode.mastercloudapps.adcs.draughts.models.Color;
import es.urjccode.mastercloudapps.adcs.draughts.models.Coordinate;
import es.urjccode.mastercloudapps.adcs.draughts.utils.Console;

public class CommandViewTest extends BaseTest{

    @Mock
    private Console console;

    @Mock
    private PlayController playController;

    @InjectMocks
    private CommandView commandView;

    @Test
    public void testInteractWithWorngEntry(){
        when(playController.getColor()).thenReturn(Color.BLACK);
        when(console.readString("Mueven las negras: ")).thenReturn("JKDC").thenReturn("21.30\n");
        commandView.interact();
        verify(console).write("Error!!!WRONG_FORMAT");
    }

    @Test
    public void testInteractWithWorngEntrySize(){
        when(playController.getColor()).thenReturn(Color.BLACK);
        when(console.readString("Mueven las negras: ")).thenReturn("21.30.42").thenReturn("21.30\n");
        commandView.interact();
        verify(console).write("Error!!!WRONG_FORMAT");
    }

    @Test
    public void testInteractWithBlackColor(){
        when(playController.getColor()).thenReturn(Color.BLACK);
        when(console.readString("Mueven las negras: ")).thenReturn("21.30\n");
        commandView.interact();
        verify(playController).move(new Coordinate(2, 1), new Coordinate(3, 0));
    }

    @Test
    public void testInteractWithWhiteColor(){
        when(playController.getColor()).thenReturn(Color.WHITE);
        when(console.readString("Mueven las blancas: ")).thenReturn("61.52\n");
        commandView.interact();
        verify(playController).move(new Coordinate(6, 1), new Coordinate(5, 2));
    }

}