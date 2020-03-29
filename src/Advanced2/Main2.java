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
            String[][] array4 = new String[][]{
                    new String[] { "1", "2", "3", "4"},
                    new String[] { "1", "2", "3", "4", "4"},
                    new String[] { "1", "d", "3", "4"},
                    new String[] { "1", "2", "3", "4"},
            };


            errorMessageMethod(array4);
        }
        catch (MyArraySizeException e) {
            System.out.println(e.getMessage());
        }

        try {
            String[][] array2 = new String[][]{
                            new String[] { "1", "2", "3", "4"},
                            new String[] { "1", "2", "3", "4"},
                            new String[] { "1", "d", "3", "4"},
                            new String[] { "1", "2", "3", "4"},
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
        int dimension = 4;

            if ((array.length != dimension)) {
                throw new MyArraySizeException("Dimension is bigger than 4");
            }

            for (int i = 0; i<dimension; i++){
                if (array[i].length != dimension){
                    throw new MyArraySizeException(String.format("Dimension is bigger than 4 in %d row", i));
                }
            }

            int sum = 0;

            for (int row = 0; row < array.length; row++) {
                for (int column = 0; column < array[1].length; column++) {
                    String parsedValue = array[row][column];
                    try{
                        sum += Integer.parseInt(parsedValue);
                    }
                    catch (NumberFormatException ex){
                        throw new MyArrayDataException(String.format("Can not parse value %s. Row: %d, column: %d", parsedValue, row, column));
                    }
                }
            }
            return sum;
    }
}
