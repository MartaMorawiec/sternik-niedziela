package pl.sternik.mm.niedziela.repositories;

public class NoSuchMonetaException extends Exception {
    private static final long serialVersionUID = -8555511053844242536L;

    public NoSuchMonetaException(String string) {
        super(string);
    }

    public NoSuchMonetaException() {
    }
}