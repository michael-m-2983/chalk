package md.chalk.note;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * NoteID
 * 
 * This class represents the ID for a single note object.
 * 
 * Note IDs are long integers that have been encoded into strings with a radix of 36,
 *  and then padded with zeros until the length is equal to 16 characters. If the long
 *  is negative, the minus sign is replaced with an underscore.
 * 
 * These IDs are generated using the {@link SecureRandom} generator and the SHA1PRNG algorithm.
 * 
 * All IDs are mathematically guaranteed to start with a '0' character, so that this system 
 *  can be replaced in the future if needed.
 * 
 * Example IDs:
 *  000_1a04b60e1h02
 */
public class NoteId {
    private static int RADIX = 36;
    private long id;

    protected NoteId(long id) {
        this.id = id;
    }

    /**
     * Generate a new NoteID
     */
    public static NoteId generate() {
        SecureRandom secureRandom = null;
        
        try {
            secureRandom = SecureRandom.getInstance("SHA1PRNG");
        } catch(NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        } 

        return new NoteId(secureRandom.nextLong());
    }

    /**
     * Load a NoteID from a string
     * @throws NoteIdException if the string is invalid
     */
    public static NoteId load(String s) throws NoteIdException {
        if(s.length() != 16) {
            throw new NoteIdException("Invalid length of NoteID");
        }
        if(!s.matches("^0[0-9a-z_]{15}$")) {
            throw new NoteIdException("Invalid NoteID!");
        }

        return new NoteId(Long.parseLong(s.replace('_', '-').replaceFirst("^0+", ""), RADIX));
    }

    @Override
    public String toString() {
        String s = Long.toString(id, RADIX).replace('-', '_');

        while(s.length() < 16) {
            s = "0" + s;
        }

        return s;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if (!(obj instanceof NoteId other)) return false;
        return this.id == other.id;
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }

    /**
     * NoteIDException
     * 
     * This is thrown when you try to load an invalid note ID
     */
    public static class NoteIdException extends Exception {
        public NoteIdException(String message) {
            super(message);
        }
    }
}