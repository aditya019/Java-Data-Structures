import java.util.*;

public class Trie {
    Node origin;
    Trie() {
        origin = new Node('*');
    }
    Trie(String s) {
        origin = new Node('*');
        attach(s);
    }
    public void attach(String s) {
        Node cnode = origin;
        int i = 0;
        while(i < s.length()) {
            char c = s.charAt(i);
            if(cnode.map.containsKey(c)) {
                cnode = cnode.map.get(c);
            } else {
                Node newNode = new Node(c);
                cnode.map.put(c, newNode);
                cnode = cnode.map.get(c);
            }
            i++;
        }
        cnode.map.put('\0', new Node('\0'));
    }
    public boolean search(String s) {
        Node cnode = origin;
        int i = 0;
        while(i < s.length()) {
            char c = s.charAt(i);
            if(cnode.map.containsKey(c)) {
                cnode = cnode.map.get(c);
            } else return false;
            i++;
        }
        return true;
    }
    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.attach("car");
        trie.attach("hello");
        trie.attach("cards");
        System.out.println(trie.search("car"));
        System.out.println(trie.search("cardiac"));
    }
}
