package org.bot;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

public class Main {
    private static String readResource(final String resourceName) {
        try (InputStream inputStream = Main.class.getClassLoader().getResourceAsStream(resourceName)) {
            if (inputStream == null) {
                throw new IllegalArgumentException("file not found! " + resourceName);
            } else {
                try (InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
                     BufferedReader reader = new BufferedReader(streamReader)) {
                    return reader.lines().collect(Collectors.joining(System.lineSeparator()));
                }
            }
        } catch (IOException e) {
            throw new UncheckedIOException("Failed to read the resource file: " + resourceName, e);
        }
    }
    public static void main(final String[] args) {
        try {
            final String botToken = (args.length > 2 && !args[0].isEmpty())
                    ? args[0] : readResource("token");
            final TelegramBotsApi api = new TelegramBotsApi(DefaultBotSession.class);
            api.registerBot(new MessageBot(botToken));
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}
