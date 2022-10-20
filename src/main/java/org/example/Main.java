package org.example;

import queue.Consumer;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException, TimeoutException {

        Consumer cons = new Consumer();
        cons.reading();
    }
}