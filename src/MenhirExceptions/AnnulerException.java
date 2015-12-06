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
public class AnnulerException extends Exception{
    public AnnulerException(String message){
        super(message);
    }
    public AnnulerException(String message, Throwable t){
        super(message,t);
    }
    
}
