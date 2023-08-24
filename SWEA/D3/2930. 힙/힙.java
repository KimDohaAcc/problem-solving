import java.io.IOException;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;
/*
 * 최대힙을 올바르게 구현했는지 확인하자!
 * 최대힙의 길이를 연산의 수만큼 주고 준비하기
 * 
 * 연산1 : 숫자 삽입
 * 연산2 : 최댓값 출력 후 숫자 삭제 -> 루트 노드를 출력하고 삭제하자!
 * 
 * 힙을 관리하는 클래스
 * 멤버변수로는 루트노드와 배열, size를 갖기
 * 메소드로는 push, pop
 * 
 * push 메소드
 * 입력 받은 숫자를 가장 마지막 노드로 추가
 * 부모와 비교해서 값이 더 크다면 계속해서 자리를 바꿔주자
 * 자식의 부모 노드를 탐색 : 자식인덱스/2
 * 
 * pop 메소드
 * 루트 노드를 출력한다
 * 자식 노드를 탐색하면서 둘 중 더 큰 것을 자기 자리에 옮기기
 * 자식 노드 탐색 : 왼쪽 - 부모인덱스*2, 오른쪽-부모인덱스*2+1
 * size만큼 다 돌았다면 크기를 하나 줄이고 끝내기
 * 
 * -------------------
 * PriorityQueue를 활용해서 heap을 구현하자
 * 파라미터로 Collections.reverseOrder()를 줘서 내림차순으로 설정할 수 있음
 * 
 */
public class Solution {
	public static void main(String[] args) throws IOException {
		Scanner scan = new Scanner(System.in);
		PriorityQueue<Integer> heap = new PriorityQueue<>(Collections.reverseOrder());
		StringBuilder sb = new StringBuilder();

		int T = scan.nextInt();
		for (int tc = 0; tc < T; tc++) {
			heap.clear();
			sb.append("#" + (tc + 1) + " ");
			int N = scan.nextInt();

			for (int i = 0; i < N; i++) {
				int a = scan.nextInt();

				// 연산1
				if (a == 1) {
					int x = scan.nextInt();
					heap.add(x);
				}

				// 연산2
				else {
					if (heap.isEmpty()) {
						sb.append(-1);
					}

					else {
						sb.append(heap.poll());
					}

					if (i < N - 1) {
						sb.append(" ");
					}
				}
			}
			sb.append("\n");
		}

		System.out.print(sb.toString());
	}
	/*
	 * static class Heap {
		int[] heapArr;
		int size;
		int rootNode;
		int avg;

		// 연산 입력 횟수 최댓값 길이로 배열을 초기화
		public Heap() {
			this.heapArr = new int[(int) Math.pow(10, 5) + 1];
		}

		// Heap에 삽입
		public void push(int x) {
			// 현재 크기 +1에 입력 받은 값을 넣기
			this.heapArr[++this.size] = x;
			// 평균
			avg = (avg*(this.size-1) + x) / this.size; 

			
			// 밑에서 올라가기
			if(x - avg < 0) {
			// 부모 노드보다 크다면 자리를 바꿔주기
			// 더이상 올라갈 수 없다면 반복문 종료
			int idx = this.size;
			while (idx / 2 >= 1 && this.heapArr[idx / 2] < this.heapArr[idx]) {
				int temp = this.heapArr[idx / 2];
				this.heapArr[idx / 2] = this.heapArr[idx];
				this.heapArr[idx] = temp;
				idx /= 2;
			}

			// 배열을 최대힙으로 정렬 후 루트 노드 갱신
			this.rootNode = this.heapArr[1];
			}
			
			else {
				// 위에서 내려가기
				int save = this.heapArr[1];
				this.heapArr[1] = x;
				int idx = 1;
				if(idx * 2 <= this.size && idx*2+1 <= this.size) {
					
				}
			}
		}

		// 노드 삭제
		public void pop() {
			// 삽입된 값이 없다면
			if (this.rootNode == 0) {
				sb.append(-1);
				return;
			}

			// 값이 있다면 출력
			sb.append(rootNode);
			for (int i = 1; i <= this.size; i++) {
				// 자식 노드가 존재하면 변수에 담기
				int sub1 = i * 2 <= this.size ? this.heapArr[i * 2] : 0;
				int sub2 = i * 2 + 1 <= this.size ? this.heapArr[i * 2 + 1] : 0;

				// 적어도 하나의 자식 노드가 있다면
				// 값을 비교해 현재 자리에 넣어주기
				if (sub1 != 0 || sub2 != 0) {
					this.heapArr[i] = Math.max(sub1, sub2);
				}

				// 마지막 노드까지 탐색했다면
				// 마지막 노드의 자리를 비워주기
				if (i == this.size) {
					this.heapArr[i] = 0;
				}
			}

			// 루트 노드의 값을 갱신
			this.rootNode = this.heapArr[1];
			// 사이즈 줄이기
			this.size--;
		}
	}
	 */
}