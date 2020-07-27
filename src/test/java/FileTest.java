import be.zeldown.configannotation.ConfigInitier;
import objects.Configs;
import org.junit.jupiter.api.Test;

public class FileTest {

    @Test
    public void createFile() {

        ConfigInitier configInitier = new ConfigInitier();

        configInitier.init(Configs.class);
        configInitier.saveAll();

    }

}
