package leetcode.dfs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

// https://leetcode.com/problems/bus-routes/description/

public class BusRoute {

  public int numBusesToDestination(int[][] routes, int source, int target) {

    if(source == target) return 0;


    // 정류장을 지나가는 버스 리스트
    // key - 버스 정류장
    // value - 정류장을 지나가는 버스 리스트
    Map<Integer, List<Integer>> busList = new HashMap<>();

    for(int i = 0; i < routes.length; i++) {
      for(int stop : routes[i]) {
        busList.putIfAbsent(stop, new ArrayList<>());
        busList.get(stop).add(i);
      }
    }

    // 위치하고 있는 버스 정류장 큐
    Queue<Integer> busStopQueue = new LinkedList<>();
    // 해당 버스를 탄지 확인
    Set<Integer> visited = new HashSet<>();

    busStopQueue.add(source);

    int busCount = 0;
    while(!busStopQueue.isEmpty()) {
      // 현재 도착한 버스 정류장 개수
      int queueSize = busStopQueue.size();

      for(int i = 0; i < queueSize; i++) {
        // 정류장을 지나는 버스들
        List<Integer> buses = busList.get(busStopQueue.poll());
        for(int bus : buses) {
          // 이미 탄 버스인지 확인
          if(visited.contains(bus)) continue;
          visited.add(bus);
          // 해당 버스에 서는 버스 정류장 확인
          for(int stop : routes[bus]) {
            if(stop == target) return busCount + 1;
            busStopQueue.add(stop);
          }
        }
      }
      busCount++;
    }
    return -1;
  }

}
