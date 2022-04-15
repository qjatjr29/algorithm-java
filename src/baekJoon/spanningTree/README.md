# 최소 스패닝 트리

<aside>
💡 Spanning Tree

- 그래프 내의 모든 정점을 포함하는 트리
- 그래프의 최소 연결 부분 그래프이다.

최소 연결 - 간선의 수가 가장 적다.

n개의 정점을 가지는 그래프의 최소 간선의 수는 n-1개,

n-1개의 간선으로 연결되어 있으면 필연적으로 트리형태가 되고 이것이 spanning tree 가 된다.

즉, 그래프에서 일부 간선을 선택해 만든 트리

</aside>

### Spanning Tree 특징

---

DFS , BFS을 이용해 신장트리를 찾을 수 있다.

- 탐색 도중에 사용된 간선만 모으면 만들 수 있다.

하나의 그래프에는 많은 신장 트리가 존재할 수 있다.

Spanning Tree는 트리의 특수한 형태이므로 모든 정점들이 연결되어 있어야 하고 사이클을 포함해서는 안된다.

Spanning Tree는 그래프에 있는 n개의 정점을 정확히 (n-1)개의 간선으로 연결 한다.

## MST - 최소 스패닝(신장) 트리

---

Spanning Tree 중에서 사용된 가선들의 가중치 합이 최소인 트리

간선의 가중치를 고려하여 최소 비용의 spanning tree를 선택하는 것이다.

즉, 네트워크에 있는 모든 정점들을 가장 적은 수의 간선과 비용으로 연결하는 것이다.

### 특징

1. 간선의 가중치의 합이 최소
2. n개의 정점을 가지는 그래프에 대해서 반드시 (n-1)개의 간선만을 사용
3. 사이클이 포함되서는 안된다.

> 사용하는 곳
>

**도시들을 모두 연결하면서 도로의 길이가 최소**

### 구현 방법

---

> Kruskal MST
>

그리디 알고리즘을 사용하여 그래프의 모든 정점을 최소 비용으로 연결하는 최적 해답을 구하는 것.

- 간선 선택을 기반으로 하는 알고리즘
- **이전 단계에서 만들어진 신장트리와 상관없이 무조건 최소 간선만을 선택**

1. 그래프의 간선들을 가중치의 오름차순으로 정렬
2. 정렬된 간선 리스트에서 순서대로 사이클을 형성되지 않는 간선을 선택한다.
3. 해당 간선을 MST의 집합에 추가

**주의**

다음 간선을 선택할 때 간선을 선택하면 사이클이 형성이 되는지를 확인해야 한다!!

- 새로운 간선이 이미 다른 경로에 의해 연결되어 있는 정점들을 연결할 때 사이클이 형성된다.
- 즉, 추가할 새로운 간선의 양끝 정점이 같은 집합에 속해 있으면 사이클이 형성된다.

**사이클 여부 확인하는 방법**

- `Union-Find 알고리즘`을 사용

```python
# 특정 원소가 속한 집합을 찾기
def find(parent, x):
    if parent[x] == x:
        return x
    parent[x] = find(parent, parent[x])
    return parent[x]

# 두 원소가 속한 집합을 합치기 (간선 연결한다고 생각!)
def union(parent, a, b):
    rootA = find(parent, a)
    rootB = find(parent, b)

    if rootA < rootB:
        parent[rootB] = rootA
    else:
        parent[rootA] = rootB

import sys

input = sys.stdin.readline
# 노드의 개수와 간선(union 연산)의 개수 입력받기
v, e = map(int, input().split())
parent = [0] * (v + 1)

edges = []
result = 0

# 부모 테이블상에서, 부모를 자기 자신으로 초기화
for i in range(1, v + 1):
    parent[i] = i

# 모든 간선에 대한 정보를 입력받기
for _ in range(e):
    a, b, cost = map(int, input().split())
    # 비용순으로 오름차순 정렬하기 위해 튜플의 첫 번째 원소를 비용으로 설정
    edges.append((cost, a, b))

edges.sort()

for edge in edges:
    cost, a, b = edge
    # 사이클이 발생하지 않는 경우에만 집합에 포함(=연결한다.)
    if find(parent, a) != find(parent, b):
        union(parent, a, b)
        result += cost

print(result)
```

> Prim MST
>

시작 정점에서 부터 출발하여 스패닝트리 집합을 단계적으로 확장

- 정점 선택을 기반으로 하는 알고리즘
- **이전 단계에서 만들어진 신장 트리를 확장하는 방법**

1. 시작 단계에서는 시작 정점만이 MST 집합에 포함
2. 앞 단계에서 만들어진 MST 집합에 인접한 정점들 중에서 최소 간선으로 연결된 정점을 선택하여 트리를 확장
3. 위 과정을 N-1 개의 간선을 가질 때 까지 반복

```java
public ArrayList<edgeSet> prim() {
         
     PriorityQueue<edgeSet> pq = new PriorityQueue<>();
     Queue<Integer> queue = new LinkedList<>();

     queue.add(graph.indexOf(graph.get(1)));

     ArrayList<edgeSet> mst = new ArrayList<>();

     while (!queue.isEmpty()) {
         int now = queue.poll();
         visited[now] = true;

         for (edgeSet set : graph.get(now)) {
              if (!visited[set.v]) {
                  pq.add(set);
              }
         }
         while (!pq.isEmpty()) {
              edgeSet set = pq.poll();
              if (!visited[set.v]) {
              queue.add(set.v);
              visited[set.v] = true;
              mst.add(set);
              break;
              }
        }
   }
   return mst;
}
```

> Prim vs Kruskal
>

적은 숫자의 간선을 가지는 ‘희소 그래프' 인 경우 Kruskal

간선이 많이 존재하는 ‘밀집 그래프' 의 경우는 Prim 알고리즘이 적합하다.