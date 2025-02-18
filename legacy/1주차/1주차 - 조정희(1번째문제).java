/*

문제 이름 : 퍼즐 게임 챌린지
알고리즘 : 이분탐색 알고리즘
난이도 : level 2
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




 변수 x

 for 문
 if (x>= diffs[i]){ total_time = total_time + times[i]}
 else{total_times = total_times + (times[i]+times[i-1])*(diffs[i]-level)+ times[i]}

 문제:
 이때 total_times가 limit보다 작아지는 최댓값을 구해야 함.
 그때의 x의 최댓값을 구해야함

 풀이:

 대략적인 x 값을 한번 구해본다. 그때의 소요시간이 어떤지 확인해본다.
 이때, total_times가 up 인지 down인지 확인한다.
 이때 limit와 total_times의 gap을 확인한다.
 gap의 크기가 작아들도록 계속 어림잡아 x를 넣어본다
 gap이 최소가 되었다.
 이때의 x 값을 구한다.

 카테고리:
 이분탐색

 전략:
 1. gap의 절대값을 구하고 그 값이 최소가 되는 음수의 값에서 result를 도출해낸다.

*/

import java.io.*;
import java.util.*;

class Solution {
    public int solution(int[] diffs, int[] times, long limit) {

        Boolean indicator = true;
        int top = 100000;
        int bottom = 1;
        int middle = (top+bottom)/2;
        long total_time = 0;
        while (indicator){

            total_time = times[0];

            total_time = makeTotalTime(diffs, times , middle, total_time);

            if(total_time - limit > 0){
                bottom = middle;
                middle = (top + bottom) / 2;
            }
            else{
                top = middle;
                middle = (top + bottom) / 2;
            }

            if (middle == top || middle == bottom){
                indicator = false;
            }
        }
        long top_time = makeTotalTime(diffs, times , top, times[0]);
        long bottom_time = makeTotalTime(diffs, times , bottom, times[0]);

        if(limit-bottom_time>=0){
            // System.out.printf(String.valueOf())
            return bottom;
        }
        else{
            // System.out.printf()
            return top;
        }

    }

    private long makeTotalTime(int[] diffs,int[] times ,int middle, long total_time){
        for(int i = 1; i<diffs.length; i++){
            if (middle>= diffs[i]){ total_time = total_time + times[i];}
            else{total_time = total_time + (times[i]+times[i-1])*(diffs[i]-middle)+ times[i];}}

        return total_time;
    }
}