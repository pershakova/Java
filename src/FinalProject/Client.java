package FinalProject;

import org.springframework.stereotype.Component;

@Component("Client")
public class Client {
    public Referral getReferral() {
        return new Referral_Stomatologist();
    }
}
