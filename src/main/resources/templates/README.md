# {{WIDGET_TITLE}}

## Structure

```
{{WIDGET_NAME}}/
├── config.xml    # W3C Widget manifest + CMS parameters
├── index.html    # Widget entry point
├── icon.svg      # Widget icon shown in CMS
└── .gitignore
```

## Parameters

Defined in `config.xml`, passed from the DS CMS as URL query string.

| Name            | Default     | Description      |
|-----------------|-------------|------------------|
| backgroundColor | #000000     | Background color |
| textColor       | #ffffff     | Text color       |
| message         | Hello World | Display text     |

## Local Testing

Open `index.html` directly in a browser with query parameters:

```
index.html?message=Hello+DS&backgroundColor=%23001122&textColor=%23ffcc00
```

## Build WGT

```bash
zip -r ../{{WIDGET_NAME}}.wgt config.xml index.html icon.svg
```
