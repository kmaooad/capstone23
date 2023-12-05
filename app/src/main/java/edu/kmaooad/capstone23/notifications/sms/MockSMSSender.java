package edu.kmaooad.capstone23.notifications.sms;

import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.notifications.events.SMSSent;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MockSMSSender extends SMSSender {
    @Override
    public Result<SMSSent> send(SMS sms) {
        System.out.println("Sending SMS to " + sms.recipient() + "\n Title: " + sms.title() + "\n\n" + sms.body());
        return new Result<>(new SMSSent(sms.recipient(),System.currentTimeMillis()));
    }
}
