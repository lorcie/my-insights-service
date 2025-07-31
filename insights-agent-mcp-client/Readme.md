# Insights Assistant Agent with Google ADK

This project demonstrates how to build an AI insights assistant using Google's Agent Development Kit (ADK) with Java. The agent is designed to help users with Entities recommendation based on Qloo Insights API.



The agent is built using Google ADK's core components:

- **LLM Agent**: Powered by Gemini 2.0 Flash model
- **MCP Tools**: Integration with Model Context Protocol tools for extended capabilities
- **Ql;oo Insights API Service**: Real-time information retrieval through specialized Qloo Insights API service
- **Session Management**: Persistent conversation context and memory

## 📋 Prerequisites

- Java 17 or higher
- Maven 3.6+
- Google API Key (for Gemini access)
- (Optional) MCP Server running for extended tool capabilities

## ⚙️ Setup

### 1. Clone the Repository

```bash
git clone <repository-url>
cd insights-assistant-agent-adk
```

### 2. Set Environment Variables

```bash
export GOOGLE_GENAI_USE_VERTEXAI=FALSE
export GOOGLE_API_KEY=<your-google-api-key>
```
You can get the API key from the [Google Cloud Console](https://console.cloud.google.com/apis/credentials).

### 3. Install Dependencies

```bash
mvn clean compile
```


### Running the ADK Web UI (Development)

For development and debugging, use the ADK Web UI:

```bash
mvn exec:java \
-Dexec.mainClass="com.google.adk.web.AdkWebServer" \
-Dexec.args="--adk.agents.source-dir=src/main/java" \
-Dexec.classpathScope="compile"
```



