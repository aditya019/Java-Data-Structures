import java.util.*;

public class DisjointSet <T>{
    Map<T,T> parent;
    Map<T, Integer> unionSize;
    Map<T, HashSet<T>> unionSet;

    DisjointSet() {
        this.parent = new HashMap<>();
        this.unionSize = new HashMap<>();
        this.unionSet = new HashMap<>();
    }

    public Map<T, T> getParent() {
        return Collections.unmodifiableMap(this.parent);
    }

    public Map<T, Integer> getUnionSize() {
        return Collections.unmodifiableMap(unionSize);
    }

    public Map<T, HashSet<T>> getUnionSet() {
        return Collections.unmodifiableMap(unionSet);
    }

    public void addElement(T ele) {
        addElement(ele, ele);
    }

    public void addElement(T ele, T par) {
        parent.put(ele, par);
        unionSize.put(ele, 1);
        HashSet<T> set = new HashSet<>();
        set.add(ele);
        unionSet.put(ele, set);
    }

    public void addElements(List<T> list) {
        for(T i: list) {
            addElement(i);
        }
    }

    public T findParent(T ele) {
        if(!parent.containsKey(ele)) {
            System.out.println("Element does not exist in DisJoint Set");
            return null;
        }
        else return parent.get(ele);
    }

    public HashSet<T> getUnion(T ele) {
        if(!parent.containsKey(ele)) {
            System.out.println("Element does not exist in DisJoint Set");
            return null;
        }
        T par = parent.get(ele);
        return unionSet.get(par);
    }

    public int getSize(T ele) {
        if(!parent.containsKey(ele)) {
            System.out.println("Element does not exist in DisJoint Set");
            return -1;
        }
        else {
            T par = parent.get(ele);
            return unionSize.get(par);
        }
    }

    public void union(T a, T b) {
        if(!parent.containsKey(a) || !parent.containsKey(b)) {
            System.out.println("Element(s) does not exist in disjoint set");
        }
        T parA = findParent(a); T parB = findParent(b);
        if(getSize(parA) >= getSize(parB)) unionize(parA,parB);
        else unionize(parB,parA);
    }

    private void unionize(T a, T b) {
        HashSet<T> temp = getUnion(b);
        unionSet.get(a).addAll(temp);
        unionSet.remove(b);
        unionSize.remove(b);
        unionSize.put(a, unionSize.get(a)+temp.size());
        for(T i: temp) {
            parent.put(i, a);
        }
    }

    public void print() {
        printParent();
        printUnion();
        printUnionSize();
    }

    private void printParent() {
        System.out.println("Parent");
        for(T i: parent.keySet()) {
            System.out.println(i + " : " + parent.get(i));
        }
    }

    private void printUnion() {
        System.out.println("Union");
        for(T i : unionSet.keySet()) {
            System.out.print(i+ " : ");
            for(T j : unionSet.get(i)) {
                System.out.print(j + " ");
            }
            System.out.println();
        }
    }

    private void printUnionSize() {
        System.out.println("Union Size");
        for(T i: unionSize.keySet()) {
            System.out.println(i + " : " + unionSize.get(i));
        }
    }

    public static void main(String[] args) {
        DisjointSet<Integer> disjointSet = new DisjointSet<>();
        Integer[] arr = {1,2,3,4,5,6,7,8,9};
        List<Integer> list = Arrays.asList(arr);
        disjointSet.addElements(list);
        disjointSet.print();
        disjointSet.union(3,5);
        disjointSet.union(3,4);
        disjointSet.union(9,1);
        disjointSet.union(1,4);
        disjointSet.print();
    }
}
