package Advanced;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        int wallHeight = 10;
        int runningTrackLength = 200;

        int humanRunLimit = 100;
        int humanJumpLimit = 5;

        int catRunLimit = 150;
        int catJumpLimit = 20;

        int robotRunLimit = 500;
        int robotJumpLimit = 40;

        ArrayList<IParticipant> participants = new ArrayList<>();

        participants.add(new Human(humanJumpLimit, humanRunLimit));
        participants.add(new Cat(catJumpLimit, catRunLimit));
        participants.add(new Robot(robotJumpLimit, robotRunLimit));

        ArrayList<Barrier> barriers = new ArrayList<>();

        barriers.add(new Wall(wallHeight));
        barriers.add(new RunningTrack(runningTrackLength));

        for (IParticipant participant : participants)
        {
            for (Barrier barrier : barriers)
            {
                if (!participant.meet(barrier))
                {
                    break;
                }
            }
        }
    }
}
