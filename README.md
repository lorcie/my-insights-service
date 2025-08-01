# MyInsightsService for Devpost Qloo LLM Hackathon

[Google Cloud Run Hosted Project url: https://my-insights-service-app-1029043021255.us-central1.run.app](https://my-insights-service-app-1029043021255.us-central1.run.app) 

|           |                                                |
| --------- | ---------------------------------------------- |
| Author(s) | [Adrien Chan](https://github.com/lorcie) |

# Table of contents
1. [Introduction](#introduction)
2. [Build Application Docker Images with Docker Compose](#build-docker-compose)
3. [Docker Compose Modules Orchestration/Start/Stop ](#orchestrate-docker-compose)
4. [Usage](#usage)
5. [My Insights Service Development](#my-insights-service-development)
6. [My Insights Service Executions](#my-insights-service-executions)
7. [Codeset Files](#codeset-files)
8. [Application Deploy on Cloud Run](#application-deploy-cloud-run)
9. [Take Away](#take-away)

## Introduction <a name="introduction"></a>

This project proposes various integrations  ( **Google Agent Development Kit** Java Application, **Claude Desktop** MCP server confiruation, **N8N** Automation tool) to a custom Insights  **ModelContextProtocol** MCP server based on **Qloo Insights API** by SSE, in order to enable users to search for tag / audiences types and/or get Entities recommendation based on the user criteria filter

Some details about Architecture, Usage and Screenshots (executions, codesets samples) are presented below.

Instructions to Build Docker containers with Docker Compose orchestration and Deployment on Google Cloud Technologies (Cloud Run, Cloud Container Registry, ..) are described below.

## Build Application Docker Images with Docker Compose <a name="build-docker-compose"></a>

Go in directory containg the project docker-compose.yaml file

- Build the image(s) with command : **docker compose build**

MyInsightsService Docker Modules Images >

![MyInsightsService Docker Images](https://github.com/lorcie/my-insights-service/blob/master/assets/my-insights-service-docker-images.png?raw=true)

## Docker Compose Modules Orchestration/Start/Stop <a name="orchestrate-docker-compose"></a>

### Start Application Docker Containers

Inside the project directory containing docker_compose.yaml file

Start Application thru MyInsightsService docker modules

- start the My Insights Service Agent with command : **docker compose up**

MyInsightsService Docker Modules Containers >

![MyInsightsService Docker Module Containers](https://github.com/lorcie/my-insights-service/blob/master/assets/my-insights-service-docker-containers.png?raw=true)


### Stop Application Docker Containers

- type **CTRL-C** to force leaving the application thru the opened console

- then stop the MyInsightsService Application thru the N8N platform with command : **docker compose down**

- list pending  docker modules with command : **docker ps -a**

- remove the pending (ollama) docker modules by specifing each container id (s) with command  : **docker rm <container_id>**

## Usage <a name="usage"></a>

On the My Insights Service ADK Web UI application, you can use the following queries to test various use cases:

1. list available tools >
list available tools

2. get Tags Types >
get Tags Types

3. search Tags of Type "vegan" 
search vegan Tags

4. get Audiences Types >
get Audiences Types

5. search Audiences of Type "communities" >
search communities Audiences

6. recommend entities of type brand >
recommend entities of type brand at New York City

7. recommend entities of type place >
recommend entities of type place at New York City

- Click on any item on left hand side of the User Interface to get an Application Map with the Tools/Agents concerned by the interaction, and also details of Request/Response


## MyInsightsService Development <a name="my-insights-service-development"></a>

Global Architecture >

![MyInsightsService Global Architecture](https://github.com/lorcie/my-insights-service/blob/master/assets/my-insights-agent-architecture.png?raw=true)

Tools List >

![MyInsightsService Tools List](https://github.com/lorcie/my-insights-service/blob/master/assets/insights-agent-mcp-client-tools.png?raw=true)


## MyInsightsService Executions <a name="my-insights-service-executions"></a>

List Tools on Cloud Run >

![MyInsightsService Tools on Cloud Run](https://github.com/lorcie/my-insights-service/blob/master/assets/my-insights-service-cloud-run-with-tools-execution.png?raw=true)



Cloud Run Services >

![MyInsightsService Cloud Run Services](https://github.com/lorcie/my-insights-service/blob/master/assets/my-insights-service-cloud-run-services.png?raw=true)


Claude Desktop Usage >

![Claude Desktop Usage](https://github.com/lorcie/my-insights-service/blob/master/assets/insights-mcp-server-claude-desktop.png?raw=true)


N8N Automation Usage >

![N8N Automation usage](https://github.com/lorcie/my-insights-service/blob/master/assets/my-insights-service-mcp-server-n8n.png?raw=true)


## Codeset Files <a name="codeset-files"></a>

Docker (Compose) Codeset >

![MyInsightsService Docker (Compose) Codeset](https://github.com/lorcie/my-insights-service/blob/master/assets/my-insights-service-docker-codeset.png?raw=true)

Java Codeset >

![MyInsightsService Java Codeset](https://github.com/lorcie/my-insights-service/blob/master/assets/my-insights-service-java-codeset.png?raw=true)

Google Cloud Shell Editor/Terminal >

![MyInsightsService Google Clloud Shell Editor/Terminal](https://github.com/lorcie/my-insights-service/blob/master/assets/my-insights-service-google-cloud-shell.png?raw=true)

## Application Deploy on Cloud Run <a name="application-deploy-cloud-run"></a>

This requires to deploy separately each of the components : insights-mcp-server, insights-agent-mcp-client 

Clone the my-insights-service project Repo

You have to open a session your **Google Cloud Shell** offering Terminal (CLI) or ineractive Editor possibilies.

You can then upload thru your Google Cloud Shell the following subdirectory **my-insights-service-deploy-cloud-run** which includes 2 subdirectories : insights-mcp-server and insights-agent-mcp-client

### MCP server Insights

// go in insights-mcp-server directory

cd insights-mcp-server

export SERVICE_NAME='my-insights-service-mcp-server'

export AR_REPO=YOUR_AR_REPO

export GCP_REGION=YOUR_REGION

export GCP_PROJECT=YOUR_PROJECT_ID

gcloud artifacts repositories list

// if not yet available, create AR_REPO

gcloud artifacts repositories create "$AR_REPO" --location="$GCP_REGION" --repository-format=Docker

gcloud auth configure-docker "$GCP_REGION-docker.pkg.dev"

gcloud builds submit --tag "$GCP_REGION-docker.pkg.dev/$GCP_PROJECT/$AR_REPO/$SERVICE_NAME" .

// set correctly the env variables  :

export QLOO_BASE_URL=https://hackathon.api.qloo.com/ 

export QLOO_API_KEY=Your_Qloo_Api_Key

gcloud run deploy "$SERVICE_NAME" --port=8090 --image="$GCP_REGION-docker.pkg.dev/$GCP_PROJECT/$AR_REPO/$SERVICE_NAME" --allow-unauthenticated --region=$GCP_REGION --platform=managed --project=$GCP_PROJECT --set-env-vars=qloo.base.url=$QLOO_BASE_URL,qloo.api.key=$QLOO_API_KEY


### MyInsightsService ADK application

// go in insights-agent-mcp-client directory

cd insights-agent-mcp-client

export SERVICE_NAME='my-insights-service-agent'

export AR_REPO=YOUR_AR_REPO

export GCP_REGION=YOUR_REGION

export GCP_PROJECT=YOUR_PROJECT_ID

gcloud artifacts repositories list

// next commnad is useless if the project artifactory repo has been already created (for instance when building the insights-mcp-server described above)

gcloud artifacts repositories create "$AR_REPO" --location="$GCP_REGION" --repository-format=Docker

gcloud auth configure-docker "$GCP_REGION-docker.pkg.dev"

gcloud builds submit --tag "$GCP_REGION-docker.pkg.dev/$GCP_PROJECT/$AR_REPO/$SERVICE_NAME" .

// set correctly the environment variables  MCP_PROXY_URL (MyInsights MCP service), 

export MCP_PROXY_URL=Your_Mcp_Server_Url_Generated_In_Previous_Step/sse

export GOOGLE_API_KEY=Your_Google_Api_Key

gcloud run deploy "$SERVICE_NAME"  --port=8080 --image="$GCP_REGION-docker.pkg.dev/$GCP_PROJECT/$AR_REPO/$SERVICE_NAME"  --allow-unauthenticated --region=$GCP_REGION --platform=managed --project=$GCP_PROJECT --set-env-vars=MCP_PROXY_URL=$MCP_PROXY_URL,GOOGLE_API_KEY=$GOOGLE_API_KEY

## Take Away <a name="take-away"></a>

**Google ADK (Agent Development Kit)** offers visual and interactive features to develop/debug and showcase Agent(s) by highlighting the tools/request/response for each user query.

It can be combined easily with **Insights MCP server** interacting in real time with **Qloo Insights API** to grant AI agents context and standardized interaction with external Qloo Insights services

**Google Cloud** technologies such as **Cloud Shell** **Cloud Run** **Cloud Logging** **Cloud Container Registry** serverless (pay per use) enables developers to deploy/manage/monitor easily application(s)/components

Use **Docker Container Images** with **Docker Compose** to build, debug and Test on your local development environment

