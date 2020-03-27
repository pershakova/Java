package Advanced2;

public class Main2 {
    public static void main(String[] args) throws MyArraySizeException, MyArrayDataException {
        try {
            String[][] array = new String[4][5];

             errorMessageMethod(array);
        }
        catch (MyArraySizeException e) {
            System.out.println(e.getMessage());
        }

        try {
            String[][] array2 = new String[][]{
                            new String[] { "1", "2", "3", "4"},
                            new String[] { "1", "2", "3", "4"},
                            new String[] { "1", "d", "3", "4"},
                            new String[] { "1", "2", "3", "4"}
                    };

            int sum = errorMessageMethod(array2);

            System.out.println(sum);
        }
        catch (MyArraySizeException | MyArrayDataException ex) {
            System.out.println(ex.getMessage());
        }

        String[][] array3 = new String[][]{
                new String[] { "1", "2", "3", "4"},
                new String[] { "1", "2", "3", "4"},
                new String[] { "1", "5", "3", "4"},
                new String[] { "1", "2", "3", "4"}
        };

        int sum = errorMessageMethod(array3);

        System.out.printf("Sum is %d%n", sum);
    }

    public static int errorMessageMethod(String[][] array) throws MyArrayDataException, MyArraySizeException {
        int row = 0;
        int column = 0;
        String parsedValue = "";

        try {
            if ((array.length != 4)||(array[1].length != 4)) {
                throw new MyArraySizeException("Dimension is bigger than 4");
            }

            int sum = 0;

            for (row = 0; row < array.length; row++) {
                for (column = 0; column < array[1].length; column++) {
                    parsedValue = array[row][column];

                    sum += Integer.parseInt(parsedValue);
                }
            }
            return sum;
        }
        catch (NumberFormatException ex) {
            throw new MyArrayDataException(String.format("Can not parse value %s. Row: %d, column: %d", parsedValue, row + 1, column +1));
        }
    }
}
