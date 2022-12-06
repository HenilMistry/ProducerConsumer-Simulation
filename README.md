# ProducerConsumer-Simulation

This repository contains the code, a very generalized code to simulate the very classical IPC (Inter Process Communication)
problem called **The Producer Consumer Problem**.

## Problem Statement

We have a **_buffer_** of some size, which can be used by either of two processes called **_Producer_** or **_Consumer_**.
The **Producer's** Process uses the **buffer** to **_put items_** in it and the **Consumer's** Process uses the **buffer** to **_extract items_** from **buffer**
and consumes it. We need to ensure that both processes should not use the **_critical section_** at the same time, here, critical 
section means the buffer. It can be bounded or unbounded in terms of size.

## Solution

There are several solutions proposed by several scientists but the very first scientists who proposed the solution to this 
classical IPC problem was "Edger W. Dijkstra" and the solution includes the **`sleep()`** and **`wakeup()`** calls. To mimic the
actual scenario, I have used threads for Producer and Consumer's Processes. There are several other ways to solve this problem.

- Using Semaphore
- Using Monitors
- Using Sleep() and Wakeup()
- etc.

The solution which I have shown over here includes the below packages.
- UnboundedBuffer
- BoundedBuffer
    - It is very generalized way of simulation of Producer-Consumer Problem. You can look for packages and main class inside 
    that package to understand the logic.