package com.sagiadinos.garlic.widgets.jetbrains;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class DsWidgetGenerator {

    private static final String[][] TEMPLATES = {
            {"config.xml",       "src/config.xml"},
            {"garlic-widget.js", "src/garlic-widget.js"},
            {"index.html",       "src/index.html"},
            {"icon.png",         "src/icon.png"},
            {"icon.svg",         "src/icon.svg"},
            {"gitignore",        ".gitignore"},
            {"Makefile",         "Makefile"},
            {"README.md",        "README.md"},
            {"build.sh",         "tools/build.sh"},
            {"build.bat",        "tools/build.bat"}
    };


    public static void generate(Project project, VirtualFile baseDir, DsWidgetSettings settings) throws Exception {
        String title = toTitleCase(settings.widgetName);

        VirtualFile srcDir   = baseDir.createChildDirectory(DsWidgetGenerator.class, "src");
        VirtualFile toolsDir = baseDir.createChildDirectory(DsWidgetGenerator.class, "tools");

        for (String[] template : TEMPLATES) {
            String source = template[0];
            String target = template[1];
            try
            {
                String content = loadTemplate(source);
                // ...
                content = content
                        .replace("{{WIDGET_NAME}}",  settings.widgetName)
                        .replace("{{WIDGET_TITLE}}", title)
                        .replace("{{AUTHOR}}",       settings.author.isEmpty() ? "Unknown" : settings.author);

                VirtualFile targetDir = target.startsWith("src/")   ? srcDir
                        : target.startsWith("tools/") ? toolsDir
                          : baseDir;

                String filename = target.contains("/") ? target.substring(target.lastIndexOf('/') + 1) : target;
                writeFile(targetDir, filename, content);
            } catch (Exception e) {
                System.err.println("Failed to load template: " + source + " -> " + e.getMessage());
            }
        }

        ApplicationManager.getApplication().invokeLater(() -> {
            VirtualFile indexHtml = srcDir.findChild("index.html");
            if (indexHtml != null) {
                FileEditorManager.getInstance(project).openFile(indexHtml, true);
            }
        });
    }
    private static String loadTemplate(String filename) throws Exception {
        String path = "/templates/" + filename;
        try (InputStream is = DsWidgetGenerator.class.getResourceAsStream(path)) {
            if (is == null) {
                throw new RuntimeException("Template not found: " + path);
            }
            return new String(is.readAllBytes(), StandardCharsets.UTF_8);
        }
    }

    private static void writeFile(VirtualFile dir, String filename, String content) throws Exception {
        VirtualFile file = dir.findChild(filename);
        if (file == null) {
            file = dir.createChildData(DsWidgetGenerator.class, filename);
        }
        file.setBinaryContent(content.getBytes(StandardCharsets.UTF_8));
    }

    private static String toTitleCase(String input) {
        String[] parts = input.split("-");
        StringBuilder sb = new StringBuilder();
        for (String part : parts) {
            if (!part.isEmpty()) {
                sb.append(Character.toUpperCase(part.charAt(0)));
                sb.append(part.substring(1));
                sb.append(" ");
            }
        }
        return sb.toString().trim();
    }
}
