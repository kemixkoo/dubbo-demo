# Dubbo Demo

## Requirement
- JDK 1.8+
- [Zookeeper 3.4+](http://zookeeper.apache.org/)

## Config Zookeeper

1. `cp conf/zoo_sample.cfg conf/zoo.cfg`
2. just do demo, no cluster. so the contents of zoo.cfg:
```
tickTime=2000
initLimit=10
syncLimit=5
dataDir=/home/dubbo/zookeeper-3.4.12/data
clientPort=2181
```

## How to do demo

1. start zookeeper.
2. run the Provider class from project `dubbo-provider`.
3. now, can run the consumer projects, like ApiConsumer, GenericConsumer,XmlConfigConsumer, also PureGenericConsumer.

## About Version of Dubbo

Currently, still use one old version 2.5.3, if use newer one, like 2.6.1, will have the NoClassDefFoundError. maybe missing some dependencies to add.

## Manage Services

If want to manage the service, need Dubbo-admin, it's in [Dubbo Ops](https://github.com/apache/incubator-dubbo-ops).

After do `mvn package` for dubbo-admin project, need put the war from target to your web container, like Tomcat.

Then, can check the service list via URL: http://localhost:2080/dubbo-admin-2.0.0/governance/services

PS, the port conflict with existed app, so change to 2080 here.
