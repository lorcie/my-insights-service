---
title: "Building AI Agents with Google Agent Development Kit (ADK) and Java"
date: 2025-05-27
tags: ["AI", "GoogleADK", "Java", "Agent", "LLM", "MCP"]
image: "google-adk-java/title.png"
description: "Learn how to build powerful AI agents using Google's Agent Development Kit (ADK) with Java. Explore ADK architecture, core concepts, and create a learning assistant agent with tools and MCP integration."
slug: "building-ai-agents-google-adk-java"
relatedSlugs: ["model-context-protocol", "investment-advisor-langchain4j"]
hide: false
---

## Introduction

Over the last year or two we have seen a lot of agent frameworks emerge like langchain, Spring AI, langchain4j, Vercel AI SDK etc. These frameworks have made it easier to build AI agents that can perform complex tasks by combining large language models (LLMs) with various tools and APIs. Recently, Google has introduced its own framework for building AI agents called the Agent Development Kit (ADK). This framework is designed to work seamlessly with Google's Gemini models and other AI capabilities, but it is also framework-compatible, model-independent, and platform-neutral. Initially it had support only for Python, but now it has been extended to Java, allowing developers to leverage the power of Google's AI capabilities in their Java applications. In this blog post, we will explore the core concepts of ADK, its architecture, and how to build a complete learning assistant agent using Java.


## Key Features and Capabilities

ADK offers a comprehensive set of features designed to address the entire agent development lifecycle:

- **Multi-Agent Architecture**: Create modular, scalable applications where different agents handle specific tasks, working in concert to achieve complex goals.

- **Rich Tool Ecosystem**: Access pre-built tools (Search, Code Execution etc.), create custom tools, implement Model Context Protocol (MCP) tools, or integrate third-party libraries.

- **Flexible Orchestration**: Structured workflows using specialized workflow agents for predictable execution patterns, and dynamic LLM-driven routing for adaptive behavior.

- **Integrated Developer Experience**: Powerful CLI and visual Web UI for local development, testing, and debugging.

- **Built-in Evaluation**: Systematically assess agent performance, evaluating both final response quality and step-by-step execution trajectories.

- **Deployment Ready**: Containerize and deploy agents anywhere, including integration with Google Cloud services.

## ADK Core Components and Architecture

<div style="background-color: #1e1e1e; border-radius: 8px; padding: 20px; margin: 20px 0;">
    <svg width="1400" height="750" viewBox="0 0 1400 750" xmlns="http://www.w3.org/2000/svg" data-no-replay="true">
        <style>
            .concept-box { fill: #2d5a87; stroke: #4285f4; stroke-width: 2; rx: 12; ry: 12; }
            .detail-box { fill: #1a4971; stroke: #64b5f6; stroke-width: 1; rx: 8; ry: 8; }
            .flow-line {
                stroke: #4285f4;
                stroke-width: 2;
                stroke-dasharray: 5;
                marker-end: url(#conceptArrow);
                animation: conceptFlow 2.5s infinite linear;
            }
            .concept-label { font-family: Arial, sans-serif; font-size: 25px; fill: #fff; font-weight: bold; text-anchor: middle; }
            .detail-label { font-family: Arial, sans-serif; font-size: 18px; fill: #e3f2fd; text-anchor: middle; }
            .title { font-family: Arial, sans-serif; font-size: 32px; fill: #4285f4; font-weight: bold; text-anchor: middle; }
            .description { font-family: Arial, sans-serif; font-size: 30px; fill: #90caf9; text-anchor: middle; }
            .robot-body { fill: #4285f4; stroke: #2196f3; stroke-width: 2; }
            .robot-head { fill: #64b5f6; stroke: #2196f3; stroke-width: 2; }
            .robot-eye { fill: #fff; }
            .antenna { stroke: #f44336; stroke-width: 3; stroke-linecap: round; }
            @keyframes conceptFlow {
                from { stroke-dashoffset: 25; }
                to { stroke-dashoffset: 0; }
            }
            .fade-in {
                animation: fadeIn 1.5s ease-in-out;
            }
            @keyframes fadeIn {
                from { opacity: 0; }
                to { opacity: 1; }
            }
            .pulse {
                animation: pulse 2s infinite ease-in-out;
            }
            @keyframes pulse {
                0% { opacity: 0.8; }
                50% { opacity: 1; }
                100% { opacity: 0.8; }
            }
        </style>        
        <!-- Robot/Agent Icon -->
            <g class="pulse" transform="translate(1100, 20)">
                <!-- Robot Body -->
                <rect x="0" y="15" width="40" height="30" rx="5" ry="5" class="robot-body" />
                <!-- Robot Head -->
                <rect x="5" y="0" width="30" height="20" rx="8" ry="8" class="robot-head" />
                <!-- Eyes -->
                <circle cx="13" cy="8" r="3" class="robot-eye" />
                <circle cx="27" cy="8" r="3" class="robot-eye" />
                <!-- Antenna -->
                <line x1="15" y1="0" x2="15" y2="-8" class="antenna" />
                <line x1="25" y1="0" x2="25" y2="-8" class="antenna" />
                <circle cx="15" cy="-8" r="2" fill="#f44336" />
                <circle cx="25" cy="-8" r="2" fill="#f44336" />
                <!-- Arms -->
                <rect x="-8" y="20" width="15" height="8" rx="4" ry="4" class="robot-body" />
                <rect x="33" y="20" width="15" height="8" rx="4" ry="4" class="robot-body" />
            </g>        
        <text x="550" y="55" class="title">Google Agent Development Kit (ADK) Core Concepts</text>        
        <!-- Agent Concept -->
        <rect x="67" y="106" width="238" height="132" class="concept-box fade-in" />
        <text x="186" y="141" class="concept-label">Agent</text>
        <text x="186" y="167" class="description">Fundamental worker unit for</text>
        <text x="186" y="187" class="description">specific tasks</text>
        <text x="186" y="207" class="description">Types - LLM/Workflow/Custom</text>        
        <!-- Multi-Agent Concept -->
        <rect x="370" y="106" width="238" height="132" class="concept-box fade-in" />
        <text x="489" y="141" class="concept-label">Multi-Agent</text>
        <text x="489" y="167" class="description">Parent-child hierarchies for</text>
        <text x="489" y="187" class="description">collaborative, modular systems</text>
        <text x="489" y="207" class="description">with specialized roles.</text>        
        <!-- Tool Concept -->
        <rect x="675" y="106" width="238" height="132" class="concept-box fade-in" />
        <text x="794" y="141" class="concept-label">Tool</text>
        <text x="794" y="167" class="description">Gives agents abilities beyond</text>
        <text x="794" y="187" class="description">conversation - interact with APIs,</text>
        <text x="794" y="207" class="description">search, run code, call services.</text>        
        <!-- Runner Concept -->
        <rect x="979" y="106" width="238" height="132" class="concept-box fade-in" />
        <text x="1098" y="141" class="concept-label">ADK Runtime</text>
        <text x="1098" y="167" class="description">Manages execution flow,</text>
        <text x="1098" y="187" class="description">orchestrates agent interactions,</text>
        <text x="1098" y="207" class="description">coordinates backend services.</text>        
        <!-- Session Concept -->
        <rect x="67" y="291" width="238" height="132" class="concept-box fade-in" />
        <text x="186" y="326" class="concept-label">Session</text>
        <text x="186" y="352" class="description">Handles context of single</text>
        <text x="186" y="372" class="description">conversation, including history</text>
        <text x="186" y="392" class="description">and agent's working memory.</text>        
        <!-- Model Concept -->
        <rect x="370" y="291" width="238" height="132" class="concept-box fade-in" />
        <text x="489" y="326" class="concept-label">Model</text>
        <text x="489" y="352" class="description">Underlying LLM that powers</text>
        <text x="489" y="372" class="description">agents, enabling reasoning and</text>
        <text x="489" y="392" class="description">language understanding.</text>        
        <!-- Event Concept -->
        <rect x="675" y="291" width="238" height="132" class="concept-box fade-in" />
        <text x="794" y="326" class="concept-label">Event</text>
        <text x="794" y="352" class="description">Basic unit of communication</text>
        <text x="794" y="372" class="description">representing things that happen</text>
        <text x="794" y="392" class="description">during a session.</text>        
        <!-- Memory Concept -->
        <rect x="979" y="291" width="238" height="132" class="concept-box fade-in" />
        <text x="1098" y="326" class="concept-label">Memory</text>
        <text x="1098" y="352" class="description">Enables agents to recall</text>
        <text x="1098" y="372" class="description">information across multiple</text>
        <text x="1098" y="392" class="description">sessions for long-term context.</text>        
        <!-- Callbacks Concept -->
        <rect x="370" y="476" width="238" height="132" class="concept-box fade-in" />
        <text x="489" y="511" class="concept-label">Callbacks</text>
        <text x="489" y="537" class="description">Custom code snippets that run</text>
        <text x="489" y="557" class="description">at specific points for checks,</text>
        <text x="489" y="577" class="description">logging, or modifications.</text>        
        <!-- Connection flows -->
        <line x1="305" y1="172" x2="370" y2="172" class="flow-line" />
        <line x1="608" y1="172" x2="675" y2="172" class="flow-line" />
        <line x1="913" y1="172" x2="979" y2="172" class="flow-line" />        
        <!-- Arrowheads -->
        <defs>
            <marker id="conceptArrow" markerWidth="8" markerHeight="6" refX="8" refY="3" orient="auto">
                <polygon points="0 0, 8 3, 0 6" fill="#4285f4" />
            </marker>
        </defs>
    </svg>
</div>

Below are the core components of ADK:

### Agents

Agent is a self-contained execution unit designed to act autonomously to achieve specific goals. Agents can perform tasks, interact with users, utilize external tools, and coordinate with other agents.

There are 3 main types of agents:
  - **LLM Agents**: Uses a language model for reasoning and decision making.
  - **Workflow Agents (SequentialAgent, ParallelAgent, LoopAgent)**: These specialized agents control the execution flow of other agents in predefined, deterministic patterns without using an LLM for the flow control itself, perfect for structured processes needing predictable execution.    
  - **Custom Agent**: User-defined agents with specific logic.
  
<div style="background-color: #1e1e1e; border-radius: 8px; padding: 20px; margin: 20px 0;">
    <svg width="1170" height="650" viewBox="0 0 1170 650" xmlns="http://www.w3.org/2000/svg" data-no-replay="true">
        <style>
            .base-agent { fill: #2d5a87; stroke: #4285f4; stroke-width: 4; rx: 20; ry: 20; }
            .llm-agent { fill: #1a4971; stroke: #64b5f6; stroke-width: 3; rx: 16; ry: 16; }
            .workflow-container { fill: #2d4a2d; stroke: #81c784; stroke-width: 3; rx: 16; ry: 16; }
            .workflow-agent { fill: #1a3d1a; stroke: #a5d6a7; stroke-width: 3; rx: 10; ry: 10; }
            .custom-agent { fill: #5a2d2d; stroke: #ef5350; stroke-width: 3; rx: 16; ry: 16; }
            .inheritance-line {
                stroke: #4285f4;
                stroke-width: 3;
                marker-end: url(#inheritanceArrow);
            }
            .title-label { font-family: Arial, sans-serif; font-size: 21px; fill: #fff; font-weight: bold; text-anchor: middle; }
            .type-label { font-family: Arial, sans-serif; font-size: 16px; fill: #ccc; text-anchor: middle; font-style: italic; }
            .feature-label { font-family: Arial, sans-serif; font-size: 14px; fill: #e3f2fd; text-anchor: middle; }
            .main-title { font-family: Arial, sans-serif; font-size: 31px; fill: #4285f4; font-weight: bold; text-anchor: middle; }
            .container-label { font-family: Arial, sans-serif; font-size: 18px; fill: #81c784; font-weight: bold; text-anchor: middle; }
            .pulse {
                animation: pulse 3s infinite ease-in-out;
            }
            @keyframes pulse {
                0% { opacity: 0.8; }
                50% { opacity: 1; }
                100% { opacity: 0.8; }
            }
        </style>        
        <!-- Main Title -->
        <text x="585" y="52" class="main-title">ADK Agent Types</text>        
        <!-- Base Agent (Top Level) -->
        <rect x="455" y="91" width="260" height="104" class="base-agent pulse" />
        <text x="585" y="130" class="title-label">BaseAgent</text>
        <text x="585" y="156" class="type-label">Abstract Base Class</text>
        <text x="585" y="176" class="feature-label">Core agent functionality</text>        
        <!-- LLM Agent -->
        <rect x="65" y="260" width="234" height="156" class="llm-agent pulse" />
        <text x="182" y="299" class="title-label">LlmAgent</text>
        <text x="182" y="325" class="type-label">&lt;&lt;LLM-Based&gt;&gt;</text>
        <text x="182" y="358" class="feature-label">• Reasoning</text>
        <text x="182" y="377" class="feature-label">• Tools</text>
        <text x="182" y="396" class="feature-label">• Transfer</text>        
        <!-- Workflow Agents Container -->
        <rect x="364" y="234" width="442" height="234" class="workflow-container pulse" />
        <text x="585" y="267" class="container-label">Workflow Agents</text>
        <text x="585" y="293" class="type-label">Structured execution patterns</text>        
        <!-- Sequential Agent -->
        <rect x="390" y="312" width="117" height="104" class="workflow-agent" />
        <text x="449" y="345" class="title-label" style="font-size: 17px;">Sequential</text>
        <text x="449" y="364" class="title-label" style="font-size: 17px;">Agent</text>
        <text x="449" y="390" class="feature-label">Step-by-step</text>
        <text x="449" y="407" class="feature-label">execution</text>        
        <!-- Parallel Agent -->
        <rect x="527" y="312" width="117" height="104" class="workflow-agent" />
        <text x="585" y="345" class="title-label" style="font-size: 17px;">Parallel</text>
        <text x="585" y="364" class="title-label" style="font-size: 17px;">Agent</text>
        <text x="585" y="390" class="feature-label">Concurrent</text>
        <text x="585" y="407" class="feature-label">execution</text>        
        <!-- Loop Agent -->
        <rect x="663" y="312" width="117" height="104" class="workflow-agent" />
        <text x="722" y="345" class="title-label" style="font-size: 17px;">Loop</text>
        <text x="722" y="364" class="title-label" style="font-size: 17px;">Agent</text>
        <text x="722" y="390" class="feature-label">Iterative</text>
        <text x="722" y="407" class="feature-label">execution</text>        
        <!-- Custom Agent -->
        <rect x="871" y="260" width="234" height="156" class="custom-agent pulse" />
        <text x="988" y="299" class="title-label">CustomAgent</text>
        <text x="988" y="325" class="type-label">&lt;&lt;Custom Logic&gt;&gt;</text>
        <text x="988" y="358" class="feature-label">• User-defined behavior</text>
        <text x="988" y="377" class="feature-label">• Specialized logic</text>
        <text x="988" y="396" class="feature-label">• Domain-specific tasks</text>        
        <!-- Inheritance Lines -->
        <!-- BaseAgent to LlmAgent -->
        <line x1="520" y1="195" x2="260" y2="260" class="inheritance-line" />        
        <!-- BaseAgent to Workflow Container -->
        <line x1="585" y1="195" x2="585" y2="234" class="inheritance-line" />        
        <!-- BaseAgent to CustomAgent -->
        <line x1="650" y1="195" x2="910" y2="260" class="inheritance-line" />        
        <!-- Arrow markers -->
        <defs>
            <marker id="inheritanceArrow" markerWidth="16" markerHeight="10" refX="16" refY="5" orient="auto">
                <polygon points="0 0, 16 5, 0 10" fill="none" stroke="#4285f4" stroke-width="3" />
            </marker>
        </defs>        
    </svg>
</div>


### Multi Agent Systems

As your AI applications grow, structuring them as a single agent becomes limiting. ADK enables you to build robust Multi-Agent Systems (MAS) by composing multiple agents—each with specialized roles—into a collaborative, maintainable architecture.

Foundation for structuring multi-agent systems is the parent-child relationship defined in BaseAgent.

You create a tree structure by passing a list of agent instances to the sub agents argument when initializing a parent agent. 


<div style="background-color: #1e1e1e; border-radius: 8px; padding: 20px; margin: 20px 0;">
    <svg width="1150" height="520" viewBox="0 0 1150 520" xmlns="http://www.w3.org/2000/svg" data-no-replay="true">
        <style>
            .parent-agent { fill: #2d5a87; stroke: #4285f4; stroke-width: 4; rx: 15; ry: 15; }
            .child-agent { fill: #1a4971; stroke: #64b5f6; stroke-width: 3; rx: 12; ry: 12; }
            .grandchild-agent { fill: #0d2d47; stroke: #90caf9; stroke-width: 2; rx: 10; ry: 10; }
            .hierarchy-line {
                stroke: #4285f4;
                stroke-width: 2;
                stroke-dasharray: 5;
                marker-end: url(#hierarchyArrow);
                animation: treeFlow 2.5s infinite linear;
            }
            .parent-label { font-family: Arial, sans-serif; font-size: 18px; fill: #fff; font-weight: bold; text-anchor: middle; }
            .child-label { font-family: Arial, sans-serif; font-size: 15px; fill: #e3f2fd; font-weight: bold; text-anchor: middle; }
            .grandchild-label { font-family: Arial, sans-serif; font-size: 13px; fill: #bbdefb; text-anchor: middle; }
            .description { font-family: Arial, sans-serif; font-size: 11px; fill: #90caf9; text-anchor: middle; }
            .main-title { font-family: Arial, sans-serif; font-size: 24px; fill: #4285f4; font-weight: bold; text-anchor: middle; }
            @keyframes treeFlow {
                from { stroke-dashoffset: 25; }
                to { stroke-dashoffset: 0; }
            }
            .fade-in {
                animation: fadeIn 1.5s ease-in-out;
            }
            @keyframes fadeIn {
                from { opacity: 0; }
                to { opacity: 1; }
            }
        </style>        
        <!-- Title -->
        <text x="575" y="35" class="main-title">Multi-Agent System: Parent-Child Hierarchy</text>        
        <!-- Parent Agent -->
        <rect x="450" y="65" width="250" height="85" class="parent-agent fade-in" />
        <text x="575" y="95" class="parent-label">Main Orchestrator Agent</text>
        <text x="575" y="115" class="description">Coordinates all sub-agents</text>
        <text x="575" y="130" class="description">Manages overall workflow</text>        
        <!-- Child Agents - Level 1 -->
        <!-- Search Agent -->
        <rect x="100" y="220" width="180" height="75" class="child-agent fade-in" />
        <text x="190" y="245" class="child-label">Search Agent</text>
        <text x="190" y="265" class="description">Web search capabilities</text>
        <text x="190" y="280" class="description">Information retrieval</text>        
        <!-- Content Agent -->
        <rect x="320" y="220" width="180" height="75" class="child-agent fade-in" />
        <text x="410" y="245" class="child-label">Content Agent</text>
        <text x="410" y="265" class="description">Content generation</text>
        <text x="410" y="280" class="description">Document processing</text>        
        <!-- Analysis Agent -->
        <rect x="540" y="220" width="180" height="75" class="child-agent fade-in" />
        <text x="630" y="245" class="child-label">Analysis Agent</text>
        <text x="630" y="265" class="description">Data analysis</text>
        <text x="630" y="280" class="description">Pattern recognition</text>        
        <!-- Communication Agent -->
        <rect x="760" y="220" width="180" height="75" class="child-agent fade-in" />
        <text x="850" y="245" class="child-label">Communication Agent</text>
        <text x="850" y="265" class="description">User interaction</text>
        <text x="850" y="280" class="description">Response formatting</text>        
        <!-- Grandchild Agents - Level 2 -->
        <!-- Under Search Agent -->
        <rect x="50" y="360" width="130" height="60" class="grandchild-agent fade-in" />
        <text x="115" y="380" class="grandchild-label">Google Search</text>
        <text x="115" y="395" class="description">Web search</text>
        <text x="115" y="408" class="description">Real-time data</text>        
        <rect x="200" y="360" width="130" height="60" class="grandchild-agent fade-in" />
        <text x="265" y="380" class="grandchild-label">Database Query</text>
        <text x="265" y="395" class="description">Internal search</text>
        <text x="265" y="408" class="description">Knowledge base</text>        
        <!-- Under Content Agent -->
        <rect x="360" y="360" width="130" height="60" class="grandchild-agent fade-in" />
        <text x="425" y="380" class="grandchild-label">Text Generator</text>
        <text x="425" y="395" class="description">Content creation</text>
        <text x="425" y="408" class="description">Writing assistance</text>        
        <!-- Under Analysis Agent -->
        <rect x="570" y="360" width="130" height="60" class="grandchild-agent fade-in" />
        <text x="635" y="380" class="grandchild-label">Data Processor</text>
        <text x="635" y="395" class="description">Statistical analysis</text>
        <text x="635" y="408" class="description">Trend detection</text>        
        <!-- Under Communication Agent -->
        <rect x="790" y="360" width="130" height="60" class="grandchild-agent fade-in" />
        <text x="855" y="380" class="grandchild-label">Response Builder</text>
        <text x="855" y="395" class="description">Output formatting</text>
        <text x="855" y="408" class="description">User adaptation</text>        
        <!-- Hierarchy Lines -->
        <!-- Parent to Children -->
        <line x1="525" y1="150" x2="220" y2="220" class="hierarchy-line" />
        <line x1="550" y1="150" x2="410" y2="220" class="hierarchy-line" />
        <line x1="600" y1="150" x2="630" y2="220" class="hierarchy-line" />
        <line x1="625" y1="150" x2="820" y2="220" class="hierarchy-line" />        
        <!-- Children to Grandchildren -->
        <line x1="160" y1="295" x2="115" y2="360" class="hierarchy-line" />
        <line x1="220" y1="295" x2="265" y2="360" class="hierarchy-line" />
        <line x1="410" y1="295" x2="425" y2="360" class="hierarchy-line" />
        <line x1="630" y1="295" x2="635" y2="360" class="hierarchy-line" />
        <line x1="850" y1="295" x2="855" y2="360" class="hierarchy-line" />        
        <!-- Arrow markers -->
        <defs>
            <marker id="hierarchyArrow" markerWidth="10" markerHeight="7" refX="10" refY="3.5" orient="auto">
                <polygon points="0 0, 10 3.5, 0 7" fill="#4285f4" />
            </marker>
        </defs>        
    </svg>
</div>

## Tools

Tools allow agents to interact with the outside world, extending their capabilities beyond just conversation like web search, code execution, database queries, and more. ADK provides a rich set of built-in tools, and you can also create custom tools tailored to your specific needs.

ADK supports the below types of tools:

### Types of Tools

**1. Function Tools**: Custom tools you create specifically for your application's unique requirements and business logic.
- **Functions/Methods**: Standard synchronous functions or methods defined in your code that can be called by agents.
- **Agents-as-Tools**: Specialized agents that can be used as tools by parent agents
- **Long Running Function Tools**: Support for asynchronous operations and time-intensive tasks that don't block the agent's execution flow.

**2. Built-in Tools**: Pre-built tools provided by ADK for common operations such as Google Search, Code Execution, and Retrieval-Augmented Generation (RAG).

**3. Third-Party Tools**: Seamless integration with external tool libraries and frameworks, including LangChain Tools and CrewAI Tools.


### ADK Runtime

ADK Runtime is the engine of your agentic application. It's the system that takes your defined agents, tools, and callbacks and orchestrates their execution in response to user input, managing the flow of information, state changes, and interactions with external services like LLMs or storage.


## Session, State and Memory

- Session represents an ongoing conversation between a user and an agent.
- State is the current context of the session and is stored in the Session.
- Memory represnts a store of information that might span multiple past sessions or include external data sources


### Model

The underlying language model that powers agents, enabling them to understand and generate human-like text. 

> Currently, ADK Java supports only Gemini models and Anthropic models.


## Callbacks

Custom code snippets that can be executed at specific points in the agent's lifecycle, allowing for checks, logging, or modifications to behavior.


## Events

Events are the basic unit of communication in ADK, representing things that happen during a session.




### Build a Learning Assistant Agent with ADK

In this section, we will build a learning assistant agent using Google ADK with Java. This will have the below components:

**1. Learning Assistant Agent** - The central LLM agent built with Google ADK

**2. Google Search Tool** - Integrated for real-time web search capabilities

**3. YouTube MCP Tool** - Model Context Protocol tool for video content. This was built as part of the [YouTube MCP Tool Blog](/blog/model-context-protocol). Refer that blog for more details on how to build MCP servers and clients. 

**4. Gemini 2.0 Flash Model** - The underlying AI model powering the agent

**5. ADK Dev Web UI** - The development and debugging interface


<div style="background-color: #1e1e1e; border-radius: 8px; padding: 20px; margin: 20px 0;">
    <svg width="1000" height="500" viewBox="0 0 1000 500" xmlns="http://www.w3.org/2000/svg" data-no-replay="true">
        <style>
            .main-agent { fill: #2d5a87; stroke: #4285f4; stroke-width: 3; rx: 15; ry: 15; }
            .google-search { fill: #1a4971; stroke: #ea4335; stroke-width: 2; rx: 10; ry: 10; }
            .youtube-mcp { fill: #5a2d87; stroke: #ff0000; stroke-width: 2; rx: 10; ry: 10; }
            .gemini-model { fill: #2d8750; stroke: #34a853; stroke-width: 2; rx: 10; ry: 10; }
            .web-ui { fill: #875a2d; stroke: #fbbc04; stroke-width: 2; rx: 10; ry: 10; }
            .flow-line {
                stroke: #4285f4;
                stroke-width: 2;
                stroke-dasharray: 5;
                marker-end: url(#mainArrow);
                animation: flowAnimation 2.5s infinite linear;
            }
            .tool-flow {
                stroke: #ea4335;
                stroke-width: 2;
                stroke-dasharray: 3;
                marker-end: url(#toolArrow);
                animation: flowAnimation 2s infinite linear;
            }
            .mcp-flow {
                stroke: #ff0000;
                stroke-width: 2;
                stroke-dasharray: 3;
                marker-end: url(#mcpArrow);
                animation: flowAnimation 2s infinite linear;
            }
            .model-flow {
                stroke: #34a853;
                stroke-width: 2;
                stroke-dasharray: 4;
                marker-end: url(#modelArrow);
                animation: flowAnimation 2.2s infinite linear;
            }
            .debug-flow {
                stroke: #fbbc04;
                stroke-width: 2;
                stroke-dasharray: 6;
                marker-end: url(#debugArrow);
                animation: flowAnimation 1.8s infinite linear;
            }
            @keyframes flowAnimation {
                from { stroke-dashoffset: 25; }
                to { stroke-dashoffset: 0; }
            }
            .title-label { font-family: Arial, sans-serif; font-size: 16px; fill: #fff; font-weight: bold; text-anchor: middle; }
            .sub-label { font-family: Arial, sans-serif; font-size: 12px; fill: #ccc; text-anchor: middle; }
            .main-title { font-family: Arial, sans-serif; font-size: 22px; fill: #4285f4; font-weight: bold; text-anchor: middle; }
            .pulse {
                animation: pulse 2s infinite ease-in-out;
            }
            @keyframes pulse {
                0% { opacity: 0.7; }
                50% { opacity: 1; }
                100% { opacity: 0.7; }
            }
        </style>        
        <!-- Main Title -->
        <text x="500" y="40" class="main-title">Learning Assistant Agent with Google ADK</text>        
        <!-- Core Learning Assistant Agent -->
        <rect x="350" y="80" width="300" height="120" class="main-agent pulse" />
        <text x="500" y="110" class="title-label">Learning Assistant Agent</text>
        <text x="500" y="135" class="sub-label">Root LLM Agent</text>
        <text x="500" y="155" class="sub-label">ADK</text>
        <text x="500" y="175" class="sub-label">Session management</text>        
        <!-- Google Search Tool -->
        <rect x="40" y="100" width="220" height="80" class="google-search pulse" />
        <text x="150" y="130" class="title-label">Google Search Agent Tool</text>
        <text x="140" y="155" class="sub-label">Real-time web search</text>        
        <!-- YouTube MCP Tool -->
        <rect x="770" y="100" width="180" height="80" class="youtube-mcp pulse" />
        <text x="860" y="130" class="title-label">YouTube MCP Tool</text>
        <text x="860" y="155" class="sub-label">Educational video content</text>        
        <!-- Gemini Model -->
        <rect x="350" y="280" width="300" height="80" class="gemini-model pulse" />
        <text x="500" y="310" class="title-label">Gemini 2.0 Flash Model</text>
        <text x="500" y="335" class="sub-label">Advanced reasoning & streaming</text>        
        <!-- ADK Dev Web UI -->
        <rect x="50" y="280" width="180" height="100" class="web-ui pulse" />
        <text x="140" y="310" class="title-label">ADK Dev Web UI</text>
        <text x="140" y="335" class="sub-label">Interactive testing & debugging</text>
        <text x="140" y="355" class="sub-label">localhost:8080</text>        
        <!-- Connection Lines -->
        <!-- Google Search to Agent -->
        <line x1="260" y1="140" x2="350" y2="140" class="tool-flow" />        
        <!-- YouTube MCP to Agent -->
        <line x1="770" y1="140" x2="650" y2="140" class="mcp-flow" />        
        <!-- Agent to Gemini Model -->
        <line x1="500" y1="200" x2="500" y2="280" class="model-flow" />        
        <!-- Web UI to Agent -->
        <line x1="230" y1="320" x2="350" y2="160" class="debug-flow" />        
        <!-- Arrow markers -->
        <defs>
            <marker id="mainArrow" markerWidth="10" markerHeight="7" refX="10" refY="3.5" orient="auto">
                <polygon points="0 0, 10 3.5, 0 7" fill="#4285f4" />
            </marker>
            <marker id="toolArrow" markerWidth="10" markerHeight="7" refX="10" refY="3.5" orient="auto">
                <polygon points="0 0, 10 3.5, 0 7" fill="#ea4335" />
            </marker>
            <marker id="mcpArrow" markerWidth="10" markerHeight="7" refX="10" refY="3.5" orient="auto">
                <polygon points="0 0, 10 3.5, 0 7" fill="#ff0000" />
            </marker>
            <marker id="modelArrow" markerWidth="10" markerHeight="7" refX="10" refY="3.5" orient="auto">
                <polygon points="0 0, 10 3.5, 0 7" fill="#34a853" />
            </marker>
            <marker id="debugArrow" markerWidth="10" markerHeight="7" refX="10" refY="3.5" orient="auto">
                <polygon points="0 0, 10 3.5, 0 7" fill="#fbbc04" />
            </marker>
        </defs>
    </svg>
</div>


### Maven Dependencies

Create a new Maven project and add the following dependencies to your `pom.xml`:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 
         http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.codewiz</groupId>
    <artifactId>learning-assistant-agent-adk</artifactId>
    <version>1.0-SNAPSHOT</version>

    <properties>
        <maven.compiler.source>23</maven.compiler.source>
        <maven.compiler.target>23</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    </properties>

    <dependencies>
        <!-- The ADK Core dependency -->
        <dependency>
            <groupId>com.google.adk</groupId>
            <artifactId>google-adk</artifactId>
            <version>0.1.0</version>
        </dependency>

        <!-- The ADK Dev Web UI to debug your agent (Optional) -->
        <dependency>
            <groupId>com.google.adk</groupId>
            <artifactId>google-adk-dev</artifactId>
            <version>0.1.0</version>
        </dependency>
    </dependencies>
</project>
```

### Implementation Steps

Now, let's implement the Learning Assistant Agent step by step:

#### 1. **Agent Initialization**
Create the agent with a name, description, and instructions that define its role as a learning assistant.

#### 2. **Model Configuration** 
Use the Gemini 2.0 Flash model for advanced reasoning and streaming capabilities.

#### 3. **Tool Integration**
Add tools for real-time web search and YouTube video content retrieval using the Model Context Protocol (MCP).

- **Google Search Tool**: Implement a specialized sub-agent that can search Google for current information. We use a workaround for the Google Search tool due to limitations in function calling support alongside the MCP tools.
- **YouTube MCP Tool**: Integrate a tool that retrieves educational video content from YouTube. This was built as part of the [YouTube MCP Tool Blog](/blog/model-context-protocol) and it is listening on port 8090.


Here's the complete implementation of our Learning Assistant Agent:

```java

/**
 * Learning Assistant Agent built with Google ADK
 * Provides educational assistance, answers questions, and creates learning plans
 */
public class LearningAssistantAgent {

    // Agent configuration
    public static BaseAgent ROOT_AGENT = initAgent();
    private static String USER_ID = "test-user";
    private static String NAME = "learning-assistant";

    /**
     * Initialize the Learning Assistant Agent with tools and configuration
     */
    public static BaseAgent initAgent() {
        var toolList = getTools();
        return LlmAgent.builder()
                .name("learning-assistant")
                .description("An AI assistant designed to help with learning and educational tasks.")
                .model("gemini-2.0-flash")
                .instruction("""
                    You are a helpful learning assistant. 
                    Your role is to assist users with educational tasks, answer questions, 
                    and provide explanations on various topics.
                    
                    You should:
                    - Explain concepts clearly with examples
                    - Guide users through problem-solving processes
                    - Create personalized learning plans based on user goals
                    - Provide step-by-step explanations for complex topics
                    - Use available tools to get the most current information
                    - Adapt your teaching style to the user's level of understanding
                    
                    Always be encouraging and supportive in your responses.
                    Use the tools available to provide accurate and up-to-date information.
                """)
                .tools(toolList)
                .build();
    }

    /**
     * Configure and return the list of tools available to the agent
     */
    private static ArrayList<BaseTool> getTools() {
        String mcpServerUrl = "http://localhost:8090/sse";
        SseServerParameters params = SseServerParameters.builder().url(mcpServerUrl).build();
        McpToolset.McpToolsAndToolsetResult toolsAndToolsetResult = null;
        try {
            toolsAndToolsetResult = McpToolset.fromServer(params, new ObjectMapper()).get();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        var toolList = toolsAndToolsetResult.getTools().stream().map(mcpTool -> (BaseTool) mcpTool)
                .collect(Collectors.toCollection(ArrayList::new));

        //toolList.add(new GoogleSearchTool()); not working (Tool use with function calling is unsupported), workaround below
        // Add GoogleSearch tool - Workaround for https://github.com/google/adk-python/issues/134
        LlmAgent googleSearchAgent = LlmAgent.builder()
                .model("gemini-2.0-flash")
                .name("google_search_agent")
                .description("Search Google for current information")
                .instruction("""
                    You are a specialist in Google Search.
                    Use the Google Search tool to find current, accurate information.
                    Always provide sources and ensure the information is up-to-date.
                    Summarize the key findings clearly and concisely.
                """)
                .tools(new GoogleSearchTool())
                .outputKey("google_search_result")
                .build();
        
        AgentTool searchTool = AgentTool.create(googleSearchAgent, false);
        toolList.add(searchTool);       
        return toolList;
    }
}
```

Now we need to add the below environment variables for accessing Gemini models.

```bash
export GOOGLE_GENAI_USE_VERTEXAI=FALSE
export GOOGLE_API_KEY=<your-google-api-key>
```

> You can get your Google API key from the [Google Cloud Console](https://console.cloud.google.com/apis/credentials). Make sure to enable the Gemini API for your project.

### Running the Agent

To run the agent, we need to create a main method that initializes the agent and starts a session. You can add it to the `LearningAssistantAgent` class or create a separate class for the main method.

Here's how you can do it:

We are using the `InMemoryRunner` to run the agent in memory, which is suitable for development and testing purposes. If you want to deploy the agent in production, you would typically use a different runner that integrates with your deployment environment including database.

Then we create a session for the user and start an interactive loop where the user can input questions or requests, and the agent responds using the configured tools.


```java
    /**
     * Main method to run the Learning Assistant Agent
     */
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
```

A screen capture of the agent in action is shown below:

![alt text](/google-adk-java/learning-assistant-agent.png)


### Testing ADK Web UI

ADK provides a Web UI built using Spring for testing and debugging agents, which is very useful during development. The Web UI allows you to interact with your agents, view their responses, see various events etc.


We can start the ADK Web UI by running the below command in the terminal:

```bash
mvn exec:java \
-Dexec.mainClass="com.google.adk.web.AdkWebServer" \
-Dexec.args="--adk.agents.source-dir=src/main/java" \
-Dexec.classpathScope="compile"
```


## Testing the Agent

![alt text](/google-adk-java/test1.png)

!![alt text](/google-adk-java/test2.png)


## Conclusion

Google's Agent Development Kit (ADK) provides a powerful, production-ready framework for building sophisticated AI agents. Although Java version is still in early stages, it offers a solid foundation for creating agents that can reason, use tools, and interact with users naturally.

Happy agent building! 🚀

## References

- [Google ADK Documentation](https://google.github.io/adk-docs/)
- [ADK Java GitHub Repository](https://github.com/google/adk-java/tree/main)

---

For more in-depth tutorials on AI, Java, Spring, and modern software development practices, follow me for more content:

🔗 [Blog](https://codewiz.info)
🔗 [YouTube](https://www.youtube.com/@Code.Wizzard)
🔗 [LinkedIn](https://www.linkedin.com/in/code-wiz-740370302/)
🔗 [Medium](https://medium.com/@code.wizzard01)
🔗 [Github](https://github.com/CodeWizzard01)

Stay tuned for more content on the latest in AI and software engineering!