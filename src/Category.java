import java.util.HashSet;
import java.util.Set;

/**
 * Created by sol on 2015-11-01.
 */
public enum Category {
    BOOKS("books"), CAMERA("camera"), DVD("dvd"), HEALTH("health"), MUSIC("music"), SOFTWARE("software");

    public String name;

    Category(String name) {
        this.name = name;
    }

    public static Set<String> getNames() {
        Set<String> names = new HashSet<String>();
        names.add(BOOKS.name);
        names.add(CAMERA.name);
        names.add(DVD.name);
        names.add(HEALTH.name);
        names.add(MUSIC.name);
        names.add(SOFTWARE.name);
        return names;
    }
}
