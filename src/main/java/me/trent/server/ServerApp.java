package me.trent.server;

import java.io.IOException;

public class ServerApp {

    public static void main(String... args) throws IOException {
        Server server1 = new Server("127.0.0.1", 6969, "Server-1");
        server1.start();

    }
}
