package cmpt276.as1.cameradepthoffield.model;

import java.util.ArrayList;

import java.util.Iterator;
import java.util.List;

/*
Class to manage lenses in an ArrayList
 */
public class LensManager implements Iterable<Lens>{
    private List<Lens> lenses = new ArrayList<>();
    private static LensManager instance;

    private LensManager() {
        // do nothing, to prevent anyone from instantiating
    }

    // singleton method to ensure only one object of LenManager exists
    public static LensManager getInstance() {
        if (instance == null) {
            instance = new LensManager();
        }
        return instance;
    }

    // method to add new lenses into the ArrayList
    public void addLenses(Lens lens) {
        lenses.add(lens);
    }

    // method to get length of the ArrayList
    public int getLength() {
        return lenses.size();
    }

    // method to get the lens at an index
    public Lens getLens(int index) {
        return lenses.get(index);
    }

    // method to get camera name for the lens at index of ArrayList
    public String getCameraName(int index) {
        return lenses.get(index).getCameraName();
    }

    // method to get the the maximum aperture for the lens at index of ArrayList
    public double getMaxAperture(int index) {
        return lenses.get(index).getMaxAperture(); // getting max aperture from Lens file
    }

    // method to get focal length of a lens at index of the ArrayList
    public int getFocalLength(int index) {
        return lenses.get(index).getFocalLength(); // getting focal length from Lens file
    }
    // method to return ArrayList
    public ArrayList<Lens> getArrayList() {
        return (ArrayList<Lens>) lenses;
    }

    // to iterate through the ArrayList
    @Override
    public Iterator<Lens> iterator() {
        return lenses.iterator();
    }
}