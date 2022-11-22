package ca.lukegrahamlandry.forgedfabric.util.depend;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.loading.FMLPaths;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.stream.Collectors;

public class DependencyDownloadHelper {
    public static Path outputDir = FMLPaths.MODSDIR.get().resolve("ff_bundle");

    // TODO: handle 404 gracefully
    public static void installMod(String url){
        String[] parts = url.split("/");
        String lastPart = parts[parts.length - 1];

        // a modrinth project
        if (url.startsWith("https://modrinth.com/mod/")) {
            if (url.contains("/version/")) installSpecificFromModrinth(lastPart);  // must be link with version id not just project link. todo: support that as well so fetch whole list of files and search for one with correct version number
            else installLatestFromModrinth(url.substring(25));
        }

        // just a link to a jar file
        else getUrlFile(url, outputDir.resolve(lastPart));
    }

    private static void installLatestFromModrinth(String projectSlug) {
        String mcVersion = ModList.get().getModFileById("minecraft").getMods().get(0).getVersion().getQualifier();

        String apiUrl = "https://api.modrinth.com/v2/project/" + projectSlug + "/version?loader=[%22forge%22]&game_versions=[%22" + mcVersion + "%22";
        JsonArray versions = getUrlJson(apiUrl).getAsJsonArray();
        JsonObject latestVersion = versions.get(0).getAsJsonObject();
        downloadModrinthVersion(latestVersion);
    }

    private static void installSpecificFromModrinth(String versionId){
        String apiUrl = "https://api.modrinth.com/v2/version/" + versionId;
        JsonObject versionData = getUrlJson(apiUrl).getAsJsonObject();
        downloadModrinthVersion(versionData);
    }

    private static void downloadModrinthVersion(JsonObject versionData){
        JsonObject latestFile = versionData.getAsJsonArray("files").get(0).getAsJsonObject();

        String fileUrl = latestFile.get("url").getAsString();
        Path targetLocation = outputDir.resolve(latestFile.get("filename").getAsString());
        String expectedFileHash = latestFile.getAsJsonObject("hashes").get("sha512").getAsString();

        getUrlFile(fileUrl, targetLocation);
        if (!checkFileHash(targetLocation, expectedFileHash)) targetLocation.toFile().delete();
    }

    public static boolean checkFileHash(Path file, String expectedHash){
        if (expectedHash == null) return true;

        try {
            byte[] actualHashValue = MessageDigest.getInstance("SHA-512").digest(Files.readAllBytes(file));
            return expectedHash.equals(bytesToHex(actualHashValue));
        } catch (NoSuchAlgorithmException | IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static final byte[] HEX_ARRAY = "0123456789abcdef".getBytes(StandardCharsets.US_ASCII);
    public static String bytesToHex(byte[] bytes) {
        byte[] hexChars = new byte[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = HEX_ARRAY[v >>> 4];
            hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }
        return new String(hexChars, StandardCharsets.UTF_8);
    }

    public static void getUrlFile(String url, Path target){
        try {
            URLConnection connection = new URL(url).openConnection();
            if (url.contains("modrinth.con")) connection.addRequestProperty("User-Agent", "LukeGrahamLandry/ForgedFabric");
            InputStream in = connection.getInputStream();
            Files.copy(in, target, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static JsonElement getUrlJson(String url){
        String response = getUrlText(url);
        if (response == null) return null;
        return new JsonParser().parse(response);
    }

    public static String getUrlText(String url) {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            InputStream responseStream = connection.getInputStream();

            return new BufferedReader(
                    new InputStreamReader(responseStream, StandardCharsets.UTF_8))
                    .lines()
                    .collect(Collectors.joining("\n"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
