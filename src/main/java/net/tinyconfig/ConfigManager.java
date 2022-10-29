/*
    https://github.com/ZsoltMolnarrr/TinyConfig

    The MIT License (MIT)

    Copyright (c) 2022

    Permission is hereby granted, free of charge, to any person obtaining a copy
    of this software and associated documentation files (the "Software"), to deal
    in the Software without restriction, including without limitation the rights
    to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
    copies of the Software, and to permit persons to whom the Software is
    furnished to do so, subject to the following conditions:

    The above copyright notice and this permission notice shall be included in
    all copies or substantial portions of the Software.

    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
    THE SOFTWARE.
 */

package net.tinyconfig;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;

public class ConfigManager<Config> {
    static final Logger LOGGER = LogManager.getLogger("tiny-config");

    public Config value;
    public String configName;
    public String directory;
    public boolean isLoggingEnabled = false;
    public boolean sanitize = false;

    public ConfigManager(String configName, Config defaultConfig) {
        this.configName = configName;
        this.value = defaultConfig;
    }

    public void refresh() {
        Path filePath = getConfigFilePath();
        load();
        if (sanitize || !Files.exists(filePath)) {
            save();
        }
    }

    public void load() {
        Path filePath = getConfigFilePath();

        try {
            Gson gson = new Gson();
            if (Files.exists(filePath)) {
                // Read
                Reader reader = Files.newBufferedReader(filePath);
                value = (Config) gson.fromJson(reader, value.getClass());
                reader.close();
            }
        } catch (Exception e) {
            if (isLoggingEnabled) {
                LOGGER.error("Failed loading " + configName + " config: " + e.getMessage());
            }
        }
    }

    public void save() {
        Config config = value;
        Path filePath = getConfigFilePath();
        Path configDir = PlatformHelper.getConfigDir();

        try {
            if (directory != null && !directory.isEmpty()) {
                Path directoryPath = configDir.resolve(directory);
                Files.createDirectories(directoryPath);
            }
            Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
            Writer writer = Files.newBufferedWriter(filePath);
            writer.write(prettyGson.toJson(config));
            writer.close();
            if (isLoggingEnabled) {
                Gson gson = new Gson();
                LOGGER.info(configName + " config written: " + gson.toJson(config));
            }
        } catch (Exception e) {
            if (isLoggingEnabled) {
                LOGGER.error("Failed writing " + configName + " config: " + e.getMessage());
            }
        }
    }

    private Path getConfigFilePath() {
        String configFilePath = configName + ".json";
        if (directory != null && !directory.isEmpty()) {
            configFilePath = directory + "/" + configFilePath;
        }
        Path configDir = PlatformHelper.getConfigDir();
        return configDir.resolve(configFilePath);
    }

    public Builder builder() {
        return new Builder(this);
    }

    public class Builder {
        ConfigManager<Config> manager;
        Builder(ConfigManager<Config> manager) {
            this.manager = manager;
        }

        public Builder enableLogging(boolean enable) {
            manager.isLoggingEnabled = enable;
            return this;
        }

        public Builder setDirectory(String directory) {
            manager.directory = directory;
            return this;
        }

        public Builder sanitize(boolean sanitize) {
            manager.sanitize = sanitize;
            return this;
        }

        public ConfigManager<Config> build() {
            return manager;
        }
    }
}
