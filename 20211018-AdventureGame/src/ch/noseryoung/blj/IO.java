package ch.noseryoung.blj;

public class IO {

    public static void drawMultipleBox(int maxLength, int width, int height, Game game, String... strings) {
        final String HO_LINE = "\u2550";
        final String VER_LINE = "\u2551";
        final String CORNER_1 = "\u2554";
        final String CORNER_2 = "\u2557";
        final String CORNER_3 = "\u255D";
        final String CORNER_4 = "\u255A";
        final String SPACE = " ";
        int[] lengthDifference;

        for (String s : strings) {
            maxLength = Math.max(s.length(), maxLength);
        }
        int leftDistance = 1; //Index of lengthDifference Array
        int rightDistance = 2; //Index of lengthDifference Array
        for (int k = 0; k < height; k++) {
            for (int j = 0; j < width; j++) {
                if (k * width + j >= strings.length || strings[k * width + j].length() == 0) {
                    System.out.print(SPACE.repeat(2 + maxLength + 2));
                } else {
                    System.out.print(CORNER_1 + HO_LINE.repeat(maxLength) + CORNER_2 + SPACE.repeat(2));
                }
            }
            System.out.println();
            for (int j = 0; j < width; j++) {
                String printText;
                if (k * width + j < strings.length) printText = strings[k * width + j];
                else printText = "";
                lengthDifference = getLength(maxLength, printText.length());
                printText = printText.replace(game.getRooms().get(game.getActiveRoom()).getName(),
                        "\u001B[35m" + printText + "\u001B[0m");
                if (k * width + j >= strings.length || strings[k * width + j].length() == 0) {
                    System.out.print(SPACE.repeat(2 + maxLength + 2));
                } else {
                    System.out.print(VER_LINE + SPACE.repeat(lengthDifference[leftDistance]) + printText +
                            SPACE.repeat(lengthDifference[rightDistance]) + VER_LINE + SPACE.repeat(2));
                }
            }
            System.out.println();
            for (int j = 0; j < width; j++) {
                if (k * width + j >= strings.length || strings[k * width + j].length() == 0) {
                    System.out.print(SPACE.repeat(2 + maxLength + 2));
                } else {
                    System.out.print(CORNER_4 + HO_LINE.repeat(maxLength) + CORNER_3 + SPACE.repeat(2));
                }
            }
            System.out.println();
        }
    }

    private static int[] getLength(int maxLength, int usedLength) {
        int[] lengthDifference = new int[3];
        lengthDifference[0] = maxLength - usedLength;
        lengthDifference[1] = lengthDifference[0] / 2;
        if (lengthDifference[0] % 2 == 1) {
            lengthDifference[2] = lengthDifference[0] / 2 + 1;
        } else {
            lengthDifference[2] = lengthDifference[0] / 2;
        }
        return lengthDifference;
    }
}