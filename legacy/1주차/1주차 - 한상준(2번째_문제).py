'''
문제 이름 : 컨베이어 벨트 위의 로봇
알고리즘 : 구현 알고리즘
난이도 : 골드 5
문제 출처 : https://www.acmicpc.net/problem/20055

Idea
컨베이어 벨트의 이동 별개로 로봇도 한칸 움직인다.
Queue나 %연산을 통해서 컨베이어 벨트의 움직임을 표현한다.

pseudo-code
1. 입력을 받는다:
   - N: 컨베이어 벨트의 한 줄 길이 (정수)
   - K : 내구도 0인 벨트가 K개 이상이면 종료 (정수)
   - belts: 벨트의 내구도를 나타내는 정수들의 리스트

2. 필요 변수 초기화
    - zero_count : 컨베이어 벨트의 내구도가 0인 개수
    - cnt : 현재 단계

3. 단계 설정
    1)벨트 와 로봇의 회전
        - 벨트와 로봇의 위치를 한칸씩 전진합니다. (pop후 append)
        - 이때 N번째 위치한 로봇은 즉시 내립니다.
    2)로봇 이동
        - N-1번째 부터 0까지 다음 위치로 이동시킵니다.
        - 단 다음 칸에 로봇이 없고, 벨트 내구도는 1이상이어야 합니다.
        - 로봇이 이동하면 이동한 칸의 내구도가 1감소합니다.
        - 이때 N-1번째 위치한 로봇은 즉시 내립니다.
    3) 로봇 올리기
        - 첫 번째 칸에 로봇을 올립니다.
        - 단 칸에 로봇이 없고, 벨트 내구도는 1이상이어야 합니다.
        - 로봇이 올라간 칸의 내구도는 1감소합니다.
    4) 1~3 반복
    5) 내구도가 0 인칸 계산
        - 벨트에서 내구도가 0인 칸의 개수를 세어 저장합니다.
        - 이때 zero_count >= K 라면 종료하고 단계를 출력합니다.

'''

from collections import deque

N,K = map(int, input().split())
belts = deque(list(map(int, input().split())))
robots = deque([0 for i in range(N)])
zero_count = 0
cnt = 0


while zero_count < K:
    cnt += 1

    # 1. 벨트와 벨트위 로봇이 같이 한칸 회전
    belts.appendleft(belts.pop())
    robots.appendleft(robots.pop())
    robots[N-1] = 0

    # 2. 가장 먼저 있던 로봇부터 1칸 전진 (앞에 로봇x 내구도0x)
    for i in range(N-1,0,-1):
        if robots[i] != 0 and robots[i+1] == 0 and belts[i+1] != 0:
            robots[i] = 0
            robots[i+1] = 1
            belts[i+1] -= 1
            robots[N-1] = 0

    # 3. 로봇 1번칸에 올리기
    if belts[0] != 0:
        belts[0] -= 1
        robots[0] = 1

    # 4. 내구도가 0인칸이 K개면 종료
    zero_count = belts.count(0)

# 5. 단계 출력
print(cnt)
