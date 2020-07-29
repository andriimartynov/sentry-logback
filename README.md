# Sentry Logback
![Build](https://github.com/andriimartynov/sentry-logback/workflows/Build/badge.svg)
[![License](https://img.shields.io/badge/License-BSD%203--Clause-blue.svg)](https://opensource.org/licenses/BSD-3-Clause)

## Motivation

Grouping logging events by logger name. It's useful than you have log messages without stack trace:

```log
WARN  [2019-09-28 10:51:32,371] [qwerty-akka.actor.default-dispatcher-26] a.h.i.e.c.PoolGateway: Connection attempt failed. Backing off new connection attempts for at least 200 milliseconds. 
WARN  [2019-09-28 10:51:32,372] [qwerty-akka.actor.default-dispatcher-2] a.m.c.b.i.HttpContactPointBootstrap: Probing [http://host4:8560/bootstrap/seed-nodes] failed due to: Tcp command [Connect(host4:8560,None,List(),Some(10 seconds),true)] failed because of java.net.ConnectException: Connection refused 
WARN  [2019-09-28 10:51:33,540] [qwerty-akka.actor.default-dispatcher-2] a.h.i.e.c.PoolGateway: Connection attempt failed. Backing off new connection attempts for at least 400 milliseconds. 
WARN  [2019-09-28 10:51:33,541] [qwerty-akka.actor.default-dispatcher-2] a.m.c.b.i.HttpContactPointBootstrap: Probing [http://host4:8560/bootstrap/seed-nodes] failed due to: Tcp command [Connect(host4:8560,None,List(),Some(10 seconds),true)] failed because of java.net.ConnectException: Connection refused 
WARN  [2019-09-28 10:51:34,600] [qwerty-akka.actor.default-dispatcher-4] a.h.i.e.c.PoolGateway: Connection attempt failed. Backing off new connection attempts for at least 800 milliseconds. 
WARN  [2019-09-28 10:51:34,600] [qwerty-akka.actor.default-dispatcher-4] a.m.c.b.i.HttpContactPointBootstrap: Probing [http://host4:8560/bootstrap/seed-nodes] failed due to: Tcp command [Connect(host4:8560,None,List(),Some(10 seconds),true)] failed because of java.net.ConnectException: Connection refused 
ERROR [2019-09-28 10:51:34,604] [qwerty-akka.actor.default-dispatcher-4] a.m.c.b.i.HttpContactPointBootstrap: Overdue of probing-failure-timeout, stop probing, signaling that it's failed
```

## Usage

```xml
<configuration>
    <!-- Configure the Console appender -->
    <appender name="Console" class="ch.qos.logback.core.ConsoleAppender">
        <encoder>
            <pattern>%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n</pattern>
        </encoder>
    </appender>

    <!-- Configure the Sentry appender, overriding the logging threshold to the WARN level -->
    <appender name="Sentry" class="com.github.andriimartynov.logback.SentryAppender">
        <filter class="ch.qos.logback.classic.filter.ThresholdFilter">
            <level>WARN</level>
        </filter>
    </appender>

    <!-- Enable the Console and Sentry appenders, Console is provided as an example
 of a non-Sentry logger that is set to a different logging threshold -->
    <root level="INFO">
        <appender-ref ref="Console" />
        <appender-ref ref="Sentry" />
    </root>
</configuration>
```