package Advanced;

public  class CommonInfo {

    public static void jumpInfo(String participant, int height, int heightLimit)
    {
        String info = "%s is jumping %d m";
        String infoFailure = "%s can not jump %d m";
        System.out.println(height<heightLimit? String.format(info, participant, height): String.format(infoFailure, participant, height));
    }

    public static void runInfo(String participant, int length, int lengthLimit)
    {
        String info = "%s is running %d m";
        String infoFailure = "%s can not run %d m";
        System.out.println(length<lengthLimit? String.format(info, participant, length): String.format(infoFailure, participant, length));
    }
}
