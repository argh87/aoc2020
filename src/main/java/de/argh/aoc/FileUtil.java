package de.argh.aoc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

public class FileUtil {

    public static List<String> getLines(String resourceFile)  {
        try (InputStream resource = Thread.currentThread()
                .getContextClassLoader()
                .getResourceAsStream(resourceFile)) {
            return new BufferedReader(new InputStreamReader(resource, StandardCharsets.UTF_8)).lines()
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
