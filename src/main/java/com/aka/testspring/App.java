package com.aka.testspring;

import java.io.File;
import java.io.IOException;

import org.apache.catalina.startup.Tomcat;

/**
 * Hello world!
 *
 */
public class App 
{
	private static final int PORT = 8280;

	public static void main(String[] args) throws Exception {return;
        /*String appBase = ".";
        Tomcat tomcat = new Tomcat();
        tomcat.setBaseDir(createTempDir());
        tomcat.setPort(PORT);
        tomcat.getHost().setAppBase(appBase);
        tomcat.addWebapp("", appBase);
        tomcat.start();
        tomcat.getServer().await();*/
    }

    // based on AbstractEmbeddedServletContainerFactory
    private static String createTempDir() {
        try {
            File tempDir = File.createTempFile("tomcat.", "." + PORT);
            tempDir.delete();
            tempDir.mkdir();
            tempDir.deleteOnExit();
            return tempDir.getAbsolutePath();
        } catch (IOException ex) {
            throw new RuntimeException(
                    "Unable to create tempDir. java.io.tmpdir is set to " + System.getProperty("java.io.tmpdir"),
                    ex
            );
        }
    }
}
