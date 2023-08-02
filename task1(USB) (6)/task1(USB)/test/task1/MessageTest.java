/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package task1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.Test;

/**
 *
 * @author ngcon
 */
public class MessageTest {
    
    Message message = new Message();
    
    public MessageTest() { }

    @Test
    public void testGetMethod() {
        String expected = "I have arrived";
        String actual = message.getMessage();
        
        assertEquals(expected,actual);
    }
    
}
