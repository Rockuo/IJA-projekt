package interfaces;

public interface Card {

    enum Color {
        DIAMONDS('D'),
        HEARTS('H'),
        CLUBS('C'),
        SPADES('S');
        private final char c;

        Color(char c) {
            this.c = c;
        }

        @Override
        public String toString() {
            switch (this) {
                case CLUBS:
                    return "C";
                case DIAMONDS:
                    return "D";
                case HEARTS:
                    return "H";
                case SPADES:
                    return "S";
                default:
                    throw new IllegalArgumentException();
            }
        }
    }


    int value();

    boolean isTurnedFaceUp();

    boolean turnFaceUp();

    Color color();

    boolean similarColorTo(Card c);

    int compareValue(Card c);

}
