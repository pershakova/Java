package FinalProject;

public class Matrix {
    public void getSpiralMatrix(int dimension) {
        int[][] spiralFilledMatrix = new int[dimension][dimension];

        int columnMin = 0;
        int columnMax = dimension-1;
        int rowMin = 0;
        int rowMax = dimension-1;
        int value = 1;

        while (value <= dimension*dimension) {
            for (int i = columnMin; i <= columnMax; i++) {
                spiralFilledMatrix[rowMin][i] = value;

                value++;
            }

            for (int i = rowMin+1; i <= rowMax; i++) {
                spiralFilledMatrix[i][columnMax] = value;

                value++;
            }

            for (int i = columnMax-1; i >= columnMin; i--) {
                spiralFilledMatrix[rowMax][i] = value;

                value++;
            }

            for (int i = rowMax-1; i >= rowMin+1; i--) {
                spiralFilledMatrix[i][columnMin] = value;

                value++;
            }

            columnMin++;
            rowMin++;
            columnMax--;
            rowMax--;
        }

        for (int[] filledMatrix : spiralFilledMatrix) {
            for (int j = 0; j < spiralFilledMatrix.length; j++) {
                System.out.print(filledMatrix[j] + "\t");
            }

            System.out.println();
        }
    }
}
