# MJML to HTML Converter Library

This library provides an efficient way to convert MJML templates into HTML format. It leverages the power of the MJML API and provides Spring-based integrations for ease of use in Java applications.

## Table of Contents

- [Installation](#installation)
  - [Gradle](#gradle)
  - [Maven](#maven)
- [Configuration](#configuration)
- [Usage](#usage)
- [License](#license)

## Installation

### Gradle

Add the following to your `build.gradle`:

```gradle
repositories {
    mavenCentral()
    maven { url 'https://jitpack.io' }
}

dependencies {
    implementation 'com.github.gerry-mandering:mjml.converter:1.0.0'
}
```

### Maven

Add the following to your `pom.xml`:

```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>

<dependency>
    <groupId>com.github.gerry-mandering</groupId>
    <artifactId>mjml.converter</artifactId>
    <version>1.0.0</version>
</dependency>
```

## Configuration

To properly use the library, ensure you've set up the following configurations:

MJML API Authentication:

- `mjml.app-id`: Your MJML API App ID.
- `mjml.app-secret`: Your MJML API App Secret.

You can configure these in your `application.properties` or `application.yml` file:

```yml
mjml:
  app-id: YOUR_APP_ID
  app-secret: YOUR_APP_SECRET
```

## Usage

By default, the library expects your MJML templates to be located in the `resources/templates` directory of your project.

### Injecting the Service

To use the `mjml.converter` library in your Spring application, first inject the `MjmlRequestBuilder` and `MjmlConverter` by using the `@Autowired` annotation:

```java
@Autowired
private MjmlRequestBuilder mjmlRequestBuilder;

@Autowired
private MjmlConverter mjmlConverter;
```

There are two primary ways to use the library for converting MJML templates to HTML:

### 1. Compile MJML Templates with Parameters

If you have MJML templates with dynamic placeholders and want to compile them with specific parameters, follow these steps:

1. Create a parameter map:

```java
Map<String, Object> params = new HashMap<>();
params.put("key1", "value1");
params.put("key2", "value2");
// Add more parameters as needed...
```

2. Build the `MjmlRequest` with the template name and parameters:

```java
MjmlRequest mjmlRequest = mjmlRequestBuilder.build("your-template-name.mjml", params);
```

3. Convert the MJML content to HTML:

```java
HtmlResponse htmlResponse = mjmlConverter.convert(mjmlRequest);
String htmlContent = htmlResponse.getHtml();
```

### 2. Compile MJML Templates without Parameters

For MJML templates that don't require dynamic content:

1. Build the `MjmlRequest` with just the template name:

```java
MjmlRequest mjmlRequest = mjmlRequestBuilder.build("your-template-name.mjml");
```

2. Convert the MJML content to HTML:

```java
HtmlResponse htmlResponse = mjmlConverter.convert(mjmlRequest);
String htmlContent = htmlResponse.getHtml();
```

Replace placeholders like `your-template-name.mjml`, `key1`, and `value1` with your actual template names and parameters as appropriate. Adjust the instructions as needed based on your specific use case.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
