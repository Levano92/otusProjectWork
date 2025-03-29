package exeptions;

public class BrowserNorSupportedExeption extends RuntimeException {
    public BrowserNorSupportedExeption(String browser){
        super(String.format("Browser %s не поддерживается", browser));
    }
}
