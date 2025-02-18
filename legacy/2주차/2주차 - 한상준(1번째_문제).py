'''
문제 이름 : 그림 
알고리즘 : 깊이 우선 탐색(DFS), 너비 우선 탐색(BFS)
난이도 : 실버 1
문제 출처 : https://www.acmicpc.net/problem/1926

Idea
내 위치에서 위 아래 양 옆을 확인한다.(재귀나 반복 사용)
확인 한 곳이 그림(1)이면 빈칸(0)으로 
재귀 사용 시 limit이 있는지 확인한다.

pseudo-code
1. 재귀 제한을 설정한다.

2. 사용자로부터 N (행의 개수)와 M (열의 개수)을 입력받는다.
3. N x M 크기의 2차원 배열 maps를 입력받는다.
4. biggest: 가장 큰 연결된 영역의 크기 초기화 (0으로 설정)
5. c: 현재 탐색 중인 영역의 크기 초기화 (0으로 설정)
6. cnt: 연결된 영역의 개수 초기화 (0으로 설정)

7. dfs(x, y) 함수:
   a. 만약 (x, y)가 지도 범위를 벗어나면 종료.
   b. 만약 maps[x][y]가 1이라면:
      i. 해당 위치를 방문 처리 (maps[x][y]를 0으로 변경)
      ii. 현재 영역 크기 c를 1 증가.
      iii. 상, 하, 좌, 우 방향으로 dfs 호출.
      iv. True를 반환하여 새로운 영역임을 알림.
   c. 그렇지 않으면 False 반환.

8. 2차원 배열을 순회하며 각 위치(i, j)에 대해:
   a. 만약 dfs(i, j)가 True라면:
      i. 현재 영역 크기 c와 biggest를 비교하여 업데이트.
      ii. 연결된 영역의 개수 cnt를 1 증가.
      iii. 현재 영역 크기 c를 0으로 초기화.

9. cnt와 biggest를 출력한다.


'''

import sys
sys.setrecursionlimit(10**8) # 기존 1000값을 대폭 늘려줌

N,M = map(int,sys.stdin.readline().split())
maps = [list(map(int, sys.stdin.readline().split())) for _ in range(N)]
biggest = 0
c = 0
cnt = 0

def dfs(x,y):
  '''
  DFS함수 4방향을 탐색한다.
  '''
  global c 
  if x < 0 or x > N-1 or y < 0 or y > M-1:
    return False
  
  if maps[x][y] == 1:
    maps[x][y] = 0 
    c += 1
    # x,y를 기준으로 상 하 좌 우를 탐색
    dfs(x + 1, y)
    dfs(x - 1, y)
    dfs(x, y + 1)
    dfs(x, y - 1)
    return True
  return False

for i in range(N):
  for j in range(M):
    if dfs(i,j) == True:
      biggest = max(c,biggest)
      cnt += 1
      c = 0

print(cnt)
print(biggest)