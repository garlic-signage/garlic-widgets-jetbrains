# HTML5 Widget Creator for Digital Signage — JetBrains Plugin

Adds **File > New Project > HTML5 Widget** to JetBrains IDEs (PHPStorm, WebStorm, IntelliJ IDEA Ultimate).

## Requirements

- JDK 21+
- JetBrains IDE 2025.1+

## Build

```bash
./gradlew buildPlugin
```

Output: `build/distributions/garlic-ide-plugin-idea-1.0.0.zip`

## Local Development

```bash
export IDE_LOCAL=/Applications/PhpStorm.app/Contents
./gradlew runIde
```

## Install

Settings > Plugins > ⚙ > Install Plugin from Disk > select ZIP 

## Usage

1. File > New Project > HTML5 Widget for Digital Signage
2. Enter Widget-Name and Author
3. Project will be created with:

```
my-widget/
├── .gitignore
├── Makefile
├── README.md
├── tools/
│   ├── build.sh
│   └── build.bat
└── src/
    ├── config.xml
    ├── garlic-widget.js
    ├── index.html
    └── icon.svg
```

## Pack as WGT

```bash
make
# oder
./tools/build.sh
```

Output lands in `dist/my-widget.wgt`

## Project structure

```
garlic-ide-plugin-idea/
├── build.gradle
├── settings.gradle
├── gradle/wrapper/
└── src/main/
    ├── java/com/sagiadinos/garlic/dswidgets/ideplugin/
    │   ├── DsWidgetGenerator.java
    │   ├── DsWidgetGeneratorPeer.java
    │   ├── DsWidgetProjectGenerator.java
    │   └── DsWidgetSettings.java
    └── resources/
        ├── META-INF/
        │   ├── plugin.xml
        │   └── pluginIcon.svg
        └── templates/
            ├── config.xml
            ├── garlic-widget.js
            ├── index.html
            ├── icon.svg
            ├── gitignore
            ├── Makefile
            ├── README.md
            ├── build.sh
            └── build.bat
```