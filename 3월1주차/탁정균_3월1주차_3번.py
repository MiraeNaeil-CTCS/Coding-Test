# 링크: https://www.acmicpc.net/problem/17271
# 백준 - 17271: 리그 오브 레전설 (Small)
# 출처: 2019 인하대학교 프로그래밍 경진대회(IUPC) I번
# 알고리즘 분류: 다이나믹 프로그래밍

# 1. 싸움 시간 N, B스킬의 시전 시간 M 입력 (N은 10,000 이하의 자연수, M은 2 이상 100 이하의 자연수)
# 1-1. M > N인 경우 A스킬로만 싸울 수 있으므로 1을 출력 후 종료
# 2. dp 테이블 생성
# 3. 1부터 M - 1까지의 원소는 모두 1로 채우기
# 4. M번 원소는 2(A*M, B의 두 가지 경우)
# 5. 이후부터는 점화식 a_N = a_{N - 1} + a_{N - M}에 따라 계산 후 1000000007로 나누어 저장
# 6. dp 테이블의 마지막 원소 출력

N, M = map(int, input().split())
MOD = 1000000007
if M > N:
    print(1)
    exit()

dp = [0] * (N + 1)

for i in range(1, M):
    dp[i] = 1
dp[M] = 2

for i in range(M + 1, N + 1):
    dp[i] = (dp[i - 1] + dp[i - 2])

print(dp[-1] % MOD)
