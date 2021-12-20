import java.util.*;

public class Node {
    public char val;
    public Map<Character, Node> map;

    public Node(char val) {
        this.val = val;
        this.map = new HashMap<>();
    }
}
