package com.sagiadinos.garlic.widgets.jetbrains;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;

public class NewDsWidgetAction extends AnAction {

    @Override
    public void actionPerformed(@NotNull AnActionEvent e)
    {
        Project project = e.getProject();
        VirtualFile selectedDir = e.getData(CommonDataKeys.VIRTUAL_FILE);

        if (project == null || selectedDir == null)
        {
            return;
        }

        // If a file is selected, use its parent directory
        if (!selectedDir.isDirectory())
        {
            selectedDir = selectedDir.getParent();
        }

        String widgetName = Messages.showInputDialog(
                project,
                "Widget name:",
                "New DS Widget",
                Messages.getQuestionIcon(),
                "my-widget",
                null
        );

        if (widgetName == null || widgetName.trim().isEmpty())
        {
            return;
        }

        widgetName = widgetName.trim().toLowerCase().replaceAll("[^a-z0-9-]", "-");

        final VirtualFile targetDir = selectedDir;
        final String finalName = widgetName;

        ApplicationManager.getApplication().runWriteAction(() -> {
            try {
                DsWidgetGenerator.generate(project, targetDir, new DsWidgetSettings(finalName, ""));
            } catch (Exception ex) {
                Messages.showErrorDialog(project, "Failed to create widget: " + ex.getMessage(), "Error");
            }
        });
    }

    @Override
    public void update(@NotNull AnActionEvent e)
    {
        VirtualFile file = e.getData(CommonDataKeys.VIRTUAL_FILE);
        e.getPresentation().setEnabledAndVisible(
                e.getProject() != null && file != null
        );
    }
}
