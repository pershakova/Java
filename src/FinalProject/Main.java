package FinalProject;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args){
        (new Matrix()).getSpiralMatrix(5);

        //-----------

        Client client = new Client();
        Registry registry = new Registry();

        registry.getReferral(client);
        registry.issue_direction();

        //------------

        ApplicationContext context = new AnnotationConfigApplicationContext("FinalProject");
        Client client1 = context.getBean("Client", Client.class);
        Registry registry1 = context.getBean("Registry", Registry.class);
        registry1.getReferral(client1);
        registry1.issue_direction();
    }
}
