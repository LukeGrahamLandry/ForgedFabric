package ca.lukegrahamlandry.forgedfabric.depend;

import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.fml.loading.StringUtils;
import net.minecraftforge.fml.loading.moddiscovery.AbstractJarFileLocator;
import net.minecraftforge.fml.loading.moddiscovery.ModFile;
import net.minecraftforge.forgespi.locating.IModFile;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.text.html.Option;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BundledDependencyLocator extends AbstractJarFileLocator {
    private static final Logger LOGGER = LogManager.getLogger("ForgedFabric");
    private final Path modDir;
    private final String bundleDirName;
    private final Path bundleOutputDirectory;
    private final String selfClassId;
    private static final String locatorServiceFile = "META-INF/services/net.minecraftforge.forgespi.locating.IModLocator";

    public BundledDependencyLocator(){
        this.modDir = FMLPaths.MODSDIR.get();
        this.bundleDirName = "ff_bundle";
        this.bundleOutputDirectory = new File(this.modDir.toFile(), this.bundleDirName).toPath();
        this.selfClassId = "ca.lukegrahamlandry.forgedfabric.depend.BundledDependencyLocator";
    }

    public List<IModFile> scanMods() {
        LOGGER.debug("Scanning For Bundled Dependencies");
        try {
            List<IModFile> jars = getModJarsWithBundleDir();

            if (!jars.isEmpty()) Files.createDirectories(this.bundleOutputDirectory);

            List<Path> results = new ArrayList<>();
            for (IModFile file : jars){
                results.addAll(unwrapDependencies(file));
            }

            // ModsFolderLocator#scanMods ignores any paths from ModDirTransformerDiscoverer.allExcluded()
            // which includes anything with the IModLocator service file
            // so I have to add forgedfabric to the results manually
            Optional<IModFile> ff = forgedfabric();
            if (!ff.isPresent()) LOGGER.error("Could not find jar containing the locator service" + this.selfClassId);
            else results.add(ff.get().getFilePath());

            return jarPathsToModFiles(results.stream());
        } catch (IOException e) {
            return Collections.emptyList();
        }
    }

    private List<Path> unwrapDependencies(IModFile file) {
        LOGGER.debug(file.getFileName() + " has bundled dependencies:");
        List<Path> results = new ArrayList<>();
        try {
            Files.list(file.findResource(this.bundleDirName)).forEach((dep) -> {
                LOGGER.debug("  - " + dep.getFileName());
                File target = new File(this.bundleOutputDirectory.toFile(), dep.getFileName().toString());
                try {
                    if (target.exists()) target.delete();
                    Files.copy(dep, target.toPath());
                    results.add(target.toPath());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return results;
    }

    private List<IModFile> jarPathsToModFiles(Stream<Path> jars){
        return jars.map((p) -> ModFile.newFMLInstance(p, this)).peek((f) -> this.modJars.compute(f, (mf, fs) -> this.createFileSystem(mf))).collect(Collectors.toList());
    }

    private List<IModFile> getModJarsWithBundleDir() throws IOException {
        return jarPathsToModFiles(getJarFiles()).stream().filter((file) -> Files.exists(file.findResource(this.bundleDirName))).collect(Collectors.toList());
    }

    private Stream<IModFile> getModJarsWithLocatorService() throws IOException {
        return jarPathsToModFiles(getJarFiles()).stream().filter((file) -> Files.exists(file.findResource(locatorServiceFile)));
    }

    private List<String> getLocatorServices(IModFile file){
        List<String> results = new ArrayList<>();
        Path filePath = file.findResource(locatorServiceFile);
        StringBuilder contentBuilder = new StringBuilder();
        try (Stream<String> stream = Files.lines(filePath, StandardCharsets.UTF_8)) {
            stream.forEach(results::add);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return results;
    }

    private Stream<Path> getJarFiles() throws IOException {
        return Files.list(this.modDir).filter((path -> StringUtils.toLowerCase(path.getFileName().toString()).endsWith(".jar")));
    }

    private Optional<IModFile> forgedfabric() throws IOException {
        return getModJarsWithLocatorService().filter((file) -> getLocatorServices(file).contains(this.selfClassId)).findFirst();
    }

    public String name() {
        return "ForgedFabric Bundled";
    }

    @Override
    public void initArguments(Map<String, ?> arguments) {

    }
}
