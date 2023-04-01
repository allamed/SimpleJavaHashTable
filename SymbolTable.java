import java.util.LinkedList;

public class SymbolTable {
    private static final int TABLE_SIZE = 256;
    private LinkedList<Entry>[] table;

    public SymbolTable() {
        table = new LinkedList[TABLE_SIZE];
        for (int i = 0; i < TABLE_SIZE; i++) {
            table[i] = new LinkedList<>();
        }
    }

    public void insert(String lexeme, IdentifierType type) {
        int index = hash(lexeme);
        Entry entry = new Entry(lexeme, type);
        table[index].add(entry);
    }

    public IdentifierType search(String lexeme) {
        int index = hash(lexeme);
        for (Entry entry : table[index]) {
            if (entry.lexeme.equals(lexeme)) {
                return entry.type;
            }
        }
        return null;
    }

    private int hash(String lexeme) {
        int sum = 0;
        for (char c : lexeme.toCharArray()) {
            sum += c;
        }
        return sum % TABLE_SIZE;
    }

    public enum IdentifierType {
        VARIABLE,
        FUNCTION
    }

    private static class Entry {
        String lexeme;
        IdentifierType type;

        Entry(String lexeme, IdentifierType type) {
            this.lexeme = lexeme;
            this.type = type;
        }
    }

    public static void main(String[] args) {
        SymbolTable symbolTable = new SymbolTable();

        // Insert a variable identifier
        symbolTable.insert("myVariable", IdentifierType.VARIABLE);

        // Insert a function identifier
        symbolTable.insert("myFunction", IdentifierType.FUNCTION);

        // Search for identifiers
        System.out.println("myVariable is a " + symbolTable.search("myVariable")+"\n");
        System.out.println("myFunction is a " + symbolTable.search("myFunction"));
    }
}
