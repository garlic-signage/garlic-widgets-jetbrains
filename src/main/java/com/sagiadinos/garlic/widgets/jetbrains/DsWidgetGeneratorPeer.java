package com.sagiadinos.garlic.widgets.jetbrains;

import com.intellij.ide.util.projectWizard.SettingsStep;
import com.intellij.platform.ProjectGeneratorPeer;
import com.intellij.openapi.ui.ValidationInfo;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;

public class DsWidgetGeneratorPeer implements ProjectGeneratorPeer<DsWidgetSettings> {

    private final JPanel panel;
    private final JTextField widgetNameField;
    private final JTextField authorField;

    public DsWidgetGeneratorPeer() {
        panel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(4, 4, 4, 4);
        c.fill   = GridBagConstraints.HORIZONTAL;

        c.gridx = 0; c.gridy = 0; c.weightx = 0;
        panel.add(new JLabel("Widget name:"), c);
        widgetNameField = new JTextField("my-widget", 20);
        c.gridx = 1; c.weightx = 1;
        panel.add(widgetNameField, c);

        c.gridx = 0; c.gridy = 1; c.weightx = 0;
        panel.add(new JLabel("Author:"), c);
        authorField = new JTextField("", 20);
        c.gridx = 1; c.weightx = 1;
        panel.add(authorField, c);
    }

    @NotNull
    @Override
    public JComponent getComponent() {
        return panel;
    }

    @Override
    public void buildUI(@NotNull SettingsStep settingsStep) {
        // not needed
    }

    @NotNull
    @Override
    public DsWidgetSettings getSettings() {
        String name   = widgetNameField.getText().trim().toLowerCase().replaceAll("[^a-z0-9-]", "-");
        String author = authorField.getText().trim();
        return new DsWidgetSettings(name, author);
    }

    @Nullable
    @Override
    public ValidationInfo validate() {
        if (widgetNameField.getText().trim().isEmpty()) {
            return new ValidationInfo("Widget name is required", widgetNameField);
        }
        return null;
    }

    @Override
    public boolean isBackgroundJobRunning() {
        return false;
    }
}