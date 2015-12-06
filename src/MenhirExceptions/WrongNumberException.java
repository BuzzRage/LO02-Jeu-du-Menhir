/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MenhirExceptions;

/**
 *
 * @author Apache
 */
public class WrongNumberException extends Exception{
    public WrongNumberException(String message){
        super(message);
    }
    public WrongNumberException(String message, Throwable t){
        super(message,t);
    }
    
}
