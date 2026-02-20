package org.eternalreturn.area;


import org.eternalreturn.util.DataStructure.Graph.Graph;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.google.gson.Gson;
import java.io.FileReader;
import java.nio.file.Path;



public class GraphLoader {

    public static Graph<AreaNode> load(Path jsonPath) {
        try {
            Gson gson = new Gson();
            GraphJsonDTO dto = gson.fromJson(new FileReader(jsonPath.toFile()), GraphJsonDTO.class);

            Graph<AreaNode> graph = new Graph<>(dto.vertices.size());

            var map = new HashMap<String, AreaNode>();
            // vertex 등록
            for(String v : dto.vertices){
                var node = new AreaNode(v);
                graph.addVertex(node);
                map.put(v, node);
            }

            // edge 등록
            for(List<String> e : dto.edges){
                if(e.size() != 2) continue;
                graph.addEdge(map.get(e.get(0)), map.get(e.get(1)));
            }

            return graph;

        } catch (Exception e) {
            throw new RuntimeException("Graph load failed", e);
        }
    }
}
