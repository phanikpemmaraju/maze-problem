
record Point(int xPos, int yPos) {

    public Point traverseForward() {
        return new Point(xPos - 1, yPos);
    }

    public Point traverseBackward() {
        return new Point(xPos + 1, yPos);
    }

    public Point traverseLeft() {
        return new Point(xPos, yPos - 1);
    }

    public Point traverseRight() {
        return new Point(xPos, yPos + 1);
    }
}

