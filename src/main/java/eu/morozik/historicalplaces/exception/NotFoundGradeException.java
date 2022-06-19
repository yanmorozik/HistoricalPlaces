package eu.morozik.historicalplaces.exception;

public class NotFoundGradeException extends RuntimeException{
    public NotFoundGradeException(Long grade) {
        super("entity with grade: " + grade + " was not found");
    }
}
