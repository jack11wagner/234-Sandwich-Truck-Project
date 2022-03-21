import java.util.Collection;

/**
 * Author: Nikolas Kovacs
 * This is an abstract class that is meant to be inherited from in order to implement different navigation modes
 */
public abstract class NavigationStrategy {

    public abstract Collection<int[]> calculateNavInstructions(int begDirection, int[] begCoords, Collection<int[]> destCoords);
}
