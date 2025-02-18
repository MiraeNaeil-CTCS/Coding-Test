import java.util.*;

// 프로그래머스 타겟 넘버
// 출처: https://school.programmers.co.kr/learn/courses/30/lessons/43165
// 난이도: 레벨2
// 사용 알고리즘: DFS
// 시간 복잡도: O(2^N)

// 1. 인덱스와 누적합을 나타낼 클래스 State 선언
// 2. 스택을 선언하고 초기값 push
// 3. while문에서 탐색 진행
// 4. State객체의 index가 numbers[] 길이와 같다면 탐색의 최대 깊이에 도달했다는 뜻. 탐색 종료되도록 continue
// 5. 빼는 경우와 더하는 경우 둘 다 push
// 6. stack이 비면 count값 반환

class Solution {
    private static class State{
        final int index;
        final int acc; // 현재까지 누적합

        State(int index, int acc){
            this.index = index;
            this.acc = acc;
        }
    }

    public int solution(int[] numbers, int target) {
        Stack<State> s = new Stack<>();
        s.push(new State(0, 0));

        int count = 0;

        while(!s.isEmpty()){ // 스택 비어 있지 않는 동안 반복
            State state = s.pop();

            if(state.index == numbers.length){ // 종료 조건
                if(state.acc == target) count++;
                continue;
            }

            s.push(new State(state.index + 1, state.acc - numbers[state.index])); // 빼는 경우
            s.push(new State(state.index + 1, state.acc + numbers[state.index])); // 더하는 경우
        }

        return count;
    }
}