public class CorruptSaveException extends Exception {
    public CorruptSaveException(String line) {
        super("Save file is corrupted at line '" + line + "'");
    }
}
