package com.example.springai_neo4j_vector_demo.controller;

import com.example.springai_neo4j_vector_demo.service.Neo4jVectorService;
import org.springframework.ai.document.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
