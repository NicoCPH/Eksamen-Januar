/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package errorhandling;

/**
 *
 * @author Nicol
 */
public class ContactNotFoundException extends Exception{
    
    public ContactNotFoundException(String message) {
        super(message);
    }

}
