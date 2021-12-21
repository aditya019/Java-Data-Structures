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
    public void print() {
        print("", origin);
    }

    private void print(String s, Node root) {
        s += root.val;
        if(root == origin) s = "";
        if(root.map.containsKey('\0')) System.out.println(s);
        for(char c : root.map.keySet()) {
            print(s, root.map.get(c));
        }
        return;
    }

    public List<String> getList() {
        List<String> list = new LinkedList<>();
        getList("",list, origin);
        return list;
    }
    private List<String> getList(String s, List<String> list, Node root) {
        s += root.val;
        if(root == origin) s = "";
        if(root.map.containsKey('\0')) list.add(s);
        for(char c : root.map.keySet()) {
            getList(s, list, root.map.get(c));
        }

        return list;
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.attach("car");
        trie.attach("hello");
        trie.attach("cards");
        trie.attach("hey");
        trie.attach("hello world");
        trie.attach("bye");
        System.out.println(trie.search("car"));
        System.out.println(trie.search("cardiac"));
        trie.print();
        List<String> list = trie.getList();
        System.out.println(list);
    }
}
