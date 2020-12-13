import java.util.ArrayList;

/**
 * The type Properties.
 */
public class Properties {
    private ArrayList<Property> properties;

    /**
     * Instantiates a new list of properties.
     */
    public Properties() {
        properties = new ArrayList<Property>();
    }

    /**
     * Add property.
     *
     * @param p The new property to be added.
     */
    public void addProperty(Property p) {
        properties.add(p);
    }

    /**
     * Gets properties.
     *
     * @return The list of properties.
     */
    public ArrayList<Property> getProperties() {
        return properties;
    }

    @Override
    public String toString() {
        String s = "";

        for(int i = 0; i < properties.size(); i++) {
            s += properties.get(i) + "\n";
        }

        return s;
    }
}
