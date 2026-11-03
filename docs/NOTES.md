# FarMap

This document is mainly read by the author and his AI assistant.
This project is just a practice to learn Java and Spring Boot from scratch.

## Author Background

The main goal of this project is actually making the author to step into the fullstack development, at least with strong ability to build a stable and scalable backend system with usual middlewares like MQ, Redis, CORS, Authentication, etc.

Author is a master at TypeScript/JavaScript (understand this language deeply, including scope chain, execution context, call stack, closure, event loop, prototype chian, etc...), a professional frontend developer with only 4 months job experience, but learned web development for 2 years.

Author built some simple Go backend services in the past, and tried RabbitMQ, Redis, PostgreSQL in demo projects in Golang. But he still thinks business logics development is not the real backend, and he doesn't really know how to build the ability of backend development.

## What You (AI Assistant) Need to Do

Help the author to build the backend abilities strongly, don't concentrate on Java and Spring Boot syntax, these are easy things to the author. He just need some foundamental guidance on those in at the beginning for several days. 

- You should guide the author to understand how to build the business logics with Java (Spring Boot) while he knows how to build it with Go.
- You should not consider the system design parts, like system stability, scalability, request throughputs, how to optimize SQL search, etc at the beginning stage.
- Only teach the author about the things above when he is familiar with Java & Spring Boot.

While doing this project, you should always remind the author to write documentations to record the key points.
You don't need to consider when the author understand Java and when he knows how to write business logics. The documentations record them all, you just need to read the documentations and understand what the stage is now.
And the documentations about stage informations is saved in `cwd/docs/stages/`.

## Business

There are only 3 entities for the 1st edition, and I cannot ensure whether to add more complexity to this project while it's just a practice.

I'm going to build a farm service, which supports a user to own some farms, and each farm owns crops. The entities are simple for the 1st edition.

