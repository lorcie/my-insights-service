package com.insights;

import java.util.logging.Logger;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.adk.agents.BaseAgent;
import com.google.adk.agents.LlmAgent;
import com.google.adk.events.Event;
import com.google.adk.runner.InMemoryRunner;
import com.google.adk.sessions.Session;
import com.google.adk.tools.AgentTool;
import com.google.adk.tools.BaseTool;
import com.google.adk.tools.GoogleSearchTool;
import com.google.adk.tools.mcp.McpToolset;
import com.google.adk.tools.mcp.SseServerParameters;
import com.google.genai.types.Content;
import com.google.genai.types.Part;
import io.reactivex.rxjava3.core.Flowable;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InsightsAssistantAgent {
    private static final Logger logger = Logger.getLogger(InsightsAssistantAgent.class.getName());
    public static BaseAgent ROOT_AGENT = initAgent();
    private static String USER_ID = "test-user";
    private static String NAME = "insights-assistant";

    public static BaseAgent initAgent() {
        var toolList = getTools();
        return LlmAgent.builder()
                .name("insights-assistant")
                .description("An AI assistant designed to help with insights recommendation tasks.")
                .model("gemini-2.0-flash")
                .instruction("""
                         You are a helpful insights assistant.
                         Your role is to assist users with insights features tasks, such as entities recommendation based o Qloo Insights service/ API.
                        
                         You can provide recommendations based on user input, such as location, interests, filter criteria, and other parameters.
                         You can also provide information about entities (place, album, artist, media, brand, person, destination, podcast,...)with filter to to match exactky or signal criteria for similarity according to Qloo Insight API Documentation

                        Location can be expressed as city, state, country, or coordinates (latitude, longitude),
                        either in WKT format with a POINT (longitude, latitude) or POLYGON, or as a simple string.

                         You should:
                         - Explain concepts clearly with examples
                         - Guide users through problem-solving processes
                         - Create personalized learning plans based on user goals
                         - Provide step-by-step explanations for complex topics
                         - Use available tools to get the most current information
                         - For Insight Mcp server, use the tools provided to get the most current information and return the response in clear and understandable for the human user
                         - Adapt your teaching style to the user's level of understanding

                         Always be encouraging and supportive in your responses.
                         Use the tools available to provide accurate and up-to-date information.
                        """)
                .tools(toolList)
                .build();

    }

    private static ArrayList<BaseTool> getTools() {
        String mcpServerUrl = System.getenv("MCP_PROXY_URL");
        logger.info("MCP_PROXY_URL environment variable: " + mcpServerUrl);
        if (mcpServerUrl == null || mcpServerUrl.isEmpty()) {
            mcpServerUrl = "http://localhost:8090/sse"; // Fallback URL
            logger.warning("⚠️ MCP_PROXY_URL environment variable not set, using default:" + mcpServerUrl);
        }
        SseServerParameters params = SseServerParameters.builder().url(mcpServerUrl).build();
        McpToolset.McpToolsAndToolsetResult toolsAndToolsetResult = null;
        try {
            toolsAndToolsetResult = McpToolset.fromServer(params, new ObjectMapper()).get();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        var toolList = toolsAndToolsetResult.getTools().stream().map(mcpTool -> (BaseTool) mcpTool)
                .collect(Collectors.toCollection(ArrayList::new));

        return toolList;
    }

    public static void main(String[] args) {
        InMemoryRunner runner = new InMemoryRunner(ROOT_AGENT);
        Session session =
                runner
                        .sessionService()
                        .createSession(NAME, USER_ID)
                        .blockingGet();
        try (Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8)) {
            while (true) {
                System.out.print("\nYou > ");
                String userInput = scanner.nextLine();
                if ("quit".equalsIgnoreCase(userInput)) {
                    break;
                }
                Content userMsg = Content.fromParts(Part.fromText(userInput));
                Flowable<Event> events = runner.runAsync(USER_ID, session.id(), userMsg);
                System.out.print("\nAgent > ");
                events.blockingForEach(event -> System.out.println(event.stringifyContent()));
            }
        }
    }
}