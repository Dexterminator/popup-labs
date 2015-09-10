package se.dxtr;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by dexter on 10/09/15.
 */
public class DisjointSets {
    private int n;
    private Map<Integer, Integer> elementToSetId = new HashMap<Integer, Integer> ();
    private Map<Integer, Set<Integer>> setIdToSet = new HashMap<Integer, Set<Integer>> ();
    private int newSetId = 0;

    public DisjointSets (int n) {
        this.n = n;
    }

    public void union (int a, int b) {
        if (inSingletonSet (a) && inSingletonSet (b))
            unionSingletonSets (a, b);
        else if (inSingletonSet (a) && isJoinedSet (b))
            unionSingletonSetWithJoinedSet (a, b);
        else if (isJoinedSet (a) && inSingletonSet (b))
            unionSingletonSetWithJoinedSet (b, a);
        else if (isJoinedSet (a) && isJoinedSet (b))
            unionJoinedSets (a, b);

        for (Map.Entry<Integer, Integer> integerIntegerEntry : elementToSetId.entrySet ()) {
            System.out.println (integerIntegerEntry);
        }

        System.out.println ();
        for (Map.Entry<Integer, Set<Integer>> integerSetEntry : setIdToSet.entrySet ()) {
            System.out.println (integerSetEntry);
        }
        System.out.println ();
    }

    private void unionJoinedSets (int a, int b) {
        Integer setIdA = elementToSetId.get (a);
        Integer setIdB = elementToSetId.get (b);
        for (Integer elem : setIdToSet.get (setIdA)) {
            elementToSetId.put (elem, setIdB);
            setIdToSet.get (setIdB).add (elem);
        }
        setIdToSet.remove (setIdA);
    }

    private void unionSingletonSetWithJoinedSet (int a, int b) {
        Integer setId = elementToSetId.get (b);
        elementToSetId.put (a, setId);
        setIdToSet.get (setId).add (a);
    }

    private void unionSingletonSets (int a, int b) {
        elementToSetId.put (a, newSetId);
        elementToSetId.put (b, newSetId);
        HashSet<Integer> set = new HashSet<Integer> ();
        set.add (a);
        set.add (b);
        setIdToSet.put (newSetId, set);
        newSetId++;
    }

    public boolean inSameSet (int a, int b) {
        if (a == b)
            return true;
        if (inSingletonSet (a) || inSingletonSet (b))
            return false;
        return elementToSetId.get (a).equals (elementToSetId.get (b));
    }

    private boolean inSingletonSet (int a) {
        return !elementToSetId.containsKey (a);
    }

    private boolean isJoinedSet (int a) {
        return elementToSetId.containsKey (a);
    }
}
