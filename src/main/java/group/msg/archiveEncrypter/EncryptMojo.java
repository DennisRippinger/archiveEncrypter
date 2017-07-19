package group.msg.archiveEncrypter;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

import java.io.File;

/**
 * Encrypts a given file within the target directory as a ZIP file.
 */
@Mojo(name = "encrypt")
public class EncryptMojo extends AbstractMojo {

    private static final String EXTENSION = "zip";

    @Parameter(readonly = true, defaultValue = "${project}")
    public MavenProject project;

    @Parameter(property = "filename")
    public String filename;

    @Parameter(property = "password", defaultValue = "0815")
    public String password;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        String directory = project.getBuild().getDirectory();

        File file = new File(directory + File.separator + filename);
        if (!file.exists()) {
            throw new MojoExecutionException("File " + file.toString() + " does not exist");
        }

        try {
            pack(file);
        } catch (ZipException e) {
            throw new MojoExecutionException(e.getMessage());
        }

    }

    public void pack(File file) throws ZipException {
        ZipParameters zipParameters = new ZipParameters();
        zipParameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
        zipParameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_ULTRA);
        zipParameters.setEncryptFiles(true);
        zipParameters.setEncryptionMethod(Zip4jConstants.ENC_METHOD_AES);
        zipParameters.setAesKeyStrength(Zip4jConstants.AES_STRENGTH_256);
        zipParameters.setPassword(password);

        String destinationZipFilePath = file.toString() + "." + EXTENSION;
        ZipFile zipFile = new ZipFile(destinationZipFilePath);
        zipFile.addFile(file, zipParameters);
    }
}
