class Range {
    final int startIndex;
    final int endIndex;

    Range(int startIndex, int endIndex) {
        this.startIndex = startIndex;
        this.endIndex = endIndex;
    }

    @Override
    public String toString() {
        return "(" + startIndex + " , " + endIndex + ")";
    }
}