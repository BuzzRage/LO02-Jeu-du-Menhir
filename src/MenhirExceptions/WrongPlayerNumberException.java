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
public class WrongPlayerNumberException extends Exception{
    public WrongPlayerNumberException(String message){
        super(message);
    }
    public WrongPlayerNumberException(String message, Throwable t){
        super(message,t);
    }
    
}
