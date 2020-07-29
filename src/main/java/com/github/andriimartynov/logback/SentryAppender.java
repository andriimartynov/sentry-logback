package com.github.andriimartynov.logback;

import ch.qos.logback.classic.spi.ILoggingEvent;
import io.sentry.event.EventBuilder;

public class SentryAppender extends io.sentry.logback.SentryAppender {

    public SentryAppender() {
        super();
    }

    protected EventBuilder createEventBuilder(ILoggingEvent iLoggingEvent) {
        EventBuilder eventBuilder = super.createEventBuilder(iLoggingEvent);

        eventBuilder.withFingerprint(iLoggingEvent.getLoggerName());

        return eventBuilder;
    }
}
