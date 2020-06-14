package FinalProject;

import org.springframework.stereotype.Component;

@Component("Registry")
public class Registry {

    private Referral referral;

    public Referral getReferral(Client client) {
        Referral referral = client.getReferral();
        setReferral(referral);
        return referral;
    }

    public void setReferral(Referral referral) {
        this.referral = referral;
    }

    public void issue_direction() {
        System.out.println("Направление выдано");
        referral.direct();
    }
}
