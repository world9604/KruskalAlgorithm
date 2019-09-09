import java.util.*;

public class Main {
    public static void main(String[] args) {
        int n = 7;

        List<Edge> list = new ArrayList<>();
        list.add(new Edge(1, 7, 12));
        list.add(new Edge(1, 4, 28));
        list.add(new Edge(1, 2, 67));
        list.add(new Edge(1, 5, 17));
        list.add(new Edge(2, 4, 24));
        list.add(new Edge(2, 5, 62));
        list.add(new Edge(3, 5, 20));
        list.add(new Edge(3, 6, 37));
        list.add(new Edge(4, 7, 13));
        list.add(new Edge(5, 6, 45));
        list.add(new Edge(5, 7, 73));

        // 간선의 비용을 오름차순 정렬
        Collections.sort(list);

        // 각 정점이 포함된 그래프가 어디인지 저장
        int set[] = new int[n];
        for (int i = 0; i < n; i++) {
            set[i] = i;
        }

        // 거리의 합을 0으로 초기화
        int sum = 0;
        UnionFind uf = new UnionFind();

        for (int i = 0; i < list.size(); i++) {
            // 동일한 부모를 가르키지 않는 경우,
            // 즉 사이클이 발생하지 않을 때만 선택
            if (!uf.findParent(set, list.get(i).node[0] - 1, list.get(i).node[1] - 1)) {
                sum += list.get(i).distance;
                uf.unionParent(set, list.get(i).node[0] - 1, list.get(i).node[1] - 1);
            }
        }
    System.out.printf("%d\n", sum);
    }
}


class Edge implements Comparable {
    int[] node;
    int distance;

    Edge(int a, int b, int distance) {
        this.node = new int[2];
        this.node[0] = a;
        this.node[1] = b;
        this.distance = distance;
    }

    @Override
    public int compareTo(Object edge) {
        if (this.distance < ((Edge) edge).distance)
            return -1;
        else if(this.distance == ((Edge) edge).distance)
            return 0;
        else
            return 1;
    }
}


class UnionFind {
    private int getParent(int parent[], int x) {
        if (parent[x] == x) return x;
        return parent[x] = getParent(parent, parent[x]);
    }

    // 각 부모 노드를 합칩니다.
    public void unionParent(int parent[], int a, int b) {
        a = getParent(parent, a);
        b = getParent(parent, b);
        if (a < b) parent[b] = a;
        else parent[a] = b;
    }

    public boolean findParent(int parent[], int a, int b) {
        a = getParent(parent, a);
        b = getParent(parent, b);
        return a == b;
    }
}