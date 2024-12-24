'''
문제 이름 : 체인
알고리즘 : 그리디(Greedy) 알고리즘
난이도 : 실버 1
문제 출처 : https://www.acmicpc.net/problem/2785

Idea
N개의 chain을 연결하려면 N-1개의 open_chain이 필요하다.

pseudo-code
1. 입력을 받는다:
   - N: 체인의 총 개수 (정수)
   - chains: 체인의 길이를 나타내는 정수들의 리스트

2. 체인의 길이를 오름차순으로 정렬한다.

3. 변수 `open_chain`을 0으로 초기화한다. 
   - 이는 현재까지 연결된 체인의 개수를 추적하기 위한 변수이다.

4. 모든 체인의 길이를 반복하여 처리한다:
   - 각 체인의 길이를 `open_chain`에 더한다.
   - N-i개의 체인을 연결하기 위해 최소 열린 체인의 갯수가 N-i-1보다 크다면:
     - `N-1-i` 값을 반환한다. 
       (남은 체인을 연결할 수 있는 시점에 도달했다는 의미)
'''

N = int(input())
chains = sorted(map(int, input().split()))

def connect_chain(N, chains):
    open_chain = 0

    for i in range(N):
        open_chain += chains[i]
        if N-1-i <= open_chain: # 최소(N-i-1)개가 열려야야 N-i개의 체인을 연결 할 수 있있습니다.
            return N-1-i

print(connect_chain(N,chains))