package br.com.valim.contratoapi.exceptions;

public class RecordNotFoundException extends RuntimeException {
    public  RecordNotFoundException() {
        super("Not found");
    }

    public RecordNotFoundException(Long id) {
        super("Could not find record " + id);
    }

    public RecordNotFoundException(String id) {
        super("Could not find record " + id);
    }
}
