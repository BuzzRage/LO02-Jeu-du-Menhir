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
public class HasChienException extends Exception {

    /**
     * Creates a new instance of <code>HasChienException</code> without detail
     * message.
     */
    public HasChienException() {
    }

    /**
     * Constructs an instance of <code>HasChienException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public HasChienException(String msg) {
        super(msg);
    }
}
