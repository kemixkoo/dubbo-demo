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

