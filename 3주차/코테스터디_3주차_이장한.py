#코딩테스트 스터디(2025-01-08)
#문제-백준 11660번(실버1)-구간 합 구하기5
#DP를 이용하여 푸는 문제. 
#먼저 배열합을 구한 뒤, 그 배열합을 토대로 값을 구한다.

import sys
input = sys.stdin.readline #입력을 빠르게 받기 위한 방법

N,M = map(int,input().split()) #N은 사이즈, M은 합을 구하는 횟수이다.


#2.A에 숫자를 입력받는다. A는 원본 2차원 배열
A=[]
numbers=0
for i in range(N):
    numbers=list(map(int,input().split())) #한 줄에 입력된 값을 리스트로 변환
    A.append(numbers)

#3.배열합 구하기:D배열은 (1,1)부터 (i,j)까지의 합을 저장한다.

D=[[0]*(N+1)for _ in range(N+1)] #2차원 배열 초기화. 이 방법을 잘 알아두자.
for i in range(1,N+1):
    for j in range(1,N+1):
        D[i][j] = D[i][j-1]+D[i-1][j]-D[i-1][j-1]+A[i-1][j-1]
        #여기서, A[i-1][j-1]은 원본 값이다. 배열 A와 D의 인덱싱이 달라서 헛갈릴 수 있다!

#4.숫자 입력

for i in range(M):
    a,b,c,d = map(int,input().split())
    print(D[c][d]-D[a-1][d]-D[c][b-1]+D[a-1][b-1])