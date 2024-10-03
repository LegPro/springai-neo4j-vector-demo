
# Spring AI Neo4j Vector Demo

This project demonstrates how to use **Neo4j** as a vector store in a Spring Boot application. The application provides functionality for loading documents, splitting them into tokens, and performing similarity searches using vector embeddings.

## Prerequisites

- **Java 21**
- **Maven**
- **Docker**
- **Neo4j Database** (running in a Docker container)
- **OpenAI API Key** for generating vector embeddings

## Getting Started

### 1. Run Neo4j in Docker

To run a Neo4j instance locally, you can use Docker. Run the following command to spin up a container:

```bash
docker run -d --name neo4j-container -p 7474:7474 -p 7687:7687 -e NEO4J_AUTH=neo4j/strongPass123 neo4j
```

This will start Neo4j with the default authentication (`username: neo4j`, `password: strongPass123`) and expose the necessary ports (`7474` for the Neo4j browser and `7687` for Bolt protocol).

### 2. Add OpenAI API Key

You must add your **OpenAI API key** to the project. This key is used to generate the vector embeddings for documents in the application. Make sure to configure the OpenAI API key in your application before running the project.

### 3. Running the Application

To run the application, use the following Maven command:

```bash
mvn spring-boot:run
```

This will start the Spring Boot application and expose the API endpoints for loading documents and performing vector similarity searches.

### 4. REST API Endpoints

The project provides two REST API endpoints:

#### 1. Load Documents
- **URL**: `/load`
- **Method**: `GET`
- **Description**: This endpoint loads and tokenizes a set of predefined documents into the Neo4j vector store.

Example request:

```bash
curl http://localhost:8080/load
```

#### 2. Search Documents
- **URL**: `/search`
- **Method**: `GET`
- **Description**: This endpoint performs a vector-based similarity search to find the most relevant documents based on the query.

Example request:

```bash
curl "http://localhost:8080/search?query=Technology"
```

The response will return a list of documents that are most similar to the query based on cosine similarity.

### 5. Code Overview

#### Neo4j Vector Service (`Neo4jVectorService`)
- This service handles document loading and vector search functionality.
- The documents are tokenized using a `TokenTextSplitter` and stored in Neo4j using vector embeddings.
- The `search` method allows searching for documents based on a query by performing a similarity search.


#### AI Controller (`AIController`)
- This controller provides the REST API endpoints to load documents into the vector store and search for documents.

```java
@RestController
public class AIController {
    @Autowired
    Neo4jVectorService neo4jVectorService;

    @GetMapping("/load")
    public void loadDocuments() {
        neo4jVectorService.load();
    }

    @GetMapping("/search")
    public List<Document> searchDocuments(@RequestParam(value = "query", defaultValue = "Technology") String query) {
        return neo4jVectorService.search(query);
    }
}
```
