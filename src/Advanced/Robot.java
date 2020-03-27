package Advanced;

public class Robot implements IParticipant, IAction{
    private String participant = "Robot";

    private int heightLimit;
    private int lengthLimit;

    public Robot(int heightLimit, int lengthLimit)
    {
        this.heightLimit = (heightLimit<0)? 0: heightLimit;
        this.lengthLimit = (lengthLimit<0)? 0: lengthLimit;
    }

    @Override
    public boolean run(int length)
    {
        CommonInfo.runInfo(participant, length, lengthLimit);

        return length < lengthLimit;
    }

    @Override
    public boolean jump(int height) {
        CommonInfo.jumpInfo(participant, height, heightLimit);

        return height < heightLimit;
    }

    @Override
    public boolean meet(Barrier barrier)
    {
        if (barrier instanceof Wall)
        {
            return jump(((Wall) barrier).getHeight());
        }
        else
        {
            return run(((RunningTrack) barrier).getLength());
        }
    }
}
