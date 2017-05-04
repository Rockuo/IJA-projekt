package backend.hinter;

/**
 * Created by rockuo on 4.5.17.
 */
public class CompositeKey {
    private String controllerType;
    private int index;

    public String getControllerType() {
        return controllerType;
    }

    public int getIndex() {
        return index;
    }

    public CompositeKey(String controllerType, int index) {
        this.controllerType = controllerType;
        this.index = index;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj != null && obj instanceof CompositeKey) {
            CompositeKey s = (CompositeKey)obj;
            return controllerType.equals(s.controllerType) && (index ==s.index);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return (controllerType + index).hashCode();
    }
}
