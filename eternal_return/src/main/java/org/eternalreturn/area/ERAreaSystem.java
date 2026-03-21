package org.eternalreturn.area;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.eternalreturn.eranimal.manager.ERAnimalManager;
import org.eternalreturn.eranimal.manager.behavs.ManageERAnimals;
import org.eternalreturn.eranimal.manager.events.SummonAlphaEvent;
import org.eternalreturn.util.DataStructure.Graph.Graph;
import org.eternalreturn.util.DataStructure.Graph.Vertex;

import java.nio.file.Path;
import java.util.*;

/**
 *
 * */
public class ERAreaSystem extends Graph<AreaNode>{

    private CommandSender commandSender = Bukkit.createCommandSender(builder -> {});

    private List<AreaNode> nodes;
    private List<AreaNode> greenNodes;
    private List<AreaNode> yellowNodes;
    private List<AreaNode> redNodes;

    private static final Path path = Path.of("plugins/lumia_graph.json");


    public ERAreaSystem() {
        super(1);
        nodes = new ArrayList<>(20);
        greenNodes = new ArrayList<>();
        yellowNodes = new ArrayList<>();
        redNodes = new ArrayList<>();
        super.modifyAs(GraphLoader.load(path));

        for(var v : super.vertexList){
            nodes.add(v.getData());
            greenNodes.add(v.getData());
        }
    }

    public void reset(){
        nodes.clear();
        greenNodes.clear();
        yellowNodes.clear();
        redNodes.clear();
        super.modifyAs(GraphLoader.load(path));
        for(var v : super.vertexList){
            nodes.add(v.getData());
            greenNodes.add(v.getData());
        }
    }

    ERAnimalManager managerWhichHasSummonedAlpha = null;
    public void allowToSummonAlphaOnDay2(){
        int lastIdx = greenNodes.size();
        int randIdx = (int)(Math.random() * (lastIdx - 1));
        var vertex = greenNodes.get(randIdx);
        var manager = vertex.getManager();
        manager.allowToSummonAlpha(true);

        managerWhichHasSummonedAlpha = manager;

        System.out.println("[ERAreaSystem] " + vertex.getName() + "에서 알파가 소환됨. -> manager info : " + manager.getAreaName());
    }

    public void allowToSummonOmegaOnDay3(){

        List<AreaNode> greenNodes = new ArrayList<>(List.copyOf(this.greenNodes));
        Collections.shuffle(greenNodes);

        var randNode = greenNodes.getFirst();
        if(randNode.getManager() == managerWhichHasSummonedAlpha){
            randNode = greenNodes.getLast();
        }

        var manager = randNode.getManager();
        manager.allowToSummonOmega(true);
        System.out.println("[ERAreaSystem] " + randNode.getName() + "에서 오메가가 소환됨. -> manager info : " + manager.getAreaName());
    }

    public void sendAreaStateToScoreboard(){

        Bukkit.dispatchCommand(commandSender, "scoreboard objectives add Region dummy");
        for(AreaNode n : greenNodes){
            String s = n.getName();
            Bukkit.dispatchCommand(commandSender, "scoreboard players set " + s + " Region 0");
        }

        for(AreaNode n : yellowNodes){
            String s = n.getName();
            Bukkit.dispatchCommand(commandSender, "scoreboard players set " + s + " Region 1");
        }

        for(AreaNode n : redNodes){
            String s = n.getName();
            Bukkit.dispatchCommand(commandSender, "scoreboard players set " + s + " Region 2");
        }
    }


    //getter
    public List<AreaNode> getAreaNodes(){
        return nodes;
    }


    /**
     * dfs알고리즘을 사용하여 yellow 노드를 설정하는 함수
     * */
    public void update(int numToBeYellow){
        
        //옐로 존 모두 레드존으로 변경
        if(!yellowNodes.isEmpty()){
            for(AreaNode yellowNode : yellowNodes){
                yellowNode.setZoneAsRed();
                redNodes.add(yellowNode);
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
        int yellows = 0;
        var iter = greenNodes.iterator();
        while(iter.hasNext()){

            if(yellows == numToBeYellow) break;

            var gNode = iter.next();
            //해당 노드를 yellow라고 가정하고 dfs 계산
            gNode.setZoneState(AreaNode.State.Yellow);
            
            //이전 상태의 greenNodes 개수 - 현재까지 확정된 YellowNodes 개수 == 도달 가능한 노드의 총 개수
            //파라미터로는 가정된 yellow node를 전달한다.
            int reachableGreenNodes = calculateLengthOfReachableGreenNodes(gNode);
            if(greenNodes.size() - 1 == reachableGreenNodes){
                yellowNodes.add(gNode); //yellowNodes에 삽입 -> 다음 라운드에 Red Node가 됨.
                iter.remove(); //동시에 해당 노드를 제거
                yellows++;
                continue;
            }
            //만약 위 조건에 맞지 않는 경우, GreenNode로 변경
            //다음 노드를 찾아서...
            gNode.setZoneState(AreaNode.State.Green);

        }



    }

    // ((All - Reds) - numToBeYellow) == dfsResult
    // 전체 구역 수에서 yellow로 배정될 구역을 제외하고, 도달한 모든 노드의 개수가 남은 green노드의 수와 같아야 한다.
    public int calculateLengthOfReachableGreenNodes(AreaNode supposedYellow){

        Vertex<AreaNode> startVertex = null;

        Vertex<AreaNode> suppYellowVertex = vertices.get(supposedYellow);
        for(var edge : suppYellowVertex.getEdgeList()){
            var vOpp = edge.getOpposite(suppYellowVertex);
            if(vOpp.getData().getZoneState() == AreaNode.State.Green){
                startVertex =  vOpp;
                break;
            }
        }

        assert startVertex == null : "starVertex is NULL";

        //노드 초기화
        for(Vertex<AreaNode> v : super.vertexList){
            if(v.isVisited()){
                v.setVisited(false);
            }
        }

        var stackFrame = new ArrayList<Vertex<AreaNode>>();
        stackFrame.addLast(startVertex);

        int length = 0;
        while(!stackFrame.isEmpty()){

            var curNode = stackFrame.removeLast();
            if(curNode.isVisited()) continue; //방문한 노드 혹은 Green Node가 아니라면 버린다.
            curNode.setVisited(true);
            length++;

            for(var edge : curNode.getEdgeList()){
                var vOpp = edge.getOpposite(curNode); //반대편 노드 얻어오기
                if(!vOpp.isVisited() && (vOpp.getData().getZoneState() == AreaNode.State.Green)){
                    stackFrame.addLast(vOpp); //얻어온 노드가 visited노드가 아니면 stack에 넣기
                }

            }

        }
        return length;
    }


}
