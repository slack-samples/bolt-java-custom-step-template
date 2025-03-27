# Bolt for Java Custom Step Template

This is a Bolt for Java template app used to build custom steps for use in
[Workflow Builder](https://api.slack.com/start#workflow-builder).

## Setup

Before getting started, first make sure you have a development workspace where
you have permission to install apps. **Please note that the features in this
project require that the workspace be part of
[a Slack paid plan](https://slack.com/pricing).**

### Developer Program

Join the [Slack Developer Program](https://api.slack.com/developer-program) for
exclusive access to sandbox environments for building and testing your apps,
tooling, and resources created to help developers build and grow.

## Installation

#### Create a Slack App

1. Open [https://api.slack.com/apps/new](https://api.slack.com/apps/new) and choose "From an app manifest"
2. Choose the workspace you want to install the application to
3. Copy the contents of [manifest.json](./manifest.json) into the text box that says `*Paste your manifest code here*` (within the JSON tab) and click *Next*
4. Review the configuration and click *Create*
5. Click *Install to Workspace* and *Allow* on the screen that follows. You'll then be redirected to the App Configuration dashboard.

#### Environment Variables

Before you can run the app, you'll need to store some environment variables.

1. Open your app's configuration page from this list, click **OAuth & Permissions** in the left hand menu, then copy the Bot User OAuth Token. You will store this in your environment as `SLACK_BOT_TOKEN`.
2. Click **Basic Information** from the left hand menu and follow the steps in the App-Level Tokens section to create an app-level token with the `connections:write` scope. Copy this token. You will store this in your environment as `SLACK_APP_TOKEN`.

```zsh
# Replace with your app token and bot token
export SLACK_BOT_TOKEN=<your-bot-token>
export SLACK_APP_TOKEN=<your-app-token>
```

### Setup Your Local Project

```zsh
# Clone this project onto your machine
git clone https://github.com/WilliamBergamin/bolt-java-custom-step-template.git

# Change into this project directory
cd bolt-java-template
```

#### Maven: Run

Ensure [maven](https://maven.apache.org/index.html) is installed on your local environment.
* We recommend using [brew to install Maven on macOS](https://formulae.brew.sh/formula/maven)

```zsh
# Install the dependencies and compile
mvn clean compile

# apply linter to your project
mvn spotless:apply

# Install the dependencies and test
mvn clean test

# Compile and start your local server
mvn clean compile exec:java -Dexec.mainClass="Main"
```

**NOTE**: If you chose to use Maven as your build tool you can remove the `builde.gradle` file from this project.

------

#### Gradle: Run

Ensure [gradle](https://gradle.org/) is installed on your local environment.
* We recommend using [brew to install Gradle on macOS](https://formulae.brew.sh/formula/gradle)

```zsh
# Run tests
gradle test

# Apply linter to project
gradle spotlessApply

# Start your local server
gradle run
```

**NOTE**: If you chose to use Gradle as your build tool you can remove the `pom.xml` file from this project.

## Using Steps in Workflow Builder

With your server running, the `Sample step` is now ready for use in
[Workflow Builder](https://api.slack.com/start#workflow-builder)! Add it as a
custom step in a new or existing workflow, then run the workflow while your app
is running.

For more information on creating workflows and adding custom steps, read more
[here](https://slack.com/help/articles/17542172840595-Create-a-new-workflow-in-Slack).

## Project Structure

### `manifest.json`

`manifest.json` is a configuration for Slack apps. With a manifest, you can create an app with a pre-defined configuration, or adjust the configuration of an existing app.

### `Main.java`

`./src/main/java/Main.java` is the entry point for the application and is the file you'll run to start the server. This project aims to keep this file as thin as possible, primarily using it as a way to route inbound requests.

### `/listeners`

Every incoming request is routed to a "listener". Inside this directory, we group each listener based on the Slack Platform feature used, so `/listeners/actions` handles incoming [Actions](https://docs.slack.dev/reference/interaction-payloads/block_actions-payload) requests and `/listeners/functions` handles [Custom Steps](https://docs.slack.dev/workflows/workflow-steps).

### `/logback.xml`

[`logback-classic`](https://search.maven.org/artifact/ch.qos.logback/logback-classic) is imported as a dependency to configure the logs of the project, this configuration is defined in a [logback.xml](https://logback.qos.ch/manual/configuration.html) found in `src/main/resources/logback.xml`. Note that by default the project should be logging `debug` level logs for slack dependencies.

### Tests

This project provides some sample unit tests. They can be found in `src/test`. They are to be used as examples to show how unit tests can be implemented. **As you modify this project don't hesitate to modify, add, or remove these tests.**
