package pl.edu.uph.tpsi.exceptions;

public class EmptyCartException extends RuntimeException
{
        public EmptyCartException ()
        {
                super( "Cart cannot be empty" );
        }
}
