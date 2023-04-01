import java.util.LinkedList;

public class HashTable {
    // Taille de la table de hachage
    private final int size;

    // Tableau de listes chaînées pour stocker les entrées
    private final LinkedList<Entry>[] table;

    // Constructeur pour initialiser la table de hachage avec une taille spécifique
    public HashTable(int size) {
        this.size = size;
        this.table = new LinkedList[size];
        for (int i = 0; i < size; i++) {
            table[i] = new LinkedList<>();
        }
    }

    // Fonction de hachage pour calculer l'index à partir d'une clé
    private int hashFunction(String key) {
        int hash = 0;
        for (char c : key.toCharArray()) {
            hash += c;
        }
        return hash % size;
    }

    // Insérer une entrée avec une clé et une valeur dans la table de hachage
    public void insert(String key, String value) {
        int index = hashFunction(key);
        for (Entry entry : table[index]) {
            // Si la clé existe déjà, mettre à jour la valeur
            if (entry.key.equals(key)) {
                entry.value = value;
                return;
            }
        }
        // Sinon, ajouter une nouvelle entrée
        table[index].add(new Entry(key, value));
    }

    // Rechercher une valeur associée à une clé donnée dans la table de hachage
    public String search(String key) {
        int index = hashFunction(key);
        for (Entry entry : table[index]) {
            // Si la clé correspond, retourner la valeur
            if (entry.key.equals(key)) {
                return entry.value;
            }
        }
        // Si la clé n'est pas trouvée, retourner null
        return null;
    }

    // Supprimer une entrée avec une clé donnée de la table de hachage
    public void delete(String key) {
        int index = hashFunction(key);
        for (Entry entry : table[index]) {
            // Si la clé correspond, supprimer l'entrée
            if (entry.key.equals(key)) {
                table[index].remove(entry);
                return;
            }
        }
        // Si la clé n'est pas trouvée, lever une exception
        throw new RuntimeException("Key '" + key + "' not found in the hash table");
    }

    // Classe interne pour représenter les entrées (paires clé-valeur) de la table de hachage
    private static class Entry {
        String key;
        String value;

        Entry(String key, String value) {
            this.key = key;
            this.value = value;
        }
    }

    // Exemple d'utilisation de la classe HashTable
    public static void main(String[] args) {
        HashTable hashTable = new HashTable(10);
        hashTable.insert("name", "John Doe");
        hashTable.insert("age", "30");
        hashTable.insert("job", "Software Engineer");

        System.out.println(hashTable.search("name")); // Output: John Doe
        System.out.println(hashTable.search("age"));  // Output: 30

        hashTable.delete("age");
        System.out.println(hashTable.search("age"));  // Output: null
    }
}