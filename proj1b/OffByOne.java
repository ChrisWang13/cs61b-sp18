public class OffByOne implements CharacterComparator {
    private int diff = 1;

    @Override
    public boolean equalChars(char x, char y) {
        return Math.abs(x - y) == diff;
    }
}