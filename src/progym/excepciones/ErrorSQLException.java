/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package progym.excepciones;

/**
 *
 * @author Klac
 */
public class ErrorSQLException extends Exception{

    public ErrorSQLException(){
        mensaje="Conexion fallida";
    }

    public ErrorSQLException(String mensaje) {
        super(mensaje);
        this.mensaje = mensaje;
    }

    public ErrorSQLException(String mensaje, Throwable cause) {
        super(mensaje, cause);
        this.mensaje = mensaje;
    }

    public ErrorSQLException( Throwable cause) {
        super(cause);
        this.mensaje = "Conexion fallida";
    }

    public ErrorSQLException(String mensaje, String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.mensaje = mensaje;
    }

    @Override
    public String getMessage() {
        return super.getMessage(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString() {
        return super.toString(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    private String mensaje;
}
