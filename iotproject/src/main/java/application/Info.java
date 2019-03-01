package application;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.boot.context.event.ApplicationReadyEvent;

@Component
public class Info {

  @EventListener(ApplicationReadyEvent.class)
    public void contextRefreshedEvent() {
      System.out.println("The following sockets already:-");
      System.out.println("  Application   : http://localhost:8080/");
    }

}
