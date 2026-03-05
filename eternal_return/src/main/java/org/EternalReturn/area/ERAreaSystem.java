package org.eternalreturn.area;

import org.eternalreturn.system.PluginInstance;
import org.eternalreturn.system.SystemManager;
import org.eternalreturn.util.DataStructure.Graph.Edge;
import org.eternalreturn.util.DataStructure.Graph.Graph;
import org.eternalreturn.util.DataStructure.Graph.Vertex;
import org.yaml.snakeyaml.util.ArrayStack;

import java.nio.file.Path;
import java.util.*;

/**
 *
 * */
public class ERAreaSystem extends Graph<AreaNode>{

    private List<AreaNode> nodes;
    private List<AreaNode> greenNodes;
    private List<AreaNode> yellowNodes;


    public ERAreaSystem() {
        super(1);
        nodes = new ArrayList<>(20);
        greenNodes = new ArrayList<>();
        yellowNodes = new ArrayList<>();
        super.modifyAs(GraphLoader.load(Path.of("lumia_graph.json")));

        for(var v : super.vertexList){
            nodes.add(v.getData());
            greenNodes.add(v.getData());
        }


    }


    //getter
    public List<AreaNode> getAreaNodes(){
        return nodes;
    }

    //setter
    //...

    /**
     * dfs알고리즘을 사용하여 yellow 노드를 설정하는 함수
     * */
    public void update(int numToBeYellow){

        int numOfSelectedYellowNode = 0;
        
        //옐로 존 모두 레드존으로 변경
        if(!yellowNodes.isEmpty()){
            for(AreaNode yellowNode : yellowNodes){
                yellowNode.setZoneState(AreaNode.State.Red);
            }
            yellowNodes.clear();
        }
        
        //남은 그린 노드의 개수가 numToBeYellow보다 작다면, 그냥 싸그리 Yellow로 변경
        //후 해당 로직 종료
        if(greenNodes.size() <= numToBeYellow){
            for(AreaNode n : greenNodes){
                n.setZoneState(AreaNode.State.Yellow);
                yellowNodes.add(n);
            }
            return;
        }

        Collections.shuffle(greenNodes); //섞고, 0부터 쭉 순회
        int yellows = 1;
        var iter = greenNodes.iterator();
        while(iter.hasNext()){
            var gNode = iter.next();
            //해당 노드를 yellow라고 가정하고 dfs 계산
            gNode.setZoneState(AreaNode.State.Yellow);
            
            //이전 상태의 greenNodes 개수 - 현재까지 확정된 YellowNodes 개수 == 도달 가능한 노드의 총 개수
            if((greenNodes.size() - yellows) == calculateLengthOfReachableGreenNodes(greenNodes.getFirst())){
                yellowNodes.add(gNode); //yellowNodes에 삽입 -> 다음 라운드에 Red Node가 됨.
                iter.remove(); //동시에 해당 노드를 제거
                continue;
            }
            //만약 위 조건에 맞지 않는 경우, GreenNode로 변경
            //다음 노드를 찾아서...
            gNode.setZoneState(AreaNode.State.Green);

        }


    }

    // ((All - Reds) - numToBeYellow) == dfsResult
    // 전체 구역 수에서 yellow로 배정될 구역을 제외하고, 도달한 모든 노드의 개수가 남은 green노드의 수와 같아야 한다.
    public int calculateLengthOfReachableGreenNodes(AreaNode startNode){

        Vertex<AreaNode> startVertex = vertices.get(startNode);
        
        //노드 초기화
        for(Vertex<AreaNode> v : super.vertexList){
            if(v.isVisited()){
                v.setVisited(false);
            }
        }

        var stackFrame = new ArrayDeque<Vertex<AreaNode>>();
        stackFrame.addLast(startVertex);

        int length = 0;
        while(!stackFrame.isEmpty()){

            var curNode = stackFrame.removeFirst();
            length++;

            for(var edge : curNode.getEdgeList()){
                var vOpp = edge.getOpposite(curNode); //반대편 노드 얻어오기
                if(!vOpp.isVisited() && (vOpp.getData().getZoneState() == AreaNode.State.Green)){
                    stackFrame.addLast(vOpp); //얻어온 노드가 visited노드가 아니면 stack에 넣기
                }

            }

        }
        return length; //길이 반환
    }


}
