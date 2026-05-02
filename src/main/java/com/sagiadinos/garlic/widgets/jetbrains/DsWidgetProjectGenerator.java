package com.sagiadinos.garlic.widgets.jetbrains;

import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.platform.DirectoryProjectGeneratorBase;
import com.intellij.platform.ProjectGeneratorPeer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.application.ApplicationManager;

import javax.swing.*;

public class DsWidgetProjectGenerator extends DirectoryProjectGeneratorBase<DsWidgetSettings>
{
    @NotNull
    @Override
    public String getName() {
        return "DS Widget";
    }

    @Nullable
    @Override
    public Icon getLogo() {
        return null;
    }

    @NotNull
    @Override
    public ProjectGeneratorPeer<DsWidgetSettings> createPeer() {
        return new DsWidgetGeneratorPeer();
    }

    @Override
    public void generateProject(@NotNull Project project,
                                @NotNull VirtualFile baseDir,
                                @NotNull DsWidgetSettings settings,
                                @NotNull Module module) {
        ApplicationManager.getApplication().runWriteAction(() -> {
            try {
                DsWidgetGenerator.generate(project, baseDir, settings);
            } catch (Exception e) {
                throw new RuntimeException("Failed to generate DS Widget project: " + e.getMessage(), e);
            }
        });
    }
}